/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 下午09:43:26
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.pu.reference.ct.Z2CTServices;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.CTBillType;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>合同号
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 下午09:43:26
 */
public class Ccontractid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String csourcetype =
        (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
    if (ObjectUtils.equals(CTBillType.PurDaily.getCode(), csourcetype)) {
      panel.setCellEditable(row, OrderItemVO.CCONTRACTID, false);
      return;
    }

    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(OrderItemVO.CCONTRACTID).getComponent();
    if (null == pane) {
      return;
    }

    String pk_org =
        (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
    String pk_supplier =
        (String) panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    String pk_material =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
    String corigcurrencyid =
        (String) panel.getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
            .getValueObject();
    UFDate dbilldate =
        (UFDate) panel.getHeadItem(OrderHeaderVO.DBILLDATE).getValueObject();
    if (null == pk_org || null == pk_supplier || null == pk_material
        || null == corigcurrencyid || null == dbilldate) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    String wherepart = null;
    try {
      wherepart =
          Z2CTServices.getRefWherePart(pk_org, pk_supplier, pk_material,
              corigcurrencyid, dbilldate.toString());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    pane.getRefModel().setWherePart(wherepart);
    pane.getRefModel().setPara1(pk_org);
    pane.getRefModel().setPara2(dbilldate.toString());
  }

}
