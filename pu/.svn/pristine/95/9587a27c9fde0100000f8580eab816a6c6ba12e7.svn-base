/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 下午04:50:40
 */
package nc.ui.pu.m25.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购员编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-1 下午04:50:40
 */
public class BizPerson implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    /*
     * 根据公共需求： 编辑人员时，如部门已经有值，则人员参照的界面上光标默认放在对应部门上。
     */
    // BillItem item =
    // e.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_BIZPSN);
    // if (null == item) {
    // return;
    // }
    CardEditorHelper helper = new CardEditorHelper(e.getBillCardPanel());
    // String pk_dept = (String) helper.getHeadValue(InvoiceHeaderVO.PK_DEPT_V);
    // 参照的范围为当前主组织下属的的人员档案录入
    UIRefPane pane =
        (UIRefPane) e.getBillCardPanel()
            .getHeadTailItem(InvoiceHeaderVO.PK_BIZPSN).getComponent();
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(pane);

    // 采购组织
    String purchase_v =
        (String) helper.getHeadValue(InvoiceHeaderVO.PK_PURCHASEORG);
    filter.filterItemRefByOrg(purchase_v);

    // FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(
    // (UIRefPane) item.getComponent()).fixFocusByPK(pk_dept);
    e.setReturnValue(Boolean.TRUE);
  }
}
