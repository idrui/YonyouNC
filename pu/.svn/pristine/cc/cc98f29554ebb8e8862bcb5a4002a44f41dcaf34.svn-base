/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:55:23
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划部门编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:55:23
 */
public class Plandept implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    this.filterDept(event);
  }

  private void filterDept(CardHeadTailBeforeEditEvent event) {
    // 参照的范围为请购单当前主组织下属的部门组织档案
    UIRefPane pane =
        (UIRefPane) event.getBillCardPanel()
            .getHeadItem(PraybillHeaderVO.PK_PLANDEPT_V).getComponent();
    FilterDeptRefUtils filter =
        FilterDeptRefUtils.createFilterDeptRefUtilsOfIC(pane);
    filter.filterItemRefByOrg(event.getContext().getPk_org());
  }
}
