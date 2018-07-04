package nc.bs.pu.m27.settlebill;

import nc.bs.pu.m27.feesettle.FeeSettleCancelToIABP;
import nc.bs.pu.m27.feesettle.FeeSettleCancelToPCIABP;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.DeleteIARule;
import nc.bs.pu.m27.settlebill.rule.DeletePCIARule;
import nc.bs.pu.m27.settlebill.rule.FeeSettleCheckRule;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m27.entity.SettleBillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消传存货核算的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-3 下午08:24:25
 */
public class SettleBillCancelIABP {
  public SettleBillVO[] cancelIA(SettleBillVO[] vos) {
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(vos);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    SettleBillVO[] originBills = tool.getOriginBills();
    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.CANCEL_IA);
    // 增加保存前规则：结算单状态检查
    processor.addBeforeFinalRule(new ToIAStatusRule(false));
    // 检查费用分摊，确定是否可取消传存货
    processor.addBeforeFinalRule(new FeeSettleCheckRule());
    // 增加保存前规则：删除存货核算单据
    processor.addAfterRule(new DeleteIARule());
    processor.addAfterRule(new DeletePCIARule());

    // 删除费用结算单前删除其所传递的存货核算单据
    processor.addAfterFinalRule(new FeeSettleCancelToIABP());
    processor.addAfterFinalRule(new FeeSettleCancelToPCIABP());

    processor.before(clientVos);
    clientVos = new BillUpdate<SettleBillVO>().update(clientVos, originBills);
    processor.after(clientVos);

    clientVos = tool.getBillForToClient(clientVos);
    return clientVos;
  }

}
