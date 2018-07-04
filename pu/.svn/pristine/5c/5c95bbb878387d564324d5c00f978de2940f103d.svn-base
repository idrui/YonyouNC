/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午10:15:04
 */
package nc.ui.pu.m422x.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

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
 * @author wuxla
 * @time 2010-7-20 下午10:15:04
 */
public class AppDept implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    String pk_org =
        (String) e.getBillCardPanel()
            .getHeadTailItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();

    BillItem biv =
        e.getBillCardPanel().getHeadTailItem(StoreReqAppHeaderVO.PK_APPDEPTH_V);
    UIRefPane panev = (UIRefPane) biv.getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfIC(panev).filterItemRefByOrg(
        pk_org);

    BillItem bi =
        e.getBillCardPanel().getHeadTailItem(StoreReqAppHeaderVO.PK_APPDEPTH);
    UIRefPane pane = (UIRefPane) bi.getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfIC(pane).filterItemRefByOrg(
        pk_org);

    e.setReturnValue(Boolean.TRUE);
  }

}
