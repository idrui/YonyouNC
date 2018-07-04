package nc.bs.pu.m27.settlebill;

import nc.bs.pu.m27.feesettle.FeeSettleToIABP;
import nc.bs.pu.m27.feesettle.FeeSettleToPCIABP;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>传存货的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-3 下午08:25:02
 */
public class SettleBillToIABP {
  public SettleBillVO[] toIA(SettleBillVO[] vos) {
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(vos);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    SettleBillVO[] originBills = tool.getOriginBills();
    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.TO_IA);
    // 增加保存前规则：结算单状态检查
    processor.addBeforeFinalRule(new ToIAStatusRule(true));
    this.toIATemp(clientVos);

    processor.before(clientVos);
    BillUpdate<SettleBillVO> update = new BillUpdate<SettleBillVO>();
    SettleBillVO[] savedVos = update.update(clientVos, originBills);

    // 操作后规则：费用结算时传存货核算规则类
    processor.addAfterFinalRule(new FeeSettleToIABP());
    processor.addAfterFinalRule(new FeeSettleToPCIABP());
    processor.after(savedVos);

    return tool.getBillForToClient(savedVos);
  }

  private void toIATemp(SettleBillVO[] clientVos) {
    StockInfoUtil util = new StockInfoUtil(clientVos);
    for (SettleBillVO vo : clientVos) {
      SettleEnvironment env =
          new SettleEnvironment(vo.getParentVO().getPk_org(),
              EnumSettleType.SAME_MATERIAL);
      try {
        new nc.impl.pu.m27.bp.SettleBillToIABP(vo, env).submitToIA(util);
        new nc.impl.pu.m27.bp.SettleBillToPCIABP(vo, env).submitToPCIA(util);
      }
      catch (BusinessException e) {
        // 日志异常
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
