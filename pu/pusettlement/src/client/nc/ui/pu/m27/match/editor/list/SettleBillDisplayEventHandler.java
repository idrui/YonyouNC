package nc.ui.pu.m27.match.editor.list;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import nc.ui.pu.m27.match.editor.event.SettleBillDisplayEvent;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.view.MatchResultListPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.util.CombineToInvoiceSettleVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算完成后结算单显示处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 上午10:01:10
 */
public class SettleBillDisplayEventHandler implements
    IAppEventHandler<SettleBillDisplayEvent> {
  private MatchResultListPanel listView;

  public MatchResultListPanel getListView() {
    return this.listView;
  }

  @Override
  public void handleAppEvent(SettleBillDisplayEvent e) {
    // 加载发票部分
    this.loadInvoiceVos();
    // 加载入库单部分
    this.loadStockVos();
    // 显示当前视图
    this.listView.showMeUp();
  }

  public void setListView(MatchResultListPanel listView) {
    this.listView = listView;
  }

  private InvoiceSettleVO[] getQueryInvoices() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();
    return model.getInvoiceSettleVO();
  }

  private void loadInvoiceVos() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();

    // 获得查询出来的发票的VO
    InvoiceSettleVO[] invoices = this.getQueryInvoices();
    if (ArrayUtils.isEmpty(invoices)) {
      return;
    }

    // 获得参与结算的发票的行ID
    Set<String> settledInvoiceIds = new LinkedHashSet<String>();
    SettleBillVO[] settleBills = model.getSettleBillVOs();
    if (ArrayUtils.isEmpty(settleBills)) {
      this.getListView().getBillListPanel().setHeaderValueVO(null);
      return;
    }
    for (SettleBillVO settleBill : settleBills) {
      SettleBillItemVO[] items = settleBill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_invoice_b = item.getPk_invoice_b();
        if (pk_invoice_b != null) {
          settledInvoiceIds.add(pk_invoice_b);
        }
      }
    }

    // 组织参与结算的发票的VO集合
    List<InvoiceSettleVO> settledInvoices = new ArrayList<InvoiceSettleVO>();
    for (InvoiceSettleVO invoice : invoices) {
      if (settledInvoiceIds.contains(invoice.getPk_invoice_b())) {
        settledInvoices.add(invoice);
      }
    }

    // 显示发票到结算结果界面上
    InvoiceSettleVO[] invoicesToDisplay =
        settledInvoices.toArray(new InvoiceSettleVO[settledInvoices.size()]);
    this.getListView().getBillListPanel().setHeaderValueVO(invoicesToDisplay);
    this.getListView().getBillListPanel().getHeadBillModel().execLoadFormula();
  }

  private void loadStockVos() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();

    // 获得查询出来的入库单的VO
    StockSettleVO[] stockVos = model.getQueryStockVOs();
    if (ArrayUtils.isEmpty(stockVos)) {
      return;
    }

    // 获得参与结算的入库单的行ID
    Set<String> settledStockIds = new LinkedHashSet<String>();
    SettleBillVO[] settleBills = model.getSettleBillVOs();
    if (ArrayUtils.isEmpty(settleBills)) {
      this.getListView().getBillListPanel().setBodyValueVO(null);
      return;
    }
    for (SettleBillVO settleBill : settleBills) {
      SettleBillItemVO[] items = settleBill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_stock_b = item.getPk_stock_b();
        if (pk_stock_b != null) {
          settledStockIds.add(pk_stock_b);
        }
      }
    }

    // 组织参与结算的入库单的VO集合
    List<StockSettleVO> settledStocks = new ArrayList<StockSettleVO>();
    for (StockSettleVO stockVo : stockVos) {
      if (settledStockIds.contains(stockVo.getPk_stockps_b())) {
        settledStocks.add(stockVo);
      }
    }

    // 显示入库单到界面上
    StockSettleVO[] stocksToDisplay =
        settledStocks.toArray(new StockSettleVO[settledStocks.size()]);
    this.getListView().getBillListPanel().setBodyValueVO(stocksToDisplay);
    this.getListView().getBillListPanel().getBodyBillModel().execLoadFormula();
  }

}
