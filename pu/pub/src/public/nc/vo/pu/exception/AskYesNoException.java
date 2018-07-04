/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-7 上午09:19:37
 */
package nc.vo.pu.exception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>一般询问使用异常类（当后台或者前台只有一个询问时使用，多个时每种询问使用一个异常类）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-7 上午09:19:37
 */
public class AskYesNoException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = -5183531612340425143L;

  /**
   * AskYesNoException 的构造子
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
