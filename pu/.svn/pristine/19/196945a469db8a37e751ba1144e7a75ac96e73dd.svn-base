package nc.vo.pu.report.queryinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.pub.smart.context.SmartContext;
import nc.vo.pu.pub.sql.PUBeanSqlView;
import nc.vo.pub.SuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 采购处理报表查询的工具类
 * 
 * @since 6.36
 * @version 2015-6-08 下午3:24:59
 * @author luojw
 */
public abstract class PUAbstractReportQueryView {

  /** 每个表名对应的条件 */
  private Map<String, List<ConditionVO>> conditionsMap = new HashMap<>();

  /** 前后台传递条件的变量, 用于权限的控制 */
  private SmartContext context;

  /** sql语句中from部分 */
  private StringBuilder fromPart = new StringBuilder();

  /** 判断某个表名的from片段是已经加入 */
  private Map<String, Boolean> isTableAddMap = new HashMap<>();

  /** 可能需要用到的表名,有子类传人。 下面的三个map的key都是表名 */
  private String[] mayUseTables;

  /** 报表查询参数 */
  private PUReportQueryPara queryPara;

  /** 每个表名对应的工具view */
  private Map<String, PUBeanSqlView> viewMap = new HashMap<>();

  /** sql语句中where部分 */
  private StringBuilder wherePart = new StringBuilder();

  public PUAbstractReportQueryView(ConditionVO[] conditions, String[] tables) {
    this.mayUseTables = tables;
    this.initConds(conditions);
  }

  public PUAbstractReportQueryView(ConditionVO[] conditions, String[] tables,
      SmartContext context) {
    this(conditions, tables);
    this.context = context;
  }

  /**
   * 获取sql的from部分
   * 
   * @return
   */
  public StringBuilder getFromPart() {
    if (this.fromPart.length() == 0) {
      // 合并from片段
      this.combineFromPart();
      // 添加权限
      this.appendRptPermission();
      // 添加固定的表连接
      this.addFixFromPart();
    }
    return this.fromPart;
  }

  /**
   * 获取最终的sql语句
   * 
   * @return
   */
  public String getViewSql() {
    return this.getViewSql(this.queryPara.getSelectPart());
  }

  /**
   * 获取最终的sql语句, 选择字段带别名
   * 
   * @return
   */
  public String getViewSqlName() {
    return this.getViewSql(this.queryPara.getSelectPartName());
  }

  /**
   * 获取sql的where部分
   * 
   * @return
   */
  public StringBuilder getWherePart() {
    if (this.wherePart.length() == 0) {
      // 合并where片段
      this.combineWherePart();
      // 添加固定的条件
      this.addFixWherePart();
    }
    return this.wherePart;
  }

  /**
   * 合并每个表对应的from片段，主要是加入连接信息
   * 1、从where部分或查询参数中获取需要连接的表名
   * 2、如果只有一个表，不处理表连接，直接加入from片段
   * 3、如果多个表，做一次二层循环，保证每两个表都能有交叉
   * 4、在查询参数中判断是否存在两个表的连接信息，来判断是否添加from片段
   * 5、有个map用来存储table表对应的from片段是否已经加入，防止重复加入
   * 6、每次循环始终让rightTable取当前遍历得到的表名，与leftTable做链接
   * 7、如果连接成功，则leftTable 取 rightTable，即第二次需要连接的左表，等于上一次连接的右表
   * 8、如果不成功，leftTable不变，继续遍历表名赋值给rightTable
   */
  private void combineFromPart() {
    // 获取存在from部分需要加入的所有表名
    List<String> existTableList = this.getExistTables();
    int length = existTableList.size();

    // existTables中，肯定存在一个表。先将第一个表加入。
    String leftTable = existTableList.get(0);
    this.realJoin(leftTable, null);
    String rightTable = null;
    // 如果length>1, 再去处理其他表的连接
    for (int j = 1; j < length; j++) {
      rightTable = existTableList.get(j);
      this.join(leftTable, rightTable);
    }

    // 如果length>2, 需要做一个额外的二层循环
    // 上面可以看成一个i=0的一次二层循环, 原理是一样的。
    for (int i = 1; i < length - 1; i++) {
      leftTable = existTableList.get(i);
      for (int j = i + 1; j < length; j++) {
        rightTable = existTableList.get(j);
        this.join(leftTable, rightTable);
      }
    }
  }

  /**
   * 合并每个表对应的where片段
   */
  private void combineWherePart() {
    for (PUBeanSqlView view : this.getViewMap().values()) {
      if (!PubAppTool.isNull(view.getViewWherePart())) {
        this.appendWherePart(view.getViewWherePart()).appendWherePart(
            TableJoinInfo.AND);
      }
    }
    this.wherePart.delete(this.wherePart.lastIndexOf(TableJoinInfo.AND),
        this.wherePart.length());
  }

  /**
   * 判断是否可以填加
   * 
   * @param rightTable
   * @return
   */
  private String getAddTable(String leftTable, String rightTable) {
    boolean isLeftAdd = this.isAdd(leftTable);
    boolean isRightAdd = this.isAdd(rightTable);
    if (!isLeftAdd && isRightAdd) {
      return leftTable;
    }
    else if (isLeftAdd && !isRightAdd) {
      return rightTable;
    }
    return null;
  }

  /**
   * 获取存在from部分需要加入的所有表名
   * 1、where条件中存在的tables，保证每个where条件都有对应的表别名
   * 2、queryPara中传进的字段中包括的表名
   * 
   * @return
   */
  private List<String> getExistTables() {
    List<String> existTables = new ArrayList<>();
    for (String table : this.mayUseTables) {
      if (this.getWherePart().indexOf(table) >= 0
          || this.queryPara.isTableExist(table)) {
        existTables.add(table);
      }
    }
    return existTables;
  }

  private Map<String, PUBeanSqlView> getViewMap() {
    if (this.viewMap.size() == 0) {
      this.initBeanSqlView();
    }
    return this.viewMap;
  }

  private String getViewSql(String selectPart) {
    StringBuilder sql = new StringBuilder();
    sql.append("select ");
    sql.append(selectPart);
    sql.append(" from ");
    sql.append(this.getFromPart());
    sql.append(" where ");
    sql.append(this.getWherePart());
    sql.append(this.queryPara.getGroupPart());
    sql.append(this.queryPara.getOrderPart());

    return sql.toString();
  }

  /**
   * 对工具view进行设置
   */
  private void initBeanSqlView() {
    for (String table : this.conditionsMap.keySet()) {
      Class<? extends SuperVO> clazz = this.queryPara.getClazzs(table);
      this.viewMap.put(table, new PUBeanSqlView(clazz));
    }
  }

  /**
   * 对传递进来的总条件按表名分组，存入conditionsMap
   * 
   * @param conditions
   */
  private void initConds(ConditionVO[] conditions) {
    for (ConditionVO condition : conditions) {
      String fieldCode = condition.getFieldCode();
      String first = fieldCode.split("\\.")[0];
      for (String table : this.mayUseTables) {
        if (!table.equals(first)) {
          continue;
        }
        List<ConditionVO> cond = this.conditionsMap.get(table);
        if (cond == null) {
          cond = new ArrayList<>();
          this.conditionsMap.put(table, cond);
        }
        cond.add(condition);
      }
    }
  }

  /**
   * 判断是否已经加过
   * 
   * @param rightTable
   * @return
   */
  private boolean isAdd(String rightTable) {
    Boolean isAdd = this.isTableAddMap.get(rightTable);
    return isAdd != null && isAdd.booleanValue();
  }

  /**
   * 根据两表是否有表连接信息、是否已经添加过，来判断是否做真正的表连接
   * 
   * @param leftTable
   * @param rightTable
   * @return 如果连接成功返回有表，否则返回左表
   */
  private String join(String leftTable, String rightTable) {
    List<TableJoinInfo> joinInfos = this.queryPara.getJoinInfos();
    for (TableJoinInfo joinInfo : joinInfos) {
      // 判断是否存在两表的连接信息
      if (joinInfo.isExist(leftTable, rightTable)) {
        // 如果没有添加, 才可以去做真正的连接
        String addTable = this.getAddTable(leftTable, rightTable);
        if (addTable != null) {
          return this.realJoin(addTable, joinInfo);
        }
      }
    }
    return leftTable;
  }

  /**
   * 根据参数判断后，做真正的表连接
   * 
   * @param rightTable
   * @param joinInfo
   * @return 返回连接成功后有表
   */
  private String realJoin(String rightTable, TableJoinInfo joinInfo) {
    PUBeanSqlView view = this.viewMap.get(rightTable);
    String addFromPart = null;
    // 如果没有该表名对应的工具，则只加一个表名
    if (view == null) {
      addFromPart = rightTable + " " + rightTable;
    }
    else {
      addFromPart = view.getViewFromPart();
    }
    // 如果没有表连接信息，可能是第一次添加，直接添加
    if (joinInfo == null) {
      this.appendFromPart(addFromPart);
    }
    else {
      joinInfo.join(this.getFromPart(), addFromPart);
    }
    // 添加成功后，将对应表名加个默认的dr = 0
    this.appendWherePart(TableJoinInfo.AND).appendWherePart(rightTable)
        .appendWherePart(".dr = 0");
    // 添加成功后，将对应表名的标志位设位true，以防重复添加
    this.isTableAddMap.put(rightTable, Boolean.TRUE);
    return rightTable;
  }

  /**
   * 增加固定的表连接信息
   */
  protected void addFixFromPart() {
  }

  /**
   * 增加固定的条件
   */
  protected void addFixWherePart() {
  }

  /**
   * 添加from部分
   * 
   * @return
   */
  protected PUAbstractReportQueryView appendFromPart(String from) {
    this.fromPart.append(from);
    return this;
  }

  /**
   * 添加权限表连接
   */
  protected void appendRptPermission() {
  }

  /**
   * 添加where部分
   * 
   * @param where
   * @return
   */
  protected PUAbstractReportQueryView appendWherePart(String where) {
    this.wherePart.append(where);
    return this;
  }

  protected SmartContext getContext() {
    return this.context;
  }

  /**
   * 处理from部分和where部分，主要是参照档案类型的
   * 此方法由在子类的构造方法中调用，需要在queryPara参数设置之后
   */
  protected void processFromAndWhere() {
    for (String table : this.mayUseTables) {
      PUBeanSqlView view = this.getViewMap().get(table);
      if (view != null) {
        view.processFromAndWhere(this.conditionsMap.get(table).toArray(
            new ConditionVO[0]));
      }
    }
  }

  /**
   * 设置报表查询参数
   * 
   * @param queryPara
   */
  protected void setQueryPara(PUReportQueryPara queryPara) {
    this.queryPara = queryPara;
  }

}
