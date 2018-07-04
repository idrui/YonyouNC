/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 下午07:32:34
 */
package nc.bs.pu.m21.query;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据订单表体主键查询开票关闭属性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-3 下午07:32:34
 */
public class OrderQueryBInvoiceCloseBP {

  /**
   * 方法功能描述：根据订单表体主键查询开票关闭属性
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-3 下午07:34:09
   */
  public Map<String, UFBoolean> getBInvoiceCloseMap(String[] bids) {
    if (ArrayUtils.isEmpty(bids)) {
      return new HashMap<String, UFBoolean>();
    }

    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.BINVOICECLOSE
        });

    OrderItemVO[] itemVOs = query.query(bids);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return new HashMap<String, UFBoolean>();
    }

    Map<String, UFBoolean> retMap = new HashMap<String, UFBoolean>();
    for (OrderItemVO itemVO : itemVOs) {
      UFBoolean binvoiceclose = itemVO.getBinvoiceclose();
      if (null == binvoiceclose) {
        retMap.put(itemVO.getPk_order_b(), UFBoolean.FALSE);
      }
      else {
        retMap.put(itemVO.getPk_order_b(), binvoiceclose);
      }
    }

    return retMap;
  }
}
