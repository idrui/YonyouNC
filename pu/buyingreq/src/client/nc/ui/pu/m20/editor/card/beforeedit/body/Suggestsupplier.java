/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:25:08
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>建议供应商编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:25:08
 */
public class Suggestsupplier implements ICardBodyBeforeEditEventListener {

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
