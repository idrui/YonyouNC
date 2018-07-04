package nc.impl.pu.m23.approve.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description 检查是否可以审核
 * @scene 到货单审核
 * @param 无
 * 
 * @since 6.3
 * @version 2010-5-13 上午11:51:38
 * @author hanbin
 */

public class ChkCanApproveRule implements IRule<ArriveVO> {

	@Override
	public void process(ArriveVO[] voArray) {
		for (ArriveVO aggVO : voArray) {

			this.chkCanApprove(aggVO);
		}
	}

	/*
	 *  检查是否可以审核
	 */
	private void chkCanApprove(ArriveVO aggVO) {

		if (POEnumBillStatus.APPROVE.value().equals(
				aggVO.getHVO().getFbillstatus())) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4004040_0",
									"04004040-0118")/* @res "到货单已审核，不允许再次审核！" */);
		}
	}

}