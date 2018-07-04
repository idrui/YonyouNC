package nc.impl.pu.report.arrival;

import nc.bs.pu.report.arrive.DayArriveReportBP;
import nc.itf.pu.report.arrival.IDayArriveReport;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * �ɹ�����-�յ��������ѯ
 * ���ݼӹ�
 * 
 * @since 6.3
 * @version 2012-8-11 ����01:11:17
 * @author fanly3
 */
public class DayArriveReportImpl implements IDayArriveReport {

  @Override
  public String getDayArrvQuerySql(SmartContext context) throws SmartException {
    return new DayArriveReportBP().getDayArrvQuerySql(context);
  }

}
