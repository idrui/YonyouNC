package nc.ui.pu.m20.action;

import nc.ui.pu.m20.editor.card.pub.BodyLineDefaultValue;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>插入行的按钮事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-2 下午08:22:03
 */
public class PraybillInsertLineAction extends BodyInsertLineAction {
  private static final long serialVersionUID = -1173068897937372463L;

  @Override
  public void doAction() {
    super.doAction();

    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // 设置表体默认值
    new BodyLineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(this.getCurrentSelectIndex());
    int[] rows = new int[] {
      this.getCardPanel().getBodyPanel().getTable().getSelectedRow()
    };
    this.getCardPanel().getBillModel().loadLoadRelationItemValue(rows);
  }
}
