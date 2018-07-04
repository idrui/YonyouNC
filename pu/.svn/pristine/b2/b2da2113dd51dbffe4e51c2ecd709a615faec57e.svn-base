/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 上午07:30:54
 */
package nc.ui.pu.m422x.editor.card.afteredit.body;

import nc.ui.pu.m422x.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m422x.rule.EditableByUnit;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.rule.UnitAndChangeRate;

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
 * @time 2010-8-5 上午07:30:54
 */
public class AssistUnit implements ICardBodyAfterEditEventListener {

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
    cal.calculate(panel, rows, StoreReqAppItemVO.VCHANGERATE);
  }

}
