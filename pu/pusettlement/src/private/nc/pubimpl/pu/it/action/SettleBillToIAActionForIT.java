package nc.pubimpl.pu.it.action;

import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.bs.pu.m27.settlebill.rule.ToIAStatusRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubimpl.pu.it.bp.FeeSettleToIABPForIT;
import nc.pubimpl.pu.it.bp.SettleBillToIABPForIT;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.31
 * @version 2013-11-21 ����02:09:32
 * @author mengjian
 */
public class SettleBillToIAActionForIT {
  public SettleBillVO[] toIA(SettleBillVO[] vos) {
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(vos);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    SettleBillVO[] originBills = tool.getOriginBills();
    AroundProcesser<SettleBillVO> processor =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.TO_IA_IT);
    // ���ӱ���ǰ���򣺽��㵥״̬���
    processor.addBeforeFinalRule(new ToIAStatusRule(true));
    this.toIATemp(clientVos);

    processor.before(clientVos);
    BillUpdate<SettleBillVO> update = new BillUpdate<SettleBillVO>();
    SettleBillVO[] savedVos = update.update(clientVos, originBills);

    // ��������򣺷��ý���ʱ��������������
    processor.addAfterFinalRule(new FeeSettleToIABPForIT());
    processor.after(savedVos);

    return tool.getBillForToClient(savedVos);
  }

  private void toIATemp(SettleBillVO[] clientVos) {
    StockInfoUtil util = new StockInfoUtil(clientVos);
    for (SettleBillVO vo : clientVos) {
      SettleEnvironment env =
          new SettleEnvironment(vo.getParentVO().getPk_org(),
              EnumSettleType.IT_SAME_MATERIAL);
      try {
        new SettleBillToIABPForIT(vo, env).submitToIA(util);
      }
      catch (BusinessException e) {
        // ��־�쳣
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
