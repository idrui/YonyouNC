/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午03:53:31
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
 * <b>本类主要完成以下功能：</b> *
 * <ul>
 * 新增动作事件
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-2 下午03:53:31
 */
public class PraybillAddAction extends AddAction {

  /**
   * 
   */
  private static final long serialVersionUID = 6467151901479492427L;

  private ShowUpableBillForm editor = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.AddAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 设置界面状态
    this.getModel().setUiState(UIState.ADD);

    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());
    // 设置表头默认值
    new HeaderDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue();
    // 设置表体默认值
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
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.AddAction#getModel()
   */
  @Override
  public AbstractAppModel getModel() {
    return super.getModel();
  }

  /**
   * @param editor 要设置的 editor
   */
  public void setEditor(ShowUpableBillForm editor) {
    this.editor = editor;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.AddAction#setModel(nc.ui.uif2.model.AbstractAppModel)
   */
  @Override
  public void setModel(AbstractAppModel model) {
    super.setModel(model);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.AddAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return super.isActionEnable();
  }

}
