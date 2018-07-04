/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 下午04:15:45
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ITBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * 订单类型编辑前事件
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
 * @author GGR
 * @time 2010-2-3 下午04:15:45
 */
public class Trantypecode implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String pk_org =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_PURCHASEORG);

    if (StringUtil.isEmptyWithTrim(pk_org)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 设置订单类型参照过滤
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(event.getBillCardPanel().getBodyItem(
            PraybillItemVO.CORDERTRANTYPECODE), pk_org);

    Boolean bsctype =
        (Boolean) event.getBillCardPanel()
            .getHeadItem(PraybillHeaderVO.BSCTYPE).getValueObject();
    if (null != bsctype && bsctype.booleanValue()) {
      filter.filterTranType(new String[] {
        SCBillType.Order.getCode()
      });
    }
    else {
      // 直运
      Boolean bdirecttransit =
          (Boolean) event.getBillCardPanel()
              .getHeadItem(PraybillHeaderVO.BDIRECTTRANSIT).getValueObject();
      if (bdirecttransit != null && bdirecttransit.booleanValue()) {
        this.filter(event.getBillCardPanel(), filter);
      }
      else {
        filter.filterTranType(new String[] {
          POBillType.Order.getCode(), ITBillType.Contract.getCode()
        });
      }
    }
    event.setReturnValue(Boolean.TRUE);
  }

  private void filter(BillCardPanel panel, FilterTransTypeRefUtils filter) {
    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(PraybillItemVO.CORDERTRANTYPECODE)
            .getComponent();
    if (null == pane) {
      return;
    }
    pane.getRefModel().addWherePart(" ");
    filter.filterTranType(new String[] {
      POBillType.Order.getCode()
    }, null);
    // this.filterByOrg(panel);
    String pk_group =
        (String) panel.getHeadItem(PraybillHeaderVO.PK_GROUP).getValueObject();
    SqlBuilder builder = new SqlBuilder();
    builder.append(" and ");
    builder.append(" pk_billtypecode in ( ");
    builder.append(" select vtrantypecode from po_potrantype where ");
    builder.append("pk_group", pk_group);
    builder.append(" and bdirect", UFBoolean.TRUE);
    builder.append(" and dr = 0");
    builder.append(" ) ");
    pane.getRefModel().addWherePart(builder.toString());
  }

  // private void filterByOrg(BillCardPanel panel) {
  // UIRefPane pane =
  // (UIRefPane) panel.getHeadItem(OrderHeaderVO.CTRANTYPEID).getComponent();
  // if (null == pane) {
  // return;
  // }
  // pane.getRefModel().addWherePart(" ");
  // String pk_org =
  // (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
  // FilterTransTypeRefUtils filter =
  // new FilterTransTypeRefUtils(
  // panel.getHeadTailItem(OrderHeaderVO.CTRANTYPEID), pk_org);
  // filter.filterTranType(new String[] {
  // POBillType.Order.getCode()
  // }, null);
  // }
}
