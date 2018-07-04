package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillTotalListener;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;

public class TotalListener implements BillTotalListener {
  private BillCardPanel billCard;

  public TotalListener(BillCardPanel billcard) {
    this.billCard = billcard;
  }

  @Override
  public UFDouble calcurateTotal(String key) {
    UFDouble total = new UFDouble();
    if (this.billCard != null) {
      int nCount = this.billCard.getRowCount();
      for (int i = 0; i < nCount; i++) {
        UFDouble num =
            ValueUtils.getUFDouble(this.billCard.getBodyValueAt(i, key));
        if (key.equals(OrderItemVO.NORIGTAXMNY)
            || key.equals(OrderItemVO.NORIGMNY)
            || key.equals(OrderItemVO.NTAXMNY) || key.equals(OrderItemVO.NTAX)
            || key.equals(OrderItemVO.NMNY)
            || key.equals(OrderItemVO.NGLOBALMNY)
            || key.equals(OrderItemVO.NGLOBALTAXMNY)) {
          UFBoolean blargess =
              ValueUtils.getUFBoolean(this.billCard.getBodyValueAt(i,
                  OrderItemVO.BLARGESS));
          if (num != null && blargess != null && !blargess.booleanValue()) {
            total = total.add(num);
          }
        }
        else {
          if (num != null) {
            total = total.add(num);
          }
        }
      }

    }
    return total;
  }

}
