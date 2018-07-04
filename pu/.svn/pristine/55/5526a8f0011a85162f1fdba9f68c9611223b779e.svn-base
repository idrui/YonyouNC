/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <b>本类主要完成以下功能：散户</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午11:02:34
 */
public class FreeCust implements ICardHeadTailAfterEditEventListener {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit
   * (nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    new SupplierFreeCustInfoUtil().afterFreeCust(util);
  }

}
