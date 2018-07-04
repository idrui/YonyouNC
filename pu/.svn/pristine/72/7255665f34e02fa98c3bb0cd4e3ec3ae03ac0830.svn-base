package nc.pubimpl.pu.it.action;

import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.DeleteIARule;
import nc.bs.pu.m27.settlebill.rule.FeeSettleCheckRule;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m27.entity.SettleBillVO;

/**
 * @since 6.31
 * @version 2013-11-21 下午02:09:45
 * @author mengjian
 */
public class SettleBillCancelIAActionForIT {
  public SettleBillVO[] cancelIA(SettleBillVO[] vos) {
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(vos);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    SettleBillVO[] originBills = tool.getOriginBills();
    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.CANCEL_IA_IT);
    // 增加保存前规则：结算单状态检查
    processor.addBeforeFinalRule(new ToIAStatusRule(false));
    // 检查费用分摊，确定是否可取消传存货
    processor.addBeforeFinalRule(new FeeSettleCheckRule());
    // 增加保存前规则：删除存货核算单据
    processor.addAfterRule(new DeleteIARule());

    // 删除费用结算单前删除其所传递的存货核算单据
    processor.addAfterFinalRule(new FeeSettleCancelToIAActionForIT());

    processor.before(clientVos);
    clientVos = new BillUpdate<SettleBillVO>().update(clientVos, originBills);
    processor.after(clientVos);

    clientVos = tool.getBillForToClient(clientVos);
    return clientVos;
  }

}
