/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:35:52
 */
package nc.itf.pu.m21;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单审批操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:35:52
 */
public interface IOrderApprove {

  /**
   * 方法功能描述：订单的审批操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 下午03:37:31
   */
  public OrderVO[] approve(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：订单的送审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 下午03:38:58
   */
  public OrderVO[] sendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：订单的弃审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 下午03:38:13
   */
  public OrderVO[] unapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：订单的收回操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 下午03:38:58
   */
  public OrderVO[] unSendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
