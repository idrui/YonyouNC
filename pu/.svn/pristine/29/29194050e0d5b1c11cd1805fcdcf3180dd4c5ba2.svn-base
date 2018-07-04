package nc.ui.pu.est.editor.head.before;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.ui.pu.pub.editor.list.listener.IListHeadBeforeEditEventListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListHeadBeforeEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.GoodsEstVO;

/**
 * 计税金额
 * 当购销类型=国内采购时不可编辑；购销类型=进口采购时跨国业务支持编辑。
 * 
 * @since 6.0
 * @version 2012-2-17 下午12:58:32
 * @author wuxla
 */
public class Nestcaltaxmny implements IListHeadBeforeEditEventListener {

  @Override
  public void beforeEdit(ListHeadBeforeEditEvent e) {
    int row = e.getRow();
    if (row < 0) {
      return;
    }

    BillListPanel blp = e.getBillListPanel();
    ListPanelValueUtils lpvu = new ListPanelValueUtils(blp);
    Integer fbuysellflag =
        (Integer) lpvu.getHeadValueAt(row, GoodsEstVO.FBUYSELLFLAG);
    if (BuySellFlagEnum.NATIONAL_BUY.value().equals(fbuysellflag)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    e.setReturnValue(Boolean.TRUE);
  }

}
