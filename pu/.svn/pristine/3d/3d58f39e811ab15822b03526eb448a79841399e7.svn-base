package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购订单查询对话框初始化类。
 * 
 * @since 6.0
 * @version 2012-9-18 上午10:50:05
 * @author lixyp
 */
public class OrderOnwayQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  /** 采购员 */
  public static final String CEMPLOYEEID = "po_order.cemployeeid";

  /** 部门 */
  public static final String PK_DEPT = "po_order.pk_dept";

  /** 交易类型 */
  public static final String VTRANTYPECODE = "po_order.vtrantypecode";

  /** 客户 */
  private static final String CASSCUSTID = "po_order_b.casscustid";

  /** 项目 */
  private static final String CPROJECTID = "po_order_b.cprojectid";

  /** 采购组织 */
  private static final String PK_ORG = "po_order.pk_org";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator, OrderOnwayQryDlgInit.VTRANTYPECODE,
        POBillType.Order.getCode()).filter();
    // 采购员
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            OrderOnwayQryDlgInit.CEMPLOYEEID);
    psnFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    psnFilter.addEditorListener();
    // 采购部门
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderOnwayQryDlgInit.PK_DEPT);
    deptFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 供应商编码
    QSupplierFilter supCodeFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_CODE);
    supCodeFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    supCodeFilter.addEditorListener();
    // 供应商名称
    QSupplierFilter supNameFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_NAME);
    supNameFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    supNameFilter.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    marbasFilter.addEditorListener();
    // 物料编码
    new QMarterialoidFilter(condDLGDelegator, OrderOnwayQryDlgInit.PK_ORG,
        PUOrderQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, OrderOnwayQryDlgInit.PK_ORG,
        PUOrderQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // 物料采购分类编码
    QMarPuClassFilter marpufilter =
        new QMarPuClassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARPUCLASS_CODE);
    marpufilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    marpufilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, OrderOnwayQryDlgInit.PK_ORG,
        OrderOnwayQryDlgInit.CPROJECTID).addEditorListener();
    // 客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, OrderOnwayQryDlgInit.CASSCUSTID);
    customerFilter.setPk_orgCode(OrderOnwayQryDlgInit.PK_ORG);
    customerFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilter(condDLGDelegator, "pk_order_b.pk_srcmaterial",
        "pk_order_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, "pk_order_b.pk_srcmaterial",
        "pk_order_b.cffileid.vskucode").addEditorListener();

    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new OrderHeaderVO(), new OrderItemVO())));
  }
}
