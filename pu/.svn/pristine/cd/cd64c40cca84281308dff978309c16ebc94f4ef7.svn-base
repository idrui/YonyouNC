package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * 项目编辑前事件处理类
 * 
 * @since 6.3.1
 * @version 2013-8-9 下午04:35:31
 * @author fanly3
 */
public class Project implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel billCardPanel = event.getBillCardPanel();
    String csourceTypeCode =
        (String) billCardPanel.getBodyValueAt(event.getRow(),
            StoreReqAppItemVO.CSOURCETYPECODE);
    if ("4D14".equals(csourceTypeCode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    event.setReturnValue(Boolean.TRUE);
  }

}
