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
 * �����в���Action
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����в���
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-12-28 ����04:33:56
 */
public class PositionInsertLineAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -4982799103899935141L;

  // �б���ͼ
  private PositionListEditor listEditor;

  // ����Ӧ��ģ��
  private AbstractAppModel model;

  /**
   * ������
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
   * ȡ���б���ͼ
   * 
   * @return �б���ͼ
   */
  public PositionListEditor getListEditor() {
    return this.listEditor;
  }

  /**
   * ȡ�ù���Ӧ��ģ��
   * 
   * @return ����Ӧ��ģ��
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * �趨�б���ͼ
   * 
   * @param listEditor
   *          �б���ͼ
   */
  public void setListEditor(PositionListEditor listEditor) {
    this.listEditor = listEditor;
  }

  /**
   * �趨����Ӧ��ģ��
   * 
   * @param model
   *          ����Ӧ��ģ��
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * ����һ�С�
   * 
   * @author GGR
   * @time 2009-12-28 ����03:46:13
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
   * �����в��봦��
   * 
   * @author GGR
   * @time 2009-12-28 ����03:46:13
   */
  protected void onInsertLine() {
    int[] selectedRow =
        this.getListEditor().getBillListPanel().getBodyTable()
            .getSelectedRows();
    if (selectedRow.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004080_0", "04004080-0002")/*
                                                                   * @res
                                                                   * "û��ѡ�б�����"
                                                                   */);
    }
    this.insertLine(selectedRow[0]);
  }

}
