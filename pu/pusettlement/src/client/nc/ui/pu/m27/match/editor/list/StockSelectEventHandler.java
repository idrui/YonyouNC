package nc.ui.pu.m27.match.editor.list;

import java.util.Set;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListBodyRowStateChangeEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库单行选中的事件处理器
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 上午08:39:50
 */
public class StockSelectEventHandler implements
    IAppEventHandler<ListBodyRowStateChangeEvent> {
  private MatchManageModel model;

  public MatchManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(ListBodyRowStateChangeEvent e) {
    Set<String> selected = this.getModel().getSelectedStocks();
    int row = e.getRow();
    int endRow = e.getEndRow();
    BillModel bm = e.getBillListPanel().getBodyBillModel();
    if (e.getRowStaus() == BillModel.SELECTED) {
      for (int i = row; i <= endRow; i++) {
        Object obj = bm.getValueAt(i, StockSettleVO.PK_STOCKPS_B);
        selected.add((String) obj);
      }
    }
    else {
      for (int i = row; i <= endRow; i++) {
        Object obj = bm.getValueAt(i, StockSettleVO.PK_STOCKPS_B);
        selected.remove(obj);
      }
    }

    // 此处如果不进行事件的派发，全选、全消按钮的状态不刷新
    this.getModel().fireEvent(new AppEvent(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0029")/*@res "刷新按钮状态"*/));
  }

  public void setModel(MatchManageModel model) {
    this.model = model;
  }

}