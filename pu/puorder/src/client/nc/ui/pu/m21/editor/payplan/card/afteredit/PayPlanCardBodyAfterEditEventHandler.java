package nc.ui.pu.m21.editor.payplan.card.afteredit;

import java.util.Map;

import nc.ui.pu.m21.editor.payplan.card.afteredit.body.Dbegindate;
import nc.ui.pu.m21.editor.payplan.card.afteredit.body.Denddate;
import nc.ui.pu.m21.editor.payplan.card.afteredit.body.Iitermdays;
import nc.ui.pu.m21.editor.payplan.card.afteredit.body.Norigmny;
import nc.ui.pu.m21.editor.payplan.card.afteredit.body.Nrate;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-23 ÉÏÎç11:36:26
 * @author wuxla
 */

public class PayPlanCardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    listenerMap.put(AbstractPayPlanVO.DENDDATE, new Denddate());
    listenerMap.put(AbstractPayPlanVO.DBEGINDATE, new Dbegindate());
    listenerMap.put(AbstractPayPlanVO.IITERMDAYS, new Iitermdays());
    listenerMap.put(AbstractPayPlanVO.NORIGMNY, new Norigmny());
    listenerMap.put(AbstractPayPlanVO.NRATE, new Nrate());

  }

}
