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

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-14 下午10:12:09
 */
public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel bcp = e.getBillCardPanel();
    BillItem bi = bcp.getBodyItem(FeeEstVO.PK_FEEMATERIAL);
    if (null == bi) {
      return;
    }
    String pk_fiorg =
        (String) bcp.getBodyValueAt(e.getRow(), FeeEstVO.PK_FINANCEORG);
    FilterMaterialRefUtils util =
        new FilterMaterialRefUtils((UIRefPane) bi.getComponent());
    util.filterItemRefByOrg(pk_fiorg);
    util.filterRefByFeeOrDiscount(null, UFBoolean.FALSE);
    util.filterRefByCostfactor(pk_fiorg);
    util.filterRefByOIDNotIn(this.getExistOID(bcp, e.getRow()));
  }

  private String[] getExistOID(BillCardPanel bcp, int row) {
    int rowcount = bcp.getRowCount();
    if (2 > rowcount) {
      return null;
    }
    List<String> oids = new ArrayList<String>();
    for (int i = 0; i < rowcount; i++) {
      if (i == row) {
        continue;
      }
      oids.add((String) bcp.getBodyValueAt(i, FeeEstVO.PK_SRCFEEMATERIAL));
    }
    return oids.toArray(new String[oids.size()]);
  }
}
