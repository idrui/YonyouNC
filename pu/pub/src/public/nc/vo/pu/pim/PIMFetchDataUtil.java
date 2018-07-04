package nc.vo.pu.pim;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.scmpub.pim.fetch.DefaultFetchDataForPim;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 项目管理抓取数据工具类
 * 
 * @since 6.0
 * @version 2014-7-14 下午3:44:42
 * @author wangweir
 */

public class PIMFetchDataUtil {

  /**
   * 
   */
  private static final String SR_FOR_PIM = "SRForPIM";

  /**
   * @param beginDateTime
   * @param endDateTime
   * @param cprojectids
   * @param tableName
   * @param projectfield
   * @param datetimefield
   * @return
   */
  public static String buildCommonWherePart(UFDateTime beginDateTime,
      UFDateTime endDateTime, String[] cprojectids, String tableName,
      String projectfield, String datetimefield) {
    Set<String> cprojectidSet = new HashSet<String>();
    cprojectidSet.addAll(Arrays.asList(cprojectids));

    SqlBuilder sql = new SqlBuilder();
    sql.append(tableName + "." + StoreReqAppItemVO.DR, 0);
    IDExQueryBuilder inSQLBuilder =
        new IDExQueryBuilder(PIMFetchDataUtil.SR_FOR_PIM);
    String inSQL =
        inSQLBuilder.buildSQL(projectfield,
            cprojectidSet.toArray(new String[0]));

    sql.append(" and " + inSQL);
    sql.append(" and " + datetimefield + " >='" + beginDateTime + "' and "
        + datetimefield + "  <='" + endDateTime + "'");
    return sql.toString();
  }

  /**
   * @param cprojectid
   * @param pkSrcmaterial
   * @return
   */
  public static String buildGroupByPart(String cprojectid, String pkSrcmaterial) {
    StringBuilder groupByPart = new StringBuilder(" group by ");
    groupByPart.append(cprojectid).append(", ");
    groupByPart.append(pkSrcmaterial).append(" ");
    return groupByPart.toString();
  }

  /**
   * @return
   */
  public static String buildSelectPart(String projectfield,
      String materialfield, String numfiled) {
    StringBuilder selectSql = new StringBuilder();
    selectSql.append(" select ");
    selectSql.append(projectfield).append(" ")
        .append(DefaultFetchDataForPim.CPROJECTID).append(", ");
    selectSql.append(materialfield).append(" ")
        .append(DefaultFetchDataForPim.MATERIALID).append(", ");
    selectSql.append(" sum(coalesce(").append(numfiled).append(", 0.0)) ")
        .append(DefaultFetchDataForPim.NNUM);
    return selectSql.toString();
  }

}
