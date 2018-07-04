package nc.ui.pu.m4t.editor.body.after;

import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * 收货国家/地区
 * 
 * @since 6.0
 * @version 2012-2-17 上午11:20:07
 * @author wuxla
 */
public class Crececountryid implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String crececountryid =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.CRECECOUNTRYID);
    if (StringUtils.isEmpty(crececountryid)) {
      return;
    }

    int[] rows = new int[] {
      row
    };
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
  }

}
