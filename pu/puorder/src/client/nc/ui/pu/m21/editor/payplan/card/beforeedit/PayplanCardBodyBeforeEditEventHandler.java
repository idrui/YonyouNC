package nc.ui.pu.m21.editor.payplan.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.payplan.card.beforeedit.body.PayPlanBeforeEdit;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-23 ÉÏÎç11:38:17
 * @author wuxla
 */

public class PayplanCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    listenerMap.put(AbstractPayPlanVO.DBEGINDATE, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.DENDDATE, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.IITERMDAYS, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.NORIGMNY, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.NRATE, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.BPREFLAG, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.CROWNO, new PayPlanBeforeEdit());
    listenerMap.put(AbstractPayPlanVO.FEFFDATETYPE, new PayPlanBeforeEdit());
  }

}
