package nc.bs.pu.pub;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * 根据传入的ID的数量构造查询sql条件。 如果ID少，走in SQL 如果ID多，走临时表 ID中不能有空值
 * 
 * @since 6.36
 * @version 2015-5-12 上午10:28:53
 * @author wandl
 */
public class PUIDQueryBuilder {

  private static final String PU_TEMP_TABLE_PREFIX = "TEMP_PO_";

  /**
   * 根据传入的ID值构造查询条件。传入的ID的值不能 重复，也不能为空。
   * <p>
   * 在我们的程序中，常常会出现要查询两组ID。此时，只有一个临时表是不够用的。 这里再默认一个新的临时表以便业 务代码使用.
   * 
   * @param name sql字段名
   * @param ids 要查询的ID数组
   * @return 返回的查询条件，不会以 and开始
   */
  public String buildAnotherSQL(String name, String[] ids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUIDQueryBuilder.PU_TEMP_TABLE_PREFIX);
    String anotherSql = builder.buildAnotherSQL(name, ids);
    return anotherSql;
  }

  /**
   * 根据传入的ID值构造查询条件，传入的ID的值不能重复，也不能为空
   * 
   * @param name sql字段名
   * @param ids 要查询的ID数组
   * @return 返回的查询条件，不会以 and开始
   */
  public String buildSQL(String name, String[] ids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUIDQueryBuilder.PU_TEMP_TABLE_PREFIX);
    String sql = builder.buildSQL(name, ids);
    return sql;
  }
}
