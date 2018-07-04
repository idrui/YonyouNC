package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.vo.pu.m21.entity.OrderPaymentVO;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * 固定结账日编辑后事件
 * 
 * @author zhangshqb
 */
public class CheckDate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    Object value = event.getValue();
    if (value != null && !value.equals("")) {
      helper.setBodyValue(event.getRow(), OrderPaymentVO.EFFECTMONTH, Integer.valueOf(0));
      helper.setBodyValue(event.getRow(), OrderPaymentVO.EFFECTADDMONTH, Integer.valueOf(0));
      helper.clearBodyValue(event.getRow(), new String[] {
        OrderPaymentVO.PAYMENTDAY
      });
    }
    // 清空固定结账日的同时清空生效月和附加月
    else {
      helper.clearBodyValue(event.getRow(), new String[] {
        OrderPaymentVO.EFFECTMONTH,OrderPaymentVO.EFFECTADDMONTH
      });
    }

  }

}
