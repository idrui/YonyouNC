package nc.pubitf.pu.it;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * @since 6.31
 * @version 2013-11-25 ����09:45:35
 * @author mengjian
 */
public interface IInvoiceAutoMatchForIT {
  /**
   * �����������������ڷ�Ʊ�Զ����㡣
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voInvoice ��ƱVO
   * @param settleEnv ���㻷��
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-6-29 ����11:32:32
   */
  public void invoiceAutoMatch4IT(InvoiceVO[] voInvoice)
      throws BusinessException;

}
