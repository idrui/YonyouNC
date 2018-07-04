/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 上午10:24:49
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单关闭操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-12 上午10:24:49
 */
public interface IOrderClose {

  /**
   * 方法功能描述：订单分状态关闭操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 订单轻量级vo
   * @param closeType
   * @param isAllItems是否整单
   * @return 订单轻量级vo
   * @throws BusinessException
   *           <p>
   * @see nc.vo.pu.m21.enumeration.EnumCloseFlag
   * @since 6.0
   * @author wuxla
   * @time 2010-4-12 上午11:14:48
   */
  public OrderVO[] close(OrderVO[] vos, int closeType, boolean isAllItems)
      throws BusinessException;

  /**
   * 方法功能描述：执行订单分状态打开操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 订单轻量级vo
   * @param openType 打开类型
   * @param isAllItems 是否整单
   * @return 轻量级vo
   * @throws BusinessException
   *           <p>
   * @see nc.vo.pu.m21.enumeration.EnumCloseFlag
   * @since 6.0
   * @author wuxla
   * @time 2010-4-12 上午11:15:45
   */
  public OrderVO[] open(OrderVO[] vos, int openType, boolean isAllItems)
      throws BusinessException;

}
