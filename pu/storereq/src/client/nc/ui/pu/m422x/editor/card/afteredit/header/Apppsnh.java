package nc.ui.pu.m422x.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * @since 6.0
 * @version 2011-4-16 ÏÂÎç02:59:05
 * @author wuxla
 */

public class Apppsnh implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    if (0 == panel.getRowCount()) {
      return;
    }

    for (int i = 0; i < panel.getRowCount(); ++i) {
      String pk_apppsnh =
          (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_APPPSNH)
              .getValueObject();
      panel.setBodyValueAt(pk_apppsnh, i, StoreReqAppItemVO.PK_APPPSN);

      String pk_appdept_v =
          (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_APPDEPTH_V)
              .getValueObject();
      panel.setBodyValueAt(pk_appdept_v, i, StoreReqAppItemVO.PK_APPDEPT_V);

      String pk_appdept =
          (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_APPDEPTH)
              .getValueObject();
      panel.setBodyValueAt(pk_appdept, i, StoreReqAppItemVO.PK_APPDEPT);
    }
  }

}
