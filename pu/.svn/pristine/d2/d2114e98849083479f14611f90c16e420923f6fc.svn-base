/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:57:52
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划员编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:57:52
 */
public class Planpsn implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // 参照的范围为当前主组织下属的的人员档案录入
    UIRefPane pane =
        (UIRefPane) event.getBillCardPanel()
            .getHeadItem(PraybillHeaderVO.PK_PLANPSN).getComponent();
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(pane);
    filter.filterItemRefByOrg(event.getContext().getPk_org());

    String cdeptId =
        (String) event.getBillCardPanel()
            .getHeadTailItem(PraybillHeaderVO.PK_PLANDEPT).getValueObject();
    if (cdeptId != null) {
      FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(pane).fixFocusByPK(
          cdeptId);
    }

  }

}
