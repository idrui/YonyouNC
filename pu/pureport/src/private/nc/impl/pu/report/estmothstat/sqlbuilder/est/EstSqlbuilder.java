package nc.impl.pu.report.estmothstat.sqlbuilder.est;

import java.util.HashMap;

import nc.impl.pu.report.estmothstat.sqlbuilder.AbstractEstMonthStatSqlBuilder;
import nc.impl.pu.report.estmothstat.sqlbuilder.settle.SettleSqlbuilder;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @since 6.0
 * @version 2011-8-22 ÏÂÎç03:08:31
 * @author Ìï·æÌÎ
 */

public class EstSqlbuilder extends SettleSqlbuilder {

  /**
   * @param queryPara
   */
  public EstSqlbuilder(PuEstStatQryInfoPara queryPara) {
    super(queryPara);
  }

  /**
   * 
   */
  @Override
  protected void initSqlBuilder() {
    this.sqlBuilder = new HashMap<String, AbstractEstMonthStatSqlBuilder>();
    this.sqlBuilder.put(ICBillType.PurchaseIn.getCode(), new M45EstSqlbuilder(
        this.getQueryPara()));
    this.sqlBuilder.put(ICBillType.SubContinIn.getCode(), new M47EstSqlbuilder(
        this.getQueryPara()));
    this.sqlBuilder.put(POBillType.InitEstimate.getCode(),
        new M4TEstSqlbuilder(this.getQueryPara()));
    this.sqlBuilder.put(ICBillType.VmiSum.getCode(),
        new M50EstSqlbuilder(this.getQueryPara()));
  }
}
