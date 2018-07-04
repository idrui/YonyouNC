package nc.ui.pu.est.editor.body.after;

import nc.ui.pu.est.rule.FeeEstValueAndReCal;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pubapp.calculator.data.IRelationForItems;

public class Crececountryid implements ICardBodyAfterEditEventListener {
  private IRelationForItems relaitems;

  public Crececountryid(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    new FeeEstValueAndReCal().setVatAndReCal(event, this.relaitems);
  }

}
