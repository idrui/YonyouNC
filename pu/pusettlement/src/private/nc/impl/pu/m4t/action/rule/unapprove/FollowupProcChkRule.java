package nc.impl.pu.m4t.action.rule.unapprove;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description <ul>
 *              <li>后续操作检查：
 *              <li>已经生成过采购发票，不能弃审；已经参与结算，不能弃审。
 *              <li>不用检查是否进行过费用结算，因期初暂估费用结算必须在货物结算之后进行
 *              </ul>
 * @scene
 * 期初暂估单取消审批
 * @param 无
 * 
 * @since 6.3
 * @version 2010-9-8 上午09:27:34
 * @author wuxla
 */

public class FollowupProcChkRule implements IRule<InitialEstVO> {

	@Override
	public void process(InitialEstVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		// 检查累计开票数量
		this.checkInvoice(vos);
		// 检查结算
		this.checkSettle(vos);
	}
	
	private void checkAccnum(InitialEstVO[] vos, StringBuilder msg,
			String checkNumKey) {
		StringBuilder sb = new StringBuilder();
		for (InitialEstVO vo : vos) {
			InitialEstItemVO[] items = vo.getItems();
			if (ArrayUtils.isEmpty(items)) {
				continue;
			}
			for (InitialEstItemVO item : items) {
				if (null == item) {
					continue;
				}
				UFDouble accNum = MathTool.nvl((UFDouble) item
						.getAttributeValue(checkNumKey));
				if (!UFDouble.ZERO_DBL.equals(accNum)) {
					String billcode = vo.getHeader().getVbillcode();
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(billcode);
					break;
				}
			}
		}
		if (sb.length() > 0) {
			msg.append(sb);
			ExceptionUtils.wrappBusinessException(msg.toString());
		}
	}

	/*
	 * 检查累计开票数量
	 */
	private void checkInvoice(InitialEstVO[] vos) {
		this.checkAccnum(vos, new StringBuilder(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004060_0", "04004060-0116")/*
																		 * @res
																		 * "以下单据已经开票，不能再取消审批："
																		 */),
				InitialEstItemVO.NACCINVOICENUM);
	}
	/*
	 * 检查结算
	 */
	private void checkSettle(InitialEstVO[] vos) {
		this.checkAccnum(vos, new StringBuilder(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004060_0", "04004060-0117")/*
																		 * @res
																		 * "以下单据已经参与结算，不能再取消审批："
																		 */),
				InitialEstItemVO.NACCSETTLENUM);
	}

}