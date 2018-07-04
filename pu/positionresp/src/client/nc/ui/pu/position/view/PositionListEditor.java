package nc.ui.pu.position.view;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BillListEditor;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.IMultiRowSelectModel;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.util.PUSysParamUtil;

/**
 * 岗位设置列表视图
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>重写行改变事件
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 下午04:33:56
 */
public class PositionListEditor extends BillListEditor {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1346128740769765394L;

  /**
   * 重写行改变事件. 当在编辑和增加状态时选择行改变时，不进行处理
   */
  @Override
  public void bodyRowChange(BillEditEvent e) {

    if (this.getModel().getUiState() == UIState.ADD
        || this.getModel().getUiState() == UIState.EDIT) {
      return;
    }
    super.bodyRowChange(e);
  }

  @Override
  public void initUI() {
    super.initUI();
    this.setHideItem();
    this.getBillListPanel().getBodyTable().setSortEnabled(false);
    this.getBillListPanel().getBodyTable()
        .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  }

  private BillListSelectionListener selectionListener = null;

  @Override
  protected void setListMultiProp() {
    selectionListener = new BillListSelectionListener();
    billListPanel.getHeadTable().getSelectionModel()
        .addListSelectionListener(selectionListener);
    billListPanel.getHeadTable().setSelectionMode(
        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  }

  // CTRL+鼠标点击方式实现多选的监听类
  private class BillListSelectionListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
      if (handlingModelEvent)
        return;

      if (!e.getValueIsAdjusting()
          && getModel().getUiState() == UIState.NOT_EDIT) {// 增加了非编辑态限制
        if (needSyncMultiSelection()
            && getModel() instanceof IMultiRowSelectModel) {
          int[] rows = getBillListPanel().getHeadTable().getSelectedRows();
          getModel().setSelectedOperaRows(rows);
        }
      }
    }

    // 除非是处于checkbox多选模式，否则总要同步单选和多选
    private boolean needSyncMultiSelection() {
      return !(isMultiSelectionEnable() && getMultiSelectionMode() == BillListView.CHECKBOX_SELECTION);
    }
  }

  /**
   * 方法功能描述：增加动作执行时调用。
   */
  @Override
  public void onAdd() {
    super.onAdd();

    BillModel hm = this.getBillListPanel().getHeadBillModel();

    int row = hm.getRowCount() - 1;
    this.setCurrentRow(row);
    this.setHeadEditRow(row);
    hm.setEditRow(row);
    hm.setValueAt(this.getModel().getContext().getPk_group(), row,
        PositionHeaderVO.PK_GROUP);
    hm.setValueAt(this.getModel().getContext().getPk_org(), row,
        PositionHeaderVO.PK_ORG);

    // 岗位类型 采购岗：1 计划岗：0
    if (this.isPlanPostionFunNode()) {
      hm.setValueAt(Integer.valueOf(PositionHeaderVO.planPosition), row,
          PositionHeaderVO.FPOSITIONTYPE);
    }
    else {
      hm.setValueAt(Integer.valueOf(PositionHeaderVO.purchasePosition), row,
          PositionHeaderVO.FPOSITIONTYPE);
    }

    this.getBillListPanel().updateUI();

  }

  /**
   * 方法功能描述：增加动作执行时调用。
   */
  @Override
  public void onEdit() {

    BillListPanel panel = this.getBillListPanel();
    int[] selectedRows = panel.getHeadTable().getSelectedRows();
    if (selectedRows != null && selectedRows.length == 1) {
      panel.getHeadBillModel().setEditRow(selectedRows[0]);
      this.showHintMessage(nc.ui.ml.NCLangRes.getInstance().getStrByID(
          "4004073005", "UPP4004073005-000158")/* @res "正在修改" */);
      super.onEdit();
      this.setHeadEditRow(selectedRows[0]);
      this.getBillListPanel().updateUI();
    }
  }

  @Override
  public void onSave() throws Exception {
    super.onSave();
    this.getBillListPanel().updateUI();
  }

  /**
   * 设置当前选择行为可编辑。 表头：当前行，可编辑的字段可以编辑；其余行，均不能编辑
   * 
   * @param row 当前选择行
   */
  public void setHeadEditRow(int row) {
    BillModel bm_head = this.getBillListPanel().getHeadBillModel();
    BillItem[] bi_head = bm_head.getBodyItems();
    for (int i = 0, len = bi_head.length; i < len; i++) {
      if (bi_head[i].isShow()) {
        for (int j = 0, len2 = bm_head.getRowCount(); j < len2; j++) {
          bm_head.setCellEditable(j, bi_head[i].getKey(), false);
        }
      }
    }

    if (row > -1 && row < bm_head.getRowCount()) {
      for (int i = 0, len = bi_head.length; i < len; i++) {
        String key = bi_head[i].getKey();
        if (bi_head[i].isShow() && bi_head[i].isEdit()) {
          bm_head.setCellEditable(row, key, true);
        }
        // 设置表头的code不允许编辑
        if (this.getModel().getUiState() == UIState.EDIT
            && (PositionHeaderVO.CODE.equals(key))) {
          bm_head.setCellEditable(row, key, false);
        }
      }
    }
  }

  /**
   * 设置状态栏提示信息
   * 
   * @param message 提示信息
   */
  public void showHintMessage(String message) {
    ShowStatusBarMsgUtil
        .showStatusBarMsg(message, this.getModel().getContext());
  }

  /**
   * 是否计划岗节点
   * 
   * @return true-计划岗；false-采购岗
   */
  private boolean isPlanPostionFunNode() {
    return PuNodeCode.n40010515.code().equals(
        this.getModel().getContext().getNodeCode());
  }

  /**
   * 根据集团级参数“采购基础设置物料分类方式”设置隐藏列
   */
  private void setHideItem() {

    po85 type =
        PUSysParamUtil.getPO85(this.getModel().getContext().getPk_group());
    // 计划岗按物料基本分类，不走参数
    if (po85.pu_marclass == type && !this.isPlanPostionFunNode()) {
      this.getBillListPanel().hideBodyTableCol(PositionItemVO.PK_MARBASCLASS);
      this.getBillListPanel().hideBodyTableCol(
          PositionItemVO.PK_MARBASCLASS + ".name");
      this.getBillListPanel().getBodyItem(PositionItemVO.PK_MARBASCLASS)
          .setNull(false);
    }
    else {
      this.getBillListPanel().hideBodyTableCol(PositionItemVO.PK_MARPUCLASS);
      this.getBillListPanel().hideBodyTableCol(
          PositionItemVO.PK_MARPUCLASS + ".name");
      this.getBillListPanel().getBodyItem(PositionItemVO.PK_MARPUCLASS)
          .setNull(false);
    }
    // }
    this.getBillListPanel().updateUI();
  }

  @Override
  protected void handleSelectionChanged() {
    super.handleSelectionChanged();
  }

}
