package nc.ui.pu.m23.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于控制某些字段不允许编辑，也是为了防止不应该被编辑的字段，用户在模板上设置可以编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-26 下午03:11:59
 */
public class NeverEditHeaderItem implements
    ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    e.setReturnValue(Boolean.FALSE);
  }
}
