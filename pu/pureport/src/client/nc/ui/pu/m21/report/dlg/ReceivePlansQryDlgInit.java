package nc.ui.pu.m21.report.dlg;

import nc.ui.pu.m21.report.action.ReceivePlansQryAction;
import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QTransTypeFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.order.PUOrderQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 到货计划查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class ReceivePlansQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {
  public static final String CEMPLOYEEID = "po_order.cemployeeid";

  public static final String PK_ARRVSTOORG = "po_order_bb1.pk_arrvstoorg";

  public static final String PK_DEPT = "po_order.pk_dept";

  public static final String PK_RECVSTORDOC = "po_order_bb1.pk_recvstordoc";

  public static final String PO_ORDER_CTRANTYPEID = "po_order.ctrantypeid";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 订单类型，参照采购订单交易类型录入
    new QTransTypeFilter(condDLGDelegator, "po_order.ctrantypeid",
        POBillType.Order.getCode()).filter();
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            ReceivePlansQryDlgInit.PK_DEPT);
    deptFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            ReceivePlansQryDlgInit.CEMPLOYEEID);
    psnFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    psnFilter.addEditorListener();
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_CODE);
    supFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    supFilter.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_MARBASCLASS_CODE);
    marbasFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        PUOrderQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // 供应商名称
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            PUOrderQryInfoPara.BD_SUPPLIER_NAME);
    supFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    supFilter.addEditorListener();
    // 收货仓库
    QWareHouseFilter whfilter =
        new QWareHouseFilter(condDLGDelegator,
            ReceivePlansQryDlgInit.PK_ARRVSTOORG,
            ReceivePlansQryDlgInit.PK_RECVSTORDOC);
    // 根据设置条件过滤仓库
    whfilter.filter();
    whfilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, ReceivePlansQryAction.mainOrg,
        "po_order_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new ArriveHeaderVO(), new ArriveItemVO())));
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M21_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(ReceivePlansQryAction.mainOrg);
    customerFilter.addEditorListener();
  }
}
