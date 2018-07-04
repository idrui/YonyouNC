package nc.ui.pu.pub.editor.card.afteredit;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

public class CProject implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();
    int row = event.getRow();
    card.setBodyValueAt(null, row, PuAttrNameEnum.cprojecttaskid.name());
  }

}
