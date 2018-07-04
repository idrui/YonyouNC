package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.m21.rule.vat.VatValueUISetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * 收货过编辑后
 * 
 * @since 6.0
 * @version 2012-2-20 下午02:31:29
 * @author tianft
 */
public class ReceiveCountry implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    new VatValueUISetter(new CardEditorHelper(event.getBillCardPanel()))
        .setVatValue(event.getRow());
  }

}
