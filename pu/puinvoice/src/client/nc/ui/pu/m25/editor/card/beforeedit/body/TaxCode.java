package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * ˰��༭ǰ�¼�
 * 
 * @since 6.0
 * @version 2012-2-20 ����1:26:00
 * @author zhaoyha
 */
public class TaxCode implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // �ñ�˰���Һ͹������Ͷ�˰����ս��й���
    BillItem bi =
        event.getBillCardPanel().getBodyItem(InvoiceItemVO.CTAXCODEID);
    BuySellFlagEnum bsflag = null;
    Integer bsflagint =
        (Integer) event.getBillCardPanel()
            .getHeadItem(InvoiceHeaderVO.FBUYSELLFLAG).getValueObject();
    bsflag = null == bsflagint ? null : BuySellFlagEnum.valueOf(bsflagint);
    String pk_country =
        (String) event.getBillCardPanel()
            .getHeadItem(InvoiceHeaderVO.CTAXCOUNTRYID).getValueObject();
    new FilterTaxCodeRefUtils(bi).filterItemRefWithCompatible(pk_country,
        bsflag);
  }
}
