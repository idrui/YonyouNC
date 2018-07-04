package nc.ui.pu.m25.billref.processor;

import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pubapp.AppContext;

/**
 * ��Ʊת���б��൥�����ȴ���
 * 
 * @since 6.0
 * @version 2012-5-11 ����01:47:08
 * @author lixyp
 */
public class InvoiceTransferListProcessor implements ITransferListViewProcessor {

  @Override
  public void processAfter(BillListView listView, Object[] bills) {
  }

  @Override
  public void processBefore(BillListView listView, Object[] bills) {
    // ��Ʊת���б��൥�����ȴ���
    String pk_group = AppContext.getInstance().getPkGroup();
    InvoiceUIScaleProcessor processor = new InvoiceUIScaleProcessor();
    processor.setListScale(listView.getBillListPanel(), pk_group);
  }

}
