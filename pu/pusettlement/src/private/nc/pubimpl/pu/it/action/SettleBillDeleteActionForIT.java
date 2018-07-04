package nc.pubimpl.pu.it.action;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.pu.m27.settlebill.rule.WriteM5801AndM5805Rule;
import nc.vo.pu.m27.entity.SettleBillVO;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.DeleteIARule;
import nc.bs.pu.m27.settlebill.rule.DeleteVirtualInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.FeeSettleCheckRule;
import nc.bs.pu.m27.settlebill.rule.InvoiceToAPCheckRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillCodeReturnRule;
import nc.bs.pu.m27.settlebill.rule.UnBackWashEstAPForRBStockRush;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackOrderRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;

/**
 * @since 6.31
 * @version 2013-11-21 ����02:09:22
 * @author mengjian
 */
public class SettleBillDeleteActionForIT {

  /**
   * ɾ�����㵥
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @author wangyf
   * @time 2009-6-29 ����01:49:20
   */
  public void delete(SettleBillVO[] voaBill) {
    if (ArrayUtils.isEmpty(voaBill)) {
      return;
    }
    // ExceptionUtils.wrappBusinessException("stop !");
    BillTransferTool<SettleBillVO> tool =
        new BillTransferTool<SettleBillVO>(voaBill);
    SettleBillVO[] clientVos = tool.getClientFullInfoBill();
    AroundProcesser<SettleBillVO> proc =
        new AroundProcesser<SettleBillVO>(SettlebillPluginPoint.DELETE_IT);
    // ɾ�����ý������(������Ϊ����ǰ���򣬷�����������й������ӱ�����dr=1)
    proc.addBeforeFinalRule(new FeeSettleDeleteActionForIT());
    // �����÷�̯��ȷ���Ƿ��ɾ��
    proc.addBeforeFinalRule(new FeeSettleCheckRule());
    // ����ɾ��ǰ����ɾ��������㵥��
    proc.addBeforeFinalRule(new DeleteIARule());
    // ����ɾ��ǰ��������Ǻ�����ⵥ�Գ岢�һس���ݹ�Ӧ������ȡ���س�
    proc.addBeforeFinalRule(new UnBackWashEstAPForRBStockRush());
    // ����ɾ��ǰ���򣺻�д�ɹ���
    proc.addBeforeFinalRule(new PurchsInSettleUpdateRule(WritebackPoint.DELETE));
    // ����ɾ��ǰ���򣺻�д��Ʊ
    proc.addBeforeFinalRule(new WritebackInvoiceRule(WritebackPoint.DELETE));
    // ����ɾ��ǰ����ɾ�����ⷢƱ����ʱ���ɵ����ⷢƱ
    proc.addBeforeFinalRule(new DeleteVirtualInvoiceRule());
    // ����ɾ��ǰ���򣺻�д����
    proc.addBeforeFinalRule(new WritebackOrderRule(WritebackPoint.DELETE));
    // ����ɾ��ǰ���򣺻�д��ģ����ں�ͬ��������ϸ��
    proc.addBeforeFinalRule(new WriteM5801AndM5805Rule(WritebackPoint.DELETE));
    // ����ɾ��ǰ���򣺷�Ʊ����Ѿ���Ӧ�������Ҳɹ���ⵥҲ����Ӧ����������ɾ��
    proc.addAfterFinalRule(new InvoiceToAPCheckRule());
    // ���ݺŻ��˹���
    proc.addAfterRule(new SettleBillCodeReturnRule());
    proc.before(clientVos);
    new BillDelete<SettleBillVO>().delete(clientVos);

    proc.after(clientVos);
  }

}
