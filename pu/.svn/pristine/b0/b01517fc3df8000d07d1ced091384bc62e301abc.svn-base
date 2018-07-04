package nc.ui.pu.journal.dlg;

import nc.ui.pu.journal.action.JournalQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.scmpub.query.refregion.QCustomerFilter;
import nc.ui.scmpub.query.refregion.QDeptFilter;
import nc.ui.scmpub.query.refregion.QProjectFilter;
import nc.ui.scmpub.query.refregion.QPsndocFilter;
import nc.ui.scmpub.query.refregion.QSupplierFilter;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.report.queryinfo.journal.JournalConstant;
import nc.vo.pub.lang.UFBoolean;

/**
 * 综合日报查询过滤
 * 
 * @since 6.0
 * @version 2011-9-7 上午10:20:10
 * @author zhaoyha
 */
public class JournalQueryDlgInit implements IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    // 参照过滤
    this.refFilter(condDLGDelegator);
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    condDLGDelegator.getQueryConditionDLG().setVisibleAdvancePanel(false);
  }

  private void refFilter(QueryConditionDLGDelegator condDLGDelegator) {
    // 采购部门，参照采购组织下的部门档案输入
    QDeptFilter deptFilter =
        QDeptFilter.createQDeptFilterOfPU(condDLGDelegator,
            OrderHeaderVO.PK_DEPT);
    deptFilter.setPk_orgCode(JournalQueryAction.mainOrg);
    deptFilter.setbUsedflag(UFBoolean.TRUE);
    deptFilter.addEditorListener();
    // 采购员，参照采购组织下的人员档案输入
    QPsndocFilter psnFilter =
        QPsndocFilter.createQPsndocFilterOfPU(condDLGDelegator,
            JournalQueryAction.CEMPLOYEEID);
    psnFilter.setPk_orgCode(JournalQueryAction.mainOrg);
    psnFilter.addEditorListener();
    // 供应商编码
    QSupplierFilter supFilter =
        new QSupplierFilter(condDLGDelegator, "bd_supplier.code");
    supFilter.setPk_orgCode(JournalQueryAction.mainOrg);
    supFilter.addEditorListener();
    // 供应商名称
    supFilter = new QSupplierFilter(condDLGDelegator, "bd_supplier.name");
    supFilter.setPk_orgCode(JournalQueryAction.mainOrg);
    supFilter.addEditorListener();
    // 供应商地区分类（应该是基本档案有变化，不需要过滤）
    // 项目（应该是基本档案有变化）
    new QProjectFilter(condDLGDelegator, JournalQueryAction.mainOrg,
        JournalConstant.CPROJECTID).addEditorListener();
    // 按主组织过滤客户
    new QCustomerFilter(condDLGDelegator, PuAttrNameEnum.casscustid.name())
        .addEditorListener();
  }

}
