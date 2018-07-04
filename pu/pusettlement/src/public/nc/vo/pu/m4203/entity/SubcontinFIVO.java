package nc.vo.pu.m4203.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 委托加工入财务聚合VO
 * 
 * @since 6.0
 * @version 2011-1-19 下午02:22:07
 * @author zhaoyha
 */
public class SubcontinFIVO extends AbstractBill {

  private static final long serialVersionUID = -493197123529593215L;

  @Override
  public SubcontinFIItemVO[] getChildrenVO() {
    return (SubcontinFIItemVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(SubcontinFIVOMeta.class);
    return billMeta;
  }

  @Override
  public SubcontinFIHeaderVO getParentVO() {
    return (SubcontinFIHeaderVO) super.getParentVO();
  }

}
