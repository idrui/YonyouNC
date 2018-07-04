/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午02:24:14
 */
package nc.itf.pu.m25;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票审批操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-26 下午02:24:14
 */
public interface IInvoiceApprove {
  /**
   * 方法功能描述：发票审批操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-26 下午02:26:05
   */
  public InvoiceVO[] approve(InvoiceVO[] vos, AbstractCompiler2 script,
      InvoiceUIToBSEnv[] envs) throws BusinessException;

  /**
   * 方法功能描述：采购发票送审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-28 上午08:38:17
   */
  public InvoiceVO[] sendapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：发票弃审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-26 下午02:26:28
   */
  public InvoiceVO[] unapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：采购发票收回操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-28 上午08:38:17
   */
  public InvoiceVO[] unSendapprove(InvoiceVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
