package nc.impl.pu.report.arrival;

import nc.bs.pu.report.arrive.DayArriveReportBP;
import nc.itf.pu.report.arrival.IDayArriveReport;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * 采购到货-日到货情况查询
 * 数据加工
 * 
 * @since 6.3
 * @version 2012-8-11 下午01:11:17
 * @author fanly3
 */
public class DayArriveReportImpl implements IDayArriveReport {

  @Override
  public String getDayArrvQuerySql(SmartContext context) throws SmartException {
    return new DayArriveReportBP().getDayArrvQuerySql(context);
  }

}
