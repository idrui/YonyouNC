package nc.ui.pu.m27.report.action;

import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.scale.m27.SettleBillQueryScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购结算单查询action
 * 
 * @since 6.0
 * @version 2011-3-24 上午10:29:24
 * @author lizhengb
 */
public class PuSettleBillReportQueryAction extends PURptDefaultQueryAction {
  public static final String mainOrg = "pk_org";

  public static final String PO_SETTLEBILL_B_PK_DEPT =
      "po_settlebill_b.pk_dept";

  public static final String PO_SETTLEBILL_B_PK_MATERIAL_PK_MARBASCLASS_CODE =
      "po_settlebill_b.pk_material.pk_marbasclass.code";

  public static final String PO_SETTLEBILL_B_PK_PSNDOC =
      "po_settlebill_b.pk_psndoc";

  public static final String PO_SETTLEBILL_B_PK_SRCMATERIAL_CODE =
      "po_settlebill_b.pk_srcmaterial.code";

  public static final String PO_SETTLEBILL_B_PK_SRCMATERIAL_NAME =
      "po_settlebill_b.pk_srcmaterial.name";

  public static final String PO_SETTLEBILL_B_PK_SUPPLIER_CODE =
      "po_settlebill_b.pk_supplier.code";

  public static final String PO_SETTLEBILL_B_PK_SUPPLIER_NAME =
      "po_settlebill_b.pk_supplier.name";

  public static final String PO_SETTLEBILL_B_VSTOCKTRANTYPE_PK_BILLTYPECODE =
      "po_settlebill_b.vstocktrantype.pk_billtypecode";

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(
        PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SRCMATERIAL_CODE,
        PUEntity.SettleBill_B_TAB + "." + SettleBillItemVO.PK_SRCMATERIAL);
    columnMapping.put(
        PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SRCMATERIAL_NAME,
        PUEntity.SettleBill_B_TAB + "." + SettleBillItemVO.PK_SRCMATERIAL);
    columnMapping.put(
        PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SUPPLIER_CODE,
        PUEntity.SettleBill_B_TAB + "." + SettleBillItemVO.PK_SUPPLIER);
    columnMapping.put(
        PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SUPPLIER_NAME,
        PUEntity.SettleBill_B_TAB + "." + SettleBillItemVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return SettleBillHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    return PuSettleBillReportQueryAction.mainOrg;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new SettleBillQueryScaleStrategy();
  }

  @Override
  protected void setFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 供应商编码，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SUPPLIER_CODE);
    qSupplierFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierFilter.addEditorListener();

    // 供应商名称，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SUPPLIER_NAME);
    qSupplierNameFilter.setPk_orgCode(this.getMainOrgKey());
    qSupplierNameFilter.addEditorListener();

    // 部门，如果查询一个财务组织的数据，则可参照该组织所属的公司下的部门档案；否则可参照集团范围的部门档案。
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_DEPT);
    deptFilter.setPk_orgCode(this.getMainOrgKey());
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 采购员，如果查询一个财务组织的数据，则可参照该组织所属的公司下的人员档案；否则可参照集团范围的人员档案。
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_PSNDOC);
    psndoc.setPk_orgCode(this.getMainOrgKey());
    psndoc.addEditorListener();

    // 物料基本分类编码，参照库存组织可见的物料基本分类档案录入
    QMarbasclassFilter qMarbasclassFilter =
        new QMarbasclassFilter(
            dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_MATERIAL_PK_MARBASCLASS_CODE);
    qMarbasclassFilter.setPk_orgCode(this.getMainOrgKey());
    qMarbasclassFilter.addEditorListener();

    // 物料编码
    QMarterialFilter materialCodeFilter =
        new QMarterialFilter(dlgDelegator, this.getMainOrgKey(),
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SRCMATERIAL_CODE);
    materialCodeFilter.setbFee(UFBoolean.FALSE);
    materialCodeFilter.setbDiscount(UFBoolean.FALSE);
    materialCodeFilter.addEditorListener();

    // 物料名称
    QMarterialFilter materialNameFilter =
        new QMarterialFilter(dlgDelegator, this.getMainOrgKey(),
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_PK_SRCMATERIAL_NAME);
    materialNameFilter.setbFee(UFBoolean.FALSE);
    materialNameFilter.setbDiscount(UFBoolean.FALSE);
    materialNameFilter.addEditorListener();

    // 订单类型，参照委外订单交易类型录入
    QTransTypeFilter trantype =
        new QTransTypeFilter(
            dlgDelegator,
            PuSettleBillReportQueryAction.PO_SETTLEBILL_B_VSTOCKTRANTYPE_PK_BILLTYPECODE,
            null);
    trantype.filterByBillTypes(new String[] {
      ICBillType.PurchaseIn.getCode(), ICBillType.SubContinIn.getCode(),
      ICBillType.VmiSum.getCode(), ICBillType.GeneralIn.getCode(),
      POBillType.InitEstimate.getCode()
    });
  }

}
