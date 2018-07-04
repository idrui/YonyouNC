package nc.ui.pu.m4t.editor.body.after;

import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

import org.apache.commons.lang.StringUtils;

public class Ctaxcountryid implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String ctaxcountryid =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      return;
    }

    int[] rows = new int[] {
      row
    };
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
  }

}
