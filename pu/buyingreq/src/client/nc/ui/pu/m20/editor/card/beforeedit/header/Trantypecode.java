/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 下午04:15:45
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 请购类型编辑前事件
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-3 下午04:15:45
 */
public class Trantypecode implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // 设置请购类型参照过滤
    this.filterTransType(event.getBillCardPanel(), event.getContext()
        .getPk_org());
  }

  private void filterTransType(BillCardPanel card, String pk_org) {
    // 设置请购类型参照过滤
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(
            card.getHeadItem(PraybillHeaderVO.CTRANTYPEID), pk_org);

    filter.filterTranType(new String[] {
      POBillType.PrayBill.getCode()
    });
  }

}
