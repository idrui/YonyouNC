package nc.ui.pu.m27.match.editor.list;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyRowChangedEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算结果页面的表体行切换事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-1 下午09:51:06
 */
public class SettleBillBodyRowChangeEventHandler implements
    IAppEventHandler<ListBodyRowChangedEvent> {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListBodyRowChangedEvent e) {
    BillScrollPane panel = e.getBillListPanel().getParentListPanel();
    // 备份并清除原有的表头的换行变化事件（否则会引起死循环）
    BillEditListener l = panel.getEditListener();
    panel.removeEditListener();

    // 选中相关的发票行
    this.selectRelatedInvoices(e);

    // lixyp 2012.4.23
    // 在消耗汇总暂估的结果页面，点击表头（或表体）的时候，应该联动选中表体（或表头）。
    // 这时应触发两个事件，将两个联查按钮启用。
    // 但第一行（只有第一行有问题，第二行开始往后都是正常的）不知何故只触发了一次。
    // 根据有经验的同事建议，添加下面这条语句，手工触发。
    this.getModel().fireEvent(new AppEvent(""));

    // 还原表头的换行变化事件
    panel.addEditListener(l);
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

  private Set<String> getRelatedInvoices(String pk_stock_b, SettleBillVO[] bills) {
    Set<String> invoices = new HashSet<String>();
    for (SettleBillVO bill : bills) {
      SettleBillItemVO[] items = bill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_invoice_b = item.getPk_invoice_b();
        Integer rowType = item.getFrowtype();
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(rowType)
            && pk_stock_b.equals(item.getPk_stock_b()) && pk_invoice_b != null) {
          invoices.add(pk_invoice_b);
        }
      }
    }
    return invoices;
  }

  private void selectRelatedInvoices(ListBodyRowChangedEvent e) {
    // 只有当this.getModel().getSettleBillVOs()不为空时，才是在匹配结果页面
    // 当返回到第一个和第二个界面时，会将settleBillVOs设置成null
    SettleBillVO[] bills = this.getModel().getSettleBillVOs();
    int row = e.getRow();
    if (bills != null && row >= 0) {
      BillModel bmStock = e.getBillListPanel().getBodyBillModel();
      String pk_stock_b =
          (String) bmStock.getValueAt(row, StockSettleVO.PK_STOCKPS_B);
      Set<String> relatedInvoices = this.getRelatedInvoices(pk_stock_b, bills);
      if (relatedInvoices.size() == 0) {
        return;
      }

      BillModel bmInvoice = e.getBillListPanel().getHeadBillModel();
      int rowcount = bmInvoice.getRowCount();
      for (int i = 0; i < rowcount; i++) {
        String pk_invoice_b =
            (String) bmInvoice.getValueAt(i, InvoiceSettleVO.PK_INVOICE_B);
        if (relatedInvoices.contains(pk_invoice_b)) {
          e.getBillListPanel().getHeadTable().getSelectionModel()
              .addSelectionInterval(i, i);
        }
        else {
          e.getBillListPanel().getHeadTable().getSelectionModel()
              .removeSelectionInterval(i, i);
        }
      }
    }
  }

}
