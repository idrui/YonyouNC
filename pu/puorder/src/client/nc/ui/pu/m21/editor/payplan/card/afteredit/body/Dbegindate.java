package nc.ui.pu.m21.editor.payplan.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.scmf.payplan.rule.DbegindateRule;

/**
 * @since 6.0
 * @version 2011-1-23 обнГ02:50:12
 * @author wuxla
 */

public class Dbegindate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    new DbegindateRule().afterEdit(event);
  }

}
