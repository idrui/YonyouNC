/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午10:53:11
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购员
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午10:53:11
 */
public class Bizpsn implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    String pk_purchaseorg =
        (String) panel.getHeadItem(InitialEstHeaderVO.PK_PURCHASEORG)
            .getValueObject();
    if (null == pk_purchaseorg) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU((UIRefPane) panel
            .getHeadTailItem(InitialEstHeaderVO.PK_BIZPSN).getComponent());
    filter.filterItemRefByOrg(pk_purchaseorg);

    String pk_dept =
        (String) panel.getHeadTailItem(InitialEstHeaderVO.PK_DEPT)
            .getValueObject();
    if (pk_dept != null) {
      BillItem bi =
          e.getBillCardPanel().getHeadTailItem(InitialEstHeaderVO.PK_BIZPSN);
      UIRefPane pane = (UIRefPane) bi.getComponent();
      FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(pane).fixFocusByPK(
          pk_dept);
    }
    e.setReturnValue(Boolean.TRUE);
  }

}
