/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m25.editor.utils.ExchangeRateUtil;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <b>本类主要完成以下功能：币种</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午10:27:09
 */
public class CurrencyType implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    if (StringUtils.isEmpty(newValue)) {
      return;
    }
    // 1. 币种改变汇率 2.汇率变动处理
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    ExchangeRateUtil.changeExchangeRate(util);
    // by luojw 币种更改后清空表体价格
    this.clearPrice(event.getBillCardPanel());

    // 3.触发询价,针对表体所有行
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel());
    // 远程调用合并
    price.prepareQueryPrice();
    price.handleQueryPrice();
  }
  
  /**
   * 清空表体单价
   * 
   * @param event
   */
  private void clearPrice(BillCardPanel cardPanel) {
    int count = cardPanel.getRowCount();
    int[] rows = new int[count];
    for (int i = 0; i < count; i++) {
      rows[i] = i;
      cardPanel.setBodyValueAt(null, i, InvoiceItemVO.NPRICE);
    }
    RelationCalculate tool =
        new RelationCalculate();
    tool.calculate(cardPanel, rows, InvoiceItemVO.NPRICE);
  }
}
