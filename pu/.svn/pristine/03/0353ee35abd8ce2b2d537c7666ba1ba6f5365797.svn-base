package nc.ui.pu.m4t.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.vo.pu.m4t.rule.LineDefaultValue;

/**
 * @since 6.0
 * @version 2010-11-11 下午01:06:09
 * @author wuxla
 */

public class InitialEstInsertLineAction extends BodyInsertLineAction {

  private static final long serialVersionUID = 6659920488855494L;

  @Override
  public void doAction() {
    super.doAction();

    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // 设置表体默认值
    new LineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(this.getCurrentSelectIndex());
  }
}
