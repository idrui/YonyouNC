package nc.vo.pu.m27.entity;

/**
 * 字段对应名称的数据集
 * 
 * @author zhangshqb
 */
public class NameToFieldDataSet implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String tableName; // 要查询的表名

  private String fieldName; // 要查询的字段名

  private String conField; // 条件字段名

  private String conFieldValue; // 条件字段名对应的值

  /**
   * 字段对应名称的数据集
   * 
   * @param tableName 要查询的表名
   * @param fieldName 要查询的字段名
   * @param conField 条件字段名
   * @param conFieldValue 条件字段名对应的值
   */
  public NameToFieldDataSet(String tableName, String fieldName,
      String conField, String conFieldValue) {
    this.tableName = tableName;
    this.fieldName = fieldName;
    this.conField = conField;
    this.conFieldValue = conFieldValue;
  }

  /**
   * 获取要查询的表名
   * 
   * @return
   */
  public String getTableName() {
    return this.tableName;
  }

  /**
   * 获取要查询的字段名
   * 
   * @return
   */
  public String getFieldName() {
    return this.fieldName;
  }

  /**
   * 获取条件字段名
   * 
   * @return
   */
  public String getConField() {
    return this.conField;
  }

  /**
   * 获取条件字段名对应的值
   * 
   * @return
   */
  public String getConFieldValue() {
    return this.conFieldValue;
  }
}
