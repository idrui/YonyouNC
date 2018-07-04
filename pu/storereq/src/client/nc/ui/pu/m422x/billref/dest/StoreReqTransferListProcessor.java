package nc.ui.pu.m422x.billref.dest;

import nc.ui.pu.m422x.rule.ScaleSetter;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pubapp.AppContext;

/**
 * 物资需求申请单转单时列表精度处理类
 * 
 * @since 6.3.1
 * @version 2013-8-12 上午09:54:49
 * @author fanly3
 */
public class StoreReqTransferListProcessor implements
    ITransferListViewProcessor {

  @Override
  public void processAfter(BillListView listView, Object[] bills) {

  }

  @Override
  public void processBefore(BillListView listView, Object[] bills) {
    String pk_group = AppContext.getInstance().getPkGroup();
    ScaleSetter scale = new ScaleSetter(pk_group);
    scale.setListScale(listView.getBillListPanel());
  }

}
