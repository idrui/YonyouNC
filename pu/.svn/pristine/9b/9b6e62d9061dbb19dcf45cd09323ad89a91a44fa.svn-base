/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午11:46:39
 */
package nc.ui.pu.m4t.editor.body.after;

import nc.ui.pu.m4t.rule.EditableByUnit;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.rule.UnitAndChangeRate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单位
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午11:46:39
 */
public class AssistUnit implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    int[] rows = new int[] {
      row
    };

    UnitAndChangeRate rate = new UnitAndChangeRate(helper);
    rate.setChangeRate(rows);

    this.relationCalculate(event.getBillCardPanel(), rows);

    new EditableByUnit(event.getBillCardPanel()).setEditable(rows);
  }

  private void relationCalculate(BillCardPanel panel, int[] rows) {
    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, rows, InitialEstItemVO.VCHANGERATE);
  }
}
