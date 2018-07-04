package nc.impl.pu.report.estmothstat.sqlbuilder;

import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 暂估月统计查询sql构造
 * 
 * @since 6.0
 * @version 2011-8-22 下午01:52:08
 * @author 田锋涛
 */

public abstract class AbstractEstMonthStatSqlBuilder {

  /** 追加的where条件 */
  private String addWhereSql;

  private PuEstStatQryInfoPara queryPara;

  private String selectFromWhere = " Select * from ";

  public AbstractEstMonthStatSqlBuilder(PuEstStatQryInfoPara queryPara) {
    this.queryPara = queryPara;
  }

  public boolean containSupplier() {
    // 语义模型生成元数据时可能为空。
    if (this.getQueryPara() == null) {
      return false;
    }

    String where = this.getQueryPara().getDlgWherePart();
    if (StringUtils.isEmpty(where)) {
      return false;
    }
    // 供应商直接拼的主键，但有供应商分类时需关联供应商表
    return where.contains("pk_areacl");
  }

  public String getAddWhereSql() {
    return this.addWhereSql;
  }

  /**
   * 统计费用未冲销时部分需加以下条件
   * 
   * @return
   */
  public String getFeeUnclashAddWhere() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" (");
    // 费用未结算或者结算了但未传成本,统计的是本期之后的回冲部分
    sql.appendIDIsNull("ef.pk_firstsettle");//
    sql.append("     or  exists ");
    sql.append("      ( select 1 from po_settlebill_b sb2 ");
    sql.append("         inner join po_settlebill sh2 on sb2.pk_settlebill=sh2.pk_settlebill");
    sql.append("         where sb2.dr=0 and sh2.dr=0 and sb2.pk_settlebill_b = ef.pk_firstsettle_b  ");
    sql.append("          and isnull(sh2.btoia,'N')='Y'");
    if (this.getQueryPara() != null) {// 语义模型点完成时可能为空。
      // 统计的是本期之后的回冲部分
      sql.append("          and sh2.dbilldate", ">", this.getQueryPara()
          .getEndDate().toString());
    }
    sql.append("      )");
    sql.append("  )");
    return sql.toString();
  }

  public PuEstStatQryInfoPara getQueryPara() {
    return this.queryPara;
  }

  /**
   * 查询语句，各个子类实现
   * 
   * @return
   */
  public abstract String getQuerySql();

  public String getSelectFromWhere() {
    return this.selectFromWhere;
  }

  public void setAddWhereSql(String addWhereSql) {
    this.addWhereSql = addWhereSql;
  }

  public void setQueryPara(PuEstStatQryInfoPara queryPara) {
    this.queryPara = queryPara;
  }

  public void setSelectFromWhere(String selectFromWhere) {
    this.selectFromWhere = selectFromWhere;
  }
}
