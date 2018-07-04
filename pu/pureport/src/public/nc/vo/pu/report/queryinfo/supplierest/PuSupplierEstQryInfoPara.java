package nc.vo.pu.report.queryinfo.supplierest;

import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2011-3-22 上午11:35:54
 * @author yinfy
 */

public class PuSupplierEstQryInfoPara extends PuQueryInfoPara {
  private static final long serialVersionUID = 4261430916314384475L;

  /**
   * 起始日期
   */
  private UFDate begindate = null;

  /**
   * 统计内容,可选值为：采购入库暂估、委托加工入库暂估、消耗汇总暂估
   */
  private String[] billtypes;

  /**
   * 已全部结算入库单是否统计
   */
  private boolean bincldfinish = true;

  /**
   * 结束日期
   */
  private UFDate enddate = null;

  /**
   * 查询模板中的查询条件sql
   */
  private String wheresql = null;

  public UFDate getBegindate() {
    // 日期必输，这里返回值，拼sql的时候不考虑空判断
    if (this.begindate == null) {
      return new UFDate("1000-01-01");
    }
    return this.begindate;
  }

  public String[] getBilltypes() {
    return this.billtypes;
  }

  public UFDate getEnddate() {
    // 日期必输，这里返回值，拼sql的时候不考虑空判断
    if (this.enddate == null) {
      return new UFDate("9999-12-30");
    }
    return this.enddate;
  }

  public String getWheresql() {
    return this.wheresql;
  }

  public boolean isBincldfinish() {
    return this.bincldfinish;
  }

  public void setBegindate(UFDate begindate) {
    this.begindate = begindate;
  }

  public void setBilltypes(String[] billtypes) {
    this.billtypes = billtypes;
  }

  public void setBincldfinish(boolean bincldfinish) {
    this.bincldfinish = bincldfinish;
  }

  public void setEnddate(UFDate enddate) {
    this.enddate = enddate;
  }

  public void setWheresql(String wheresql) {
    this.wheresql = wheresql;
  }
}
