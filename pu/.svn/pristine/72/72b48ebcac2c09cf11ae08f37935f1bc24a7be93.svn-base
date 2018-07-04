package nc.ui.pu.est.editor.body.before;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.vo.pu.est.entity.FeeEstVO;

import org.apache.commons.lang.StringUtils;

/**
 * Ë°Âë¹ýÂË
 * 
 * @since 6.0
 * @version 2012-2-21 ÏÂÎç02:07:11
 * @author lixyp
 */
public class Ctaxcodeid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String ctaxcountryid =
        (String) panel.getBodyValueAt(row, FeeEstVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    Integer fbuysellflag =
        (Integer) panel.getBodyValueAt(row, FeeEstVO.FBUYSELLFLAG);
    if (null == fbuysellflag) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    BillItem item = panel.getBodyItem(FeeEstVO.CTAXCODEID);
    FilterTaxCodeRefUtils util = new FilterTaxCodeRefUtils(item);
    util.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }
}
