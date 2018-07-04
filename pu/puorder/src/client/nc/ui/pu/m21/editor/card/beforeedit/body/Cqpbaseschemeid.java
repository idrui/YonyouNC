package nc.ui.pu.m21.editor.card.beforeedit.body;

import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m24.IQueryPricestl;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;

/**
 * @since 6.0
 * @version 2011-5-23 下午12:07:20
 * @author wuxla
 */

public class Cqpbaseschemeid implements ICardBodyBeforeEditEventListener {
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    Object pk_srcmatetial = panel.getBodyValueAt(row, OrderItemVO.PK_SRCMATERIAL);
    Object pk_org = panel.getBodyValueAt(row, OrderItemVO.PK_ORG);
    Object pk_group = panel.getBodyValueAt(row, OrderItemVO.PK_GROUP);
    if (ObjectUtil.isEmptyWithTrim(pk_srcmatetial)
        || ObjectUtil.isEmptyWithTrim(pk_org)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    boolean havePs = this.haveFollowPs(panel, row);
    if (havePs) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(OrderItemVO.CQPBASESCHEMEID)
            .getComponent();
    if (null == pane) {
      return;
    }
    // by luojw 由于优质优价方案存放的只是物料的主键，如果多版本以后，物料主键会变
    // 所以根据pk_source查出所有的版本，然后再去过滤
    StringBuilder apendwhere = new StringBuilder();
    apendwhere.append("dr = 0 and blatest = 'Y'")
    .append(" and pk_material in ( select pk_material from bd_material where pk_source= '")
    .append(pk_srcmatetial.toString())
    .append("') and pk_org in('").append(pk_org.toString())
    .append("','").append(pk_group.toString()).append("')");
    pane.getRefModel().setWherePart(apendwhere.toString());

    event.setReturnValue(Boolean.TRUE);
  }

  private boolean haveFollowPs(BillCardPanel panel, int row) {
    String pk_order =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORDER).getValueObject();
    if (null == pk_order) {
      return false;
    }
    String pk_order_b =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_ORDER_B);
    if (null == pk_order_b) {
      return false;
    }
    IQueryPricestl service =
        NCLocator.getInstance().lookup(IQueryPricestl.class);
    try {
      MapSet<String, String> mapset = service.queryOrderUsedByHid(new String[] {
        pk_order
      });
      if (null == mapset) {
        return false;
      }
      Set<String> set = mapset.get(pk_order);
      if (set != null && set.contains(pk_order_b)) {
        return true;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }
}
