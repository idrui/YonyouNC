package nc.bs.pu.m21.writeback.pu.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-10-25 ÏÂÎç01:26:21
 * @author wuxla
 */

public class StoreOpenFor4TRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    List<OrderViewVO> list = new ArrayList<OrderViewVO>();
    for (OrderViewVO vo : vos) {
      UFBoolean bstockclose = vo.getBstockclose();
      if (UFBoolean.TRUE.equals(bstockclose)) {
        continue;
      }
      UFBoolean barrvclose = vo.getBarriveclose();
      if (UFBoolean.TRUE.equals(barrvclose)) {
        vo.setBarriveclose(UFBoolean.FALSE);
        list.add(vo);
      }
    }
    if (list.size() == 0) {
      return;
    }
    OrderViewVO[] views = list.toArray(new OrderViewVO[list.size()]);
    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    bo.update(views, OrderItemVO.class, new String[] {
      OrderItemVO.BARRIVECLOSE
    });
    // List<String> bidlist = new ArrayList<String>();
    // for (OrderViewVO vo : vos) {
    // bidlist.add(vo.getPk_order_b());
    // }
    // IDQueryBuilder builder = new IDQueryBuilder();
    // String cond =
    // builder.buildSQL(OrderItemVO.PK_ORDER_B,
    // bidlist.toArray(new String[bidlist.size()]));
    // DataAccessUtils util = new DataAccessUtils();
    // SqlBuilder sql = new SqlBuilder();
    // sql.append("update po_order_b  set ");
    // sql.append(OrderItemVO.BARRIVECLOSE, UFBoolean.FALSE);
    // sql.append(" where ");
    // sql.append(cond);
    // sql.append(" and " + OrderItemVO.BSTOCKCLOSE, UFBoolean.FALSE);
    // util.update(sql.toString());
  }

}
