package nc.vo.pu.m4203.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

public class SubcontinFIVOMeta extends AbstractBillMeta {
  public SubcontinFIVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(SubcontinFIHeaderVO.class);
    this.addChildren(SubcontinFIItemVO.class);
  }
}
