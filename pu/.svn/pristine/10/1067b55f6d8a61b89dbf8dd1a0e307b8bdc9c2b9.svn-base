package nc.vo.pu.report.queryinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nc.vo.pub.SuperVO;

/**
 * 报表查询参数, 用来存放表名及表名对应的字段和class类
 * 
 * @since 6.36
 * @version 2015-6-6 下午3:16:31
 * @author luojw
 */
public class PUReportQueryPara {
  
  /** 查询条件 */
  public static final String QUERY_CONDS = "#query_conds#";

  /** sql语句中选择部分 */  
  private String selectPart;
  
  /** sql语句中带别名选择部分 */  
  private String selectPartName;

  /** sql语句中排序部分 */
  private String groupPart;

  /** sql语句中分组部分 */
  private String orderPart;
  
  /** sql语句中having部分 */
  private String having; 
  
  /** 需要特别声明的选择字段 */
  private Map<String, String> specialNameMap = new HashMap<>();
  
  /** 选择字段 */
  private Map<String, String[]> selectFieldsMap = new HashMap<>();

  /** 排序字段 */
  private Map<String, String[]> orderFieldsMap = new TreeMap<>();
  
  /** 分组字段 */
  private Map<String, String[]> groupFieldsMap = new TreeMap<>();
  
  /** 表名对应dVO类 */
  private Map<String, Class<? extends SuperVO>> clazzMap= new HashMap<>();
  
  /** 存在的表名 */
  private List<String> tableList = new ArrayList<>();
  
  /** 表连接信息 */
  private List<TableJoinInfo> joinInfos = new ArrayList<>();

  /** 
   * 添加对应表的选择字段
   * 
   * @param table
   * @param selectFields
   */
  public void addSelectFields(String table, String[] selectFields) {
    this.addTable(table);
    this.selectFieldsMap.put(table, selectFields);
  }
  
  /** 
   * 添加特别声明的选择字段
   * 
   * @param table
   * @param selectFields
   */
  public void addSpecialNameField(String field, String name) {
    this.specialNameMap.put(field, name);
  }
  
  /**
   * 添加对应表的分组字段
   * 
   * @param table
   * @param groupFields
   */
  public void addGroupFields(String table, String[] groupFields) {
    this.addTable(table);
    this.groupFieldsMap.put(table, groupFields);
  }

  private void addTable(String table) {
    if(this.tableList.contains(table)){
      return;
    }
    this.tableList.add(table);
  }

  /**
   * 添加对应表的排序字段
   * 
   * @param table
   * @param orderFields
   */
  public void addOrderFields(String table, String[] orderFields) {
    this.addTable(table);
    this.orderFieldsMap.put(table, orderFields);
  }
  
  /**
   * 添加对应表的类
   * 
   * @param table
   * @param clazz
   */
  public void addClazz(String table, Class<? extends SuperVO> clazz){
    this.clazzMap.put(table, clazz);
  }
  
  /**
   * 根据表名获取对应的class类
   * 
   * @param table
   * @return
   */
  public Class<? extends SuperVO> getClazzs(String table) {
    return this.clazzMap.get(table);
  }

  /**
   * 添加表连接信息
   * 
   * @param joinInfo
   */
  public void addJoinInfos(TableJoinInfo joinInfo) {
    this.joinInfos.add(joinInfo);
  }

  public List<TableJoinInfo> getJoinInfos() {
    return this.joinInfos;
  }
  
  /**
   * 判断是否存在某个表，以防where和from中都没有此表名，但是添加的字段有该表的别名
   * 
   * @param table
   * @return
   */
  public boolean isTableExist(String table){
    return this.tableList.contains(table);
  }
  
  /**
   * 初始化选择部分字段
   * 
   */
  private void initSelectPart() {
    String fieldsPart = this.getFieldsPart(this.selectFieldsMap);
    if(fieldsPart.length() == 0){
      this.selectPart = "*";
    }else{
      this.selectPart = fieldsPart.substring(0, fieldsPart.length() - 2);
    }
  }
  
  /**
   * 初始化选择部分字段
   * 
   */
  private void initSelectPartName() {
    StringBuilder fieldsPart = new StringBuilder();
    for(String table : this.tableList){
      for(String field : this.selectFieldsMap.get(table)){
        String code = table + "." + field;
        String name = this.specialNameMap.remove(code);
        if(name == null){
          name = field;
        }
        fieldsPart.append(code)
        .append(" ").append(name).append(", ");
      }
    }
    for(Entry<String, String> entry : this.specialNameMap.entrySet()){
      fieldsPart.append(entry.getKey()).append(" ")
      .append(entry.getValue()).append(", ");
    }
    if(fieldsPart.length() == 0){
      this.selectPartName = "*";
    }else{
      this.selectPartName = fieldsPart.substring(0, fieldsPart.length() - 2);
    }
  }
  
  /**
   * 初始化分组部分字段及having
   * 
   */
  private void initGroupPart() {
    String fieldsPart = this.getFieldsPart(this.groupFieldsMap);
    if(fieldsPart.length() == 0){
      this.groupPart = "";
    }else{
      String haveStr = this.having == null ? "" : this.having;
      this.groupPart = " group by " + fieldsPart.substring(0, fieldsPart.length() - 2) + haveStr;
    }
  }

  /**
   * 初始化排序部分字段
   * 
   */
  private void initOrderPart() {
    String fieldsPart = this.getFieldsPart(this.orderFieldsMap);
    if(fieldsPart.length() == 0){
      this.orderPart = "";
    }else{
      this.orderPart = " order by " + fieldsPart.substring(0, fieldsPart.length() - 2);
    }
  }

  /**
   * 字段合并
   * 
   * @param fields
   * @return
   */
  private String getFieldsPart(Map<String, String[]> fields) {
    StringBuilder fieldsPart = new StringBuilder();
    for(Entry<String, String[]> entry : fields.entrySet()){
      for(String field : entry.getValue()){
        fieldsPart.append(entry.getKey())
        .append(".").append(field).append(", ");
      }
    }
    return fieldsPart.toString();
  }
  
  /**
   * 获取选择部分sql片段
   * 
   * @return
   */
  public String getSelectPart(){
    if(this.selectPart == null){
      this.initSelectPart();
    }
    return this.selectPart;
  }
  
  /**
   * 获取带有别名的选择部分sql片段
   * 
   * @return
   */
  public String getSelectPartName(){
    if(this.selectPartName == null){
      this.initSelectPartName();
    }
    return this.selectPartName;
  }
  
  /**
   * 获取分组部分sql片段
   * 
   * @return
   */
  public String getGroupPart(){
    if(this.groupPart == null){
      this.initGroupPart();
    }
    return this.groupPart;
  }
  
  /**
   * 获取排序部分sql片段
   * 
   * @return
   */
  public String getOrderPart(){
    if(this.orderPart == null){
      this.initOrderPart();
    }
    return this.orderPart;
  }
  
}
