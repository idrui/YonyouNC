package nc.ui.pu.m27.match.editor.list;

import nc.ui.pu.m27.match.editor.event.InvoiceDisplayEvent;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.view.MatchDisplayListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.util.CombineToInvoiceSettleVO;
import nc.vo.pu.pub.util.VOSortUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ��ѯ�����ʾ
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����09:29:33
 */
public class InvoiceDisplayEventHandler implements
    IAppEventHandler<InvoiceDisplayEvent> {
  private MatchDisplayListPanel listView;

  public MatchDisplayListPanel getListView() {
    return this.listView;
  }

  @Override
  public void handleAppEvent(InvoiceDisplayEvent e) {
    MatchManageModel model = (MatchManageModel) this.listView.getModel();

    // �Ѹ��ַ�Ʊ���ͺϲ���InvoiceSettleVO
    CombineToInvoiceSettleVO combine = new CombineToInvoiceSettleVO();
    combine.setGoodsInvoices(model.getQueryInvoiceVOs());
    combine.setFeeInvoices(model.getQueryFeeInvoices());
    combine.setDiscountInvoices(model.getQueryDiscountInvoices());
    InvoiceSettleVO[] invoices = combine.combineInvoice();

    BillModel bm = this.listView.getBillListPanel().getHeadBillModel();
    // �����ݺ�����
    VOSortUtils.sortVOs(invoices, InvoiceHeaderVO.VBILLCODE);
    bm.setBodyDataVO(invoices);
    bm.execLoadFormula();

    // ��ʾ��ǰ����
    this.listView.showMeUp();

    // �˴�����������¼����ɷ���ȫѡ��ȫ����ť��״̬��ˢ��
    model.fireEvent(new AppEvent(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0029")/*@res "ˢ�°�ť״̬"*/));
  }

  public void setListView(MatchDisplayListPanel listView) {
    this.listView = listView;
  }
}