package nc.itf.pu.report.estmothstat;

import nc.pub.smart.context.SmartContext;

/**
 * �ݹ���ͳ�����ݼӹ�
 * 
 * @since 6.0
 * @version 2011-8-22 ����12:00:24
 * @author �����
 */

public interface IEstMonthStatReport {
  /**
   * ��ȡ�����Ĳ�ѯsql
   * 
   * @param context
   * @return
   */
  String getQuerySql(SmartContext context);
}
