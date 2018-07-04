/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午02:07:59
 */
package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;

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
 * @time 2010-7-20 下午02:07:59
 */
public class Dept implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    String pk_org =
        (String) e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_ORG)
            .getValueObject();

    if (pk_org != null) {
      BillItem bi =
          e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_DEPT_V);
      UIRefPane pane = (UIRefPane) bi.getComponent();
      FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(pane).filterItemRefByOrg(
          pk_org);

      BillItem bi2 =
          e.getBillCardPanel().getHeadTailItem(OrderHeaderVO.PK_DEPT);
      UIRefPane pane2 = (UIRefPane) bi2.getComponent();
      FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(pane2)
          .filterItemRefByOrg(pk_org);
    }

    e.setReturnValue(Boolean.TRUE);
  }

}
