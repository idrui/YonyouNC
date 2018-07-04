package nc.ui.pu.m25.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>部门编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-3-4 下午06:55:23
 */
public class Dept implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    this.filterDept(event);
  }

  private void filterDept(CardHeadTailBeforeEditEvent event) {
    // 采购组织
    Object purchase_v = event.getBillCardPanel()
        .getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG).getValueObject();
    if(purchase_v == null|| purchase_v.toString().trim().length() == 0){
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    UIRefPane pane =
        (UIRefPane) event.getBillCardPanel().getHeadItem(event.getKey())
            .getComponent();
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(pane);
    filter.filterItemRefByOrg(purchase_v.toString());
  }
}
