package nc.vo.pu.report.queryinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import nc.vo.pub.SuperVO;

/**
 * �����ѯ����, ������ű�����������Ӧ���ֶκ�class��
 * 
 * @since 6.36
 * @version 2015-6-6 ����3:16:31
 * @author luojw
 */
public class PUReportQueryPara {
  
  /** ��ѯ���� */
  public static final String QUERY_CONDS = "#query_conds#";

  /** sql�����ѡ�񲿷� */  
  private String selectPart;
  
  /** sql����д�����ѡ�񲿷� */  
  private String selectPartName;

  /** sql��������򲿷� */
  private String groupPart;

  /** sql����з��鲿�� */
  private String orderPart;
  
  /** sql�����having���� */
  private String having; 
  
  /** ��Ҫ�ر�������ѡ���ֶ� */
  private Map<String, String> specialNameMap = new HashMap<>();
  
  /** ѡ���ֶ� */
  private Map<String, String[]> selectFieldsMap = new HashMap<>();

  /** �����ֶ� */
  private Map<String, String[]> orderFieldsMap = new TreeMap<>();
  
  /** �����ֶ� */
  private Map<String, String[]> groupFieldsMap = new TreeMap<>();
  
  /** ������ӦdVO�� */
  private Map<String, Class<? extends SuperVO>> clazzMap= new HashMap<>();
  
  /** ���ڵı��� */
  private List<String> tableList = new ArrayList<>();
  
  /** ��������Ϣ */
  private List<TableJoinInfo> joinInfos = new ArrayList<>();

  /** 
   * ��Ӷ�Ӧ���ѡ���ֶ�
   * 
   * @param table
   * @param selectFields
   */
  public void addSelectFields(String table, String[] selectFields) {
    this.addTable(table);
    this.selectFieldsMap.put(table, selectFields);
  }
  
  /** 
   * ����ر�������ѡ���ֶ�
   * 
   * @param table
   * @param selectFields
   */
  public void addSpecialNameField(String field, String name) {
    this.specialNameMap.put(field, name);
  }
  
  /**
   * ��Ӷ�Ӧ��ķ����ֶ�
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
   * ��Ӷ�Ӧ��������ֶ�
   * 
   * @param table
   * @param orderFields
   */
  public void addOrderFields(String table, String[] orderFields) {
    this.addTable(table);
    this.orderFieldsMap.put(table, orderFields);
  }
  
  /**
   * ��Ӷ�Ӧ�����
   * 
   * @param table
   * @param clazz
   */
  public void addClazz(String table, Class<? extends SuperVO> clazz){
    this.clazzMap.put(table, clazz);
  }
  
  /**
   * ���ݱ�����ȡ��Ӧ��class��
   * 
   * @param table
   * @return
   */
  public Class<? extends SuperVO> getClazzs(String table) {
    return this.clazzMap.get(table);
  }

  /**
   * ��ӱ�������Ϣ
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
   * �ж��Ƿ����ĳ�����Է�where��from�ж�û�д˱�����������ӵ��ֶ��иñ�ı���
   * 
   * @param table
   * @return
   */
  public boolean isTableExist(String table){
    return this.tableList.contains(table);
  }
  
  /**
   * ��ʼ��ѡ�񲿷��ֶ�
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
   * ��ʼ��ѡ�񲿷��ֶ�
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
   * ��ʼ�����鲿���ֶμ�having
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
   * ��ʼ�����򲿷��ֶ�
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
   * �ֶκϲ�
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
   * ��ȡѡ�񲿷�sqlƬ��
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
   * ��ȡ���б�����ѡ�񲿷�sqlƬ��
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
   * ��ȡ���鲿��sqlƬ��
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
   * ��ȡ���򲿷�sqlƬ��
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
