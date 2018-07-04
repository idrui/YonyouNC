/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 ����04:30:27
 */
package nc.vo.pu.m25.exception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊɾ��ʱ�Ƿ�ͬʱɾ�����÷�Ʊ����ʾ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-20 ����04:30:27
 */
public class InvoiceDelResumeException extends BusinessException implements
    IResumeException {

  /**
   * 
   */
  private static final long serialVersionUID = -3490321076978547122L;

  public InvoiceDelResumeException(String msg) {
    super(msg);
  }

  /**
   * ���෽����д
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return null;
  }

}
