/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:55:23
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * 是否委外编辑前
 * 
 * @since 6.0
 * @version 2012-2-21 下午02:39:32
 * @author tianft
 */
public class ScType implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // 有来源时，是否委外不允许编辑
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
