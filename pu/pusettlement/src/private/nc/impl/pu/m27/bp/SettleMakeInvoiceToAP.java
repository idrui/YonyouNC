// Source file:
// F:\\Work\\版本\\V6.0\\设计\\Rose\\aa\\purchase\\ccclass\\settle\\settlebill\\BP\\nc.impl.pu.m27.bp\\SettleMakeInvoiceToAPBP.java

package nc.impl.pu.m27.bp;

import java.util.LinkedHashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleSendAP;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SettleMakeInvoiceToAP {
  private SettleBillVO[] voaBill;

  /**
   * @roseuid 4A484E300303
   */
  public SettleMakeInvoiceToAP(SettleBillVO[] voaBill) {
    this.voaBill = voaBill;
  }

  /**
   * @roseuid 4A12708900DF
   */
  public void makeInvoiceToAP() {
    // 应付未启用，返回
    if (!SysInitGroupQuery.isAPEnabled()) {
      return;
    }
    // 如果该采购入库单与采购发票进行自动结算，则对应的采购发票传应付时回冲对应采购入库单的暂估应付。
    // 如果采购入库单已确认应付和成本（即对应的采购入库单配置了确认应付和成本的组件）,
    // 结算后，对应的采购发票传应付时按照“采购发票行的价税合计－采购入库单行价税合计”的差额生成应付单。
    this.invoiceAutoSendAP();
  }

  /**
   * 方法功能描述：发票传应付
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-7-9 上午11:21:58
   */
  private void invoiceAutoSendAP() {
    Set<String> invids = new LinkedHashSet<String>();
    for (SettleBillVO bill : this.voaBill) {
      SettleBillItemVO[] items = bill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        // 异物料结算不传应付
        if (!EnumMatchRowType.InvoiceInDiffMatch.value().equals(
            item.getFrowtype())
            && item.getPk_invoice() != null) {
          invids.add(item.getPk_invoice());
        }
      }
    }

    if (invids.size() > 0) {
      IInvoiceSettleSendAP service =
          NCLocator.getInstance().lookup(IInvoiceSettleSendAP.class);
      try {
        service.autoSendAP(invids.toArray(new String[invids.size()]));
      }
      catch (BusinessException e) {
        // 日志异常
        ExceptionUtils.wrappException(e);
      }
    }
  }
}
