/**
 * 
 */
package nc.pubitf.pu.m25.pu.settle;

import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�ɹ������ṩ�Ļ�д����</li>
 * </ul>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 ����03:32:22
 */
public interface IInvoiceSettleWriteBack {

  /**
   * @author xiebo
   * @time 2010-1-26 ����04:12:52
   * @param wbVos ������֯�Ļ�дVO���顣
   * @return void��
   * @throws ��д�����е��쳣��Ϣ��
   */
  public void writeBack(InvoiceWriteBackVO[] wbVos) throws BusinessException;
}
