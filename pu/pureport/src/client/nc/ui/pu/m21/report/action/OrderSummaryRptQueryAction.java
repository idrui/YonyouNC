package nc.ui.pu.m21.report.action;

import nc.scmmm.ui.scmpub.report.action.SCMRptAbsQueryAction;
import nc.scmmm.ui.scmpub.report.adapter.ISCMReportQueryUIInitializer;
import nc.ui.pu.m21.report.initializer.OrderSummaryRptUIInitializer;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;

public class OrderSummaryRptQueryAction extends SCMRptAbsQueryAction {

  @Override
  protected void doBusinessProcess(BaseQueryCondition condition,
      IContext context) {
  }

  @Override
  protected ISCMReportQueryUIInitializer getReportInitializer() {
    return new OrderSummaryRptUIInitializer();
  }

}
