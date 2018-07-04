/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 上午09:32:00
 */
package nc.pubitf.pu.m21.ic.m45;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为库存提供直运接口，查询直运订单来源销售订单头和体id
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 上午09:32:00
 */
public interface IDirectOrderQueryForIC {

  /**
   * 方法功能描述：为库存提供直运接口，根据订单行id查询直运订单来源销售订单头和体id
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   *          采购订单表体id
   * @return
   *         MapList-key：采购订单表体id；value：ArrayList（0-销售订单头id，1-销售订单体id，2-来源单据类型，3
   *         -
   *         来源交易类型）
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-11 上午09:41:34
   */
  public MapList<String, String> directQueryForIC(String[] bids)
      throws BusinessException;
}
