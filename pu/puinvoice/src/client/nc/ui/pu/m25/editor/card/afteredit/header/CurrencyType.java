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
 * <b>������Ҫ������¹��ܣ�����</b>
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
 * @time 2010-2-25 ����10:27:09
 */
public class CurrencyType implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    if (StringUtils.isEmpty(newValue)) {
      return;
    }
    // 1. ���ָı���� 2.���ʱ䶯����
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    ExchangeRateUtil.changeExchangeRate(util);
    // by luojw ���ָ��ĺ���ձ���۸�
    this.clearPrice(event.getBillCardPanel());

    // 3.����ѯ��,��Ա���������
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel());
    // Զ�̵��úϲ�
    price.prepareQueryPrice();
    price.handleQueryPrice();
  }
  
  /**
   * ��ձ��嵥��
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
