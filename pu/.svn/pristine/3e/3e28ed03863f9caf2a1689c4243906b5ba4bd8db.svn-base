package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.bd.ref.RefPubUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.ref.FilterCustAddressRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 收货地址编辑前处理类，主要是根据收货客户和组织过滤参照。
 * 
 * @since 6.3
 * @version 2012-12-19 上午10:36:32
 * @author lixyp
 */
public class ReceiveAddress implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    UIRefPane refPanel =
        (UIRefPane) panel.getBodyItem(OrderItemVO.PK_RECEIVEADDRESS)
            .getComponent();

    String pk_customer =
        (String) panel.getHeadItem(OrderHeaderVO.PK_RECVCUSTOMER)
            .getValueObject();
    if (pk_customer == null) {
      refPanel.setRefModel(RefPubUtil.getRefModel("地址簿"));/* -=notranslate=- */
    }
    else {
      refPanel.setRefModel(RefPubUtil.getRefModel("客户收货地址"));/* -=notranslate=- */
      FilterCustAddressRefUtils utils = new FilterCustAddressRefUtils(refPanel);
      utils.filteRefByCustomer(pk_customer, null);
    }
  }
}
