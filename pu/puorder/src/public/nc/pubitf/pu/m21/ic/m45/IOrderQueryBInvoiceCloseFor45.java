/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 ����07:27:30
 */
package nc.pubitf.pu.m21.ic.m45;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�����Ƿ�Ʊ�ر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-3 ����07:27:30
 */
public interface IOrderQueryBInvoiceCloseFor45 {

  /**
   * �����������������ݶ������������ѯ��������ġ���Ʊ�رա�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-3 ����07:29:10
   */
  public Map<String, UFBoolean> getBInvoiceCloseMap(String[] bids)
      throws BusinessException;
}
