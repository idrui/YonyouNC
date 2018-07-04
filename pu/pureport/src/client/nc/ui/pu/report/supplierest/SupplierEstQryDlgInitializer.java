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
 * @version 2011-8-2 下午04:56:02
 * @author 田锋涛
 */

public class SupplierEstQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限,主组织是财务组织
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "eb.pk_financeorg"
    });
    // 已全部结算入库单是否统计
    condDLGDelegator.setFieldValueElementEditor(null,
        SupplierEstQryFieldCode.bincldfinish.code(),
        new QueryComboEditorCreator());
    // 参照过滤
    this.refFilter(condDLGDelegator);

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void refFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 供应商编码，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            SupplierEstQueryAction.BD_SUPPLIER_NAME);
    qSupplierFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    qSupplierFilter.addEditorListener();
    // 供应商名称，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator, SupplierEstQueryAction.EB_PK_SUPPLIER);
    qSupplierNameFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    qSupplierNameFilter.addEditorListener();
    // 部门
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            SupplierEstQueryAction.EH_PK_DEPT);
    deptFilter.setPk_orgCode(SupplierEstQueryAction.EB_PK_PURCHASEORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            SupplierEstQueryAction.EH_PK_PSNDOC);
    psndoc.setPk_orgCode(SupplierEstQueryAction.EB_PK_PURCHASEORG);
    psndoc.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(dlgDelegator,
            SupplierEstQueryAction.BD_MATERIAL_PK_MARBASCLASS);
    marbasFilter.setPk_orgCode(SupplierEstQueryAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(dlgDelegator, SupplierEstQueryAction.mainOrg,
        SupplierEstQueryAction.EB_PK_SRCMATERIAL).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(dlgDelegator, SupplierEstQueryAction.mainOrg,
        SupplierEstQueryAction.BD_MATERIAL_NAME).addEditorListener();

  }

}
