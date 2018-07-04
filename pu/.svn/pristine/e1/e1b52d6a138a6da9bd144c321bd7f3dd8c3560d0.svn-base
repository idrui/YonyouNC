/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 下午01:32:16
 */
package nc.ui.pu.est.editor.head.after.m45;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.list.ListHeadRowStateChangeEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头钩选行变化处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-29 下午01:32:16
 */
public class RowSelectStateChangeHandler implements
    IAppEventHandler<ListHeadRowStateChangeEvent> {

  @Override
  public void handleAppEvent(ListHeadRowStateChangeEvent e) {
    if (BillModel.ADD == e.getOldRowStaus()) {
      // 查询后nc.ui.uif2.editor.BillListView.setCheckBoxMultiUnstate()
      // 方法设置未选中状态，会触发行改变状态。有效率问题。
      // 如果状态不是BillModel.SELECTED（选中）和UNSTATE（未选中），则返回
      return;
    }

    if (BillModel.SELECTED == e.getRowStaus()) {
      this.select(e);
    }
    else {
      this.unSelect(e);
    }
  }

  private void select(ListHeadRowStateChangeEvent e) {
    int srow = e.getRow();
    int erow = e.getEndRow();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils util = new ListPanelValueUtils(blp);
    BillModel bm = blp.getHeadBillModel();
    int rowcnt = bm.getRowCount();
    Set<String> oneSelectPks = new HashSet<String>();
    for (int row = srow; row <= erow; ++row) {
      String pk =
          (String) util.getHeadValueAt(row, PurchaseinFIHeaderVO.PK_STOCKPS);
      if (oneSelectPks.contains(pk)) {
        continue;
      }
      oneSelectPks.add(pk);
      for (int i = 0; i < rowcnt; ++i) {
        String ipk =
            (String) util.getHeadValueAt(i, PurchaseinFIHeaderVO.PK_STOCKPS);
        if (!pk.equals(ipk)) {
          continue;
        }
        int rowstate = bm.getRowState(i);
        if (BillModel.SELECTED != rowstate) {
          oneSelectPks.remove(pk);
        }
      }
    }
    if (oneSelectPks.isEmpty()) {
      return;
    }
    for (int i = 0; i < rowcnt; ++i) {
      String ipk =
          (String) util.getHeadValueAt(i, PurchaseinFIHeaderVO.PK_STOCKPS);
      if (!oneSelectPks.contains(ipk)) {
        continue;
      }
      util.setHeadValueAt(UFBoolean.TRUE, i,
          PurchaseInEstHeaderVO.ONEBILLSELECT);
    }
  }

  private void unSelect(ListHeadRowStateChangeEvent e) {
    int srow = e.getRow();
    int erow = e.getEndRow();
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils util = new ListPanelValueUtils(blp);
    int rowcnt = blp.getHeadBillModel().getRowCount();
    for (int row = srow; row <= erow; ++row) {
      String pk =
          (String) util.getHeadValueAt(row, PurchaseinFIHeaderVO.PK_STOCKPS);
      for (int i = 0; i < rowcnt; ++i) {
        String ipk =
            (String) util.getHeadValueAt(i, PurchaseinFIHeaderVO.PK_STOCKPS);
        boolean billselect =
            ValueUtils.getBoolean(util.getHeadValueAt(i,
                PurchaseInEstHeaderVO.ONEBILLSELECT));
        if (pk.equals(ipk) && billselect) {
          util.setHeadValueAt(UFBoolean.FALSE, i,
              PurchaseInEstHeaderVO.ONEBILLSELECT);
        }
      }
    }
  }

}
