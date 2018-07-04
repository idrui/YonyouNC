/**
 * 
 */
package nc.pubitf.pu.m25.pf;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>传应付服务
 * </ul>
 * <p>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午03:40:44
 */
public interface IInvoiceSendAP {

  /**
   * @author xiebo
   * @time 2010-1-26 下午04:15:08
   * @param vos 要传应付的发票VO数组；env 前台传递信息。
   * @return 已正确传应付的发票VO数组。
   * @throws 传应付过程中的异常信息。
   */
  public InvoiceVO[] cancelSendAP(InvoiceVO[] vos, InvoiceUIToBSEnv env)
      throws BusinessException;

  /**
   * @author xiebo
   * @time 2010-1-26 下午04:13:50
   * @param vos 要传应付的发票VO数组；env 前台传递信息。
   * @param pfVo 流程平台的参数VO
   * @return 已正确传应付的发票VO数组。
   * @throws 传应付过程中的异常信息。
   */
  public InvoiceVO[] sendAP(InvoiceVO[] vos, InvoiceUIToBSEnv env,
      PfParameterVO pfVo) throws BusinessException;
}
