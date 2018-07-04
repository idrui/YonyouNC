/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 ����11:19:51
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.ExchangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ��Ʊ���ڱ༭����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 ����11:19:51
 */
public class ArriveDate implements ICardHeadTailAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    String oldExchangeRate =
        util.getHeadStringValue(InvoiceHeaderVO.NEXCHANGERATE);
    // 1.���ñ�ͷ����
    ExchangeRateUtil.setExchangerate(util, util.getHeadUFDateValue(InvoiceHeaderVO.DARRIVEDATE));
    // 2.���ʱ仯����
    ExchangeRateUtil.calculateBodyRows(util, oldExchangeRate);
  }

}
