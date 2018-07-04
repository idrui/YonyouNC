/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.TaxRateAndTypeUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <b>本类主要完成以下功能：税率</b>
 * <ul>
 * <li>表头税率编辑后事件处理
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午11:05:58
 */
public class TaxRate implements ICardHeadTailAfterEditEventListener {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit
   * (nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 1. 表体税率随之变化
    // 2. 表体税率变化后的处理
    new TaxRateAndTypeUtil(event.getBillCardPanel()).changeBodyByHeader(
        InvoiceItemVO.NTAXRATE, event.getValue());
  }
}
