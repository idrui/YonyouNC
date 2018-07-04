package nc.ui.pu.m20.billref.pu.m29;

import java.awt.Container;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.TemplateInfo;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ṩ��ѯ���۵��Ĳ�ѯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-10 ����06:19:55
 */
public class BillReferQueryFor29 extends DefaultBillReferQuery {

  public BillReferQueryFor29(Container c, TemplateInfo info) {
    super(c, info);

  }

  public String getDefaultPk_OrgValue() {
    String pk_org;
    try {
      pk_org = DefaultDataSettingAccessor.getDefaultOrgUnit();
      if (StringUtil.isEmptyWithTrim(pk_org)) {
        return null;
      }
      if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.PURCHASEORGTYPE)) {
        return pk_org;
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void filterOrgPower(QueryConditionDLGDelegator dlgDelegator) {
    // ����֯Ȩ��
    dlgDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG
    });
    // ����ʹ��Ȩ��
    dlgDelegator.setPowerEnable(true);
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // �ƻ����ţ������ѯһ�������֯�����ݣ���ֻ�ɲ��ոÿ����֯�ɼ��Ĳ��ŵ�����������ռ��ŷ�Χ�Ĳ��ŵ���¼�롣
    QDeptFilter planDeptFilter =
        QDeptFilter.createDeptFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANDEPT);
    planDeptFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planDeptFilter.addEditorListener();

    // �ƻ�Ա�������ѯһ�������֯�����ݣ���ֻ�ɲ��ոÿ����֯�ɼ�����Ա������������ռ��ŷ�Χ����Ա����¼�롣
    QPsndocFilter planPsndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            PraybillHeaderVO.PK_PLANPSN);
    planPsndocFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planPsndocFilter.addEditorListener();

    // �빺���ͣ������빺����������¼��
    new QTransTypeFilter(dlgDelegator, "ctrantypeid",
        POBillType.PrayBill.getCode()).filter();

    // ���ϻ���������룬���ղɹ���֯�����ϻ�������
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(dlgDelegator,
            "pk_praybill_b.pk_material.pk_marbasclass.code");
    qMarbasclassFilter.setPk_orgCode("pk_praybill_b.pk_purchaseorg");
    qMarbasclassFilter.addEditorListener();

    // ���ϱ��룬���ղɹ���֯�ɼ������ϵ���¼��
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();

    // ��Ŀ
    new QProjectFilter(dlgDelegator, "pk_praybill_b.pk_purchaseorg",
        "pk_praybill_b.cprojectid").addEditorListener();
    // ������֯���˿ͻ�
    new QCustomerFilter(dlgDelegator, "pk_praybill_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
  }

  @Override
  protected void initQueryConditionDLG(QueryConditionDLGDelegator dlgDelegator) {
    // ��֯Ȩ�޹���
    this.filterOrgPower(dlgDelegator);
    this.setFilter(dlgDelegator);
    dlgDelegator.setDefaultValue("pk_org", null);
    dlgDelegator.setDefaultValue("pk_praybill_b.pk_purchaseorg",
        this.getDefaultPk_OrgValue());
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
