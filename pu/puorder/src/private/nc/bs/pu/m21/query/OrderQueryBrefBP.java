/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-5 下午04:52:21
 */
package nc.bs.pu.m21.query;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补货订单参照退库单，退货单查询语句需要拼接的语句。
 * <li>采购订单表头属性“退货/库是否基于原订单补货”，如果为是，
 * <li>则基于该采购订单生成的退货单或者退库单保存时按照负数回写对应订单的累计到货数量、累计入库数量，
 * <li>并在补货订单节点参照查询退货单、退库单时此类退货单、退库单查询不出来
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-5 下午04:52:21
 */
public class OrderQueryBrefBP {

  /**
   * 方法功能描述：根据订单主键数组查询订单的“退货/库基于原订单补货”属性
   * <p>
   * <b>参数说明</b>
   * 
   * @param ids
   * @return key:订单主键 value:订单属性
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-5 下午04:42:03
   */
  public Map<String, UFBoolean> getBrefwhenreturnMap(String[] ids) {
    if (ArrayUtils.isEmpty(ids)) {
      return null;
    }

    VOQuery<OrderHeaderVO> query =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, new String[] {
          OrderHeaderVO.PK_ORDER, OrderHeaderVO.BREFWHENRETURN
        });

    OrderHeaderVO[] headerVOs = query.query(ids);
    if (ArrayUtils.isEmpty(headerVOs)) {
      return null;
    }

    Map<String, UFBoolean> retMap = new HashMap<String, UFBoolean>();
    for (OrderHeaderVO headerVO : headerVOs) {
      retMap.put(headerVO.getPk_order(), headerVO.getBrefwhenreturn());
    }
    return retMap;
  }
}
