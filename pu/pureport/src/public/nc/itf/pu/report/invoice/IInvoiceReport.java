package nc.itf.pu.report.invoice;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;
import nc.vo.pub.BusinessException;

/**
 * �ɹ���Ʊ����ѯ����������
 * 
 * @since 6.0
 * @version 2011-9-14 ����3:08:32
 * @author zhaoyha
 */
public interface IInvoiceReport {

  /**
   * ��Ʊ��ϸ��ѯ�����ɲ�ѯ��Ʊ���������Ϣ��SQLƬ��
   * 
   * @param context
   * @return
   * @throws SmartException
   */
  String getInvoiceDetailAPVerifySql(SmartContext context)
      throws BusinessException;

  /**
   * ��Ʊ��ϸ��ѯ�����ɲ�ѯ��Ʊ��Դ��Ϣ��SQLƬ��
   * 
   * @param context ������Ʊ��ѯ����������Ϣ
   * @return
   * @throws SmartException
   */
  String getInvoiceDetailSourceSql(SmartContext context)
      throws BusinessException;

}
