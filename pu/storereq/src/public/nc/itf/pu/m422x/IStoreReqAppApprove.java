/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午08:57:43
 */
package nc.itf.pu.m422x;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单审批操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 下午08:57:43
 */
public interface IStoreReqAppApprove {
  /**
   * 方法功能描述：物资需求申请单的审批操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午08:59:54
   */
  public StoreReqAppVO[] approve(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：物资需求申请单的送审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午09:00:04
   */
  public StoreReqAppVO[] sendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException;

  /**
   * 方法功能描述：物资需求申请单的弃审操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午08:59:59
   */
  public StoreReqAppVO[] unapprove(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：物资需求申请单的收回操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午09:00:04
   */
  public StoreReqAppVO[] unSendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException;
}
