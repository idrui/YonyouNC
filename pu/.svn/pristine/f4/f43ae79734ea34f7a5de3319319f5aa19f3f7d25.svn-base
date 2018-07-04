package nc.ui.pu.m21.editor.payplan.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.scmf.payplan.rule.DenddateRule;

/**
 * 账期到期日
 * 
 * @since 6.0
 * @version 2011-1-23 下午12:32:10
 * @author wuxla
 */

public class Denddate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    new DenddateRule().afterEdit(event);
  }

}
