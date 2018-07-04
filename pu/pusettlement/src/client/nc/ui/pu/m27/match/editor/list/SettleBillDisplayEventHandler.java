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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ɺ���㵥��ʾ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����10:01:10
 */
public class SettleBillDisplayEventHandler implements
    IAppEventHandler<SettleBillDisplayEvent> {
  private MatchResultListPanel listView;

  public MatchResultListPanel getListView() {
    return this.listView;
  }

  @Override
  public void handleAppEvent(SettleBillDisplayEvent e) {
    // ���ط�Ʊ����
    this.loadInvoiceVos();
    // ������ⵥ����
    this.loadStockVos();
    // ��ʾ��ǰ��ͼ
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

    // ��ò�ѯ�����ķ�Ʊ��VO
    InvoiceSettleVO[] invoices = this.getQueryInvoices();
    if (ArrayUtils.isEmpty(invoices)) {
      return;
    }

    // ��ò������ķ�Ʊ����ID
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

    // ��֯�������ķ�Ʊ��VO����
    List<InvoiceSettleVO> settledInvoices = new ArrayList<InvoiceSettleVO>();
    for (InvoiceSettleVO invoice : invoices) {
      if (settledInvoiceIds.contains(invoice.getPk_invoice_b())) {
        settledInvoices.add(invoice);
      }
    }

    // ��ʾ��Ʊ��������������
    InvoiceSettleVO[] invoicesToDisplay =
        settledInvoices.toArray(new InvoiceSettleVO[settledInvoices.size()]);
    this.getListView().getBillListPanel().setHeaderValueVO(invoicesToDisplay);
    this.getListView().getBillListPanel().getHeadBillModel().execLoadFormula();
  }

  private void loadStockVos() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();

    // ��ò�ѯ��������ⵥ��VO
    StockSettleVO[] stockVos = model.getQueryStockVOs();
    if (ArrayUtils.isEmpty(stockVos)) {
      return;
    }

    // ��ò���������ⵥ����ID
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

    // ��֯����������ⵥ��VO����
    List<StockSettleVO> settledStocks = new ArrayList<StockSettleVO>();
    for (StockSettleVO stockVo : stockVos) {
      if (settledStockIds.contains(stockVo.getPk_stockps_b())) {
        settledStocks.add(stockVo);
      }
    }

    // ��ʾ��ⵥ��������
    StockSettleVO[] stocksToDisplay =
        settledStocks.toArray(new StockSettleVO[settledStocks.size()]);
    this.getListView().getBillListPanel().setBodyValueVO(stocksToDisplay);
    this.getListView().getBillListPanel().getBodyBillModel().execLoadFormula();
  }

}
