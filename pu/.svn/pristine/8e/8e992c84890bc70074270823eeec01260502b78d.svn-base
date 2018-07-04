package nc.vo.pu.m21.exception;

import nc.vo.pu.exception.AskYesNoException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单超最高限价的异常信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-27 下午02:02:57
 */
public class AskMaxPriceException extends AskYesNoException {
  private static final long serialVersionUID = 2145879471683626833L;

  public AskMaxPriceException(String msg) {
    super(msg);
  }

  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.MaxPriceCheck.name();
  }

}
