package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>数量相关的编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑前：设置“到货数量”、“到货主数量”是否可以进行编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class NumHandler implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    BillCardPanel card = event.getBillCardPanel();

    // 到货数量为空、负到货，则可以编辑“到货数量”、“到货主数量”
    UFDouble nnum =
        ArriveClientUtil.getUFDoubleCellValue(card, event.getRow(),
            ArriveItemVO.NNUM);
    if ((nnum == null) || (nnum.doubleValue() < 0)) {
      event.setReturnValue(Boolean.TRUE);
      return;
    }

    // 已报检过，不允许编辑“到货数量”、“到货主数量”
    UFDouble accumCheckNum =
        ArriveClientUtil.getUFDoubleCellValue(card, event.getRow(),
            ArriveItemVO.NACCUMCHECKNUM);
    if ((accumCheckNum != null) && (accumCheckNum.doubleValue() > 0)) {
      event.setReturnValue(Boolean.FALSE);
    }
  }

}
