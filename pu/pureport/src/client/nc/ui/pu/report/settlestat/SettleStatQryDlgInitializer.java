package nc.ui.pu.report.settlestat;

import nc.ui.pu.report.pub.query.refregion.QOrgAccountPeriod;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QMarbasclassFilter;
import nc.ui.scmpub.query.refregion.QMarterialoidFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.ui.scmpub.query.refregion.QWareHouseFilter;
import nc.vo.pu.report.enumeration.EstMonthStatQryFieldCode;
import nc.vo.pub.lang.UFBoolean;

/**
 * 暂估月统计查询过滤
 * 
 * @since 6.0
 * @version 2011-8-2 下午04:59:34
 * @author 田锋涛
 */
public class SettleStatQryDlgInitializer implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 主组织权限,主组织是财务组织
    condDLGDelegator.registerNeedPermissionOrgFieldCodes(new String[] {
      "pk_financeorg"
    });
    // 参照过滤
    this.refFilter(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
  }

  protected void refFilter(QueryConditionDLGDelegator dlgDelegator) {
    // 供应商编码，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierFilter =
        new QSupplierFilter(dlgDelegator, SettleStatSubscribeAction.PK_SUPPLIER);
    qSupplierFilter.setPk_orgCode(SettleStatSubscribeAction.mainOrg);
    qSupplierFilter.addEditorListener();
    // 供应商名称，参照财务组织可见的供应商档案录入
    QSupplierFilter qSupplierNameFilter =
        new QSupplierFilter(dlgDelegator,
            SettleStatSubscribeAction.SUPPLIERNAME);
    qSupplierNameFilter.setPk_orgCode(SettleStatSubscribeAction.mainOrg);
    qSupplierNameFilter.addEditorListener();
    // 部门
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(dlgDelegator,
            SettleStatSubscribeAction.PK_DEPT);
    deptFilter.setPk_orgCode(SettleStatSubscribeAction.PK_PURCHASEORG);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员
    QPsndocFilter psndoc =
        QPsndocFilter.createQPsndocFilterOfPU(dlgDelegator,
            SettleStatSubscribeAction.PK_PSNDOC);
    psndoc.setPk_orgCode(SettleStatSubscribeAction.PK_PURCHASEORG);
    psndoc.addEditorListener();
    // 物料基本分类编码
    QMarbasclassFilter marbasFilter =
        new QMarbasclassFilter(dlgDelegator,
            SettleStatSubscribeAction.PK_MARBASCLASS);
    marbasFilter.setPk_orgCode(SettleStatSubscribeAction.mainOrg);
    marbasFilter.addEditorListener();
    // 物料编码(OID)
    new QMarterialoidFilter(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        SettleStatSubscribeAction.PK_SRCMATERIAL).addEditorListener();
    // 物料名称
    new QMarterialoidFilter(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        SettleStatSubscribeAction.MATERIALNAME).addEditorListener();
    // 仓库编码
    new QWareHouseFilter(dlgDelegator, SettleStatSubscribeAction.PK_STOREORG,
        SettleStatSubscribeAction.PK_STORDOC).addEditorListener();
    // 库管员
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfIC(dlgDelegator,
            SettleStatSubscribeAction.CWHSMANAGERID);
    psnFilter.setPk_orgCode(SettleStatSubscribeAction.PK_STOREORG);
    psnFilter.addEditorListener();
    // 会计期间取财务组织（业务单元）的会计期间
    new QOrgAccountPeriod(dlgDelegator, SettleStatSubscribeAction.mainOrg,
        EstMonthStatQryFieldCode.fperiod.code()).addEditorListener();

  }
}
