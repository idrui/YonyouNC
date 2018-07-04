/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午07:41:04
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.pu.reference.so.M30SOServices;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>直运、协同销售订单时结算财务组织不可编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-9 下午07:41:04
 */
public class Psfinanceorg implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();

    Object vcoopordercode =
        panel.getHeadItem(OrderHeaderVO.VCOOPORDERCODE).getValueObject();
    if (!ObjectUtil.isEmptyWithTrim(vcoopordercode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    String csourcetypecode =
        (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
    if (!ObjectUtils.equals(SOBillType.Order.getCode(), csourcetypecode)) {
      event.setReturnValue(Boolean.TRUE);
      return;
    }

    String vsourcetrantype =
        (String) panel.getBodyValueAt(row, OrderItemVO.VSOURCETRANTYPE);
    UFBoolean direct = M30SOServices.queryIsDirectPO(vsourcetrantype);
    if (UFBoolean.TRUE.equals(direct)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);
  }
}
