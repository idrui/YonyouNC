/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-7 ����09:19:37
 */
package nc.vo.pu.exception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>һ��ѯ��ʹ���쳣�ࣨ����̨����ǰֻ̨��һ��ѯ��ʱʹ�ã����ʱÿ��ѯ��ʹ��һ���쳣�ࣩ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-7 ����09:19:37
 */
public class AskYesNoException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = -5183531612340425143L;

  /**
   * AskYesNoException �Ĺ�����
   * 
   * @param string
   */
  public AskYesNoException(String msg) {
    super(msg);
  }

  @Override
  public String getBusiExceptionType() {
    return null;
  }

}
