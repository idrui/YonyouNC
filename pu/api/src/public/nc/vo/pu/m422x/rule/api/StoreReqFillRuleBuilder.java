package nc.vo.pu.m422x.rule.api;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.api.fill.FillAssistUnitInfo;
import nc.vo.pu.m422x.rule.api.fill.FillBillDateInfo;
import nc.vo.pu.m422x.rule.api.fill.FillCurrencyInfo;
import nc.vo.pu.m422x.rule.api.fill.FillDefaultVOInfo;
import nc.vo.pu.m422x.rule.api.fill.FillDeptVidInfo;
import nc.vo.pu.m422x.rule.api.fill.FillPersonInfo;
import nc.vo.pu.m422x.rule.api.fill.FillTransTypeInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.fill.BillVOsFillRule;
import nc.vo.scmpub.fill.billfill.OIDSetterByVIDBillFill;
import nc.vo.scmpub.fill.billfill.OrgAndGroupBillValueFill;
import nc.vo.scmpub.fill.billfill.RowNoBillFill;
import nc.vo.scmpub.fill.vofill.MaterialOIDSetterByVIDVOFill;
import nc.vo.scmpub.fill.vofill.MaterialUnitSetterByVIDVOFill;

/**
 * 
 * @description
 *             物资需求申请单保存前，进行信息填充
 * @scene
 *       物资需求申请单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-31 下午3:25:40
 * @author luojw
 */
public class StoreReqFillRuleBuilder {
	/**
	 * 物资需求申请单保存填充规则
	 * @param vos
	 * @return StoreReqAppVO[] vos
	 * @throws BusinessException
	 */
	public StoreReqAppVO[] fillValue(AbstractBill[] vos) throws BusinessException {
	  OIDSetterByVIDBillFill vidfill = new OIDSetterByVIDBillFill();
	  
	  vidfill.addBodySetter(new MaterialOIDSetterByVIDVOFill(StoreReqAppItemVO.PK_SRCMATERIAL,
	      StoreReqAppItemVO.PK_MATERIAL));
	  vidfill.addBodySetter(new MaterialUnitSetterByVIDVOFill(StoreReqAppItemVO.CUNITID,
	      StoreReqAppItemVO.PK_MATERIAL));
	  vidfill.addHeadSetter(new FillDeptVidInfo(StoreReqAppHeaderVO.PK_APPDEPTH_V, 
	      StoreReqAppHeaderVO.PK_APPDEPTH));
	  
		BillVOsFillRule fillrule = new BillVOsFillRule();
		// 集团、组织
		fillrule.addBillFillRule(new OrgAndGroupBillValueFill());
		// 行号
		fillrule.addBillFillRule(new RowNoBillFill());
		// 单据的日期字段
		fillrule.addBillFillRule(new FillBillDateInfo());
    // 单据的人员字段
    fillrule.addBillFillRule(new FillPersonInfo());
		// 将比表头的信息，填充到表体
		fillrule.addBillFillRule(new FillDefaultVOInfo());
		// 币种
		fillrule.addBillFillRule(new FillCurrencyInfo());
		// 交易类型
		fillrule.addBillFillRule(new FillTransTypeInfo());
		// 物料的vid和主单位的设置
		fillrule.addBillFillRule(vidfill);
		// 填充辅单位、换算率、根据数量做联动计算
		fillrule.addBillFillRule(new FillAssistUnitInfo());
		fillrule.fillValue(vos);
		return (StoreReqAppVO[]) vos;
	}

}
