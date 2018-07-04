package nc.vo.pu.m23.rule.api;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.api.fill.FillPresentNum;
import nc.vo.pu.m23.rule.api.fill.FillPurchaseorgVidRule;
import nc.vo.pu.m23.rule.api.fill.PUAssUnitAndChangeRate;
import nc.vo.pu.m23.rule.api.fill.TrantypeCodeFillFor23;
import nc.vo.pu.m23.rule.api.fill.TrantypeIDFillFor23;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.BillVOsFillRule;
import nc.vo.scmpub.fill.billfill.BillDateBillFill;
import nc.vo.scmpub.fill.billfill.OIDSetterByVIDBillFill;
import nc.vo.scmpub.fill.billfill.OrgAndGroupBillValueFill;
import nc.vo.scmpub.fill.billfill.RowNoBillFill;
import nc.vo.scmpub.fill.billfill.TrantypeCodeBillFill;
import nc.vo.scmpub.fill.vofill.MaterialOIDSetterByVIDVOFill;
import nc.vo.scmpub.fill.vofill.MaterialUnitSetterByVIDVOFill;

/**
 * 
 * @description
 *		API到货单保存基本规则组装类
 * @scene
 * 		到货单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-23 下午5:30:57
 * @author wandl
 */
public class PUArriveVOFillRuleBuilder {
	/**
	 * 到货单保存填充规则
	 * @param vos
	 * @return ArriveVO[] vos
	 * @throws BusinessException
	 */
	public void fillValue (AbstractBill[] vos) throws BusinessException {
		BillVOsFillRule fillrule = new BillVOsFillRule();
		fillrule.addBillFillRule(new FillPurchaseorgVidRule());
		fillrule.addBillFillRule(new BillDateBillFill());
		fillrule.addBillFillRule(new FillPresentNum());
		fillrule.addBillFillRule(new TrantypeCodeFillFor23("23"));
		fillrule.addBillFillRule(new TrantypeIDFillFor23());
		fillrule.addBillFillRule(new RowNoBillFill());
		OIDSetterByVIDBillFill vidfill = new OIDSetterByVIDBillFill();
		
		vidfill.addBodySetter(new MaterialOIDSetterByVIDVOFill(ArriveItemVO.PK_SRCMATERIAL,
				ArriveItemVO.PK_MATERIAL));
/*		vidfill.addBodySetter(new MaterialUnitSetterByVIDVOFill(ArriveItemVO.CUNITID,
				ArriveItemVO.PK_MATERIAL));*/
		fillrule.addBillFillRule(vidfill);
		fillrule.fillValue(vos);
	}

}
