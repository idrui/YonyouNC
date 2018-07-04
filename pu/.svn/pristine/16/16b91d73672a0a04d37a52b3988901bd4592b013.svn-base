package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>“是否赠品”字段编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：根据“是否赠品”字段的值，设置赠品数量、赠品主数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class PresentFlag implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {

    BillCardPanel card = e.getBillCardPanel();

    boolean bpresent =
        ArriveClientUtil.getBooleanCellValue(card, e.getRow(),
            ArriveItemVO.BPRESENT);
    if (bpresent) {
      // 赠品数量 = 到货数量
      Object obj = card.getBodyValueAt(e.getRow(), ArriveItemVO.NASTNUM);
      card.setBodyValueAt(obj, e.getRow(), ArriveItemVO.NPRESENTASTNUM);

      // 赠品主数量 = 到货主数量
      obj = card.getBodyValueAt(e.getRow(), ArriveItemVO.NNUM);
      card.setBodyValueAt(obj, e.getRow(), ArriveItemVO.NPRESENTNUM);
    }
    else {
      // 清空赠品数量、赠品主数量
      ArriveClientUtil.clearBodyCellValues(card, e.getRow(), new String[] {
        ArriveItemVO.NPRESENTASTNUM, ArriveItemVO.NPRESENTNUM
      });
    }
  }
}
