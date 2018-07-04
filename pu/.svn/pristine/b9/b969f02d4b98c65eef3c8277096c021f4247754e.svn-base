/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-3 下午03:03:06
 */
package nc.ui.pu.m422x.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>申请人
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-3 下午03:03:06
 */
public class Apppsn implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    String cdeptid =
        (String) panel.getBodyValueAt(event.getRow(),
            StoreReqAppItemVO.PK_APPDEPT);

    if (cdeptid != null) {
      BillItem bi = panel.getBodyItem(StoreReqAppItemVO.PK_APPPSN);
      UIRefPane pane = (UIRefPane) bi.getComponent();
      FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(pane).fixFocusByPK(
          cdeptid);
    }

    // this.filterApppsnRef(panel, event.getRow());
    // 设置返回值
    event.setReturnValue(Boolean.TRUE);
  }

  // private void filterApppsnRef(BillCardPanel panel, int row) {
  // // 过滤申请人参照
  // ReferenceFilterApppsn filter =
  // new ReferenceFilterApppsn(panel, StoreReqAppItemVO.PK_APPDEPT,
  // StoreReqAppItemVO.PK_APPPSN);
  // filter.filterPsn(row);
  // }
}
