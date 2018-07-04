/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 下午08:03:17
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 计税金额编辑前事件处理
 * 
 * @since 6.0
 * @version 2012-2-20 上午11:12:04
 * @author tianft
 */
public class CalTaxMny implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Integer buySellFlag =
        (Integer) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.FBUYSELLFLAG);
    // 当购销类型=国内采购时不可编辑；购销类型=进口采购时跨国业务支持编辑。
    boolean editable =
        BuySellFlagEnum.OUTPUT.value().equals(buySellFlag)
            || BuySellFlagEnum.IMPORT.value().equals(buySellFlag);
    event.setReturnValue(editable);
  }
}
