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
 * @version 2013-11-21 ����02:09:45
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
    // ���ӱ���ǰ���򣺽��㵥״̬���
    processor.addBeforeFinalRule(new ToIAStatusRule(false));
    // �����÷�̯��ȷ���Ƿ��ȡ�������
    processor.addBeforeFinalRule(new FeeSettleCheckRule());
    // ���ӱ���ǰ����ɾ��������㵥��
    processor.addAfterRule(new DeleteIARule());

    // ɾ�����ý��㵥ǰɾ���������ݵĴ�����㵥��
    processor.addAfterFinalRule(new FeeSettleCancelToIAActionForIT());

    processor.before(clientVos);
    clientVos = new BillUpdate<SettleBillVO>().update(clientVos, originBills);
    processor.after(clientVos);

    clientVos = tool.getBillForToClient(clientVos);
    return clientVos;
  }

}
