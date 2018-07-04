/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午12:08:06
 */
package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.pub.util.ObjectUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商，参照协同销售订单，供应商不可编辑
 * <li>供应商过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午12:08:06
 */
public class Supplier implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    Object vcoopordercode =
        e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.VCOOPORDERCODE)
            .getValueObject();
    FilterSupplierRefUtils filter =
        new FilterSupplierRefUtils(e.getBillCardPanel().getHeadItem(
            OrderHeaderVO.PK_SUPPLIER));
    String pk_org =
        (String) e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_ORG)
            .getValueObject();
    filter.filterItemRefByOrg(pk_org);
    if (!ObjectUtil.isEmptyWithTrim(vcoopordercode)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    e.setReturnValue(Boolean.TRUE);
  }

}
