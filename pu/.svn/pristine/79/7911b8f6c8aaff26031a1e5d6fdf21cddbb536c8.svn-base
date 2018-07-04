package nc.ui.pu.m23.editor.card.beforeedit.header;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class VtranTypeCode implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();

    String stockOrg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_ORG);
    // 到货类型
    BillItem tranTypeItem = card.getHeadItem(ArriveHeaderVO.CTRANTYPEID);
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(tranTypeItem, stockOrg);
    String busitype =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_BUSITYPE);
    filter.filterTranType(new String[] {
      POBillType.Arrive.getCode()
    }, busitype);

    e.setReturnValue(Boolean.TRUE);
  }
}
