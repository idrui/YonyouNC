/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午11:47:33
 */
package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m21.maintain.rule.ATPUpdateRule;
import nc.bs.pu.m21.maintain.rule.MaintainMPPCtrlChkRule;
import nc.bs.pu.m21.maintain.rule.WriteBackSourceRule;
import nc.bs.pu.m21.maintain.rule.delete.DelHistoryVORule;
import nc.bs.pu.m21.maintain.rule.delete.DelVOValidateRule;
import nc.bs.pu.m21.maintain.rule.delete.DeleteAfterEventRule;
import nc.bs.pu.m21.maintain.rule.delete.DeleteBeforeEventRule;
import nc.bs.pu.m21.maintain.rule.delete.OrderCodeReturnRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单删除BP
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
 * @time 2009-12-30 上午11:47:33
 */
public class OrderDeleteBP {

  private OrderContext ctx;

  public OrderDeleteBP(OrderContext ctx) {
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：订单删除的核心BP。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-30 上午11:52:44
   */
  public void delete(OrderVO[] orderVos) {
    CompareAroundProcesser<OrderVO> processer =
        new CompareAroundProcesser<OrderVO>(OrderPluginPoint.DELETE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(orderVos, orderVos);
    new BillDelete<OrderVO>().delete(orderVos);
    processer.after(null, orderVos);
  }

  private void addAfterRule(CompareAroundProcesser<OrderVO> processer) {
    // 回写上游单据
    processer.addAfterRule(new WriteBackSourceRule(this.ctx));
    // 可用量更新
    processer.addAfterRule(new ATPUpdateRule());
    // 采购计划控制检查
    processer.addAfterRule(new MaintainMPPCtrlChkRule());
    // 更新内部交易信息,V60暂时不实现
    // 单据号回退
    processer.addAfterRule(new OrderCodeReturnRule());
    // 如果有修订历史，删除修订历史
    processer.addAfterRule(new DelHistoryVORule());
    // 删除后事件处理
    processer.addAfterRule(new DeleteAfterEventRule());
    // 生产订单为采购订单提供的回写接口，采购订单删除后调用，为了调出请购订单的ATP序列
    // processer.addAfterRule(new Update55A3StatusRule());
  }

  private void addBeforeRule(CompareAroundProcesser<OrderVO> processer) {
    // VO检查
    processer.addBeforeRule(new DelVOValidateRule());
    // 删除前可用量前处理
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    // 删除前事件处理
    processer.addBeforeRule(new DeleteBeforeEventRule());
  }

}
