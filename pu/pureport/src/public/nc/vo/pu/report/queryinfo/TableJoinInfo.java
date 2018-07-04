package nc.vo.pu.report.queryinfo;

/**
 * �����洢������֮���������Ϣ
 * 
 * @since 6.36
 * @version 2015-6-5 ����4:07:35
 * @author luojw
 */
public class TableJoinInfo {

  /** ���  */
  private String leftTable;
  
  /** �ұ�  */
  private String rightTable;
  
  /** ���ƴ���ֶ� */
  private String leftField;
  
  /** �ұ�ƴ���ֶ� */
  private String rightField;
  
  /** ���� ,�������������ӻ������� */
  private String mainTable;

  /** ���ӷ�ʽ */
  private String joinType;
  
  /** ������ */
  public static final String LEFTJOIN = " left join ";
  
  /** ������ */
  public static final String RIGHTJOIN = " right join ";
  
  /** ������ */
  public static final String INNERJOIN = " inner join ";
  
  /** and */
  public static final String AND = " and ";
  
  /** or */
  public static final String OR = " or ";

  /**
   *
   * @param leftTable  ���
   * @param rightTable �ұ�
   * @param leftField  ����Ӧ�������ֶ�
   * @param rightField �ұ��Ӧ�������ֶ�
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
   * @param leftTable  ���
   * @param rightTable �ұ�
   * @param leftField  ����Ӧ�������ֶ�
   * @param rightField �ұ��Ӧ�������ֶ�
   * @param mainTable  ����, �������������ӻ������� 
   */
  public TableJoinInfo(String leftTable, String rightTable, String leftField,
      String rightField, String mainTable) {
    this(leftTable, rightTable, leftField, rightField);
    this.mainTable = mainTable;
  }

  /**
   * �����������ж��Ƿ���ڱ�������Ϣ
   * 
   * @param left
   * @param right
   * @return
   */
  public boolean isExist(String left, String right){
    if(this.leftTable.equals(left) && this.rightTable.equals(right)){
      return true;
    }
    // ���˳���ˣ���Ҫ�Ե������ֶ�
    if(this.leftTable.equals(right) && this.rightTable.equals(left)){
      this.exchange();
      return true;
    }
    return false;
  }

  /**
   * ���ұ�������Ӧ�������ֶζԵ�
   */
  private void exchange() {
    // ��������
    String tempStr = this.leftTable;
    this.leftTable = this.rightTable;
    this.rightTable = tempStr;
    // ����������Ӧ���ֶ�
    tempStr = this.leftField;
    this.leftField = this.rightField;
    this.rightField = tempStr;
  }

  /**
   * ��ʼ����������
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
   * ��Զ��ⵥ��ı����ӣ��ֶ������ұ�
   * 
   * @param fromPart
   */
  public void joinSingleTable(StringBuilder fromPart, String table){
    this.join(fromPart, table + " " + table);
  }
  
  /**
   * �������Ƭ��ƴ��ǰ��
   * 
   * @param fromPart �Ѿ�ƴ�Ӻõ�fromƬ��
   * @param addFromPart ��Ҫ��ӵ�fromƬ��
   */
  public void join(StringBuilder fromPart, String addFromPart){
    fromPart.append(this.getJoinType()).append(addFromPart)
    .append(" on ")
    .append(this.leftTable).append(".").append(this.leftField)
    .append(" = ")
    .append(this.rightTable).append(".").append(this.rightField);
  }
  
}
