/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-14 下午10:12:09
 */
package nc.ui.pu.est.editor.body.before;

import nc.ui.pu.est.util.EstRefFilterUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.est.entity.FeeEstVO;

/**
 * 供应商编辑前
 * 
 * @since 6.0
 * @version 2012-6-4 下午02:01:14
 * @author tianft
 */
public class Supplier implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel bcp = e.getBillCardPanel();
    BillItem bi = bcp.getBodyItem(FeeEstVO.PK_SUPPLIER);
    if (null == bi) {
      return;
    }
    String pk_fiorg =
        (String) bcp.getBodyValueAt(e.getRow(), FeeEstVO.PK_FINANCEORG);
    EstRefFilterUtil filterUtil = new EstRefFilterUtil(bcp);
    filterUtil.filterSupplier(FeeEstVO.PK_SUPPLIER, pk_fiorg);
  }
}
