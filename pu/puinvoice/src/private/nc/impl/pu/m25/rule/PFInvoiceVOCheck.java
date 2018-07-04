/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-15 上午11:32:23
 */
package nc.impl.pu.m25.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.CheckStatusCallbackContext;
import nc.bs.pub.pf.ICheckStatusCallback;
import nc.bs.scmpub.pf.PfBeforeAndAfterAction;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.pf.change.IActionDriveChecker;
import nc.vo.pf.change.IChangeVOCheck;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoicePlanPriceSetter;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.pf.IPFSourceBillFinder;
import nc.vo.pub.pf.SourceBillInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-7-15 上午11:32:23
 */
public class PFInvoiceVOCheck extends PfBeforeAndAfterAction implements
		IActionDriveChecker, IChangeVOCheck, IPFSourceBillFinder,
		ICheckStatusCallback {

	@Override
	public Object[] afterBatch(AggregatedValueObject[] reloadVOs,
			Object[] objsAfterAction, HashMap hmPfExParams)
			throws BusinessException {
		Object[] returnvos = super.afterBatch(reloadVOs, objsAfterAction,
				hmPfExParams);
		//删除操作后此处会有空值问题
		if (objsAfterAction != null) {
			Map<String, UFDouble> planpriceMap = this
					.getPlanPriceMap((InvoiceVO[]) objsAfterAction);

			this.setPlanPrice((InvoiceVO[]) returnvos, planpriceMap);
		}
		return returnvos;
	}

	@Override
	public void callCheckStatus(CheckStatusCallbackContext cscc)
			throws BusinessException {
		try {
			// 是否终止
			if (!cscc.isTerminate()) {
				return;
			}
			if (null == cscc.getBillVo()
					|| !(cscc.getBillVo() instanceof AbstractBill)) {
				return;
			}
			AbstractBill billVO = (AbstractBill) cscc.getBillVo();
			if (billVO.getParentVO() == null) {
				return;
			}
			// 2013-1-29 流程终止，需要更新状态和审批信息，不更新审批信息，再次提交无法收回。
			VOUpdate<ISuperVO> bo = new VOUpdate<ISuperVO>();
			String[] names = new String[] { InvoiceHeaderVO.FBILLSTATUS,
					InvoiceHeaderVO.APPROVER, InvoiceHeaderVO.TAUDITTIME };
			bo.update(new ISuperVO[] { billVO.getParent() }, names);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pf.change.IChangeVOCheck#checkValidOrNeed(nc.vo.pub.AggregatedValueObject,
	 *      java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkValidOrNeed(AggregatedValueObject srcBillVo,
			String srcAction, String destBilltype, String drivedAction)
			throws BusinessException {
		return true;
	}

	/**
	 * 父类方法重写:支持下游单据给订单发送上游消息，查询采购订单返回平台要求信息
	 * 
	 * @see nc.vo.pub.pf.IPFSourceBillFinder#findSourceBill(java.lang.String,
	 *      nc.vo.pub.AggregatedValueObject)
	 */
	@Override
	public SourceBillInfo[] findSourceBill(String strUpBilltype,
			Object voThisBill) {
		// 数据合法性校验
		if (voThisBill == null || strUpBilltype == null) {
			return null;
		}
		// 只查询采购订单和采购入库单
		if (!(strUpBilltype.startsWith(POBillType.Order.getCode()) || strUpBilltype
				.startsWith(ICBillType.PurchaseIn.getCode()))) {

			return null;
		}

		CircularlyAccessibleValueObject[] items = ((AggregatedValueObject) voThisBill)
				.getChildrenVO();
		if (items == null || items.length == 0) {
			return null;
		}
		int iLen = items.length;
		ArrayList<String> souriceid = new ArrayList<String>();
		for (int i = 0; i < iLen; i++) {
			if (strUpBilltype.startsWith((String) items[i]
					.getAttributeValue(InvoiceItemVO.CSOURCETYPECODE))) {
				souriceid.add((String) items[i]
						.getAttributeValue(InvoiceItemVO.CSOURCEID));
			}
		}
		if (souriceid.size() == 0) {

			return null;
		}
		// 返回
		SourceBillInfo[] voaRet = null;
		if (strUpBilltype.startsWith(POBillType.Order.getCode())) {
			voaRet = this.getOrderBillInfo(souriceid);
		} else if (strUpBilltype.startsWith(ICBillType.PurchaseIn.getCode())) {
			voaRet = this.getPurchaseInInfo(souriceid);
		}

		return voaRet;
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pf.change.IActionDriveChecker#isEnableDrive(java.lang.String,
	 *      nc.vo.pub.AggregatedValueObject, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public boolean isEnableDrive(String srcBilltype,
			AggregatedValueObject srcBillVO, String srcAction,
			String destBillType, String beDrivedActionName)
			throws BusinessException {
		return true;
	}

	/**
	 * 方法功能描述：取得上游采购订单单据信息，发送上游消息时使用
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param listOrderId
	 * @return <p>
	 * @since 6.0
	 * @author gaogr
	 * @time 2010-9-17 上午09:31:32
	 */
	private SourceBillInfo[] getOrderBillInfo(ArrayList<String> listOrderId) {

		try {
			return NCLocator
					.getInstance()
					.lookup(IOrderPubQuery.class)
					.getOrderBillInfo(
							listOrderId.toArray(new String[listOrderId.size()]));
		} catch (BusinessException e) {
			// 日志异常
			ExceptionUtils.wrappException(e);
			return null;
		}

	}

	private Map<String, UFDouble> getPlanPriceMap(InvoiceVO[] vos) {
		new InvoicePlanPriceSetter().setPlanPrice(vos);
		Map<String, UFDouble> map = new HashMap<String, UFDouble>();
		for (InvoiceVO vo : vos) {
			for (InvoiceItemVO itemVO : vo.getChildrenVO()) {
				map.put(itemVO.getPk_invoice_b(), itemVO.getNplanprice());
			}
		}
		return map;
	}

	/**
	 * 方法功能描述：取得上游采购入库单单据信息，发送上游消息时使用
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param listPurchaseInId
	 * @return <p>
	 * @since 6.0
	 * @author gaogr
	 * @time 2010-9-17 上午09:29:44
	 */
	private SourceBillInfo[] getPurchaseInInfo(
			ArrayList<String> listPurchaseInId) {

		VOQuery<PurchaseInHeadVO> query = new VOQuery<PurchaseInHeadVO>(
				PurchaseInHeadVO.class);

		PurchaseInHeadVO[] heads = query.query(listPurchaseInId
				.toArray(new String[listPurchaseInId.size()]));

		int iLen = heads.length;
		SourceBillInfo[] voaRet = new SourceBillInfo[iLen];

		for (int i = 0; i < iLen; i++) {
			voaRet[i] = new SourceBillInfo();

			voaRet[i].setBillId(heads[i].getCgeneralhid());
			voaRet[i].setApprover(heads[i].getApprover());
			voaRet[i].setBillmaker(heads[i].getCreator());
		}
		return voaRet;
	}

	private void setPlanPrice(InvoiceVO[] vos,
			Map<String, UFDouble> planpriceMap) {
		for (InvoiceVO vo : vos) {
			for (InvoiceItemVO itemVO : vo.getChildrenVO()) {
				itemVO.setNplanprice(planpriceMap.get(itemVO.getPk_invoice_b()));
			}
		}
	}
}
