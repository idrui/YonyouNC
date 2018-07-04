/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午01:07:00
 */
package nc.pubitf.pu.est.m45;

import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询采购入库单的暂估信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-16 下午01:07:00
 */
public interface IPurchaseInEstPubQuery {
  /**
   * 方法功能描述：查询采购入库单的暂估信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids 表体ID数组
   * @return 所有数据都包括，中要符合条件的(包括未暂估过的数据)
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-16 下午01:08:32
   */
  public PurchaseInEstVO[] query(String[] bids) throws BusinessException;
}
