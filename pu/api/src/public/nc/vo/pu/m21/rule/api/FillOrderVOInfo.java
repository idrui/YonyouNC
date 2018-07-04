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
 * @description 订单API保存前数据处理（数据校验以及数据填充）
 * @scene
 * 
 * @param
 * 
 * @functionName 采购订单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:06:24
 * @author zhangshqb
 */
public class FillOrderVOInfo {

	public OrderVO[] fillInfo(OrderVO[] orders) throws BusinessException {
		// 检查必输项
		BillVOsCheckRule checkRule = new BillVOsCheckRule(new PUOrderVOValidator());
		checkRule.check(orders);
		// 补充相应的数据
		BillVOsFillRule fillrule = new BillVOsFillRule();
		// 根据pk_org补充pk_group和pk_org_v
		fillrule.addBillFillRule(new OrgAndGroupBillValueFill());
		// 补充交易类型相关字段的值
		fillrule.addBillFillRule(new Vtrantypecode(POBillType.Order.getCode()));
		fillrule.addBillFillRule(new Ctrantperid());
		// 补充行号
		fillrule.addBillFillRule(new RowNoBillFill());
		// 付款协议
		fillrule.addBillFillRule(new FillPaymentInfo());
		fillrule.addBillFillRule(new FillPuMaterialUnitRule());
		fillrule.addBillFillRule(new PUAssUnitAndChangeRate());
//		fillrule.addBillFillRule(new FillOrderVORule());
		fillrule.addBillFillRule(new FillDefaultValueRule());
		fillrule.fillValue(orders);
		return orders;
	}
}
