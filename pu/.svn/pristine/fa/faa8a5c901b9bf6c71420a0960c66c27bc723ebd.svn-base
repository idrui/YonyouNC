/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 上午09:43:31
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m21.action.rule.approve.SendAppoveVOValidateRule;
import nc.impl.pu.m21.action.rule.approve.SendApproveAfterRule;
import nc.impl.pu.m21.action.rule.approve.SendApproveBeforeEventRule;
import nc.impl.pu.m21.action.rule.approve.SendApproveFlowCheckRule;
import nc.impl.pu.m21.action.rule.approve.SendApproveStatusChangeRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的送审操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 上午09:43:31
 */
public class OrderSendApproveAction {

  public OrderVO[] sendApprove(OrderVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<OrderVO> util =
        new PfParameterUtil<OrderVO>(script.getPfParameterVO(), vos);
    OrderVO[] originBills = util.getClientOrignBills();
    OrderVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<OrderVO> processor =
        new AroundProcesser<OrderVO>(OrderPluginPoint.SEND_APPROVE);
    // 添加规则
    this.addRule(processor);
    // 执行持久化前规则
    processor.before(clientBills);
    // 数据持久化
    OrderVO[] returnVos =
        new BillUpdate<OrderVO>().update(clientBills, originBills);
    // 执行持久化后规则
    processor.after(returnVos);

    return returnVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 添加持久化前规则
    processer.addBeforeFinalRule(new SendApproveFlowCheckRule());
    // 单据状态检查规则
    processer.addBeforeFinalRule(new SendAppoveVOValidateRule());
    // 更新单据状态规则
    processer.addBeforeFinalRule(new SendApproveStatusChangeRule());
    // 送审前事件处理
    processer.addBeforeRule(new SendApproveBeforeEventRule());
    processer.addAfterRule(new SendApproveAfterRule());
  }
}
