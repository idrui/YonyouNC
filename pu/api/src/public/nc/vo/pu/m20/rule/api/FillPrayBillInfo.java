package nc.vo.pu.m20.rule.api;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.api.fill.Ctrantperid;
import nc.vo.pu.m20.rule.api.fill.FillPuMaterialUnitRule;
import nc.vo.pu.m20.rule.api.fill.PraybillVOAPIFill;
import nc.vo.pu.m20.rule.api.fill.Vtrantypecode;
import nc.vo.pu.m23.rule.api.fill.PUAssUnitAndChangeRate;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.fill.BillVOsFillRule;
import nc.vo.scmpub.fill.billfill.OrgAndGroupBillValueFill;
import nc.vo.scmpub.fill.billfill.RowNoBillFill;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description 请购单API数据校验以及数据填充
 * @scene
 * 
 * @param
 * 
 * @functionName 请购单API保存
 * @since 6.5
 * @version 2015-11-16 下午4:00:33
 * @author zhangshqb
 */
public class FillPrayBillInfo {
	/**
	 * 校验必输项并补充数据
	 * 
	 * @author zhangshqb
	 * @param vos
	 * @return
	 * @throws BusinessException
	 */
	public PraybillVO[] fill(PraybillVO[] vos) throws BusinessException {
		// 检查必输项
		BillVOsCheckRule checkRule = new BillVOsCheckRule(
				new PUPraybillVOValidator());
		checkRule.check(vos);
		TranstypeCheck transtypeCheck = new TranstypeCheck();
		transtypeCheck.check(vos);
		BillVOsFillRule fillrule = new BillVOsFillRule();
		// 根据pk_org补充pk_group和pk_org_v
		fillrule.addBillFillRule(new OrgAndGroupBillValueFill());
		// 补充交易类型相关字段的值
		fillrule.addBillFillRule(new Vtrantypecode(POBillType.PrayBill.getCode()));
		fillrule.addBillFillRule(new Ctrantperid());
		// 补充行号
		fillrule.addBillFillRule(new RowNoBillFill());
		fillrule.addBillFillRule(new FillPuMaterialUnitRule());
		fillrule.addBillFillRule(new PUAssUnitAndChangeRate());
		fillrule.addBillFillRule(new PraybillVOAPIFill());
		fillrule.fillValue(vos);
		return vos;
	}
}
