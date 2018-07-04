package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.OrderDetailsQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
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
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 采购订单明细查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class OrderDetailsQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator, POBillType.Order.getCode()).filter();
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderHeaderVO.PK_DEPT);
    deptFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            OrderHeaderVO.CEMPLOYEEID);
    psnFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    psnFilter.addEditorListener();
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_SUPPLIER_CODE);
    supFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    supFilter.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_MATERIAL_PK_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderDetailsQryAction.THIS_PK_SRCMATERIAL_NAME).addEditorListener();
    // 物料采购分类编码
    QMarPuClassFilter marpufilter =
        new QMarPuClassFilter(
            condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_MATERIAL_MATERIALSTOCK_PK_MARPUCLASS_CODE);
    marpufilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    marpufilter.addEditorListener();
    // 供应商名称
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            OrderDetailsQryAction.THIS_PK_SUPPLIER_NAME);
    supFilter.setPk_orgCode(OrderDetailsQryAction.mainOrg);
    supFilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, OrderDetailsQryAction.mainOrg,
        OrderItemVO.CPROJECTID).addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new OrderHeaderVO(), new OrderItemVO())));
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, PuAttrNameEnum.casscustid.name())
        .addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilter(condDLGDelegator, OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE,
        OrderItemVO.CFFILEID).addEditorListener();
    new QFfileFilter(condDLGDelegator, OrderDetailsQryAction.THIS_PK_SRCMATERIAL_CODE,
        "this.cffileid.vskucode").addEditorListener();
  }
}
