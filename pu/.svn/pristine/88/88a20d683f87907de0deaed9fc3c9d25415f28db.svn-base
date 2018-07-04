/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午09:05:09
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>仓库:参照库存组织下属的仓库档案录入，要求先录入库存组织。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午09:05:09
 */
public class Stordoc implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent event) {
    String pk_stockorg =
        (String) event.getBillCardPanel()
            .getHeadItem(InitialEstHeaderVO.PK_STOCKORG).getValueObject();

    if (StringUtil.isEmptyWithTrim(pk_stockorg)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    // 过滤仓库参照
    this.filterWarehouseRef(event.getBillCardPanel());

    // 设置返回值
    event.setReturnValue(Boolean.TRUE);
  }

  private void filterWarehouseRef(BillCardPanel panel) {
    // 过滤需求仓库参照
    String obj =
        (String) panel.getHeadItem(InitialEstHeaderVO.PK_STOCKORG)
            .getValueObject();

    UIRefPane pane =
        (UIRefPane) panel.getHeadItem(InitialEstHeaderVO.PK_STORDOC)
            .getComponent();
    if (pane == null) {
      return;
    }
    FilterWareHouseRefUtils filter = new FilterWareHouseRefUtils(pane);
    filter.filterItemRefByOrg(obj);
  }

}
