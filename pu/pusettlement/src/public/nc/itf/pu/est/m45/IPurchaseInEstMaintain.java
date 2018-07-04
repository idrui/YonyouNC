/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 下午06:05:13
 */
package nc.itf.pu.est.m45;

import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估处理操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-20 下午06:05:13
 */
public interface IPurchaseInEstMaintain {
  /**
   * 方法功能描述：采购入库单取消暂估。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-20 下午06:06:54
   */
  public void puchaseInUnEst(PurchaseInEstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException;

  /**
   * 方法功能描述：采购入库单暂估。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-20 下午06:06:39
   */
  public void purchaseInEst(PurchaseInEstVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：采购入库单费用暂估。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午11:06:41
   */
  public void purchaseInFeeEst(PurchaseInEstVO[] vos) throws BusinessException;
}
