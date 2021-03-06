package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderPaymentVO;

/**
 * 账期天数编辑后事件
 * 
 * @since 6.0
 * @version 2015-2-12 下午3:49:15
 * @author zhangshqb
 */
public class PaymentDay implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    helper.clearBodyValue(event.getRow(), new String[] {
      OrderPaymentVO.CHECKDATA, OrderPaymentVO.EFFECTMONTH,
      OrderPaymentVO.EFFECTADDMONTH, OrderPaymentVO.ACCOUNTDAY
    });
  }

}
