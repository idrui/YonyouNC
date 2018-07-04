package nc.ui.pu.m21.editor.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.ui.pu.m21.view.StatusBillFormEditor;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowStateChangeEvent;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pub.ISuperVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单对方确认 行选中的事件处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2012-2-23 上午08:39:25
 */
public class ConfirmEventHandler implements
    IAppEventHandler<CardBodyRowStateChangeEvent> {
  private IEditor editor;

  private BillManageModel model;

  public IEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void handleAppEvent(CardBodyRowStateChangeEvent e) {

    BillCardPanel panel = e.getBillCardPanel();
    BillModel bm = panel.getBillModel();
    int count = bm.getRowCount();

    // 把选中的表体行放入model中，取消选择时清空
    ((StatusBillFormEditor) this.editor).getBillCardPanel().getBodyPanel()
        .getTable().setRowSelectionInterval(0, count - 1);
    List<Integer> selectedRow = new ArrayList<Integer>();
    for (int i = 0; i < count; i++) {
      if (BillModel.SELECTED != bm.getRowState(i)) {
        ((StatusBillFormEditor) this.editor).getBillCardPanel().getBodyPanel()
            .getTable().removeRowSelectionInterval(i, i);
      }
      else {
        selectedRow.add(Integer.valueOf(i));
      }
    }
    int[] intSelectedRows = new int[selectedRow.size()];
    for (int i = 0; i < selectedRow.size(); i++) {
      intSelectedRows[i] = selectedRow.get(i).intValue();
    }
    this.getModel().setSelectedOperaRowsWithoutEvent(intSelectedRows);

    ISuperVO[] bodySelectedVOs =
        ((StatusBillFormEditor) this.editor).getBodySelectedVOs();

    if (ArrayUtils.isEmpty(bodySelectedVOs)) {
      ((SelectBillManageModel) this.getModel())
          .setSelectedBodyRows(new ArrayList<ISuperVO>());
    }
    else {
      List<ISuperVO> bodySelectedVOList = new ArrayList<ISuperVO>();
      bodySelectedVOList.addAll(Arrays.asList(bodySelectedVOs));
      ((SelectBillManageModel) this.getModel())
          .setSelectedBodyRows(bodySelectedVOList);
    }
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
