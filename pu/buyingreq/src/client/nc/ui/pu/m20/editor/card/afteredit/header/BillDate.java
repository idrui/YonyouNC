/**
 * $文件说明$
 * 
 * @author luojw
 * @version 6.36
 * @see
 * @since 6.36
 * @time 2015-3-9 下午04:14:37
 */
package nc.ui.pu.m20.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头请购日期编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.36
 * @since 6.36
 * @author luojw
 * @time 2015-3-9 下午04:14:37
 */
public class BillDate implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 根据表头请购日期设置表体请购日期
    BillCardPanel panel = event.getBillCardPanel();
    Object dbilldate =
        panel.getHeadItem(PraybillHeaderVO.DBILLDATE).getValueObject();

    int row = panel.getRowCount();

    for (int i = 0; i < row; i++) {
      panel.setBodyValueAt(dbilldate, i, PraybillItemVO.DBILLDATE);
    }
  }

}
