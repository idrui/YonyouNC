package nc.ui.pu.m21.rule;

import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种和汇率，以及可编辑性等的处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 下午02:15:20
 */
public class CurrencyRelated {
  private BillCardPanel panel;

  public CurrencyRelated(BillCardPanel panel) {
    this.panel = panel;
  }

  public void setCurrencyAndExchangeRate(int[] rows) {
    // 补充本位币和汇率的相关信息
    CardEditorHelper card = new CardEditorHelper(this.panel);
    new CurrencyAndExchangerate(card).setCurrencyAndExchangeRate(rows);

    this.relationCalculate(rows);

    // 设置币种相关的编辑性
    new EditableSetter(this.panel).setEditableByCurrency(rows);
  }

  private void relationCalculate(int[] rows) {
    RelationCalculate tool = new RelationCalculate();
    tool.calculate(this.panel, rows, OrderItemVO.NEXCHANGERATE);
  }
}
