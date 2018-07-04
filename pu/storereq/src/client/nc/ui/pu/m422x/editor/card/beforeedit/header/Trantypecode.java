package nc.ui.pu.m422x.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 物资需求申请类型编辑前事件
 * 
 * @since 6.5
 * @version 2014-2-17 下午01:18:36
 * @author fanly3
 */
public class Trantypecode implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // 设置物资需求申请类型参照过滤
    this.filterTransType(event.getBillCardPanel(), event.getContext()
        .getPk_org());
  }

  private void filterTransType(BillCardPanel card, String pk_org) {
    // 设置物资需求申请类型参照过滤
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(
            card.getHeadItem(StoreReqAppHeaderVO.CTRANTYPEID), pk_org);

    filter.filterTranType(new String[] {
      POBillType.MRBill.getCode()
    });
  }
}
