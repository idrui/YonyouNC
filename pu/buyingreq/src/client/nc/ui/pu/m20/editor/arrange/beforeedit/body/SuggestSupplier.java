/**
 * 
 */
package nc.ui.pu.m20.editor.arrange.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 上午9:08:55
 */
public class SuggestSupplier implements ICardBodyBeforeEditEventListener {

	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
	 */
	@Override
	public void beforeEdit(CardBodyBeforeEditEvent event) {
	  
    int row = event.getRow();
    Object purchaseorg =
        event.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.PK_PURCHASEORG);
    if ((null == purchaseorg) || (purchaseorg.toString().trim().length() == 0)) {
      // 采购组织未录入，不可编辑
      event.setReturnValue(Boolean.FALSE);
    }
    else {
      // 参照的范围为表体采购组织可见的供应商。如果采购组织为空时则参照不到任何供应商档案。
      FilterSupplierRefUtils filter =
          new FilterSupplierRefUtils(event.getBillCardPanel().getBodyItem(
              PraybillItemVO.PK_SUGGESTSUPPLIER));

      filter.filterItemRefByOrg(purchaseorg.toString());
      event.setReturnValue(Boolean.TRUE);
    }
	}

}
