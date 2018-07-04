package nc.vo.pu.ec;

import java.io.Serializable;

import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * @since 6.0
 * @version 2011-5-16 ÏÂÎç03:46:47
 * @author wuxla
 */

public class BasePUQueryECCondVO implements Serializable {
  private static final long serialVersionUID = -3567847419387167378L;

  private QueryCondition billdateCond;

  private QueryCondition matnameCond;

  private QueryCondition pk_org;

  private QueryCondition purorgNameCond;

  private QueryCondition supplierCond;

  public QueryCondition getBilldateCond() {
    return this.billdateCond;
  }

  public QueryCondition getMatnameCond() {
    return this.matnameCond;
  }

  public QueryCondition getPk_org() {
    return this.pk_org;
  }

  public QueryCondition getPurorgNameCond() {
    return this.purorgNameCond;
  }

  public QueryCondition getSupplierCond() {
    return this.supplierCond;
  }

  public void setBilldateCond(QueryCondition billdateCond) {
    this.billdateCond = billdateCond;
  }

  public void setMatnameCond(QueryCondition matnameCond) {
    this.matnameCond = matnameCond;
  }

  public void setPk_org(QueryCondition pk_org) {
    this.pk_org = pk_org;
  }

  public void setPurorgNameCond(QueryCondition purorgNameCond) {
    this.purorgNameCond = purorgNameCond;
  }

  public void setSupplierCond(QueryCondition supplierCond) {
    this.supplierCond = supplierCond;
  }

}
