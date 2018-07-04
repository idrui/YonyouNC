/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午02:54:12
 */
package nc.pubitf.pu.est.m45;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单直接确认成本和应付的组件服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午02:54:12
 */
public interface IPurchaseInTOCostAP {
  /**
   * 方法功能描述：采购入库单确认成本和应付。
   * <p>
   * <p>
   * 同时确认成本和应付，因此只提供一个方法
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-24 下午02:59:04
   */
  public void confirm(PurchaseInVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：入库单直接暂估成本/应付的组件服务。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-24 下午03:24:39
   */
  public void estimate(PurchaseInVO[] vos) throws BusinessException;

}
