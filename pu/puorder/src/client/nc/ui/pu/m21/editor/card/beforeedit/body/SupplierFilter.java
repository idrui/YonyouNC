/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 上午10:47:12
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据组织过滤承运商
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-16 上午10:47:12
 */
public class SupplierFilter implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {

    BillCardPanel cardPanel = event.getBillCardPanel();

    BillItem carrierItem = cardPanel.getBodyItem(OrderOnwayItemVO.CCARRIER);

    FilterSupplierRefUtils marFilter = new FilterSupplierRefUtils(carrierItem);

    String pk_org =
        (String) cardPanel.getBodyValueAt(event.getRow(),
            OrderOnwayItemVO.PK_ORG);

    marFilter.filterItemRefByOrg(pk_org);

  }

}
