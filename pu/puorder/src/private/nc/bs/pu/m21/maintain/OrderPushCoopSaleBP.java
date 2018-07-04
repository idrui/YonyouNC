package nc.bs.pu.m21.maintain;

import nc.bs.pu.m21.maintain.rule.CoopDiffPsfinanceOrgRule;
import nc.bs.pu.m21.maintain.rule.CooptosoRule;
import nc.bs.pu.m21.maintain.rule.PushCoopStatusChkRule;
import nc.bs.pu.m21.maintain.rule.SoCoopToOrderRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.so.M30SOServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.AggVOUtil;

/**
 * @since 6.0
 * @version 2011-3-28 ÏÂÎç06:07:44
 * @author wuxla
 */

public class OrderPushCoopSaleBP {
  public OrderVO[] pushCoop30(OrderVO[] vos) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(vos);
    OrderVO[] clientVOs = tool.getClientFullInfoBill();
    // OrderVO[] originVOs = tool.getOriginBills();

    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PUSHCOOP30);
    this.addRule(processer);
    processer.before(clientVOs);

    M30SOServices.push21To30(clientVOs);
    String[] hids = AggVOUtil.getPrimaryKeys(clientVOs);
    OrderVO[] returnVos = new BillQuery<OrderVO>(OrderVO.class).query(hids);
    // processer.after(returnVos);

    return tool.getBillForToClient(returnVos);
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    processer.addBeforeRule(new PushCoopStatusChkRule());
    processer.addBeforeRule(new CooptosoRule());
    processer.addBeforeRule(new SoCoopToOrderRule());
    processer.addBeforeRule(new CoopDiffPsfinanceOrgRule());
  }
}
