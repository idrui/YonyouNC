package nc.vo.pu.m27.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m27.entity.SettleBillHeaderVO")
public class SettleBillVO extends AbstractBill {

  private static final long serialVersionUID = -493197123529593215L;

  @Override
  public SettleBillItemVO[] getChildrenVO() {
    return (SettleBillItemVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(SettleBillVOMeta.class);
    return billMeta;
  }

  @Override
  public SettleBillHeaderVO getParentVO() {
    return (SettleBillHeaderVO) super.getParent();
  }

}
