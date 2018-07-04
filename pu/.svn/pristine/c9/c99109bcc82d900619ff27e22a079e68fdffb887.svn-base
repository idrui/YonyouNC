/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 下午03:16:16
 */
package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustBankaccRefUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>开户银行过滤，用供应商限制开户银行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-15 下午03:16:16
 */
public class AccountBank implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillItem bankItem =
        e.getBillCardPanel().getHeadItem(OrderHeaderVO.PK_BANKDOC);
    BillItem supplierItem =
        e.getBillCardPanel().getHeadItem(OrderHeaderVO.PK_INVCSUPLLIER);
    BillItem currItem = e.getBillCardPanel().getHeadItem(
    		OrderHeaderVO.CORIGCURRENCYID);
		if (bankItem == null || supplierItem == null) {
			return;
		}
    if (bankItem == null || supplierItem == null) {
      return;
    }
    String supplier =
        supplierItem.getValueObject() == null ? "" : supplierItem
            .getValueObject().toString();
    String curr = currItem.getValueObject() == null ? "" : currItem
				.getValueObject().toString();
		new FilterCustBankaccRefUtil(bankItem).filterItemRefByCustBankAndCurrency(
				supplier, null, curr);
  }

}
