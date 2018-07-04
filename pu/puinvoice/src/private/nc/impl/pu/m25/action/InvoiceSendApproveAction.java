/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 ����08:36:20
 */
package nc.impl.pu.m25.action;

import nc.impl.pu.m25.action.rule.sendapprove.ApproveFlowCheckRule;
import nc.impl.pu.m25.action.rule.sendapprove.InvoiceStateChgRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.rule.pf.SendApproveStatusChkRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-28 ����08:36:20
 */
public class InvoiceSendApproveAction {
  public InvoiceVO[] sendapprove(InvoiceVO[] invoiceVOs) {
    InvoiceVO[] vos = invoiceVOs;
    BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos); // ����
    // tool.getClientFullInfoBill()�����¿�¡��vo����ƽ̨paramvo���е�vo��ͬ��ƽ̨���µ���paramvo��vo
    // Ӧ��ʹ��ƽ̨��vo
    // ���ܳ��ֵ�������ύ���������ύ�Ѿ�������ts������ʱ���²�ѯ������ts�Ѿ��ı䡣
    // ����ƽ̨���е�vo��ts���Ǿ�ֵ���ͻᵼ������У�鲢��ʱ����
    // vos = tool.getClientFullInfoBill();
    InvoiceVO[] orgVos = tool.getOriginBills();
    AroundProcesser<InvoiceVO> processer = new AroundProcesser<InvoiceVO>(null);
    this.addRule(processer);
    processer.before(vos);
    InvoiceVO[] updatedVos = new BillUpdate<InvoiceVO>().update(vos, orgVos);
    return tool.getBillForToClient(updatedVos);
  }

  private void addRule(AroundProcesser<InvoiceVO> processer) {
    processer.addBeforeFinalRule(new ApproveFlowCheckRule());// �Ƿ��������
    processer.addBeforeFinalRule(new SendApproveStatusChkRule<InvoiceVO>());
    processer.addBeforeFinalRule(new InvoiceStateChgRule()); // �����Լ�ά������״̬
  }

}
