package nc.ui.pu.est.editor.body.before;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.est.entity.FeeEstVO;

public class Ncaltaxmny implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel bcp = event.getBillCardPanel();
    int row = event.getRow();
    Integer fbuysellflag =
        (Integer) bcp.getBodyValueAt(row, FeeEstVO.FBUYSELLFLAG);
    if (BuySellFlagEnum.NATIONAL_BUY.value().equals(fbuysellflag)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);
  }

}
