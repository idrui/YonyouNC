package nc.ui.pu.m21.editor.payplan.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmf.payplan.editor.PayPlanBeforeEditRule;

/**
 * @since 6.0
 * @version 2011-1-23 обнГ03:06:15
 * @author wuxla
 */

public class PayPlanBeforeEdit implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    new PayPlanBeforeEditRule().beforeEdit(event);
  }

}
