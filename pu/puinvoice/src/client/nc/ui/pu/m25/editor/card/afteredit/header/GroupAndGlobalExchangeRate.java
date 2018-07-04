/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 集团和全局汇率编辑后
 * 
 * @since 6.0
 * @version 2011-6-9 上午11:34:59
 * @author 田锋涛
 */
public class GroupAndGlobalExchangeRate implements
    ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    int rowCount = event.getBillCardPanel().getRowCount();
    if (StringUtils.isEmpty(newValue) || rowCount == 0) {
      return;
    }
    int rows[] = new int[rowCount];
    for (int i = 0; i < rowCount; i++) {
      rows[i] = i;
    }
    new RelationCalculate().calculate(event.getBillCardPanel(), rows,
        event.getKey());
  }
}
