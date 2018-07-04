package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.TaxRateSetter;
import nc.vo.pu.m21.rule.ValueCheck;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>整单税率的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:17:04
 */
public class TaxRate implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());

    // 税率不能小于0的检查
    ValueCheck check = new ValueCheck(editor, (UFDouble) event.getOldValue());
    check.checkValue(0, OrderItemVO.NTAXRATE);

    // 用表头的税率更新表体的所有税率
    TaxRateSetter setter = new TaxRateSetter(editor);
    setter.setBodyTaxRate();

    // 数量、单价、金额的关联计算
    this.relationCalculate(event.getBillCardPanel());
  }

  private void relationCalculate(BillCardPanel panel) {
    int rows[] = new int[panel.getRowCount()];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = i;
    }

    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, rows, OrderItemVO.NTAXRATE);
  }

}
