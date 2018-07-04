package nc.vo.pu.m23.exception;

import nc.vo.pu.exception.AskYesNoException;

public class AskWithCheckException extends AskYesNoException {

  private static final long serialVersionUID = -7243165233332981100L;

  public AskWithCheckException(String msg) {
    super(msg);
  }

}
