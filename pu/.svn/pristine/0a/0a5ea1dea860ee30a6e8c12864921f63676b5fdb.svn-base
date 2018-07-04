package nc.ui.pu.m21.report.initializer;

import nc.scmmm.ui.scmpub.report.action.SCMRptQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.report.enumeration.OrderSummaryQryFieldCode;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderSummaryRptRefInitializer extends
    SCMRptQueryConditionDLGInitializer {

  @Override
  public String getMainOrgField() {
    return OrderSummaryQryFieldCode.pk_org.getFieldCode();
  }

  @Override
  protected void initRef(QueryConditionDLGDelegator delegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(delegator, POBillType.Order.getCode()).filter();

    // 采购员
    QPsndocFilter.createQPsndocFilterOfPU(delegator,
        OrderSummaryQryFieldCode.cemployeeid.getFieldCode())
        .addEditorListener();

    // 采购部门
    QDeptFilter.createQDeptFilterOfPU(delegator,
        OrderSummaryQryFieldCode.pk_dept.getFieldCode()).addEditorListener();

    // 供应商编码
    new QSupplierFilter(delegator,
        OrderSummaryQryFieldCode.suppliercode.getFieldCode())
        .addEditorListener();

    // 供应商名称
    new QSupplierFilter(delegator,
        OrderSummaryQryFieldCode.suppliername.getFieldCode())
        .addEditorListener();

    // 物料基本分类
    new QMarbasclassFilter(delegator,
        OrderSummaryQryFieldCode.materialclasscode.getFieldCode())
        .addEditorListener();

    // 物料采购分类编码
    new QMarPuClassFilter(delegator,
        OrderSummaryQryFieldCode.marpuclasscode.getFieldCode())
        .addEditorListener();

    // 物料编码
    new QMarterialoidFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.materialcode.getFieldCode())
        .addEditorListener();

    // 物料名称
    new QMarterialoidFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.materialname.getFieldCode())
        .addEditorListener();

    // 项目
    new QProjectFilter(delegator,
        OrderSummaryQryFieldCode.pk_org.getFieldCode(),
        OrderSummaryQryFieldCode.cprojectid.getFieldCode()).addEditorListener();

    // 客户
    new QCustomerFilter(delegator,
        OrderSummaryQryFieldCode.casscustid.getFieldCode()).addEditorListener();
  }

}
