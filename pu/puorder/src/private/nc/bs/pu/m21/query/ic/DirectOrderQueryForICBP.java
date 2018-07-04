/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 上午09:48:05
 */
package nc.bs.pu.m21.query.ic;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为库存提供直运查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 上午09:48:05
 */
public class DirectOrderQueryForICBP {
  /**
   * 方法功能描述：根据订单行id查询直运订单来源销售订单头和体id
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-11 上午09:48:52
   */
  public MapList<String, String> directQueryForIC(String[] bids) {
    MapList<String, String> mapList = new MapList<String, String>();

    VOQuery<OrderItemVO> query =
        new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
          OrderItemVO.PK_ORDER_B, OrderItemVO.CSOURCEID,
          OrderItemVO.CSOURCEBID, OrderItemVO.CSOURCETYPECODE,
          OrderItemVO.VSOURCETRANTYPE
        });
    OrderItemVO[] itemVOs = query.query(bids);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return mapList;
    }

    for (OrderItemVO itemVO : itemVOs) {
      String pk_order_b = itemVO.getPk_order_b();
      if (StringUtils.isNotBlank(itemVO.getCsourceid())) {
        mapList.put(pk_order_b, itemVO.getCsourceid());
      }
      if (StringUtils.isNotBlank(itemVO.getCsourcebid())) {
        mapList.put(pk_order_b, itemVO.getCsourcebid());
      }
      if (StringUtils.isNotBlank(itemVO.getCsourcetypecode())) {
        mapList.put(pk_order_b, itemVO.getCsourcetypecode());
      }
      if (StringUtils.isNotBlank(itemVO.getVsourcetrantype())) {
        mapList.put(pk_order_b, itemVO.getVsourcetrantype());
      }
    }

    return mapList;
  }
}
