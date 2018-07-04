package nc.ui.pu.m23.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.report.queryinfo.arrival.PuArrivalQryInfoPara;

/**
 * 采购到货-日到货情况查询模板参照过滤器
 * 
 * @since 6.3
 * @version 2012-8-11 上午10:36:09
 * @author fanly3
 */
public class DayArriveQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  /** 客户 */
  public static final String CASSCUSTID = "po_order_b.casscustid";

  /** 项目 */
  public static final String CPROJECTID = "po_order_b.cprojectid";

  /** 库存组织 */
  public static final String MAIN_ORG = "pk_stockorg";

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 物料基本分类
    new QMarbasclassFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MARBASCLASS_CODE).addEditorListener();
    // 物料编码
    new QMarterialFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MATERIAL_V_CODE).addEditorListener();
    // 物料名称
    new QMarterialFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        PuArrivalQryInfoPara.BD_MATERIAL_V_NAME).addEditorListener();
    // 项目
    new QProjectFilter(condDLGDelegator, DayArriveQryDlgInit.MAIN_ORG,
        DayArriveQryDlgInit.CPROJECTID).addEditorListener();

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new ArriveHeaderVO(), new ArriveItemVO())));
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, DayArriveQryDlgInit.CASSCUSTID);
    customerFilter.setPk_orgCode(DayArriveQryDlgInit.MAIN_ORG);
    customerFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilter(condDLGDelegator, "po_arriveorder_b.pk_srcmaterial",
        "po_arriveorder_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, "po_arriveorder_b.pk_srcmaterial",
        "po_arriveorder_b.cffileid.vskucode").addEditorListener();
  }
}
