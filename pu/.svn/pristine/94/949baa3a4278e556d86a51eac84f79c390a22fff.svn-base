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

    // 生成虚拟发票，虚拟发票来源于进口入库单，将会走入库单到发票的自动结算过程
    // 从而实现了无发票结算的过程
    IVirtualInvoiceMaintainForIT invoice =
        NCLocator.getInstance().lookup(IVirtualInvoiceMaintainForIT.class);
    try {
      InvoiceVO[] invoices = null;
      // 由进口入库单生成虚拟发票
      if (!ArrayUtils.isEmpty(purVos)) {
        InvoiceVO[] purInvoices = invoice.genByPurchsIn4IT(purVos);
        invoices = (InvoiceVO[]) ArrayUtils.addAll(invoices, purInvoices);
      }
      // 根据虚拟发票VO查询结算单VO
      return this.querySettleBillVO(invoices);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return new SettleBillVO[0];
  }

}
