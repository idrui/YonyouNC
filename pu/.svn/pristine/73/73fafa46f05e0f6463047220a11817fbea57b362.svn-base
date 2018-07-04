package nc.ui.pu.m25.report.dlg;

import nc.ui.pu.report.pub.action.PuReportDlgInit;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.totalvo.MarAssistantDealer;
import nc.ui.pubapp.uif2app.query2.totalvo.UserDefPropDealer;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

public class PuInvoiceQryDlgInit extends PuReportDlgInit implements
    IQueryConditionDLGInitializer {

  @Override
  public void initQueryConditionDLG(QueryConditionDLGDelegator condDLGDelegator) {
    condDLGDelegator.addQueryCondVODealer(new MarAssistantDealer());
    // 自定义属性
    condDLGDelegator.addQueryCondVODealer(new UserDefPropDealer(null, this
        .getQueryParams(new InvoiceHeaderVO(), new InvoiceItemVO())));
  }
}
