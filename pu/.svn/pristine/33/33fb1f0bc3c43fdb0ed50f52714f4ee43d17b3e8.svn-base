package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.EditableByCurrency;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

public class Nexchangerate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    new EditableByCurrency(event.getBillCardPanel()).setEditable(new int[] {
      event.getRow()
    });
  }
}
