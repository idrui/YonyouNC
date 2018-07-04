package nc.ui.pu.report.supplierstatcomprate;

import nc.ui.pu.report.pub.action.PuReportSubscribeAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;

/**
 * 按供应商统计订单完成率-报表订阅条件设置
 * 
 * @since 6.3
 * @version 2012-12-20 上午10:29:48
 * @author fanly3
 */
public class SupplierStatCompRateAction extends PuReportSubscribeAction {

  @Override
  protected IQueryConditionDLGInitializer getQueryConditionDLGInitializer() {
    return new SupplierStatCompRateQryDlgInit();
  }
}
