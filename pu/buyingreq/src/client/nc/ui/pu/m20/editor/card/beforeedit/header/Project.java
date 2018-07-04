/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 下午04:14:37
 */
package nc.ui.pu.m20.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头项目编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-9 下午04:14:37
 */
public class Project implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    // 参照表头需求库存组织可见的项目档案录入
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterProjectRefUtils filter =
          new FilterProjectRefUtils(event.getBillCardPanel().getHeadItem(
              PraybillHeaderVO.CHPROJECTID));

      filter.filterItemRefByOrg(org);
    }
  }

}
