package nc.ui.pu.m4t.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pu.m4t.rule.LineDefaultValue;

/**
 * @since 6.0
 * @version 2010-11-11 下午01:02:03
 * @author wuxla
 */

public class InitialEstAddLineAction extends BodyAddLineAction {

  private static final long serialVersionUID = 6595788607611149674L;

  @Override
  public void doAction() {
    super.doAction();
    // modified by fanly3 2013-06-03
    // pubapp 修改效率问题，在nc.ui.pubapp.uif2app.actions.BodyAddLineAction
    // 中实现了IBatchBodyLine接口。
    // 原来在 doAction 方法中设置默认值的代码，在物料批选后不会执行。
    // 因此需要覆写afterLineInsert 方法，设置默认值。
    // 或者在监听中批量设置默认值。

    // CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // int index = this.getCardPanel().getBillModel().getRowCount() - 1;
    // // 设置表体默认值
    // new LineDefaultValue(helper, this.getModel().getContext())
    // .setDefaultValue(index);
  }

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // 设置表体默认值
    new LineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(index);
  }

}
