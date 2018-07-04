/**
 * 
 */
package nc.ui.pu.m20.editor.arrange.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 上午9:07:59
 */
public class Employee implements ICardBodyBeforeEditEventListener {

  /*
   * 父类方法重写
   * @see
   * nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit
   * (nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object pk_purchaseorg =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_PURCHASEORG);
    if (null == pk_purchaseorg
        || pk_purchaseorg.toString().trim().length() == 0) {
      // 采购组织未录入，采购员不可编辑
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 参照表体采购组织的人员档案录入
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU((UIRefPane) event
            .getBillCardPanel().getBodyItem(PraybillItemVO.PK_EMPLOYEE)
            .getComponent());
    filter.filterItemRefByOrg(pk_purchaseorg.toString());
    event.setReturnValue(Boolean.TRUE);
  }

}
