package nc.vo.pu.m4201.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO")
public class PurchaseinFIVO extends AbstractBill {

  private static final long serialVersionUID = -493197123529593215L;

  @Override
  public PurchaseinFIItemVO[] getChildrenVO() {
    return (PurchaseinFIItemVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(PurchaseinFIVOMeta.class);
    return billMeta;
  }

  @Override
  public PurchaseinFIHeaderVO getParentVO() {
    return (PurchaseinFIHeaderVO) super.getParentVO();
  }

}
