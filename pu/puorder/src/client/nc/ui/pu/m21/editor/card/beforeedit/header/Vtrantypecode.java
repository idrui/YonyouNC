/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-3 下午07:35:25
 */
package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-3 下午07:35:25
 */
public class Vtrantypecode implements ICardHeadTailBeforeEditEventListener {

  public Vtrantypecode() {

  }

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    boolean breceiveplan = this.haveReceivePlan(panel);
    if (breceiveplan) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    this.processFilter(panel);
  }

  private void filter(BillCardPanel panel, boolean directPu) {

    UIRefPane pane =
        (UIRefPane) panel.getHeadItem(OrderHeaderVO.CTRANTYPEID).getComponent();
    if (null == pane) {
      return;
    }

    this.filterByOrg(panel);
    String pk_group =
        (String) panel.getHeadItem(OrderHeaderVO.PK_GROUP).getValueObject();
    SqlBuilder builder = new SqlBuilder();
    builder.append(" and ");
    builder.append(" pk_billtypecode in ( ");
    builder.append(" select vtrantypecode from po_potrantype where ");
    builder.append("pk_group", pk_group);
    builder.append(" and bdirect", UFBoolean.valueOf(directPu));
    builder.append(" and dr = 0");
    builder.append(" ) ");
    pane.getRefModel().addWherePart(builder.toString());
  }

  private void filterByOrg(BillCardPanel panel) {
    UIRefPane pane =
        (UIRefPane) panel.getHeadItem(OrderHeaderVO.CTRANTYPEID).getComponent();
    if (null == pane) {
      return;
    }
    pane.getRefModel().addWherePart(" ");
    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(
            panel.getHeadTailItem(OrderHeaderVO.CTRANTYPEID), pk_org);
    filter.filterTranType(new String[] {
      POBillType.Order.getCode()
    }, null);
  }

  /**
   * 是否有来源
   * 
   * @param panel
   * @return
   */
  private boolean hasSource(BillCardPanel panel) {
    int rowcount = panel.getRowCount();
    CardEditorHelper card = new CardEditorHelper(panel);
    if (rowcount == 0) {
      return false;
    }
    for (int row = 0; row < rowcount; row++) {
      String sourceid = card.getBodyStringValue(row, OrderItemVO.CSOURCEID);
      String sourceTypeCode =
          card.getBodyStringValue(row, OrderItemVO.CSOURCETYPECODE);
      String sourcebid = card.getBodyStringValue(row, OrderItemVO.CSOURCEBID);
      if (!StringUtils.isEmpty(sourceid)
          || !StringUtils.isEmpty(sourceTypeCode)
          || !StringUtils.isEmpty(sourcebid)) {
        return true;
      }
    }
    return false;

  }

  private boolean haveReceivePlan(BillCardPanel panel) {
    int rows = panel.getRowCount();
    if (0 == rows) {
      return false;
    }
    for (int i = 0; i < rows; ++i) {
      UFBoolean breceiveplan =
          (UFBoolean) panel.getBodyValueAt(i, OrderItemVO.BRECEIVEPLAN);
      if (UFBoolean.TRUE.equals(breceiveplan)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 方法功能描述：是否直运
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-3 下午08:15:54
   */
  private boolean isDirect(BillCardPanel panel) {
    Boolean direct =
        (Boolean) panel.getHeadItem(OrderHeaderVO.BDIRECT).getValueObject();
    return direct == null ? false : direct.booleanValue();
  }

  private void processFilter(BillCardPanel panel) {
    boolean isdirect = this.isDirect(panel);
    // 1.直运且有来源时，只过滤直运的交易类型
    if (isdirect && this.hasSource(panel)) {
      this.filter(panel, isdirect);
    }
    // 2.非直运且有来源时，只过滤非直运的交易类型
    else if (!isdirect && this.hasSource(panel)) {
      this.filter(panel, isdirect);
    }
    // 3.无来源时视为自制，可以参照所有交易类型
    else {
      this.filterByOrg(panel);
    }
  }
}
