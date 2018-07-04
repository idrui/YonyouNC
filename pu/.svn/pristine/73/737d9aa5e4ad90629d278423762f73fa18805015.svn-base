package nc.ui.pu.m21.view;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilterByMaterCode;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUQueryConst;

/**
 * @since 6.0
 * @version 2011-1-13 下午05:56:22
 * @author wuxla
 */

public class OrderCloseQueryDLGInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      OrderHeaderVO.PK_ORG
    });
    // 参照过滤
    this.filterRef(condDLGDelegator);
    // 设置冗余字段
    this.setRedundency(condDLGDelegator);

    condDLGDelegator.setPowerEnable(true);

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  private void filterRef(QueryConditionDLGDelegator condDLGDelegator) {
    // 按采购组织过滤采购部门
    QDeptFilter.createQDeptFilterOfPU(condDLGDelegator, OrderHeaderVO.PK_DEPT)
        .addEditorListener();

    // 按采购组织过滤采购员
    QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
        OrderHeaderVO.CEMPLOYEEID).addEditorListener();
    // 物料编码
    new QMarterialFilter(condDLGDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_CODE)
        .addEditorListener();

    // 物料名称
    new QMarterialFilter(condDLGDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.MATERIAL_NAME)
        .addEditorListener();

    // 物料编码
    new QMarterialFilter(condDLGDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_CODE)
        .addEditorListener();

    // 物料名称
    new QMarterialFilter(condDLGDelegator, OrderHeaderVO.PK_ORG,
        OrderItemVO.PK_ORDER_B + PUQueryConst.SRCMATERIAL_NAME)
        .addEditorListener();

    // 根据采购组织过滤供应商
    QSupplierFilter supplierFilter =
        new QSupplierFilter(condDLGDelegator, "pk_supplier.code");
    supplierFilter.setPk_orgCode("pk_org");
    supplierFilter.addEditorListener();

    // 根据库存组织过滤需求仓库
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_reqstoorg",
        "pk_order_b.pk_reqstordoc").addEditorListener();

    // 根据收货库存组织过滤收货仓库
    new QWareHouseFilter(condDLGDelegator, "pk_order_b.pk_arrvstoorg",
        "pk_order_b.pk_recvstordoc").addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid").addEditorListener();
    new QFfileFilterByMaterCode(condDLGDelegator, "pk_order_b.pk_srcmaterial.code",
        "pk_order_b.cffileid.vskucode").addEditorListener();
  }

  private void setRedundency(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_ORG, "pk_order_b."
        + OrderItemVO.PK_ORG);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.DBILLDATE, "pk_order_b."
        + OrderItemVO.DBILLDATE);
    condDLGDelegator.addRedundancyInfo(OrderHeaderVO.PK_SUPPLIER, "pk_order_b."
        + OrderItemVO.PK_SUPPLIER);
  }

}
