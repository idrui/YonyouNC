/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 上午08:50:28
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>交易类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 上午08:50:28
 */
public class InitialEstTranstype implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    BillItem item = panel.getHeadItem(InitialEstHeaderVO.PK_BUSITYPE);
    String pk_busitype = (String) item.getValueObject();
    item = panel.getHeadItem(InitialEstHeaderVO.PK_ORG);
    String pk_org = (String) item.getValueObject();
    item = panel.getHeadItem(e.getKey());
    FilterTransTypeRefUtils filter = new FilterTransTypeRefUtils(item, pk_org);
    boolean fromOrder = this.fromOrder(panel);
    if (fromOrder) {
      filter.filterTranType(new String[] {
        POBillType.InitEstimate.getCode()
      }, pk_busitype);
    }
    else {
      filter.filterTranType(new String[] {
        POBillType.InitEstimate.getCode()
      }, null);
    }
  }

  /**
   * 方法功能描述：是否来源于订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-15 下午04:40:41
   */
  private boolean fromOrder(BillCardPanel panel) {
    int rowcount = panel.getRowCount();
    if (0 == rowcount) {
      return false;
    }

    for (int i = 0; i < rowcount; ++i) {
      Object pk_order_b = panel.getBodyValueAt(i, InitialEstItemVO.PK_ORDER_B);
      if (!ObjectUtil.isEmptyWithTrim(pk_order_b)) {
        return true;
      }
    }

    return false;
  }

}
