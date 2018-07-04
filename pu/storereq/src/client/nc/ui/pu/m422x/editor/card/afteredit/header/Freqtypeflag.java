package nc.ui.pu.m422x.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m422x.rule.NextbalanceorgSetter;

/**
 * 需求类型
 * 
 * @since 6.0
 * @version 2014-5-7 下午08:36:18
 * @author wuxla
 */
public class Freqtypeflag implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int rowcount = panel.getRowCount();
    if (rowcount == 0) {
      return;
    }
    int[] rows = new int[rowcount];
    for (int i = 0; i < rowcount; ++i) {
      rows[i] = i;
    }
    // 设置下次平衡库存组织
    CardEditorHelper card = new CardEditorHelper(panel);
    NextbalanceorgSetter nextsetter = new NextbalanceorgSetter(card);
    nextsetter.setBclear(false);
    nextsetter.setNextbalanceorg(rows);
  }

}
