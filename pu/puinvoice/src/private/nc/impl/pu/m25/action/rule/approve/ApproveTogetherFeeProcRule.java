/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 下午03:56:23
 */
package nc.impl.pu.m25.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m25.IInvoiceQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.exception.InvoiceApproveWithFeeException;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description 审批发票时同时审批费用发票
 * @scene 审批
 * @param envs
 *          采购发票操作时前台到后台的环境信息，一般随平台动作的userObj向外传
 * 
 * @since 6.3
 * @version 2014-10-22 下午2:50:09
 * @author zhangshqb
 */
public class ApproveTogetherFeeProcRule implements IRule<InvoiceVO> {
	private InvoiceUIToBSEnv[] envs;

	/**
	 * ApproveTogetherFeeProcRule 的构造子
	 * 
	 * @param envs
	 */
	public ApproveTogetherFeeProcRule(InvoiceUIToBSEnv[] envs) {
		this.envs = envs;
	}

	@Override
	public void process(InvoiceVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		BSContext context = BSContext.getInstance();
		Object session = context.getSession(InvoiceApproveWithFeeException.class
				.getName());
		if (session != null && !((UFBoolean) session).booleanValue()) {
			return;
		}

		// 用户已经回答继续
		if (this.isUserComfirm(this.envs)) {
			return;
		}
		List<String> checkIds = new ArrayList<String>();
		for (InvoiceVO vo : vos) {
			String srcType = this.getSrcType(vo);
			boolean isCheckFee = true;
			if (null == srcType) {
				isCheckFee = false;
			} else if (POBillType.Order.getCode().equals(srcType)
					|| SCBillType.Order.getCode().equals(srcType)) {
				isCheckFee = this.isAutoSettleFromOrder(vo);
			}
			if (isCheckFee) {
				checkIds.add(vo.getParentVO().getPk_invoice());
			}
		}
		if (0 == checkIds.size()) {
			return;
		}
		InvoiceVO[] fees = this.queryFreeFeeVO(checkIds);
		if (ArrayUtils.isEmpty(fees)) {
			return;
		}
		ExceptionUtils.wrappException(new InvoiceApproveWithFeeException(
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
						"04004050-0027")/* @res "存在与之关联的未审核的费用发票，是否继续？" */));
	}

	private String getSrcType(InvoiceVO vo) {
		for (InvoiceItemVO item : vo.getChildrenVO()) {
			if (!StringUtil.isEmptyWithTrim(item.getCsourcetypecode())
					&& (InvoiceRowType.GOODS_ROW == item.getFrowtype().intValue())) {
				return item.getCsourcetypecode();
			}
		}
		return null;
	}

	/**
	 * 方法功能描述：根据参数判断是否来源于订单的发票自动结算。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @return <p>
	 * @since 6.0
	 * @author zhaoyha
	 * @time 2010-4-26 下午04:48:57
	 */
	private boolean isAutoSettleFromOrder(InvoiceVO vo) {
		return InvoiceVOUtil.isAutoSettleFromOrder(vo);
	}

	private boolean isUserComfirm(InvoiceUIToBSEnv[] uiToBsEnvs) {
		if (ArrayUtils.isEmpty(uiToBsEnvs)) {
			return false;
		}
		if (null == uiToBsEnvs[0]) {
			return false;
		}
		if (InvoiceUserAnswerType.YES == uiToBsEnvs[0].getApproveFee()) {
			return true;
		}
		return false;
	}

	private InvoiceVO[] queryFreeFeeVO(List<String> ids) {
		IInvoiceQuery querysrv = NCLocator.getInstance()
				.lookup(IInvoiceQuery.class);
		InvoiceVO[] feeVos = null;
		try {
			feeVos = querysrv.queryFreeFee(ids.toArray(new String[ids.size()]));
		} catch (BusinessException e) {
			// 日志异常
			ExceptionUtils.wrappException(e);
		}
		return feeVos;
	}

}