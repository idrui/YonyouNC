package nc.ui.pu.m21.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.ui.scmpub.query.refregion.QueryDlgInitialUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-1-13 上午11:06:25
 * @author wuxla
 */

public class OrderReplenishQueryInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderHeaderVO.PK_ORG
    });
    this.filterRef(condDLGDelegator);

    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_ORG, "pk_order_b."
        + OrderItemVO.PK_ORG);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.DBILLDATE, "pk_order_b."
        + OrderItemVO.DBILLDATE);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_SUPPLIER, "pk_order_b."
        + OrderItemVO.PK_SUPPLIER);

    condDLGDelegator.setPowerEnable(true);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());

    // 购销类型只有国内采购和进口采购
    QueryDlgInitialUtils.filterBuySellFlagForBuy(condDLGDelegator,
        "pk_order_b.fbuysellflag");
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, "pk_dept")
        .addEditorListener();

    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator, "cemployeeid")
        .addEditorListener();
    
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator, POBillType.Order.getCode()).filter();

    // 供应商编码，参照所选库存组织内可见的供应商
    new QSupplierFilter(condDLGDelegator, "pk_supplier.code")
        .addEditorListener();

    // 需求仓库
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_reqstoorg",
        "pk_order_b.pk_reqstordoc").addEditorListener();

    // 收货仓库
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_arrvstoorg",
        "pk_order_b.pk_recvstordoc").addEditorListener();
    
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, OrderItemVO.PK_ORDER_B
        + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name())
        .addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid.vskucode").addEditorListener();
  }

}
