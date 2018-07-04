package nc.ui.pu.m21.rule.vat;

import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;

/**
 * 前台设置VAT税率，三角贸易，购销类型等。主要用于编辑国家后的处理。
 * 
 * @since 6.0
 * @version 2012-2-22 上午10:01:44
 * @author tianft
 */
public class VatValueUISetter {
  CardEditorHelper editorHelper;

  public VatValueUISetter(CardEditorHelper editorHelper) {
    this.editorHelper = editorHelper;
  }

  public void setVatValue(int row) {
    CardEditorHelper card = new CardEditorHelper(this.editorHelper.getEditor());
    OrderVatValueFillRule vatrule = new OrderVatValueFillRule(card, new int[] {
      row
    });
    vatrule.prepare();
    vatrule.process();
    OrderCalculatorUtils.calculate(this.editorHelper.getEditor(),
        vatrule.getValueChangeObject());

  }

}
