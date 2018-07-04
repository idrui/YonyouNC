/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-7 ����03:06:34
 */
package nc.bs.pu.m25.maintain;

import nc.bs.pu.m25.maintain.rule.freeze.InvoiceFreezeInfoFillRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.m25.entity.InvoiceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ��̨���ᶯ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-7 ����03:06:34
 */
public class InvoiceFreezeBP {
  public InvoiceVO[] freeze(InvoiceVO[] invoiceVOs, InvoiceVO[] orgVos) {
    InvoiceVO[] vos = invoiceVOs;
    // BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
    // vos = tool.getClientFullInfoBill();
    // InvoiceVO[] orgVos = tool.getOriginBills();
    CompareAroundProcesser<InvoiceVO> processer =
        new CompareAroundProcesser<InvoiceVO>(null);
    this.addRule(processer);
    processer.before(vos, orgVos);
    vos = new BillUpdate<InvoiceVO>().update(vos, orgVos);
    processer.after(vos, orgVos);
    // vos = tool.getBillForToClient(vos);
    return vos;
  }

  private void addRule(CompareAroundProcesser<InvoiceVO> processer) {
    processer.addBeforeRule(new InvoiceFreezeInfoFillRule());// ��Ҫ��Ϣ���

  }
}
