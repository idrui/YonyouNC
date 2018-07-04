package nc.ui.pu.report.returndetail.action;

import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;

/**
 * 采购订单付款执行查询模板参照过滤器
 * 
 * @since 6.0
 * @version 2011-9-6 上午9:56:39
 * @author zhaoyha
 */
public class ReturnDetailQueryDlgInit implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator, ReturnDetailQueryAction.CVENDORID);
    supFilter.setPk_orgCode(ReturnDetailQueryAction.PK_PURCHASEORG);
    supFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(condDLGDelegator,
        ReturnDetailQueryAction.PK_PURCHASEORG,
        ReturnDetailQueryAction.CMATERIALOID).addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目（应该是基本档案有变化，不需要过滤）

    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }
}
