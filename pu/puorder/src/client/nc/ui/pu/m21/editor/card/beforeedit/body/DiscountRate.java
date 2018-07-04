package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.PUSysParamUtil;

import org.apache.commons.lang.StringUtils;

/**
 * 折扣
 * 修改订单时，如果订单行已经询过折扣规则，则修改时不允许修改折扣。如果参数“PO84调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价”
 * 为调折扣，则无税金额、价税合计、含税净价、无税净价也不允许手工编辑。
 * 
 * @since 6.0
 * @version 2012-10-25 上午09:20:55
 * @author liuyand
 */
public class DiscountRate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_discount =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_DISCOUNT);
    // 询过折扣（折扣规则编码不为空），折扣字段不允许编辑
    if (StringUtils.isNotBlank(pk_discount)) {
      event.setReturnValue(Boolean.FALSE);

      // 取参数：调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价。
      PUParaValue.po84 po84 =
          PUSysParamUtil.getPO84((String) panel.getBodyValueAt(row,
              OrderItemVO.PK_ORG));

      // 如果参数“PO84调整无税金额、价税合计、含税净价、无税净价时调整折扣还是单价”为调折扣，则无税金额、价税合计、含税净价、无税净价也不允许手工编辑
      if (PUParaValue.po84.adjust_discount.equals(po84)) {
        panel.setCellEditable(row, OrderItemVO.NORIGMNY, false);
        panel.setCellEditable(row, OrderItemVO.NORIGTAXMNY, false);
        panel.setCellEditable(row, OrderItemVO.NQTORIGTAXNETPRC, false);
        panel.setCellEditable(row, OrderItemVO.NQTORIGNETPRICE, false);
      }
    }
    else {
      event.setReturnValue(Boolean.TRUE);
    }
  }
}
