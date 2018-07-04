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
    // 查询模板过滤
    this.setFilter(dlgDelegator);
    dlgDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 供应商编码，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, "po_purchaseinfi_b.pk_supplier.code");
    qSupplierFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    qSupplierFilter.addEditorListener();

    // 供应商名称，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, "po_purchaseinfi_b.pk_supplier.name");
    qSupplierNameFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    qSupplierNameFilter.addEditorListener();

    // 部门，如果查询一个财务组织的数据，则可参照该组织所属的公司下的部门档案；否则可参照集团范围的部门档案。
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            "po_purchaseinfi.pk_dept_v");
    deptFilter.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 采购员，如果查询一个财务组织的数据，则可参照该组织所属的公司下的人员档案；否则可参照集团范围的人员档案。
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            "po_purchaseinfi.pk_psndoc");
    psndoc.setPk_orgCode("po_purchaseinfi_b.pk_financeorg_v");
    psndoc.addEditorListener();

    // // 物料基本分类编码，参照库存组织可见的物料基本分类档案录入
    // QMarbasclassFilter qMarbasclassFilter =
    // new QMarbasclassFilter(dlgDelegator, "pk_material.pk_marbasclass.code");
    // qMarbasclassFilter.setPk_orgCode("pk_order_b.pk_arrvstoorg");
    // qMarbasclassFilter.addEditorListener();
    //
    // // 物料编码，参照库存组织可见的物料档案录入
    // QMarterialFilter materialCodeFilter =
    // new QMarterialFilter(dlgDelegator, "pk_arrvstoorg", "pk_material.code");
    // materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    // materialCodeFilter.setbFee(UFBoolean.FALSE);
    // materialCodeFilter.addEditorListener();
    //
    // // 物料名称
    // QMarterialFilter materialNameFilter =
    // new QMarterialFilter(dlgDelegator, "pk_order_b.pk_arrvstoorg",
    // "pk_order_b.pk_material.name");
    // materialNameFilter.setbDiscount(UFBoolean.FALSE);
    // materialNameFilter.setbFee(UFBoolean.FALSE);
    // materialNameFilter.addEditorListener();

  }
}
