/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午11:00:45
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保管员
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午11:00:45
 */
public class Managepsn implements ICardHeadTailBeforeEditEventListener {

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

    // 过滤保管员参照
    this.filterManagepsn(event.getBillCardPanel());

    // 设置返回值
    event.setReturnValue(Boolean.TRUE);
  }

  private void filterManagepsn(BillCardPanel panel) {
    String pk_stockorg =
        (String) panel.getHeadItem(InitialEstHeaderVO.PK_STOCKORG)
            .getValueObject();

    // 过滤保管员参照
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC((UIRefPane) panel
            .getHeadTailItem(InitialEstHeaderVO.PK_MANAGEPSN).getComponent());
    filter.filterItemRefByOrg(pk_stockorg);
  }

}
