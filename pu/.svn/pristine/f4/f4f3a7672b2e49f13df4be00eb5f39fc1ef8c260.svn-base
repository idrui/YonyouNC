package nc.vo.pu.pub.exception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.BusinessCheck;

public class PUBudgetControlCheckException extends BusinessException implements
    IResumeException {

  private static final long serialVersionUID = 6817149306711915734L;

  public PUBudgetControlCheckException(String s) {
    super(s);
  }

  public PUBudgetControlCheckException(String s, String errorCode) {
    super(s, errorCode);
  }

  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.BudgetControlCheck.getCheckCode();
  }
}
