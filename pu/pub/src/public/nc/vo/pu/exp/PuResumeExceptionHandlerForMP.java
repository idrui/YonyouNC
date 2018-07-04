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
   * Y-ͬ�⣬N-��ͬ�⣬R-����
   */
  public static final String RESULT_Y = "Y";

  /**
   * Y-ͬ�⣬N-��ͬ�⣬R-����
   */
  public static final String RESULT_N = "N";

  /**
   * Y-ͬ�⣬N-��ͬ�⣬R-����
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
      // ��Ӧ�������쳣�Ĺ�������
      BSContext.getInstance().setSession(
      		InvoiceApproveWithFeeException.class.getName(), UFBoolean.FALSE);
    }
  }

  protected boolean isIResumeException(Exception e) {
    return e instanceof IResumeException;
  }

}
