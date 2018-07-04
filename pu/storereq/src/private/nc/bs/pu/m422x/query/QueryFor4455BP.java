package nc.bs.pu.m422x.query;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.query.rule.CanOutreqNumCalcRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2010-12-16 下午01:40:58
 * @author wuxla
 */

public class QueryFor4455BP extends Abstract422XRefQueryBP{
  
  public QueryFor4455BP(IQueryScheme queryScheme) {
    super(queryScheme);
  }

  private void addRule(AroundProcesser<StoreReqAppVO> processer) {
    processer.addAfterRule(new CanOutreqNumCalcRule());
  }

  @Override
  public StringBuilder makeGetPKSql() {
    String mainTableAlias = this.getHeadtb();
    String itemTableAlias = this.getItemtb();

    SqlBuilder partWhr = new SqlBuilder();
    partWhr.append(" and " + mainTableAlias + ".fbillstatus = "
        + POEnumBillStatus.APPROVE.value());
    partWhr.append(" and " + mainTableAlias + ".dr = 0");
    partWhr.append(" and " + mainTableAlias + ".pk_storereq = "
        + itemTableAlias + ".pk_storereq");
    partWhr.append(" and " + itemTableAlias + ".nnum > coalesce("
        + itemTableAlias + ".naccuoutreqnum, 0)");
    partWhr.append(" and " + itemTableAlias + ".dr = 0");
    partWhr.append(" and coalesce(" + itemTableAlias + ".bclose,'N') = 'N' ");
    // fanly3 add 2014-01-23 过滤掉来源于维修计划的物资需求申请单行
    partWhr.append(" and ");
    partWhr.append(itemTableAlias + ".csourcetypecode", "<>",
        PuRefBillTypeIdEnum.M4B32.getBillTypeId());
    partWhr.append(" and " + itemTableAlias + ".pk_material in ( ");
    partWhr
        .append("select bd_material.pk_material from bd_material bd_material ");
    partWhr.append("where " + itemTableAlias
        + ".pk_material = bd_material.pk_material ");
    partWhr
        .append("and bd_material.fee = 'N' and bd_material.discountflag = 'N' ");
    partWhr.append(")");
    
    // 按计划岗过滤物料
    FilterForPlanPosUtil.filterMaterialByPos(this.psor, partWhr);
    
    this.psor.appendWhere(partWhr.toString());

    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select distinct " + mainTableAlias + ".pk_storereq,");
    wholeSql.append(itemTableAlias + ".pk_storereq_b ");
    wholeSql.append(this.psor.getFinalFromWhere());

    return wholeSql;
  }
  
  @Override
  protected StoreReqAppVO[] processQueryResult(StoreReqAppVO[] queryResult) {
    if (ArrayUtils.isEmpty(queryResult)) {
      return null;
    }
    AroundProcesser<StoreReqAppVO> processer =
        new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.PULL_4455);
    this.addRule(processer);
    return processer.after(queryResult);
  }
}
