/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 上午11:27:46
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体地址清空，则表体相关地区、地点也清空
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-14 上午11:27:46
 */
public class RPReceiveaddress implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String str =
        (String) panel
            .getBodyValueAt(row, OrderReceivePlanVO.PK_RECEIVEADDRESS);
    // 表体地址清空，则表体相关地区、地点也清空
    if (StringUtil.isEmptyWithTrim(str)) {
      panel.setBodyValueAt(null, row, OrderReceivePlanVO.CDEVADDRID);
      panel.setBodyValueAt(null, row, OrderReceivePlanVO.CDEVAREAID);
    }
  }

}
