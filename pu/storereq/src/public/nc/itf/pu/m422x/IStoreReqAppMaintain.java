/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午08:54:43
 */
package nc.itf.pu.m422x;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请维护
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 下午08:54:43
 */
public interface IStoreReqAppMaintain {

  /**
   * 方法功能描述：删除
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午08:56:46
   */
  public void delete(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 下午08:56:01
   */
  public StoreReqAppVO[] save(StoreReqAppVO[] vos) throws BusinessException;
}
