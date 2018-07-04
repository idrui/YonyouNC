package nc.ui.pu.m21.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 付款计划查询框初始化
 * 
 * @since 6.0
 * @version 2011-1-6 下午06:21:08
 * @author wuxla
 */

public class PayPlanDLGInitializer implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {

    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderHeaderVO.PK_ORG
    });
    this.filterRef(condDLGDelegator);
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderHeaderVO.PK_DEPT);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();

    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
        OrderHeaderVO.CEMPLOYEEID).addEditorListener();

    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator, POBillType.Order.getCode()).filter();

    // 供应商编码
    new QSupplierFilter(condDLGDelegator, PUQueryConst.SUPPLIER_CODE)
        .addEditorListener();
  }
}
