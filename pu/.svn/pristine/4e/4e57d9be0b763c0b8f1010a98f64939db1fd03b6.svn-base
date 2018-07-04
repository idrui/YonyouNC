package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>收货仓库编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：
 * <li>清空货位的值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class ReceiveStore implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    CardPanelValueUtils util = new CardPanelValueUtils(card);
    // 清空货位
    String[] itemKeys = new String[1];
    itemKeys[0] = ArriveItemVO.PK_RACK;
    util.clearRowValueByItemKeys(e.getRow(), itemKeys, null);
    // TODO hanbin 设置货位的过滤条件
  }
}
