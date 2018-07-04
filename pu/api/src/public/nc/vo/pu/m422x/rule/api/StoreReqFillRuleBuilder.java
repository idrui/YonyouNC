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
 *             �����������뵥����ǰ��������Ϣ���
 * @scene
 *       �����������뵥����
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-31 ����3:25:40
 * @author luojw
 */
public class StoreReqFillRuleBuilder {
	/**
	 * �����������뵥����������
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
		// ���š���֯
		fillrule.addBillFillRule(new OrgAndGroupBillValueFill());
		// �к�
		fillrule.addBillFillRule(new RowNoBillFill());
		// ���ݵ������ֶ�
		fillrule.addBillFillRule(new FillBillDateInfo());
    // ���ݵ���Ա�ֶ�
    fillrule.addBillFillRule(new FillPersonInfo());
		// ���ȱ�ͷ����Ϣ����䵽����
		fillrule.addBillFillRule(new FillDefaultVOInfo());
		// ����
		fillrule.addBillFillRule(new FillCurrencyInfo());
		// ��������
		fillrule.addBillFillRule(new FillTransTypeInfo());
		// ���ϵ�vid������λ������
		fillrule.addBillFillRule(vidfill);
		// ��丨��λ�������ʡ�������������������
		fillrule.addBillFillRule(new FillAssistUnitInfo());
		fillrule.fillValue(vos);
		return (StoreReqAppVO[]) vos;
	}

}
