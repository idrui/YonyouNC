package nc.ui.pu.m27.match.editor.list;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ҳ��ı�ͷ���л��¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-1 ����09:49:29
 */
public class SettleBillHeadRowChangeEventHandler implements
    IAppEventHandler<ListHeadRowChangedEvent> {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListHeadRowChangedEvent e) {
    String[] tableCodes =
        e.getBillListPanel().getBillListData().getBodyTableCodes();
    BillScrollPane panel =
        e.getBillListPanel().getBodyScrollPane(tableCodes[0]);
    // ���ݲ����ԭ�еı����еĻ��б仯�¼��������������ѭ����
    BillEditListener l = panel.getEditListener();
    panel.removeEditListener();

    // ѡ����ص���ⵥ
    this.selectRelatedStocks(e);

    // lixyp 2012.4.23
    // �����Ļ����ݹ��Ľ��ҳ�棬�����ͷ������壩��ʱ��Ӧ������ѡ�б��壨���ͷ����
    // ��ʱӦ���������¼������������鰴ť���á�
    // ����һ�У�ֻ�е�һ�������⣬�ڶ��п�ʼ�����������ģ���֪�ι�ֻ������һ�Ρ�
    // �����о����ͬ�½��飬�������������䣬�ֹ�������
    this.getModel().fireEvent(new AppEvent(""));

    // ��ԭ�����еĻ��б仯�¼�
    panel.addEditListener(l);
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

  private Set<String> getRelatedStocks(String pk_invoice_b, SettleBillVO[] bills) {
    Set<String> stocks = new HashSet<String>();
    for (SettleBillVO bill : bills) {
      SettleBillItemVO[] items = bill.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk_stock_b = item.getPk_stock_b();
        Integer rowType = item.getFrowtype();
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(rowType)
            && pk_invoice_b.equals(item.getPk_invoice_b())
            && pk_stock_b != null) {
          stocks.add(pk_stock_b);
        }
      }
    }
    return stocks;
  }

  private void selectRelatedStocks(ListHeadRowChangedEvent e) {
    // ֻ�е�this.getModel().getSettleBillVOs()��Ϊ��ʱ��������ƥ����ҳ��
    // �����ص���һ���͵ڶ�������ʱ���ὫsettleBillVOs���ó�null
    SettleBillVO[] bills = this.getModel().getSettleBillVOs();
    int row = e.getRow();
    if (bills != null && row >= 0) {
      BillModel bmInvoice = e.getBillListPanel().getHeadBillModel();
      String pk_invoice_b =
          (String) bmInvoice.getValueAt(row, InvoiceSettleVO.PK_INVOICE_B);
      Set<String> relatedStocks = this.getRelatedStocks(pk_invoice_b, bills);
      if (relatedStocks.size() == 0) {
        return;
      }

      BillModel bmStock = e.getBillListPanel().getBodyBillModel();
      int rowcount = bmStock.getRowCount();
      for (int i = 0; i < rowcount; i++) {
        String pk_stock_b =
            (String) bmStock.getValueAt(i, StockSettleVO.PK_STOCKPS_B);
        if (relatedStocks.contains(pk_stock_b)) {
          e.getBillListPanel().getBodyTable().getSelectionModel()
              .addSelectionInterval(i, i);
        }
        else {
          e.getBillListPanel().getBodyTable().getSelectionModel()
              .removeSelectionInterval(i, i);
        }
      }
    }
  }

}
