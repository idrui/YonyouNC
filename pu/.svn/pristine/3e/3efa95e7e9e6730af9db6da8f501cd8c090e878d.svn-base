package nc.impl.pu.m25.pricequery;

import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;

/**
 * 参考成本查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:30:51
 * @author 田锋涛
 */

public class RefCostPriceQuery extends PlanPriceQuery {

  public RefCostPriceQuery() {
    //
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m25.DefaultPriceQueryImpl.PlanPriceQuery#setFiPrice(nc.vo.pu.m25.entity.InvoicePriceQueryVO,
   *      nc.vo.bd.material.fi.MaterialFiVO)
   */
  @Override
  protected void setFiPrice(InvoicePriceQueryVO paraVo, MaterialFiVO fiVo) {
    paraVo.setNprice(fiVo.getCostprice());
  }

}
