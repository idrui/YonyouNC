/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:40:17
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCustomerRefUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售客户编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:40:17
 */
public class Customer implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 参照的范围为请购单表头库存组织可见的客户
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterCustomerRefUtils filter =
          new FilterCustomerRefUtils(event.getBillCardPanel().getBodyItem(
              PraybillItemVO.CASSCUSTID));

      filter.filterItemRefByOrg(org);
    }

    event.setReturnValue(Boolean.TRUE);
  }

}
