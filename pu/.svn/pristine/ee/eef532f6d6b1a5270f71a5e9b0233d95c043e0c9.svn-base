package nc.ui.pu.m23.billref.dest;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pubapp.AppContext;

public class ArriveTransferListProcessor implements ITransferListViewProcessor {

  @Override
  public void processAfter(BillListView listView, Object[] bills) {

  }

  @Override
  public void processBefore(BillListView listView, Object[] bills) {
    String pk_group = AppContext.getInstance().getPkGroup();
    ArriveUIScaleRule scale = new ArriveUIScaleRule(pk_group);
    scale.setListScale(listView.getBillListPanel());
  }

}
