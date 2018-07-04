package nc.ui.pu.m21.billref.dest;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pubapp.AppContext;

public class OrderTransferListProcessor implements ITransferListViewProcessor {

  @Override
  public void processAfter(BillListView listView, Object[] bills) {
    //
  }

  @Override
  public void processBefore(BillListView listView, Object[] bills) {
    String pk_group = AppContext.getInstance().getPkGroup();
    OrderScaleSetter scale = new OrderScaleSetter(pk_group);
    scale.setListScale(listView.getBillListPanel());
  }

}
