package nc.bs.pu.it;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m27.match.WithoutInvoiceMatch;
import nc.bs.pu.m27.match.rule.WithoutInvcMatchStockProcRule;
import nc.pubitf.pu.m25.it.IVirtualInvoiceMaintainForIT;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

public class WithoutInvoiceMatchForIT extends WithoutInvoiceMatch {

  @Override
  public SettleBillVO[] matchProcess(StockSettleVO[] voaStock) {
    WithoutInvcMatchStockProcRule stockRule =
        new WithoutInvcMatchStockProcRule(voaStock);

    PurchaseInVO[] purVos = stockRule.getPurchaseInVOs();

    // �������ⷢƱ�����ⷢƱ��Դ�ڽ�����ⵥ����������ⵥ����Ʊ���Զ��������
    // �Ӷ�ʵ�����޷�Ʊ����Ĺ���
    IVirtualInvoiceMaintainForIT invoice =
        NCLocator.getInstance().lookup(IVirtualInvoiceMaintainForIT.class);
    try {
      InvoiceVO[] invoices = null;
      // �ɽ�����ⵥ�������ⷢƱ
      if (!ArrayUtils.isEmpty(purVos)) {
        InvoiceVO[] purInvoices = invoice.genByPurchsIn4IT(purVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, purInvoices);
      }
      // �������ⷢƱVO��ѯ���㵥VO
      return this.querySettleBillVO(invoices);
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return new SettleBillVO[0];
  }

}
