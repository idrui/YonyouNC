package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 原产国编辑后处理
 * 
 * @since 6.0
 * @version 2012-2-20 下午02:31:00
 * @author tianft
 */
public class OrigCountry implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 清空原产地区
    event.getBillCardPanel().setBodyValueAt(null, event.getRow(),
        OrderItemVO.CORIGAREAID);
  }

}
