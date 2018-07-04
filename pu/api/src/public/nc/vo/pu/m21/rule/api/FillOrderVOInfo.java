package nc.vo.pu.m21.rule.api;


import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.api.fill.Ctrantperid;
import nc.vo.pu.m21.rule.api.fill.FillDefaultValueRule;
import nc.vo.pu.m21.rule.api.fill.FillPaymentInfo;
import nc.vo.pu.m21.rule.api.fill.FillPuMaterialUnitRule;
import nc.vo.pu.m21.rule.api.fill.Vtrantypecode;
import nc.vo.pu.m23.rule.api.fill.PUAssUnitAndChangeRate;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.fill.BillVOsFillRule;
import nc.vo.scmpub.fill.billfill.OrgAndGroupBillValueFill;
import nc.vo.scmpub.fill.billfill.RowNoBillFill;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description ����API����ǰ���ݴ�������У���Լ�������䣩
 * @scene
 * 
 * @param
 * 
 * @functionName �ɹ�����API����
 * @since 6.5
 * @version 2015-11-16 ����4:06:24
 * @author zhangshqb
 */
public class FillOrderVOInfo {

	public OrderVO[] fillInfo(OrderVO[] orders) throws BusinessException {
		// ��������
		BillVOsCheckRule checkRule = new BillVOsCheckRule(new PUOrderVOValidator());
		checkRule.check(orders);
		// ������Ӧ������
		BillVOsFillRule fillrule = new BillVOsFillRule();
		// ����pk_org����pk_group��pk_org_v
		fillrule.addBillFillRule(new OrgAndGroupBillValueFill());
		// ���佻����������ֶε�ֵ
		fillrule.addBillFillRule(new Vtrantypecode(POBillType.Order.getCode()));
		fillrule.addBillFillRule(new Ctrantperid());
		// �����к�
		fillrule.addBillFillRule(new RowNoBillFill());
		// ����Э��
		fillrule.addBillFillRule(new FillPaymentInfo());
		fillrule.addBillFillRule(new FillPuMaterialUnitRule());
		fillrule.addBillFillRule(new PUAssUnitAndChangeRate());
//		fillrule.addBillFillRule(new FillOrderVORule());
		fillrule.addBillFillRule(new FillDefaultValueRule());
		fillrule.fillValue(orders);
		return orders;
	}
}
