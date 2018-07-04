/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 上午07:21:18
 */
package nc.ui.pu.m422x.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>申请部门
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-5 上午07:21:18
 */
public class Appdepth implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();

    if (0 == panel.getRowCount()) {
      return;
    }

    for (int i = 0; i < panel.getRowCount(); ++i) {
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
