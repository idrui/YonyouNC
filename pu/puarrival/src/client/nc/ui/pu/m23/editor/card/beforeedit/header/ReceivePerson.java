package nc.ui.pu.m23.editor.card.beforeedit.header;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;

public class ReceivePerson implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 库存组织
    String stockOrg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_ORG);

    // 收货人(按库存组织过滤)
    UIRefPane receivePsnRef =
        (UIRefPane) card.getHeadItem(ArriveHeaderVO.PK_RECEIVEPSNDOC)
            .getComponent();
    FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(receivePsnRef)
        .filterItemRefByOrg(stockOrg);

    e.setReturnValue(Boolean.TRUE);
  }
}
