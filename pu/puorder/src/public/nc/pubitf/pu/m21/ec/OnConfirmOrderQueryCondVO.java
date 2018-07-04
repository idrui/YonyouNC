package nc.pubitf.pu.m21.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class OnConfirmOrderQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = -2351623996436606086L;

  private QueryCondition planArriveDate = null;

  private QueryCondition purchasePkCond = null;

  public QueryCondition getPlanArriveDate() {
    return this.planArriveDate;
  }

  public QueryCondition getPurchasePkCond() {
    return this.purchasePkCond;
  }

  public void setPlanArriveDate(QueryCondition planArriveDate) {
    this.planArriveDate = planArriveDate;
  }

  public void setPurchasePkCond(QueryCondition purchasePkCond) {
    this.purchasePkCond = purchasePkCond;
  }

}
