/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-11 ����10:36:50
 */
package nc.vo.pu.m21.exception;

import nc.vo.pu.exception.AskYesNoException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ݲ��쳣
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-11 ����10:36:50
 */
public class AskNumException extends AskYesNoException {

  private static final long serialVersionUID = 3359042800141277443L;

  /**
   * AskInvoiceNumException �Ĺ�����
   * 
   * @param msg
   */
  public AskNumException(String msg) {
    super(msg);
  }

}
