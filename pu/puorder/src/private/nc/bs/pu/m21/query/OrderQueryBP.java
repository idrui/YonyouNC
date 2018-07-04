/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 下午02:09:42
 */
package nc.bs.pu.m21.query;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单相关查询
 * <li>
 * <li>
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-30 下午02:09:42
 */
public class OrderQueryBP {

  public OrderVO[] query(String[] ids, UFBoolean isLazy) {

    if (isLazy.booleanValue()) {
      // return new BillLazyQuery<OrderVO>(OrderVO.class).query(ids);
      return new BillQuery<OrderVO>(OrderVO.class).query(ids);
    }

    return new BillQuery<OrderVO>(OrderVO.class).query(ids);
  }
}
