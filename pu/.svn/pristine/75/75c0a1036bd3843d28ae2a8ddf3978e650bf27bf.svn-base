package nc.pubimpl.pu.tbb.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.TempTable;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

public class TbbTempTableDefine {

  private static final int MAX_IN_COUNT = 100;

  /** 地区分类临时表名 */
  private static final String TEM_AREACL = "TEM_SCM_AREACL";

  /** 物料分类临时表名 */
  private static final String TEM_MATERCL = "TEM_SCM_MATERCL";

  /** 项目临时表名 */
  private static final String TEM_PROJECT = "TEM_SCM_PROJECT";

  /** 供应商分类临时表名 */
  private static final String TEM_VENDORCL = "TEM_SCM_VENDORCL";

  /** 部门临时表名 */
  private static final String TEMP_DEPT = "TEM_SCM_DEPT";

  private Map<String, String> tempTableNameMap = new HashMap<String, String>();

  public String buildAnotherSQL(String name, String tableType, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    int length = ids.length;
    if (length <= TbbTempTableDefine.MAX_IN_COUNT) {
      sql.append(name, ids);
    }
    else {
      sql.append(name);
      sql.append(" in ");
      sql.startParentheses();
      sql.append(" select id1 from ");
      String tempTable = this.getTempTableName(tableType);
      sql.append(tempTable);
      sql.endParentheses();
    }
    return sql.toString();
  }

  /**
   * 根据传入的ID值构造查询条件，传入的ID的值不能重复，也不能为空
   *
   * @param name sql字段名
   * @param ids 要查询的ID数组
   * @return 返回的查询条件，不会以 and开始
   */
  public String buildSQL(String name, String tableType, String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    int length = ids.length;
    if (length <= TbbTempTableDefine.MAX_IN_COUNT) {
      sql.append(name, ids);
    }
    else {
      sql.append(name);
      sql.append(" in ");
      sql.startParentheses();
      sql.append(" select id1 from ");
      String tempTable = this.createTempTable(ids, tableType);

      sql.append(tempTable);
      sql.endParentheses();
    }
    return sql.toString();
  }

  private String createTempTable(String[] ids, String tableType) {
    String tableName = null;
    if (DocConst.BDDEPT.equals(tableType)) {
      tableName = this.get(ids, TbbTempTableDefine.TEMP_DEPT);
    }
    else if (DocConst.BDARERCL.equals(tableType)) {
      tableName = this.get(ids, TbbTempTableDefine.TEM_AREACL);
    }
    else if (DocConst.MATCLASS.equals(tableType)) {
      tableName = this.get(ids, TbbTempTableDefine.TEM_MATERCL);
    }
    else if (DocConst.BDPROJECT.equals(tableType)) {
      tableName = this.get(ids, TbbTempTableDefine.TEM_PROJECT);
    }
    else if (DocConst.BDVENDORCLASS.equals(tableType)) {
      tableName = this.get(ids, TbbTempTableDefine.TEM_VENDORCL);
    }
    else {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0","04004090-0002")/*@res "不支持此种数据类型的临时表！"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
    this.tempTableNameMap.put(tableType, tableName);
    return tableName;
  }

  private String get(String[] ids, String tableName) {
    List<List<Object>> data = new ArrayList<List<Object>>();

    int length = ids.length;
    for (int i = 0; i < length; i++) {
      List<Object> row = new ArrayList<Object>();
      data.add(row);
      row.add(ids[i]);
    }
    String[] columns = {
      "id1"
    };
    String[] columnTypes = {
      "CHAR(20)"
    };
    JavaType[] types = new JavaType[] {
      JavaType.String
    };
    TempTable dao = new TempTable();
    String name =
        dao.getTempTable(tableName, columns, columnTypes, types, data);
    return name;
  }

  private String getTempTableName(String tableType) {
    String tableName = this.tempTableNameMap.get(tableType);
    if (StringUtils.isEmpty(tableName)) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0","04004090-0003")/*@res "未创建此种数据类型的临时表！"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
    return tableName;
  }
}