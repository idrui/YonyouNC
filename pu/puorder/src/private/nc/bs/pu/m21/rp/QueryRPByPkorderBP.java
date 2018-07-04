/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-15 下午05:12:19
 */
package nc.bs.pu.m21.rp;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据订单id查询本订单的所有到货计划VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-15 下午05:12:19
 */
public class QueryRPByPkorderBP {

  public OrderReceivePlanVO[] getAllRPVOs(String pkOrder) {
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);

    OrderReceivePlanVO[] rpVOs =
        query.query(" and pk_order='" + pkOrder + "' and dr=0 ", null);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return null;
    }

    return rpVOs;
  }
}
