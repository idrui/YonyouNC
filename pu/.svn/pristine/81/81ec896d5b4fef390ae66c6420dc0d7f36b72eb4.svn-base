package nc.vo.pu.position.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 计划岗（采购岗）物料设置的单据VO
 * 
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 * @since 6.0
 */
public class PositionVO extends AbstractBill {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  public PositionItemVO[] getBVO() {
    return (PositionItemVO[]) this.getChildrenVO();
  }

  public PositionHeaderVO getHVO() {
    return (PositionHeaderVO) this.getParent();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(PositionVOMeta.class);
    return billMeta;
  }

  public void setBVO(PositionItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(PositionHeaderVO voaParent) {
    this.setParent(voaParent);
  }
}
