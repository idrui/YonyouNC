package nc.vo.pu.report.queryinfo.estdiffer;

import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;

/**
 * 暂估差异表查询报表参数
 * 
 * @since 6.1
 * @version 2012-8-17 下午04:02:52
 * @author tianft
 */
public class PuEstDifferQryInfoPara extends PuEstStatQryInfoPara {

  private static final long serialVersionUID = 4385620516185743141L;

  /** 本月暂估本月结算 -日期sql */
  private String curEstCurSettleDateSql;

  /** 差异率报警标准 */
  private String differRateStdValue;

  /** 只查询差异率超过报警标准的记录 */
  private boolean onlyQueryOverDifferRate = false;

  public String getCurEstCurSettleDateSql() {
    return this.curEstCurSettleDateSql;
  }

  @Override
  public String getCurStockCurSettleDateSql() {
    return this.getCurEstCurSettleDateSql();
  }

  public String getDifferRateStdValue() {
    return this.differRateStdValue;
  }

  @Override
  public String[] getGroupKeys() {
    // 用平台的分组机制会造成计算差异率不正确，所以不使用平台的分组机制。
    return new String[0];
  }

  @Override
  public String[] getTotalKeys() {
    // 此处添加合计字段会使供应商、物料等分组字段无法查询出来，造成数据不正确。
    // 返回空会造成空指针，故返回空数组。
    return new String[0];
  }

  public boolean isOnlyQueryOverDifferRate() {
    return this.onlyQueryOverDifferRate;
  }

  public void setCurEstCurSettleDateSql(String curEstCurSettleDateSql) {
    this.curEstCurSettleDateSql = curEstCurSettleDateSql;
  }

  public void setDifferRateStdValue(String differRateStdValue) {
    this.differRateStdValue = differRateStdValue;
  }

  public void setOnlyQueryOverDifferRate(boolean onlyQueryOverDifferRate) {
    this.onlyQueryOverDifferRate = onlyQueryOverDifferRate;
  }

}
