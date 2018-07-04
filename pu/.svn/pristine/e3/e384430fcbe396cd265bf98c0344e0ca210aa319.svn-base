package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.UnfreezeAfterEventRule;
import nc.bs.pu.m21.maintain.rule.UnfreezeBeforeEventRule;
import nc.bs.pu.m21.maintain.rule.UnfreezeRule;
import nc.bs.pu.m21.maintain.rule.UnfreezeStatusCheckRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class OrderUnfreezeBP {

  /**
   * 订单解冻
   * 
   * @param vos 订单全vo
   * @return 解冻后的订单全vo
   */
  public OrderVO[] unfreeze(OrderVO[] vos) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(vos);
    OrderVO[] origVos = tool.getOriginBills();
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.UNFREEZE);

    this.addRule(processer);

    processer.before(vos);

    // wuxla、杨波、王印芬、陈嘉琦、陈东白，订单冻结需要影响可用量
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);

    OrderVO[] returnVos = new BillUpdate<OrderVO>().update(vos, origVos);
    processer.after(returnVos);

    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);

    return returnVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 增加前规则：冻结状态的检查
    processer.addBeforeRule(new UnfreezeStatusCheckRule());
    // 增加前规则：冻结状态
    processer.addBeforeRule(new UnfreezeRule());
    // 解冻前事件
    processer.addBeforeRule(new UnfreezeBeforeEventRule());
    // 解冻后事件
    processer.addAfterRule(new UnfreezeAfterEventRule());
  }
}
