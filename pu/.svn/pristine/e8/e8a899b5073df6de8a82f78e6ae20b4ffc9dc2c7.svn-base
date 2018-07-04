package nc.ui.pu.m4t.editor.body.before;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * 计税金额
 * 当购销类型=国内采购时不可编辑；购销类型=进口采购时跨国业务支持编辑。
 * 
 * @since 6.0
 * @version 2012-2-17 下午12:21:09
 * @author wuxla
 */
public class Ncaltaxmny implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();

    Integer fbuysellflag =
        (Integer) panel.getBodyValueAt(row, InitialEstItemVO.FBUYSELLFLAG);
    if (BuySellFlagEnum.NATIONAL_BUY.value().equals(fbuysellflag)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);

  }

}
