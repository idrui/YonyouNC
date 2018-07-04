package nc.bs.pu.report.arrive;

import java.util.ArrayList;
import java.util.List;

import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.arrival.ArrivePlanNotQueryView;
import nc.vo.pu.report.queryinfo.arrival.ArrivePlanQueryView;
import nc.vo.pu.report.queryinfo.arrival.PuArrivalQryInfoPara;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class DayArriveReportBP {

  public String getDayArrvQuerySql(SmartContext context) {
    // 取得查询条件
    ConditionVO[] generalConds =
        (ConditionVO[]) context.getAttribute(PuQueryInfoPara.QUERY_CONDS);
    // 有到货计划的SQL
    String dayArrvSql = this.getDayArriveSql(context, generalConds);
    // 没有到货计划的SQL
    String notDayArrvSql = this.getNoDayArriveSql(context, generalConds);
    // union all操作后的SQL
    String unionSql = this.getUnionSql(dayArrvSql, notDayArrvSql);
    // 外层包装
    String wrappedSql = this.getWrappedSql(unionSql);
    // 最终SQL
    String finalSql = this.getFinalSql(wrappedSql);
    return finalSql;
  }

  /**
   * 构建有到货计划的SQL
   * 
   * @param context 语义模型上下文对象
   * @param condVos 查询对话框中传递过来的ConditionVO数组
   * @param where 查询对话框传递过来的where条件
   * @return 返回有到货计划的SQL
   */
  private String getDayArriveSql(SmartContext context, ConditionVO[] condVos) {
    ConditionVO[] orderBB1Conds =
        (ConditionVO[]) context.getAttribute(PuArrivalQryInfoPara.ORDER_BB1_COND);
    List<ConditionVO> condList = new ArrayList<>();
    this.addCondToList(condVos, condList);
    this.addCondToList(orderBB1Conds, condList);
    ArrivePlanQueryView sqlview = new ArrivePlanQueryView(condList.toArray(new ConditionVO[0]), context);
    return sqlview.getViewSqlName();
  }

  /**
   * 将条件加到list中
   * 
   * @param condVos
   * @param condList
   */
  private void addCondToList(ConditionVO[] condVos, List<ConditionVO> condList) {
    if(condVos != null){
      for(ConditionVO cond : condVos){
        condList.add(cond);
      }
    }
  }

  /**
   * 构建最终SQL
   * 
   * @param wrappedSql 通过外查询包装之后的SQL
   * @return 返回最终SQL
   */
  private String getFinalSql(String wrappedSql) {
    SqlBuilder finalSql = new SqlBuilder();
    finalSql.append(wrappedSql);
    finalSql.append("order by ");
    finalSql.append(this.getOrderBySql());
    return finalSql.toString();
  }

  /**
   * 构建没有到货计划的SQL
   * 
   * @param context 语义模型上下文对象
   * @param condVos 查询对话框中传递过来的ConditionVO数组
   * @param where 查询对话框传递过来的where条件
   * @return 返回没有到货计划的SQL
   */
  private String getNoDayArriveSql(SmartContext context, ConditionVO[] condVos) {
    ConditionVO[] orderBConds =
        (ConditionVO[]) context.getAttribute(PuArrivalQryInfoPara.ORDER_B_COND);
    List<ConditionVO> condList = new ArrayList<>();
    this.addCondToList(condVos, condList);
    this.addCondToList(orderBConds, condList);
    ArrivePlanNotQueryView sqlview = new ArrivePlanNotQueryView(condList.toArray(new ConditionVO[0]), context);
    return sqlview.getViewSqlName();
  }

  /**
   * 构建order by字符串
   * 
   * @return 返回order by字符串
   */
  private String getOrderBySql() {
    String orderByField = "t.dplanarrvdate";
    return orderByField;
  }

  /**
   * 外层SQL语句的select字段
   * 
   * @return 返回外层SQL语句的select字段
   */
  private String getOuterSelectFields() {
    SqlBuilder selectSql = new SqlBuilder();
    selectSql
        .append("t.dplanarrvdate, t.pk_material, t.castunitid, "
            + "t.nastnum, t.cunitid, t.nnum, t.naccumarrvnum, case when t.nnotaccumarrvnum < 0 then 0 else t.nnotaccumarrvnum end nnotaccumarrvnum, "
            + "t.pk_deliveradd, t.pk_transporttype, t.vbillcode, t.vcoopordercode, "
            + "t.crowno, t.vbillcodearrive, t.pk_supplier, t.vbmemo ");
    return selectSql.toString();
  }

  /**
   * 通过 union all连接两个字符串
   * 
   * @param dayArrvSql 有到货计划的SQL
   * @param notDayArrvSql 没有到货计划的SQL
   * @return 返回通过 union all连接之后的SQL
   */
  private String getUnionSql(String dayArrvSql, String notDayArrvSql) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(dayArrvSql);
    sql.append(" union all ");
    sql.append(notDayArrvSql);
    return sql.toString();
  }

  /**
   * 包装内查询语句
   * 
   * @param unionSql 通过union all连接的有到货计划和没有到货计划的SQL
   * @return 返回包装之后的SQL
   */
  private String getWrappedSql(String unionSql) {
    SqlBuilder outerSql = new SqlBuilder();
    outerSql.append("select ");
    outerSql.append(this.getOuterSelectFields());
    outerSql.append("from (");
    outerSql.append(unionSql.toString());
    outerSql.append(") t ");
    return outerSql.toString();
  }

}
