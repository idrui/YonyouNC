package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.editor.card.utils.CalculateAstNum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * <p>
 * <b>自定义项编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>维护关系：主数量 = 数量 × 换算率 ；
 * <li>重新计算：单价、金额
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class ChangeRate implements ICardBodyAfterEditEventListener {
  private BillCardPanel card;

  private int currRow;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    // 初始化类变量
    this.currRow = e.getRow();
    this.card = e.getBillCardPanel();

    // 更新各种数量的主数量
    new CalculateAstNum(this.card, this.currRow).calculateAstNum();
  }
}
