package nc.vo.pu.m4201.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class PurchaseinFIVOMeta extends AbstractBillMeta {
  public PurchaseinFIVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(PurchaseinFIHeaderVO.class);
    this.addChildren(PurchaseinFIItemVO.class);
  }
}
