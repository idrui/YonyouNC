package nc.bs.pu.m422x.query;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 
 * @since 6.5
 * @version 2015-6-17 下午2:12:05
 * @author luojw
 */

public class QueryFor20BP extends Abstract422XRefQueryBP{
  
  public QueryFor20BP(IQueryScheme queryScheme) {
    super(queryScheme);
  }

  @Override
  public StringBuilder makeGetPKSql() {
    String mainTableAlias = this.getHeadtb();
    String itemTableAlias = this.getItemtb();

    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" and ");
    whereSql.append(mainTableAlias + ".fbillstatus",
        (Integer) POEnumBillStatus.APPROVE.value());
    whereSql.append(" and ");
    whereSql.append(mainTableAlias + ".dr = 0");
    whereSql.append(" and " + itemTableAlias + ".dr = 0");
    whereSql.append(" and  isnull(" + itemTableAlias + ".bendgather,'N')", "N");
    whereSql.append(" and ");
    whereSql.append(" isnull(" + itemTableAlias + ".bclose,'N')", "N");
    whereSql.append(" and " + itemTableAlias + ".nnum - isnull(" + itemTableAlias
        + ".naccumbuyreqnum,0) - isnull(" + itemTableAlias
        + ".naccuoutnum,0) > 0");

    // 按计划岗过滤物料
    FilterForPlanPosUtil.filterMaterialByPos(this.psor, whereSql);
    
    this.psor.appendWhere(whereSql.toString());

    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select distinct " + mainTableAlias + ".pk_storereq,");
    wholeSql.append(itemTableAlias + ".pk_storereq_b ");
    wholeSql.append(this.psor.getFinalFromWhere());

    return wholeSql;
  }
  
  @Override
  protected StoreReqAppVO[] processQueryResult(StoreReqAppVO[] queryResult) {
    for(StoreReqAppVO vo : queryResult){
      for(StoreReqAppItemVO item : vo.getBVO()){
        UFDouble naccumbuyreqnum = item.getNaccumbuyreqnum();
        if(naccumbuyreqnum == null){
          naccumbuyreqnum = UFDouble.ZERO_DBL;
        }
        item.setNcanbuyreqnnum(item.getNnum().sub(naccumbuyreqnum));
      }
    }
    return super.processQueryResult(queryResult);
  }
}
