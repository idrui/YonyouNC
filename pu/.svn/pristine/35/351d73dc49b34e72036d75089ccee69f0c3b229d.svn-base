/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-16 上午11:02:34
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.TaxRateAndTypeUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头扣税类别编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-16 上午11:02:34
 */
public class TaxType implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 1. 表体扣税类别随之变化
    // 2. 表体扣税类别处理
    new TaxRateAndTypeUtil(event.getBillCardPanel()).changeBodyByHeader(
        InvoiceItemVO.FTAXTYPEFLAG, event.getValue());

  }

}
