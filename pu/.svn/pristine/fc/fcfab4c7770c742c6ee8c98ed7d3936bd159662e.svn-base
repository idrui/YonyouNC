/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:42:18
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>项目编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:42:18
 */
public class Projectid implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 参照表头需求库存组织可见的项目档案录入
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterProjectRefUtils filter =
          new FilterProjectRefUtils(event.getBillCardPanel().getBodyItem(
              PraybillItemVO.CPROJECTID));

      filter.filterItemRefByOrg(org);
    }
    event.setReturnValue(Boolean.TRUE);
  }

}
