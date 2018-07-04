package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.ref.FilterSuppAddressRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 发货地址编辑前处理类，主要是根据供应商和组织来过滤参照。
 * 
 * @since 6.3
 * @version 2012-12-19 上午10:35:56
 * @author lixyp
 */
public class Venddevaddr implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    String pk_supplier =
        (String) panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    if (pk_supplier != null) {
      String pk_org =
          (String) panel.getBodyValueAt(event.getRow(), OrderItemVO.PK_ORG);
      FilterSuppAddressRefUtils utils =
          new FilterSuppAddressRefUtils((UIRefPane) panel.getBodyItem(
              OrderItemVO.VVENDDEVADDR).getComponent());
      utils.filteRefBySupplier(pk_supplier, pk_org);
      event.setReturnValue(Boolean.TRUE);
    }
    else {
      event.setReturnValue(Boolean.FALSE);
    }
  }
}
