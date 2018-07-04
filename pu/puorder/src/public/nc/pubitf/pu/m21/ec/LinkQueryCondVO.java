package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class LinkQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = -6425109215852432357L;

  private QueryCondition materialPks = null;

  private QueryCondition planArriveDate = null;

  private QueryCondition purchasePk = null;

  public QueryCondition getMaterialPks() {
    return this.materialPks;
  }

  public QueryCondition getPlanArriveDate() {
    return this.planArriveDate;
  }

  public QueryCondition getPurchasePk() {
    return this.purchasePk;
  }

  public void setMaterialPks(QueryCondition materialPks) {
    this.materialPks = materialPks;
  }

  public void setPlanArriveDate(QueryCondition planArriveDate) {
    this.planArriveDate = planArriveDate;
  }

  public void setPurchasePk(QueryCondition purchasePk) {
    this.purchasePk = purchasePk;
  }

}
