/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午05:15:41
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.pu.reference.so.M30SOServices;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterStockOrgRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>如果是直运，参照范围为结算财务组织下属的库存组织档案.
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-9 下午05:15:41
 */
public class Arrvstoorg implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();

    String csourcetypecode =
        (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
    if (!ObjectUtils.equals(SOBillType.Order.getCode(), csourcetypecode)) {
      return;
    }

    String vsourcetrantype =
        (String) panel.getBodyValueAt(row, OrderItemVO.VSOURCETRANTYPE);
    UFBoolean direct = M30SOServices.queryIsDirectPO(vsourcetrantype);
    if (!UFBoolean.TRUE.equals(direct)) {
      return;
    }

    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(OrderItemVO.PK_ARRVSTOORG_V)
            .getComponent();
    if (null == pane) {
      return;
    }

    String pk_psfinanceorg =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_PSFINANCEORG);
    if (StringUtil.isEmptyWithTrim(pk_psfinanceorg)) {
      return;
    }

    FilterStockOrgRefUtils util =
        new FilterStockOrgRefUtils(
            panel.getBodyItem(OrderItemVO.PK_ARRVSTOORG_V));
    util.setGroup((String) panel.getHeadItem(OrderHeaderVO.PK_GROUP)
        .getValueObject());
    pane.getRefModel().addWherePart(
        " and pk_financeorg = '" + pk_psfinanceorg + "'");
  }
}
