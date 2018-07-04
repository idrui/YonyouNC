package nc.ui.pu.report.supplierest;

import nc.ui.pu.report.pub.editor.QueryComboEditorCreator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2011-8-2 ����04:56:02
 * @author �����
 */

public class SupplierEstQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // ����֯Ȩ��,����֯�ǲ�����֯
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "eb.pk_financeorg"
    });
    // ��ȫ��������ⵥ�Ƿ�ͳ��
    condDLGDelegator.setFieldValueElementEditor(null,
        SupplierEstQryFieldCode.bincldfinish.code(),
        new QueryComboEditorCreator());
    // ���չ���
    this.refFilter(condDLGDelegator);

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void refFilter(QueryConditionDLGDelegator dlgDelegator) {
    // ��Ӧ�̱��룬���ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            SupplierEstQueryAction.BD_SUPPLIER_NAME);
    qSupplierFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    qSupplierFilter.addEditorListener();
    // ��Ӧ�����ƣ����ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, SupplierEstQueryAction.EB_PK_SUPPLIER);
    qSupplierNameFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    qSupplierNameFilter.addEditorListener();
    // ����
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            SupplierEstQueryAction.EH_PK_DEPT);
    deptFilter.setPk_orgCode(SupplierEstQueryAction.EB_PK_PURCHASEORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // �ɹ�Ա
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            SupplierEstQueryAction.EH_PK_PSNDOC);
    psndoc.setPk_orgCode(SupplierEstQueryAction.EB_PK_PURCHASEORG);
    psndoc.addEditorListener();
    // ���ϻ����������
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(dlgDelegator,
            SupplierEstQueryAction.BD_MATERIAL_PK_MARBASCLASS);
    marbasFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    marbasFilter.addEditorListener();
    // ���ϱ���(OID)
    new QMarterialoidFilter(dlgDelegator, SupplierEstQueryAction.mainOrg,
        SupplierEstQueryAction.EB_PK_SRCMATERIAL).addEditorListener();
    // ��������
    new QMarterialoidFilter(dlgDelegator, SupplierEstQueryAction.mainOrg,
        SupplierEstQueryAction.BD_MATERIAL_NAME).addEditorListener();

  }

}
