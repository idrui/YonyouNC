/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-24 下午04:44:36
 */
package nc.ui.pu.m20.action;

import nc.ui.pu.m20.editor.card.pub.BodyLineDefaultValue;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-24 下午04:44:36
 */
public class PraybillBodyAddLineAction extends BodyAddLineAction {
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 2445254180120212269L;

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
    // new BodyLineDefaultValue(helper, this.getModel().getContext())
    // .setDefaultValue(index);
    //
    // // 根据Context中的标志来判断是否需要加载关联项，主要是多选物料的时候是不需要加载关联项的。
    // ClientContext clientContext = (ClientContext)
    // this.getModel().getContext();
    // if (clientContext.isNeedLoadRelationItem()) {
    // this.getCardPanel().getBillModel().loadLoadRelationItemValue(new int[] {
    // index
    // });
    // }
  }

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // 设置表体默认值
    new BodyLineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(index);

    // 根据Context中的标志来判断是否需要加载关联项，主要是多选物料的时候是不需要加载关联项的。
    ClientContext clientContext = (ClientContext) this.getModel().getContext();
    if (clientContext.isNeedLoadRelationItem()) {
      this.getCardPanel().getBillModel().loadLoadRelationItemValue(new int[] {
        index
      });
    }
  }

}
