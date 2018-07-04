/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:33:23
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求仓库编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:33:23
 */
public class Reqstor implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    Object csourceid =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.CSOURCEID);
    Object directtransit =
        event.getBillCardPanel().getHeadItem(PraybillHeaderVO.BDIRECTTRANSIT)
            .getValueObject();

    if (null != csourceid && csourceid.toString().trim().length() > 0) {
      // 有来源的单据行,表体“需求仓库”项目不可编辑"
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 参照按输入库存组织进行参照；
    // 参照仓库时，除了可以参照出输入的库存组织下的仓库外，还可以参照出能够代储输入的库存组织货物的代储仓。
    FilterWareHouseRefUtils filter =
        new FilterWareHouseRefUtils((UIRefPane) event.getBillCardPanel()
            .getBodyItem(PraybillItemVO.PK_REQSTOR).getComponent());
    filter.filterItemRefByOrg(event.getContext().getPk_org());
    // 过滤废品仓
    filter.filterWasteStorc();
    if (null != directtransit && directtransit.equals(Boolean.TRUE)) {
      // 直运仓
      filter.onlyDirectStorc();
    }
    else {
      // 过滤直运仓
      filter.filterDirectStorc();
    }
    event.setReturnValue(Boolean.TRUE);

  }
}
