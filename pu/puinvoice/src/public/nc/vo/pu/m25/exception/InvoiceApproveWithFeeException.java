/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-26 下午03:45:51
 */
package nc.vo.pu.m25.exception;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票审批时同时审批费用发票处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-26 下午03:45:51
 */
public class InvoiceApproveWithFeeException extends BusinessException implements
    IResumeException {

  /**
   * 
   */
  private static final long serialVersionUID = -5161365541906606618L;

  /**
   * InvoiceApproveWithFeeException 的构造子
   * 
   * @param s
   */
  public InvoiceApproveWithFeeException(String s) {
    super(s);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pubapp.pub.exception.IResumeException#getBusiExceptionType()
   */
  @Override
  public String getBusiExceptionType() {
    return null;
  }

}
