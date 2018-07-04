/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午10:41:47
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.maintain.OrderDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单删除动作
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
 * @time 2009-12-30 上午10:41:47
 */
public class OrderDeleteAction {

  /**
   * 方法功能描述：删除指定的订单。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos 全vo
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-30 上午11:01:59
   */
  public void delete(OrderVO[] orderVos, OrderContext ctx) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] orgOrderVos = tool.getOriginBills();
    // 调用BP
    new OrderDeleteBP(ctx).delete(orgOrderVos);
  }

}
