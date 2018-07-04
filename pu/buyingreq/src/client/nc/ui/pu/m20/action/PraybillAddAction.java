/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����03:53:31
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m20.editor.card.pub.BodyLineDefaultValue;
import nc.ui.pu.m20.editor.card.pub.HeaderDefaultValue;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> *
 * <ul>
 * ���������¼�
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-2 ����03:53:31
 */
public class PraybillAddAction extends AddAction {

  /**
   * 
   */
  private static final long serialVersionUID = 6467151901479492427L;

  private ShowUpableBillForm editor = null;

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.AddAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ���ý���״̬
    this.getModel().setUiState(UIState.ADD);

    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());
    // ���ñ�ͷĬ��ֵ
    new HeaderDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue();
    // ���ñ���Ĭ��ֵ
    new BodyLineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(0);
  }

  /**
   * @return editor
   */
  public ShowUpableBillForm getEditor() {
    return this.editor;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.AddAction#getModel()
   */
  @Override
  public AbstractAppModel getModel() {
    return super.getModel();
  }

  /**
   * @param editor Ҫ���õ� editor
   */
  public void setEditor(ShowUpableBillForm editor) {
    this.editor = editor;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.AddAction#setModel(nc.ui.uif2.model.AbstractAppModel)
   */
  @Override
  public void setModel(AbstractAppModel model) {
    super.setModel(model);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.AddAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }

}
