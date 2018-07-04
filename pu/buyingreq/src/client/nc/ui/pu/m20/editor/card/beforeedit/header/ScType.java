/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 ����06:55:23
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * �Ƿ�ί��༭ǰ
 * 
 * @since 6.0
 * @version 2012-2-21 ����02:39:32
 * @author tianft
 */
public class ScType implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // ����Դʱ���Ƿ�ί�ⲻ����༭
    event.setReturnValue(!this.hasSource(event.getBillCardPanel()));
  }

  private boolean hasSource(BillCardPanel card) {
    if (card.getRowCount() > 0) {
      for (int i = 0; i < card.getRowCount(); i++) {
        String sourceBillId =
            (String) card.getBodyValueAt(i, PraybillItemVO.CSOURCEID);
        if (StringUtils.isNotBlank(sourceBillId)) {
          return true;
        }
      }
    }
    return false;
  }

}
