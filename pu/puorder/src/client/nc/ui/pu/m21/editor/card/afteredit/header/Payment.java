package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.rule.PaymentInfo;

/**
 * 表头付款协议编辑后事件
 * 
 * @author zhangshqb
 */
public class Payment implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    String pk_payterm = helper.getHeadStringValue(OrderHeaderVO.PK_PAYTERM);
    if (pk_payterm == null || pk_payterm.isEmpty()) {
      return;
    }
    OrderPaymentVO[] paymentVOs = PaymentInfo.getOrderPaymentVOs(pk_payterm);
    BillModel model = event.getBillCardPanel().getBillModel(OrderPaymentVO.TABSNAME);

    model.clearBodyData();
    model.setBodyDataVO(paymentVOs);
    // 对应参照类型的字段，重新设置一下关联项，防止出现显示主键现象
    model.loadLoadRelationItemValue(0, paymentVOs.length, 
        new String[]{OrderPaymentVO.PK_PAYPERIOD, OrderPaymentVO.PK_BALATYPE, 
                      OrderPaymentVO.PK_RATE});
  }
}
