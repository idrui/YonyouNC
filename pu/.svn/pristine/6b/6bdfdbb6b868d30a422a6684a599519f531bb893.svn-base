/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午02:09:36
 */
package nc.ui.pu.est.editor.head.after.m45;

import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>整单选择后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-28 下午02:09:36
 */
public class OneBillSelection implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    int row = event.getRow();
    UFBoolean selected = ValueUtils.getUFBoolean(event.getValue());
    BillListPanel blp = event.getBillListPanel();
    ListPanelValueUtils utils = new ListPanelValueUtils(blp);
    String hid =
        (String) utils.getHeadValueAt(row, PurchaseinFIHeaderVO.PK_STOCKPS);
    if (StringUtil.isEmptyWithTrim(hid)) {
      return;
    }
    int rowcnt = blp.getHeadBillModel().getRowCount();
    blp.getHeadBillModel().setNeedCalculate(false);
    for (int i = 0; i < rowcnt; i++) {
      String rowhid =
          (String) utils.getHeadValueAt(i, PurchaseinFIHeaderVO.PK_STOCKPS);
      if (!hid.endsWith(rowhid)) {
        continue;
      }
      utils.setHeadValueAt(selected, i, event.getKey());
      BillModel bm = blp.getHeadBillModel();
      if (selected.booleanValue()) {
        bm.setRowState(i, BillModel.SELECTED);
      }
      else {
        bm.setRowState(i, BillModel.UNSTATE);
      }
    }
    blp.getHeadBillModel().setNeedCalculate(true);
    blp.getParentListPanel().repaint();
  }

}
