package nc.ui.pu.m422x.report.initializer;

import nc.scmmm.ui.scmpub.report.action.SCMRptQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.report.enumeration.StoreReqSummaryQryFieldCode;
import nc.vo.scmpub.res.billtype.POBillType;

public class StoreReqSummaryRptRefInitializer extends
    SCMRptQueryConditionDLGInitializer {

  @Override
  public String getMainOrgField() {
    return StoreReqSummaryQryFieldCode.org.getFieldCode();
  }

  @Override
  protected void initRef(QueryConditionDLGDelegator delegator) {
    // 物料基本分类编码
    new QMarbasclassFilter(delegator,
        StoreReqSummaryQryFieldCode.org.getFieldCode(),
        StoreReqSummaryQryFieldCode.materialclasscode.getFieldCode())
        .addEditorListener();

    // 物料编码
    new QMarterialoidFilter(delegator,
        StoreReqSummaryQryFieldCode.org.getFieldCode(),
        StoreReqSummaryQryFieldCode.materialcode.getFieldCode())
        .addEditorListener();

    // 物料名称
    new QMarterialoidFilter(delegator,
        StoreReqSummaryQryFieldCode.org.getFieldCode(),
        StoreReqSummaryQryFieldCode.materialname.getFieldCode())
        .addEditorListener();

    // 需求仓库
    new QWareHouseFilter(delegator,
        StoreReqSummaryQryFieldCode.org.getFieldCode(),
        StoreReqSummaryQryFieldCode.reqstordoc.getFieldCode())
        .addEditorListener();

    // 申请人
    QPsndocFilter.createQPsndocFilterOfIC(delegator,
        StoreReqSummaryQryFieldCode.appsn.getFieldCode()).addEditorListener();

    // 申请部门
    QDeptFilter.createDeptFilterOfIC(delegator,
        StoreReqSummaryQryFieldCode.appdept.getFieldCode()).addEditorListener();

    // 项目
    new QProjectFilter(delegator,
        StoreReqSummaryQryFieldCode.org.getFieldCode(),
        StoreReqSummaryQryFieldCode.cprojectid.getFieldCode())
        .addEditorListener();

    // 物资需求申请类型
    new QTransTypeFilter(delegator, POBillType.MRBill.getCode()).filter();
  }
}
