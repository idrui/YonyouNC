package nc.itf.pu.report.supplierstatcomprate;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;

/**
 * �ɹ���������Ӧ��ͳ�ƶ��������
 * 
 * @since 6.3
 * @version 2012-8-21 ����10:22:06
 * @author fanly3
 */
public interface ISupplierStatOrderCompRate {
  /**
   * ����������SQL
   * 
   * @param context ����ģ�������Ķ���
   * @return ����Ӧ��ͳ�ƶ�������ʵ�����SQL
   * @throws SmartException ����ģ���쳣
   */
  String getOrderCompRateSql(SmartContext context) throws SmartException;
}
