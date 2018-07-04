package nc.impl.pu.report.estmothstat.sqlbuilder.settle;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pu.report.estmothstat.sqlbuilder.AbstractEstMonthStatSqlBuilder;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-8-22 ����04:48:47
 * @author �����
 */

public class SettleSqlbuilder extends AbstractEstMonthStatSqlBuilder {

  protected Map<String, AbstractEstMonthStatSqlBuilder> sqlBuilder;

  /**
   *
   */
  public SettleSqlbuilder(PuEstStatQryInfoPara queryPara) {
    super(queryPara);
    this.initSqlBuilder();
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(this.getSelectFromWhere());
    String[] billtypes =
        new String[] {
          ICBillType.PurchaseIn.getCode(), ICBillType.SubContinIn.getCode(),
          POBillType.InitEstimate.getCode(), ICBillType.VmiSum.getCode()
        };

    boolean hasSql = false;
    sql.append(" ( ");
    // ����Ҫ��ѯһ�ֵ�������
    for (String billtype : billtypes) {
      // �Ƿ��ѯ�ɸ����Ӳ�ѯ����
      AbstractEstMonthStatSqlBuilder sqlbuilder = this.getSqlbuilder(billtype);
      sqlbuilder.setAddWhereSql(this.getAddWhereSql());
      String builderSql = sqlbuilder.getQuerySql();
      if (StringUtils.isEmpty(builderSql)) {
        continue;
      }
      if (hasSql) {
        sql.append("  union all  ");
      }
      sql.append("   ( ");
      sql.append(builderSql);
      sql.append("   ) ");
      hasSql = true;
    }
    sql.append(" ) ");
    return sql.toString();
  }

  private AbstractEstMonthStatSqlBuilder getSqlbuilder(String billType) {
    return this.sqlBuilder.get(billType);
  }

  /**
   * 
   */
  protected void initSqlBuilder() {
    this.sqlBuilder = new HashMap<String, AbstractEstMonthStatSqlBuilder>();
    this.sqlBuilder.put(ICBillType.PurchaseIn.getCode(),
        new M45SettleSqlbuilder(this.getQueryPara()));
    this.sqlBuilder.put(ICBillType.SubContinIn.getCode(),
        new M47SettleSqlbuilder(this.getQueryPara()));
    this.sqlBuilder.put(POBillType.InitEstimate.getCode(),
        new M4TSettleSqlbuilder(this.getQueryPara()));
    this.sqlBuilder.put(ICBillType.VmiSum.getCode(), new M50SettleSqlbuilder(
        this.getQueryPara()));
  }

}
