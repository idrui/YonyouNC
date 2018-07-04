package nc.ui.pu.report.supplierstatcomprate;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;

/**
 * 采购报表—按供应商统计订单完成率查询模板参照过滤器
 * 
 * @since 6.3
 * @version 2012-8-25 上午10:52:41
 * @author fanly3
 */
public class SupplierStatCompRateQryDlgInit implements
    IQueryConditionDLGInitializer {
  /** 采购组织 */
  public static final String MAIN_ORG = "po_order_b.pk_org";

  /** 物料基本分类 */
  public static final String BD_MARBASCLASS_CODE = "bd_marbasclass.code";

  /** 供应商编码 */
  public static final String BD_SUPPLIER_CODE = "bd_supplier.code";

  /** 供应商名称 */
  public static final String BD_SUPPLIER_NAME = "bd_supplier.name";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限，主组织是采购组织
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      MAIN_ORG
    });

    // 物料基本分类
    new QMarbasclassFilter(condDLGDelegator,
        SupplierStatCompRateQryDlgInit.MAIN_ORG,
        SupplierStatCompRateQryDlgInit.BD_MARBASCLASS_CODE).addEditorListener();

    // 供应商编码
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            SupplierStatCompRateQryDlgInit.BD_SUPPLIER_CODE);
    supplierFilter.setPk_orgCode(SupplierStatCompRateQryDlgInit.MAIN_ORG);
    supplierFilter.addEditorListener();

    // 供应商名称
    supplierFilter =
        new QSupplierFilter(condDLGDelegator,
            SupplierStatCompRateQryDlgInit.BD_SUPPLIER_NAME);
    supplierFilter.setPk_orgCode(SupplierStatCompRateQryDlgInit.MAIN_ORG);
    supplierFilter.addEditorListener();

  }

}
