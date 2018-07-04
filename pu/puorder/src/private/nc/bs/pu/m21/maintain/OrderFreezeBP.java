package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.FreezeAfterEventRule;
import nc.bs.pu.m21.maintain.rule.FreezeBeforeEventRule;
import nc.bs.pu.m21.maintain.rule.FreezeRule;
import nc.bs.pu.m21.maintain.rule.FreezeStatusCheckRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单冻结
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-21 下午04:55:17
 */
public class OrderFreezeBP {

  /**
   * 订单冻结
   * 
   * @param vos 订单全vo
   * @param freezeReason 冻结原因
   * @return 订单全vo
   */
  public OrderVO[] freeze(OrderVO[] vos, String freezeReason) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(vos);
    OrderVO[] origVos = tool.getOriginBills();
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.FREEZE);

    this.addRule(processer, freezeReason);

    processer.before(vos);

    // wuxla、杨波、王印芬、陈嘉琦、陈东白，订单冻结需要影响可用量
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);

    OrderVO[] returnVos = new BillUpdate<OrderVO>().update(vos, origVos);
    processer.after(returnVos);

    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);

    return returnVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer, String freezeReason) {
    // 增加前规则：冻结状态的检查
    processer.addBeforeRule(new FreezeStatusCheckRule());
    // 增加前规则：冻结状态
    processer.addBeforeRule(new FreezeRule(freezeReason));
    // 冻结前事件
    processer.addBeforeRule(new FreezeBeforeEventRule());
    // 冻结后事件
    processer.addAfterRule(new FreezeAfterEventRule());
  }
}
