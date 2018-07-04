/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 下午06:29:43
 */
package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 下午06:29:43
 */
public class SendOutNum implements ICardBodyAfterEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {

    int row = event.getRow();
    BillCardPanel cardPanel = event.getBillCardPanel();
    // 取得本次发货数量
    UFDouble sendoutNum =
        (UFDouble) cardPanel.getBodyValueAt(row, OrderOnwayItemVO.NSENDOUTNUM);

    // 取得主数量
    UFDouble num =
        (UFDouble) cardPanel.getBodyValueAt(row, OrderOnwayItemVO.NNUM);

    if (sendoutNum == null) {
      sendoutNum = UFDouble.ZERO_DBL;
    }

    // 如果本次发货数量=主数量,本次发货金额设置为原币无税金额
    UFDouble nstatusmny = UFDouble.ZERO_DBL;
    if (num.equals(sendoutNum)) {
      // 取得原币无税金额
      nstatusmny =
          (UFDouble) cardPanel.getBodyValueAt(row, OrderOnwayItemVO.NORIGMNY);
    }
    // 否则,本次发货金额=主无税净价*本次发货数量
    else {
      // 主无税净价
      UFDouble norignetprice =
          (UFDouble) cardPanel.getBodyValueAt(row,
              OrderOnwayItemVO.NORIGNETPRICE);

      nstatusmny = sendoutNum.multiply(norignetprice);
    }
    cardPanel.setBodyValueAt(nstatusmny, row, OrderOnwayItemVO.NSTATUSMNY);
  }
}
