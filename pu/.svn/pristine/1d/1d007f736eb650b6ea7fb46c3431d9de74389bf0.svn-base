package nc.ui.pu.m4t.editor.body.after;

import nc.ui.pu.m4t.rule.InitialEstVatRecRule;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * 发货国家/地区
 * 编辑后，自动重算购销类型、三角贸易标识，并触发询税。
 * 
 * @since 6.0
 * @version 2012-2-13 下午03:05:32
 * @author wuxla
 */
public class Csendcountryid implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String csendcountryid =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.CSENDCOUNTRYID);
    if (StringUtils.isEmpty(csendcountryid)) {
      return;
    }

    int[] rows = new int[] {
      row
    };
    new InitialEstVatRecRule().setVatAndReCal(panel, rows);
  }

}
