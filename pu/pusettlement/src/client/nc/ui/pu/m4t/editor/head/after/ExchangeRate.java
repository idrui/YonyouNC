/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 下午02:39:54
 */
package nc.ui.pu.m4t.editor.head.after;

import nc.ui.pu.m4t.rule.CurrencyRelated;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>折本汇率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 下午02:39:54
 */
public class ExchangeRate implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CurrencyRelated rule = new CurrencyRelated(event.getBillCardPanel());
    rule.relationCalculate();
  }

}
