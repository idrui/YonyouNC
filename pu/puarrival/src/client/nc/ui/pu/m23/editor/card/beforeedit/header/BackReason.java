package nc.ui.pu.m23.editor.card.beforeedit.header;

import nc.ui.pu.m23.editor.card.utils.BackReasonEditUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;

public class BackReason implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BackReasonEditUtil util = new BackReasonEditUtil(e.getBillCardPanel());
    if (util.getEnabled()) {
      e.setReturnValue(Boolean.TRUE);
    } else {
      e.setReturnValue(Boolean.FALSE);
    }
    
  }
}
