/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-3 上午09:27:21
 */
package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求仓库：按照库存组织过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-3 上午09:27:21
 */
public class Reqstordoc implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String pk_org =
        (String) event.getBillCardPanel()
            .getHeadItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();

    if (StringUtil.isEmptyWithTrim(pk_org)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 过滤需求仓库参照
    this.filterWarehouseRef(event.getBillCardPanel());

    // 设置返回值
    event.setReturnValue(Boolean.TRUE);

  }

  private void filterWarehouseRef(BillCardPanel panel) {
    // 过滤需求仓库参照
    String obj =
        (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();
    String group =
        (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_GROUP)
            .getValueObject();

    UIRefPane pane =
        (UIRefPane) panel.getBodyItem(StoreReqAppItemVO.PK_REQSTORDOC)
            .getComponent();
    if (pane == null) {
      return;
    }
    FilterWareHouseRefUtils filter = new FilterWareHouseRefUtils(pane);
    filter.filterItemRefByGroup(group);
    filter.filterItemRefByOrg(obj);
    filter.filterWasteStorc();
  }
}
