package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务员的编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-10 下午06:13:52
 */
public class Employee implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    String cdeptId =
        (String) e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_DEPT)
            .getValueObject();
    String pk_org =
        (String) e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_ORG)
            .getValueObject();
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU((UIRefPane) e
            .getBillCardPanel().getHeadTailItem(OrderHeaderVO.CEMPLOYEEID)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
    if (cdeptId != null) {
      BillItem bi =
          e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.CEMPLOYEEID);
      UIRefPane pane = (UIRefPane) bi.getComponent();
      FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(pane).fixFocusByPK(
          cdeptId);
    }
    // else {
    // String pk_org =
    // (String) e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_ORG)
    // .getValueObject();
    // FilterPsndocRefUtils filter =
    // new FilterPsndocRefUtils((UIRefPane) e.getBillCardPanel()
    // .getHeadTailItem(OrderHeaderVO.CEMPLOYEEID).getComponent());
    // filter.filterItemRefByOrg(pk_org);
    // }
    e.setReturnValue(Boolean.TRUE);
  }
}
