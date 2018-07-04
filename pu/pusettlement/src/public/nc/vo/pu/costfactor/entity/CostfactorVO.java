package nc.vo.pu.costfactor.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * 成本要素定义单据VO
 * 
 * @author GGR
 * @time 2009-12-22 下午04:33:56
 * @since 6.0
 */
public class CostfactorVO extends AbstractBill {
  /** v60单个财务组织最大的成本要素数 **/
  public static final int MAX_NUM = 8;

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 6270381759042457614L;

  @Override
  public CostfactorItemVO[] getChildrenVO() {
    return (CostfactorItemVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(CostfactorVOMeta.class);
    return billMeta;
  }

  @Override
  public CostfactorHeaderVO getParentVO() {
    return (CostfactorHeaderVO) super.getParentVO();
  }

}
