/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.ExchangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

import org.apache.commons.lang.StringUtils;

/**
 * <b>本类主要完成以下功能</b>
 * <ul>
 * <li>表头折本汇率编辑后处理
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午10:27:49
 */
public class ExchangeRate implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 清空的时候不处理
    if (null == event.getValue()
        || StringUtils.isBlank(event.getValue().toString())) {
      return;
    }
    ExchangeRateUtil.calculateBodyRows(new CardEditorHelper(event
        .getBillCardPanel()));

  }

}
