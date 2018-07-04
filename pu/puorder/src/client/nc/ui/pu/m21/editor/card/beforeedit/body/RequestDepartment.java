package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class RequestDepartment implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 需求库存组织
    String pk_org =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.PK_REQSTOORG);
    if (pk_org == null) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    String csourcetypecode =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            OrderItemVO.CSOURCETYPECODE);
    if (POBillType.PrayBill.getCode().equals(csourcetypecode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    // 表体需求部门参照
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC((UIRefPane) event
            .getBillCardPanel().getBodyItem(OrderItemVO.PK_REQDEPT_V)
            .getComponent());
    filter.filterItemRefByOrg(pk_org);
    event.setReturnValue(Boolean.TRUE);
  }

}
