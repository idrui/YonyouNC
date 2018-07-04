package nc.vo.pu.m21.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class OrderVOMeta extends AbstractBillMeta {
  public OrderVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(OrderHeaderVO.class);
    this.addChildren(OrderItemVO.class);
    this.addChildren(OrderPaymentVO.class);
  }
}
