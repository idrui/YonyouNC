package nc.itf.pu.report.estdiffer;

import nc.pub.smart.context.SmartContext;

/**
 * �ݹ���������ѯ�ӿ�
 * 
 * @since 6.1
 * @version 2012-8-17 ����03:51:56
 * @author tianft
 */
public interface IEstDifferReport {
  /**
   * ��ȡ�����Ĳ�ѯsql
   * 
   * @param context
   * @return
   */
  String getQuerySql(SmartContext context);
}
