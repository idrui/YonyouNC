/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 下午02:02:23
 */
package nc.itf.pu.m20;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发布到电子商务
 * <li>取消发布到电子商务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-27 下午02:02:23
 */
public interface IPraybillPublishToEC {

  /**
   * 方法功能描述：发布到电子商务。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要发布的请购单
   * @return 发布后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 下午02:04:18
   */
  PraybillVO[] publishToEC(PraybillVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：取消发布到电子商务。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要取消发布的请购单
   * @return 取消发布后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 下午02:04:18
   */
  PraybillVO[] unPublishToEC(PraybillVO[] vos) throws BusinessException;
}
