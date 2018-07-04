/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:46:34
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求部门编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:46:34
 */
public class Reqdept implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 参照需求库存组织下属的部门档案录入
    UIRefPane pane =
        (UIRefPane) event.getBillCardPanel().getBodyItem(event.getKey())
            .getComponent();
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC(pane);
    filter.filterItemRefByOrg(event.getContext().getPk_org());
    event.setReturnValue(Boolean.TRUE);
  }

}
