package nc.ui.pu.report.supplierest;

import nc.ui.ic.icreport.pub.ICRptQueryConditionDLGDelegator;
import nc.ui.pu.report.pub.action.PuReportSubscribeAction;
import nc.ui.pu.report.pub.validator.PuReportDateValidator;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;

/**
 * 供应商暂估余额订阅查询设置
 * 
 * @since 6.0
 * @version 2011-8-2 下午04:49:42
 * @author 田锋涛
 */

public class SupplierEstSubscribeAction extends PuReportSubscribeAction {

  @Override
  protected IQueryConditionDLGInitializer getQueryConditionDLGInitializer() {
    return new SupplierEstQryDlgInitializer();
  }

  /**
   * 初始化查询代理（如：校验类注册）
   */
  @Override
  protected void initQueryConditionDLGDelegator() {
    ICRptQueryConditionDLGDelegator delegator =
        this.getQueryConditionDLGDelegator();
    delegator.registerICriteriaEditorValidator(new PuReportDateValidator(
        SupplierEstQryFieldCode.fperiod.code()));
  }
}
