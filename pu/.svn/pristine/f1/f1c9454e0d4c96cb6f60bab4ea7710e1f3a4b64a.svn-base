package nc.ui.pu.report.puArrive;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QFfileFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * 采购订单付款执行查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class PuArriveQueryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator, PuArriveQueryAction.CVENDORID);
    supFilter.setPk_orgCode(PuArriveQueryAction.mainOrg);
    supFilter.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(condDLGDelegator,
            PuArriveQueryAction.PUARRIVAL_PK_MARBASCLASS);
    marbasFilter.setPk_orgCode(PuArriveQueryAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator, PuArriveQueryAction.mainOrg,
        PuArriveQueryAction.CMATERIALOID).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(condDLGDelegator, PuArriveQueryAction.mainOrg,
        PuArriveQueryAction.CMATERIALOID_NAME).addEditorListener();
    // 供应商名称
    supFilter =
        new QSupplierFilter(condDLGDelegator,
            PuArriveQueryAction.CVENDORID_NAME);
    supFilter.setPk_orgCode(PuArriveQueryAction.mainOrg);
    supFilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目
    new QProjectFilter(condDLGDelegator, PuArriveQueryAction.mainOrg,
        "po_arriveorder_b.cprojectid").addEditorListener();
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new ArriveHeaderVO(), new ArriveItemVO())));
    // 按主组织过滤客户
    QCustomerFilter customerFilter =
        new QCustomerFilter(condDLGDelegator, PUEntity.M23_B_TABLE
            + PUQueryConst.DOT + PuAttrNameEnum.casscustid.name());
    customerFilter.setPk_orgCode(PuArriveQueryAction.mainOrg);
    customerFilter.addEditorListener();
    
    // 按物料过滤特征码
    new QFfileFilter(condDLGDelegator, "pk_arriveorder_b.pk_srcmaterial",
        "pk_arriveorder_b.cffileid").addEditorListener();
    new QFfileFilter(condDLGDelegator, "pk_arriveorder_b.pk_srcmaterial",
        "pk_arriveorder_b.cffileid.vskucode").addEditorListener();
  }
}
