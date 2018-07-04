package nc.ui.pu.m23.editor.card.beforeedit.header;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m23.entity.ArriveHeaderVO;

public class Dept implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {

    BillCardPanel card = e.getBillCardPanel();
    // 采购组织
    String puOrg =
        ArriveClientUtil.getStringHeaderValue(card,
            ArriveHeaderVO.PK_PURCHASEORG);

    // 部门(按采购组织过滤)
    UIRefPane deptRef =
        (UIRefPane) card.getHeadItem(ArriveHeaderVO.PK_DEPT).getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(deptRef)
        .filterItemRefByOrg(puOrg);

    UIRefPane deptRef_v =
        (UIRefPane) card.getHeadItem(ArriveHeaderVO.PK_DEPT_V).getComponent();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(deptRef_v)
        .filterItemRefByOrg(puOrg);

    e.setReturnValue(Boolean.TRUE);
  }
}
