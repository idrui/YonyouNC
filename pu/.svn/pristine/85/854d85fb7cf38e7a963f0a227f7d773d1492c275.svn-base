/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 上午10:16:19
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.rule.CalculateBodyDays;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>数量编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-20 上午10:16:19
 */
public class Nastnum implements ICardBodyAfterEditEventListener {

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
