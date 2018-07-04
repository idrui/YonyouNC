package nc.ui.pu.position.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pu.position.view.PositionListEditor;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 表体行插入Action
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体行插入
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 下午04:33:56
 */
public class PositionInsertLineAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -4982799103899935141L;

  // 列表视图
  private PositionListEditor listEditor;

  // 管理应用模型
  private AbstractAppModel model;

  /**
   * 构造子
   */
  public PositionInsertLineAction() {
    super();
    ActionInitializer.initializeAction(this, IActionCode.INSLINE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.onInsertLine();
  }

  /**
   * 取得列表视图
   * 
   * @return 列表视图
   */
  public PositionListEditor getListEditor() {
    return this.listEditor;
  }

  /**
   * 取得管理应用模型
   * 
   * @return 管理应用模型
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 设定列表视图
   * 
   * @param listEditor
   *          列表视图
   */
  public void setListEditor(PositionListEditor listEditor) {
    this.listEditor = listEditor;
  }

  /**
   * 设定管理应用模型
   * 
   * @param model
   *          管理应用模型
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * 插入一行。
   * 
   * @author GGR
   * @time 2009-12-28 下午03:46:13
   */
  protected void insertLine(int row) {
    this.getListEditor().getBillListPanel().getBodyBillModel().insertRow(row);
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.ADD
        || this.model.getUiState() == UIState.EDIT;
  }

  /**
   * 表体行插入处理。
   * 
   * @author GGR
   * @time 2009-12-28 下午03:46:13
   */
  protected void onInsertLine() {
    int[] selectedRow =
        this.getListEditor().getBillListPanel().getBodyTable()
            .getSelectedRows();
    if (selectedRow.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004080_0", "04004080-0002")/*
                                                                   * @res
                                                                   * "没有选中表体行"
                                                                   */);
    }
    this.insertLine(selectedRow[0]);
  }

}
