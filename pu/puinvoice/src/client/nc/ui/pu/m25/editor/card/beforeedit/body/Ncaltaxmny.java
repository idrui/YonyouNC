package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * 计税金额
 * 当购销类型=国内采购时不可编辑；购销类型=进口采购时跨国业务支持编辑。
 * 
 * @since 6.1
 * @version 2012-2-23 下午12:21:09
 * @author yangtian
 */
public class Ncaltaxmny implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    Integer fbuysellflag =
        (Integer) helper.getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG);

    if (BuySellFlagEnum.NATIONAL_BUY.value().equals(fbuysellflag)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);

  }

}
