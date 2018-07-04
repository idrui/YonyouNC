package nc.bs.pu.pub;

import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.impl.pubapp.pattern.data.vo.VOQuery;

/**
 * vo查询工具类
 * 
 * @since 6.35
 * @version 2015-5-12 上午11:09:36
 * @author wandl
 */
public class VOQryUtil<E extends ISuperVO> {

  private Class<? extends E> voClass;

  public VOQryUtil(Class<? extends E> voClass) {
    this.voClass = voClass;
  }

  /**
   * 根据传入的没有where关键字并以and开头的SQL条件查询完整VO
   * 
   * @param condtion 以and开头的SQL
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByCondition(String condtion) {
    return this.qryByCondition(condtion, null);
  }

  /**
   * 根据传入的没有where关键字并以and开头的SQL条件查询指定字段VO
   * 
   * @param condtion 以and开头的SQL
   * @param fields 要查询的字段
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByCondition(String condtion, String[] fields) {
    VOQuery<E> tools = this.getVOQueryTool(fields);
    return tools.query(condtion, null);
  }

  /**
   * 根据VO主键查询完整VO
   * 
   * @param ids VO主键数组
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByPKs(String[] ids) {
    return this.qryByPKs(ids, null);
  }

  /**
   * 根据VO主键查询指定字段VO
   * 
   * @param ids VO主键数组
   * @param fields 要查询的字段
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByPKs(String[] ids, String[] fields) {
    VOQuery<E> tools = this.getVOQueryTool(fields);
    return tools.query(ids);
  }

  /**
   * 根据指定条件字段ID查询完整VO
   * 
   * @param idField 指定条件字段名称
   * @param idvalues 指定条件字段值数组
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryBySpecField(String idField, String[] idvalues) {
    return this.qryBySpecField(idField, idvalues, "");
  }

  /**
   * 根据指定条件字段ID查询完整VO
   * 
   * @param idField 指定条件字段名称
   * @param idvalues 指定条件字段值数组
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryBySpecField(String idField, String[] idvalues, String condition) {
    return this.qryBySpecField(idField, idvalues, null, condition);
  }

  /**
   * 根据指定条件字段ID查询指定字段VO
   * 
   * @param idField 指定条件字段名称
   * @param idvalues 指定条件字段值数组
   * @param fields 要查询的字段
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryBySpecField(String idField, String[] idvalues, String[] fields) {
    return this.qryBySpecField(idField, idvalues, fields, null);
  }

  /**
   * 根据指定条件字段ID查询指定字段VO
   * 
   * @param idField 指定条件字段名称
   * @param idvalues 指定条件字段值数组
   * @param fields 要查询的字段
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryBySpecField(String idField, String[] idvalues, String[] fields,
      String condition) {
    PUIDQueryBuilder builder = new PUIDQueryBuilder();
    StringBuilder where = new StringBuilder();
    where.append(" and ").append(builder.buildSQL(idField, idvalues));
    if (!PubAppTool.isNull(condition)) {
      where.append(" and ").append(condition);
    }
    VOQuery<E> tools = this.getVOQueryTool(fields);
    return tools.query(where.toString(), null);
  }

  /**
   * 根据完整SQL查询完整VO
   * 
   * @param wholeSql 完整的SQL语句
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByWholeSql(String wholeSql) {
    E[] instances = this.qryByWholeSql(wholeSql, null);
    return instances;
  }

  /**
   * 根据完整SQL查询指定属性的VO
   * 
   * @param wholeSql
   * @param fields
   * @return E[], 查询结果, 如果没查到则返回零长度数组
   */
  public E[] qryByWholeSql(String wholeSql, String[] fields) {
    String[] ids = SingleQryUtil.getUniqueIDs(wholeSql);
    if (ids.length == 0) {
      return Constructor.construct(this.voClass, 0);
    }
    VOQuery<E> tools = this.getVOQueryTool(fields);
    return tools.query(ids);
  }

  /**
   * 根据参数判断使用哪个查询工具构造器
   * 
   * @param fields
   * @return VOQuery
   */
  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private VOQuery<E> getVOQueryTool(String[] fields) {
    VOQuery<E> tools = null;
    if (fields == null || fields.length == 0) {
      tools = new VOQuery(this.voClass);
    }
    else {
      tools = new VOQuery(this.voClass, fields);
    }
    return tools;
  }

}
