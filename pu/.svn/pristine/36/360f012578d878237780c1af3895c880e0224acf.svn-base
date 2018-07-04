package nc.bs.pu.m422x.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 
 * @since 6.5
 * @version 2015-6-17 下午2:12:05
 * @author luojw
 */

public class QueryFor4b32BP extends Abstract422XRefQueryBP{
  
  public QueryFor4b32BP(IQueryScheme queryScheme) {
    super(queryScheme);
  }

  @Override
  public StringBuilder makeGetPKSql() {
    String mainTableAlias = this.getHeadtb();
    String itemTableAlias = this.getItemtb();

    SqlBuilder partWhr = new SqlBuilder();

    partWhr.append(" and " + mainTableAlias + ".dr = 0");
    partWhr.append(" and " + mainTableAlias + ".pk_storereq = "
        + itemTableAlias + ".pk_storereq");

    partWhr.append(" and " + itemTableAlias + ".dr = 0");

    // fanly3 add 2014-01-23 过滤掉来源于维修计划的物资需求申请单行
    partWhr.append(" and ");
    partWhr.append(itemTableAlias + ".csourcetypecode", "=",
        PuRefBillTypeIdEnum.M4B32.getBillTypeId());

    // 按计划岗过滤物料
    FilterForPlanPosUtil.filterMaterialByPos(this.psor, partWhr);
    
    this.psor.appendWhere(partWhr.toString());

    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select distinct " + mainTableAlias + ".pk_storereq,");
    wholeSql.append(itemTableAlias + ".pk_storereq_b ");
    wholeSql.append(this.psor.getFinalFromWhere());

    return wholeSql;
  }
}
