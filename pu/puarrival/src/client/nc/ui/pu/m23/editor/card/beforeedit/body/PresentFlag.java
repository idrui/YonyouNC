package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>“是否赠品”字段编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑前：如果来源单据是赠品，“是否赠品”字段不允许修改
 * <li>
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
public class PresentFlag implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    BillCardPanel card = event.getBillCardPanel();

    // 如果上游单据是赠品，则不允许修改“是否赠品”
    boolean bpresentsource =
        ArriveClientUtil.getBooleanCellValue(card, event.getRow(),
            ArriveItemVO.BPRESENTSOURCE);
    if (bpresentsource) {
      event.setReturnValue(Boolean.FALSE);
    }
  }
}
