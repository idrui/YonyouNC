package nc.ui.pu.m20.query;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-1-13 ����09:38:59
 * @author �߹���
 */

public class PraybillQueryDLGInitializer implements
    IQueryConditionDLGInitializer {
  /** �ɹ���֯fieldcode **/
  public static final String PK_PRAYBILL_B_PK_PURCHASEORG =
      "pk_praybill_b.pk_purchaseorg";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    this.initPermission(condDLGDelegator);
    this.filterRef(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // ������֯���˼ƻ�����
    QDeptFilter deptFilter =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANDEPT);
    deptFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    deptFilter.addEditorListener();

    QDeptFilter deptFilter_v =
        QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANDEPT_V);
    deptFilter_v.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    deptFilter_v.addEditorListener();

    QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
        "pk_praybill_b.pk_reqdept").addEditorListener();

    QDeptFilter.createDeptFilterOfIC(condDLGDelegator,
        "pk_praybill_b.pk_reqdept_v").addEditorListener();

    // ������֯���˼ƻ�Ա
    QPsndocFilter planPsndocFilter =
        QPsndocFilter.createQPsndocFilterOfIC(condDLGDelegator,
            PraybillHeaderVO.PK_PLANPSN);
    planPsndocFilter.setPk_orgCode(PraybillHeaderVO.PK_ORG);
    planPsndocFilter.addEditorListener();

    // �����빺����
    new QTransTypeFilter(condDLGDelegator, POBillType.PrayBill.getCode())
        .filter();

    // ������֯�������ϻ�������
    new QMarbasclassFilter(condDLGDelegator,
        "pk_praybill_b.pk_material.pk_marbasclass.code").addEditorListener();

    // ������֯��������
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_material.name").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.code").addEditorListener();
    new QMarterialFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_srcmaterial.name").addEditorListener();
    // ������֯��������ֿ�
    new QWareHouseFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.pk_reqstor").addEditorListener();

    // ������֯������Ŀ
    new QProjectFilter(condDLGDelegator, PraybillHeaderVO.PK_ORG,
        "pk_praybill_b.cprojectid").addEditorListener();

    // ���ɹ���֯���˽��鹩Ӧ��
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            "pk_praybill_b.pk_suggestsupplier");
    supplierFilter
        .setPk_orgCode(PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG);
    supplierFilter.addEditorListener();

    // ���ɹ���֯���˲ɹ�Ա
    QPsndocFilter psndocFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            "pk_praybill_b.pk_employee");
    psndocFilter
        .setPk_orgCode(PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG);
    psndocFilter.addEditorListener();

    // ������֯���˿ͻ�
    new QCustomerFilter(condDLGDelegator, "pk_praybill_b."
        + PuAttrNameEnum.casscustid.name()).addEditorListener();
    
    // �����Ϲ���������
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_praybill_b.pk_srcmaterial.code",
        "pk_praybill_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_praybill_b.pk_srcmaterial.code",
        "pk_praybill_b.cffileid.vskucode").addEditorListener();
  }

  private void initPermission(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      PraybillHeaderVO.PK_ORG
    });
    // ����ʹ��Ȩ
    condDLGDelegator.setPowerEnable(true);
  }

}
