package nc.vo.pu.exp;

import uap.iweb.wf.util.IWFAlertExceptionHandler;
import nc.impl.pubapp.env.BSContext;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pu.m25.exception.InvoiceApproveWithFeeException;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public class PuResumeExceptionHandlerForMP implements IWFAlertExceptionHandler {

  /**
   * Y-同意，N-不同意，R-驳回
   */
  public static final String RESULT_Y = "Y";

  /**
   * Y-同意，N-不同意，R-驳回
   */
  public static final String RESULT_N = "N";

  /**
   * Y-同意，N-不同意，R-驳回
   */
  public static final String RESULT_R = "R";

  private BusinessException exp;

  @Override
  public String getAlertMessageByException(BusinessException e)
      throws BusinessException {
    if (this.isIResumeException(e)) {
      this.exp = e;
      return e.getMessage();
    }
    return null;
  }

  @Override
  public void setAlertForcePass(String checkResult, AggregatedValueObject billvo)
      throws BusinessException {
    if (isIResumeException(exp)) {
      // 供应链交互异常的公共方案
      BSContext.getInstance().setSession(
      		InvoiceApproveWithFeeException.class.getName(), UFBoolean.FALSE);
    }
  }

  protected boolean isIResumeException(Exception e) {
    return e instanceof IResumeException;
  }

}
