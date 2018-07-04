package nc.vo.pu.report.queryinfo;

/**
 * 用来存储两个表之间的连接信息
 * 
 * @since 6.36
 * @version 2015-6-5 下午4:07:35
 * @author luojw
 */
public class TableJoinInfo {

  /** 左表  */
  private String leftTable;
  
  /** 右表  */
  private String rightTable;
  
  /** 左表拼接字段 */
  private String leftField;
  
  /** 右表拼接字段 */
  private String rightField;
  
  /** 主表 ,用来决定左连接或右连接 */
  private String mainTable;

  /** 连接方式 */
  private String joinType;
  
  /** 左连接 */
  public static final String LEFTJOIN = " left join ";
  
  /** 右连接 */
  public static final String RIGHTJOIN = " right join ";
  
  /** 内连接 */
  public static final String INNERJOIN = " inner join ";
  
  /** and */
  public static final String AND = " and ";
  
  /** or */
  public static final String OR = " or ";

  /**
   *
   * @param leftTable  左表
   * @param rightTable 右表
   * @param leftField  左表对应的连接字段
   * @param rightField 右表对应的连接字段
   */
  public TableJoinInfo(String leftTable, String rightTable, String leftField,
      String rightField) {
    this.leftTable = leftTable;
    this.rightTable = rightTable;
    this.leftField = leftField;
    this.rightField = rightField;
  }
  
  /**
   * 
   * @param leftTable  左表
   * @param rightTable 右表
   * @param leftField  左表对应的连接字段
   * @param rightField 右表对应的连接字段
   * @param mainTable  主表, 用来决定左连接或右连接 
   */
  public TableJoinInfo(String leftTable, String rightTable, String leftField,
      String rightField, String mainTable) {
    this(leftTable, rightTable, leftField, rightField);
    this.mainTable = mainTable;
  }

  /**
   * 根据两个表，判断是否存在表连接信息
   * 
   * @param left
   * @param right
   * @return
   */
  public boolean isExist(String left, String right){
    if(this.leftTable.equals(left) && this.rightTable.equals(right)){
      return true;
    }
    // 如果顺序反了，需要对调两组字段
    if(this.leftTable.equals(right) && this.rightTable.equals(left)){
      this.exchange();
      return true;
    }
    return false;
  }

  /**
   * 左右表名及对应的连接字段对调
   */
  private void exchange() {
    // 交换表名
    String tempStr = this.leftTable;
    this.leftTable = this.rightTable;
    this.rightTable = tempStr;
    // 交换表名对应的字段
    tempStr = this.leftField;
    this.leftField = this.rightField;
    this.rightField = tempStr;
  }

  /**
   * 初始化连接类型
   * 
   * @param left
   * @param right
   */
  public void initJoinType() {
    if(this.mainTable == null){
      this.joinType = INNERJOIN;
    }else if(this.mainTable.equals(this.leftTable)){
      this.joinType = LEFTJOIN;
    }else if(this.mainTable.equals(this.rightTable)){
      this.joinType = RIGHTJOIN;
    }
  }
  
  private String getJoinType() {
    if(this.joinType == null){
      this.initJoinType();
    }
    return this.joinType;
  }

  /**
   * 针对额外单表的表连接，手动连接右表
   * 
   * @param fromPart
   */
  public void joinSingleTable(StringBuilder fromPart, String table){
    this.join(fromPart, table + " " + table);
  }
  
  /**
   * 将后面的片段拼到前面
   * 
   * @param fromPart 已经拼接好的from片段
   * @param addFromPart 需要添加的from片段
   */
  public void join(StringBuilder fromPart, String addFromPart){
    fromPart.append(this.getJoinType()).append(addFromPart)
    .append(" on ")
    .append(this.leftTable).append(".").append(this.leftField)
    .append(" = ")
    .append(this.rightTable).append(".").append(this.rightField);
  }
  
}
