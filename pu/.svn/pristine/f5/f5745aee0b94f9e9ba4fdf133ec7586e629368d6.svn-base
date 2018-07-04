package nc.ui.pu.est.editor.head.before;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.list.listener.IListHeadBeforeEditEventListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.scmpub.ref.FilterTaxCodeRefUtils;
import nc.vo.pu.est.entity.GoodsEstVO;

import org.apache.commons.lang.StringUtils;

/**
 * Ë°Âë¹ýÂË
 * 
 * @since 6.0
 * @version 2012-2-21 ÏÂÎç02:07:22
 * @author lixyp
 */
public class Ctaxcodeid implements IListHeadBeforeEditEventListener {

  @Override
  public void beforeEdit(ListHeadBeforeEditEvent e) {
    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils lpvu = new ListPanelValueUtils(blp);
    int row = e.getRow();

    String ctaxcountryid =
        (String) lpvu.getHeadValueAt(row, GoodsEstVO.CTAXCOUNTRYID);
    if (StringUtils.isEmpty(ctaxcountryid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    Integer fbuysellflag =
        (Integer) lpvu.getHeadValueAt(row, GoodsEstVO.FBUYSELLFLAG);
    if (null == fbuysellflag) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    BillItem item = blp.getHeadItem(GoodsEstVO.CESTTAXCODEID);
    FilterTaxCodeRefUtils util = new FilterTaxCodeRefUtils(item);
    util.filterItemRefWithCompatible(ctaxcountryid,
        BuySellFlagEnum.valueOf(fbuysellflag));
  }
}
