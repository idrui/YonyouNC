package nc.pubitf.pu.m21.dm.m4804;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 运输关闭
 * 
 * @since 6.0
 * @version 2011-5-25 下午06:42:32
 * @author wuxla
 */

public interface I21DeliveryStatusOperater {
  /**
   * @param bids 订单表体主键
   * @param bb1ids 订单到货计划主键
   * @param status 状态，UFBoolean.TRUE：关闭，UFBoolean.FALSE：打开
   * @throws BusinessException
   */
  void setTransStatus(String[] bids, String[] bb1ids, UFBoolean status)
      throws BusinessException;
}
