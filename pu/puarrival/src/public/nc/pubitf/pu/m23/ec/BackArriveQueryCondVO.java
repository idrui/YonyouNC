package nc.pubitf.pu.m23.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class BackArriveQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = -172676863392162245L;

  /** ÍË»õµ¥ºÅ */
  private QueryCondition billCodeCond = null;

  /** ÏìÓ¦×´Ì¬ */
  private QueryCondition respStatusCond = null;

  public QueryCondition getBillCodeCond() {
    return this.billCodeCond;
  }

  public QueryCondition getRespStatusCond() {
    return this.respStatusCond;
  }

  public void setBillCodeCond(QueryCondition billCodeCond) {
    this.billCodeCond = billCodeCond;
  }

  public void setRespStatusCond(QueryCondition respStatusCond) {
    this.respStatusCond = respStatusCond;
  }

}
