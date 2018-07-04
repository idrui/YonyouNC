package nc.itf.pu.report.arrival;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * �ɹ�����-�յ��������ѯ
 * 
 * @since 6.3
 * @version 2012-8-21 ����09:29:23
 * @author fanly3
 */
public interface IDayArriveReport {
  /**
   * ��ȡ������SQL
   * 
   * @param context ����ģ�͵������Ķ���
   * @return �����յ������������SQL
   * @throws SmartException ����ģ���쳣
   */
  String getDayArrvQuerySql(SmartContext context) throws SmartException;

}
