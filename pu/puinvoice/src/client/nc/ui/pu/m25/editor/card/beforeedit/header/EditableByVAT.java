package nc.ui.pu.m25.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m25.pub.InvoiceVOUtil;

/**
 * ²É¹º·¢Æ±£¬ÅÐ¶Ï×Ö¶ÎÊÇ·ñ¿É±à¼­
 * 
 * @since 6.1
 * @version 2012-2-20 ÏÂÎç02:51:40
 * @author yangtian
 */
public class EditableByVAT implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());

    if (!InvoiceVOUtil.isSelfMade(invoice)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);
  }
}
