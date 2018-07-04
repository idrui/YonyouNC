/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-28 下午04:26:29
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存订单
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
 * @time 2009-12-28 下午04:26:29
 */
public class OrderSaveAction {

  /**
   * 采购订单后台保存action
   * 
   * @param orderVos 前台全vo
   * @param ctx 订单上下文
   * @return 保存后的全vo
   */
  public OrderVO[] save(OrderVO[] orderVos, OrderContext ctx) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] originVos = tool.getOriginBills();
    return new OrderSaveBP(ctx).save(orderVos, originVos);
  }

}
