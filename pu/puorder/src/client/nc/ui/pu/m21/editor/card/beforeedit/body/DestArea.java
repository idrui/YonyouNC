/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午05:15:41
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterCountryAreaRefUtils;
import nc.vo.pu.m21.entity.OrderItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * 目的地区
 * 
 * @since 6.0
 * @version 2012-3-8 上午10:06:08
 * @author tianft
 */
public class DestArea implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();

    String country =
        (String) panel.getBodyValueAt(row, OrderItemVO.CDESTICOUNTRYID);
    if (StringUtils.isEmpty(country)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    FilterCountryAreaRefUtils areaRef =
        new FilterCountryAreaRefUtils(
            panel.getBodyItem(OrderItemVO.CDESTIAREAID));
    areaRef.filterItemRefBy(country);

  }
}
