package nc.impl.pu.m23.approve.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description ����Ƿ�������
 * @scene ���������
 * @param ��
 * 
 * @since 6.3
 * @version 2010-5-13 ����11:51:38
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
	 *  ����Ƿ�������
	 */
	private void chkCanApprove(ArriveVO aggVO) {

		if (POEnumBillStatus.APPROVE.value().equals(
				aggVO.getHVO().getFbillstatus())) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4004040_0",
									"04004040-0118")/* @res "����������ˣ��������ٴ���ˣ�" */);
		}
	}

}