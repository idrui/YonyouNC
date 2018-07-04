package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.rule.CalculateBodyDays;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主数量编辑事件处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-3-4 下午04:54:57
 */
public class Num implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());

    // 需求日期和建议订货日期处理
    new CalculateBodyDays().setAdvDays(kv, new int[] {
      row
    });
  }
}
