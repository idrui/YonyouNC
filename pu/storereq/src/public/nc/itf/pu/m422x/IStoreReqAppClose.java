/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 上午08:08:01
 */
package nc.itf.pu.m422x;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 上午08:08:01
 */
public interface IStoreReqAppClose {

  /**
   * 方法功能描述：整单关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 上午08:13:18
   */
  public StoreReqAppVO[] billClose(StoreReqAppVO[] vos)
      throws BusinessException;

  /**
   * 方法功能描述：整单打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 上午09:30:18
   */
  public StoreReqAppVO[] billOpen(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：行关闭/打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 上午08:13:56
   */
  public StoreReqAppVO[] rowClose(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：行打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 上午09:30:22
   */
  public StoreReqAppVO[] rowOpen(StoreReqAppVO[] vos) throws BusinessException;
}
