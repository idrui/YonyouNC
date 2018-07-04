package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.OrderPayExecQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarPuClassFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购订单付款执行查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class OrderPayExecQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {
  public static final String CEMPLOYEEID = "po_order.cemployeeid";

  public static final String PK_DEPT = "po_order.pk_dept";

  public static final String PO_ORDER_CTRANTYPEID = "po_order.ctrantypeid";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator,
        OrderPayExecQryDlgInit.PO_ORDER_CTRANTYPEID, POBillType.Order.getCode())
        .filter();
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderPayExecQryDlgInit.PK_DEPT);
    deptFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            OrderPayExecQryDlgInit.CEMPLOYEEID);
    psnFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    psnFilter.addEditorListener();
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_CODE);
    supFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    supFilter.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // 物料采购分类编码
    QMarPuClassFilter marpufilter =
        new QMarPuClassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARPUCLASS_CODE);
    marpufilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    marpufilter.addEditorListener();
    // 供应商名称
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_NAME);
    supFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    supFilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, OrderPayExecQryAction.mainOrg,
        "po_order_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new OrderHeaderVO(), new OrderItemVO())));
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M21_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(OrderPayExecQryAction.mainOrg);
    customerFilter.addEditorListener();

  }
}
