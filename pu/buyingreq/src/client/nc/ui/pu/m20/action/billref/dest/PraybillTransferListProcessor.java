package nc.ui.pu.m20.action.billref.dest;

import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pubapp.billref.dest.ITransferListViewProcessor;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pubapp.AppContext;

/**
 * 请购单转单时，列表精度处理类
 * 
 * @since 6.3.1
 * @version 2013-8-21 上午10:12:36
 * @author fanly3
 */
public class PraybillTransferListProcessor implements
    ITransferListViewProcessor {

  @Override
  public void processAfter(BillListView listView, Object[] bills) {

  }

  @Override
  public void processBefore(BillListView listView, Object[] bills) {
    String pk_group = AppContext.getInstance().getPkGroup();
    PraybillScaleUtil scale = new PraybillScaleUtil();
    scale.setListScale(listView.getBillListPanel(), pk_group);
  }

}
