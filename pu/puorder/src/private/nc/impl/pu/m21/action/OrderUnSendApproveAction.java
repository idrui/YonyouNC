package nc.impl.pu.m21.action;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m21.action.rule.approve.UnSendAppoveVOValidateRule;
import nc.impl.pu.m21.action.rule.approve.UnSendApproveAfterEventRule;
import nc.impl.pu.m21.action.rule.approve.UnSendApproveBeforeEventRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>执行采购订单的收回操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-12 下午08:13:24
 */
public class OrderUnSendApproveAction {

  public OrderVO[] unSendApprove(OrderVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<OrderVO> util =
        new PfParameterUtil<OrderVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    OrderVO[] originBills = util.getClientOrignBills();
    OrderVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.UNSAVE);

    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    // 前规则
    processer.before(clientBills);

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procRecallFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 把VO持久化到数据库中
    BillUpdate<OrderVO> update = new BillUpdate<OrderVO>();
    OrderVO[] returnVos = update.update(clientBills, originBills);

    // 后规则
    processer.after(returnVos);
    return returnVos;
  }

  private void addBeforeRule(AroundProcesser<OrderVO> processer) {
    // 单据状态检查
    processer.addBeforeFinalRule(new UnSendAppoveVOValidateRule());
    // 收回前事件处理
    processer.addBeforeRule(new UnSendApproveBeforeEventRule());
  }

  private void addAfterRule(AroundProcesser<OrderVO> processer) {
    // 收回后事件处理
    processer.addAfterRule(new UnSendApproveAfterEventRule());
  }
}
