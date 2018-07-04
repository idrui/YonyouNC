package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderPaymentVO;

/**
 * 附加月编辑后事件
 * 
 * @since 6.36
 * @version 2015-5-23 上午10:49:15
 * @author luojw
 */
public class EffectAddMonth implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    if(event.getValue() == null){
      event.getBillCardPanel().setBodyValueAt(Integer.valueOf(0), 
          event.getRow(), OrderPaymentVO.EFFECTADDMONTH);
    }
  }

}
