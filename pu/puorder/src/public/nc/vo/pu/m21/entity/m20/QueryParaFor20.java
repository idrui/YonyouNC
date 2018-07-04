package nc.vo.pu.m21.entity.m20;

import nc.vo.pu.m21.query.price.PubPriceQueryPara;

/**
 * 为请购单提供的查询参数
 * 
 * @since 6.0
 * @version 2011-4-19 下午03:14:36
 * @author 田锋涛
 */

public class QueryParaFor20 extends PubPriceQueryPara {
  private static final long serialVersionUID = 1L;

  // 订单交易类型，对应请购单表体订单类型
  private String pk_ordertrantype;

  // 请购单行id
  private String pk_praybill_b;

  public String getPk_ordertrantype() {
    return this.pk_ordertrantype;
  }

  public String getPk_praybill_b() {
    return this.pk_praybill_b;
  }

  public void setPk_ordertrantype(String pk_ordertrantype) {
    this.pk_ordertrantype = pk_ordertrantype;
  }

  public void setPk_praybill_b(String pk_praybill_b) {
    this.pk_praybill_b = pk_praybill_b;
  }

}
