package nc.ui.pu.est.editor.body.after;

import nc.ui.pu.est.rule.FeeEstValueAndReCal;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pubapp.calculator.data.IRelationForItems;

/**
 * 发货国家/地区
 * 
 * @since 6.0
 * @version 2012-2-17 下午01:30:49
 * @author wuxla
 */
public class Csendcountryid implements ICardBodyAfterEditEventListener {

  private IRelationForItems relaitems;

  public Csendcountryid(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    new FeeEstValueAndReCal().setVatAndReCal(event, this.relaitems);
  }

}
