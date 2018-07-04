package nc.ui.pu.report.purchaseIn.dlg;

import java.awt.Container;

import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.querytemplate.TemplateInfo;

public class PurchaseInReportDlg extends QueryConditionDLG {

  private static final long serialVersionUID = 3147136592266075516L;

  public PurchaseInReportDlg(Container parent, TemplateInfo ti, String title) {
    super(parent, ti, title);
    QueryConditionDLGDelegator dlgDelegator =
        new QueryConditionDLGDelegator(parent, ti);
    dlgDelegator.setTitle(title);
    // ��ѯģ�����
    this.setFilter(dlgDelegator);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // ��Ӧ�̱��룬���ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, "po_purchaseinfi_b.pk_supplier.code");
    qSupplierFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    qSupplierFilter.addEditorListener();

    // ��Ӧ�����ƣ����ղ�����֯�ɼ��Ĺ�Ӧ�̵���¼��
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, "po_purchaseinfi_b.pk_supplier.name");
    qSupplierNameFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    qSupplierNameFilter.addEditorListener();

    // ���ţ������ѯһ��������֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µĲ��ŵ���������ɲ��ռ��ŷ�Χ�Ĳ��ŵ�����
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            "po_purchaseinfi.pk_dept_v");
    deptFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // �ɹ�Ա�������ѯһ��������֯�����ݣ���ɲ��ո���֯�����Ĺ�˾�µ���Ա����������ɲ��ռ��ŷ�Χ����Ա������
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            "po_purchaseinfi.pk_psndoc");
    psndoc.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    psndoc.addEditorListener();

    // // ���ϻ���������룬���տ����֯�ɼ������ϻ������൵��¼��
    // QMarbasclassFilter qMarbasclassFilter =
    // new QMarbasclassFilter(dlgDelegator, "pk_material.pk_marbasclass.code");
    // qMarbasclassFilter.setPk_orgCode("pk_order_b.pk_arrvstoorg");
    // qMarbasclassFilter.addEditorListener();
    //
    // // ���ϱ��룬���տ����֯�ɼ������ϵ���¼��
    // QMarterialFilter materialCodeFilter =
    // new QMarterialFilter(dlgDelegator, "pk_arrvstoorg", "pk_material.code");
    // materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    // materialCodeFilter.setbFee(UFBoolean.FALSE);
    // materialCodeFilter.addEditorListener();
    //
    // // ��������
    // QMarterialFilter materialNameFilter =
    // new QMarterialFilter(dlgDelegator, "pk_order_b.pk_arrvstoorg",
    // "pk_order_b.pk_material.name");
    // materialNameFilter.setbDiscount(UFBoolean.FALSE);
    // materialNameFilter.setbFee(UFBoolean.FALSE);
    // materialNameFilter.addEditorListener();

  }
}
