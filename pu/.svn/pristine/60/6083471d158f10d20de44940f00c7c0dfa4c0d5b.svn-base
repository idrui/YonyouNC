package nc.impl.pu.report.journal;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.report.journal.IJournalReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.enumeration.GroupEnum;
import nc.vo.pu.report.pub.JournalConvertor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.journal.JournalInfoPara;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 综合日报
 * 
 * @since 6.0
 * @version 2011-9-13 上午08:55:11
 * @author liuchx
 */
public class JournalReportImpl implements IJournalReport {

  private static String SELECT_Feild =
      "  sum(ordernnum) ordernnum, "
          + "  sum(ordernmny) ordernmny,case when sum(ordernmny)=0 then 0 else sum(ordernmny)/(sum(sum(ordernmny)) over())*100 end orderrate, "
          + "  sum(orderntax) orderntax,"
          + "  sum(nacccancelinvmny) nacccancelinvmny ,case when sum(nacccancelinvmny)=0 then 0 else sum(nacccancelinvmny)/(sum(sum(nacccancelinvmny)) over())*100 end nacccancelinvmnyrate,"
          + "  sum(arrivennum) arrivennum,"
          + "  sum(abs(arrivebacknnum)) arrivebacknnum,"
          + "  sum(arrivenmny) arrivenmny,"
          + "  sum(purnnum) purnnum,"
          + "  sum(abs(purbacknnum)) purbacknnum,"
          + "  sum(purnmny) purnmny,"
          + "  sum(invoicennum) invoicennum,"
          + "  sum(invoicetax) invoicetax,"
          + "  sum(invoicenmny) invoicenmny, case when sum(invoicenmny)=0 then 0 else sum(invoicenmny)/(sum(sum(invoicenmny)) over())*100 end invoicerate,"
          + "  sum(settlennum) settlennum," 
          + "  sum(settlenmny) settlenmny,"
          + "  corigcurrencyid corigcurrencyid";

  @Override
  public String getQuerySql(SmartContext context) {
    JournalInfoPara para =
        (JournalInfoPara) context.getAttribute(PuQueryInfoPara.QUERY_PARA);

    // 此处判断只为在格式设计预览的时候不会报错 。
    if (null == para) {
      return new OrderSqlGetter(null, null).getSqlWhenNull();
    }

    ConditionVO[] conditions =
        (ConditionVO[]) context.getAttribute(JournalConvertor.WHERE_CONDS);
    String whereSql = (String) context.getAttribute(JournalConvertor.WHERE_SQL);

    String[] billtypes = para.getBilltypes();
    Integer[] groups = para.getGroups();
    String[] groupFeilds = this.convertFeilds(groups);

    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    for (String group : groupFeilds) {
      sql.append(group);
      sql.append(" ");
      sql.append(group);
      sql.append(",");
    }
    sql.append(JournalReportImpl.SELECT_Feild);
    sql.append(" from (");
    for (int i = 0; i < billtypes.length; i++) {
      SqlGetterFactory sgf = new SqlGetterFactory();
      ISqlGetter sqlGetter = sgf.sqlGetter(billtypes[i], whereSql, conditions);

      sql.append(sqlGetter.getSql(groupFeilds));

      if (i != billtypes.length - 1) {
        sql.append(" union all ");
      }
    }
    sql.append(") journal");
    sql.append(" group by corigcurrencyid");
    for (int i = 0, len = groupFeilds.length; i < len; i++) {
      sql.append("," + groupFeilds[i]);
//      if (i != len - 1) {
//        sql.append(",");
//      }
    }

    return sql.toString();
  }

  private String[] convertFeilds(Integer[] groups) {
    List<String> feilds = new ArrayList<String>();
    for (Integer group : groups) {
      if (group.equals(GroupEnum.DATE.toInteger())) {
        feilds.add("dbilldate");
      } else if (group.equals(GroupEnum.DEPT.toInteger())) {
        feilds.add("pk_dept_v");
      } else if (group.equals(GroupEnum.MAR.toInteger())) {
        feilds.add("pk_material");
      } else if (group.equals(GroupEnum.MARCLASS.toInteger())) {
        feilds.add("pk_marbasclass");
      } else if (group.equals(GroupEnum.MONTH.toInteger())) {
        feilds.add("monthvalue");
      } else if (group.equals(GroupEnum.PSNDOC.toInteger())) {
        feilds.add("pk_psndoc");
      } else if (group.equals(GroupEnum.PUR_ORG.toInteger())) {
        feilds.add("pk_org_v");
      } else if (group.equals(GroupEnum.SUPPLIER.toInteger())) {
        feilds.add("pk_supplier");
      }
    }
    return feilds.toArray(new String[feilds.size()]);
  }
}
