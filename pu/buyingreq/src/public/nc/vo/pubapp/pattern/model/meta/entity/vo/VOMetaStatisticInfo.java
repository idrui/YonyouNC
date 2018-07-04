package nc.vo.pubapp.pattern.model.meta.entity.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.ITableMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.IVOMetaStatisticInfo;

/**
 * ʵ��Ԫ���ݵ�ͳ�Ƽ�����Ϣ
 * 
 * @since 6.0
 * @version 2008-6-25 ����03:06:41
 * @author ����
 */
public class VOMetaStatisticInfo implements IVOMetaStatisticInfo {
  /**
   * �ɳ��������ֶε�λ������
   */
  private Map<String, Short> perisitentIndex = new HashMap<String, Short>();

  /**
   * �ɳ��������ֶ�
   */
  private IAttributeMeta[] peristentAttributes;

  /**
   * ��������
   */
  private IAttributeMeta[] referenceAttributes;

  /**
   * �����л����ǲ��ɳ��������ֶ�
   */
  private IAttributeMeta[] serializableAttributes;

  /**
   * �����л����ǲ��ɳ��������ֶε�λ������
   */
  private Map<String, Short> serializableIndex = new HashMap<String, Short>();

  /**
   * ��̬����
   */
  private IAttributeMeta[] staticAttributes;

  /**
   * ����������ֶ�����table+"."+column���������Ӧ�������ԵĶ�Ӧ��ϵ
   */
  private Map<String, IAttributeMeta> tableColumnIndex =
      new HashMap<String, IAttributeMeta>();

  /**
   * ��Ӧ�����ݿ��
   */
  private ITableMeta[] tables;

  /**
   * ʵ��Ԫ����
   */
  private IVOMeta voMeta;

  /**
   * ʵ��Ԫ���ݵ�ͳ�Ƽ�����Ϣ���캯��
   * 
   * @param voMeta ʵ��Ԫ����
   */
  public VOMetaStatisticInfo(IVOMeta voMeta) {
    this.voMeta = voMeta;
    this.initPersistentAttribute(voMeta);
    this.initSerializableAttribute(voMeta);
    this.initTables(voMeta);
    this.initStaticAttribute(voMeta);
    this.initReferenceAttribute(voMeta);
    this.initMultiTableColumnMapping(voMeta);
  }

  @Override
  public IAttributeMeta getAttribute(IColumnMeta column) {
    String key = this.getColumnKey(column);
    return this.tableColumnIndex.get(key);
  }

  private String getColumnKey(IColumnMeta field) {
    StringBuffer buffer = new StringBuffer();
    ITableMeta table = field.getTable();
    if (table != null) {
      buffer.append(table.getName());
      buffer.append(".");
    }
    buffer.append(field.getName());
    return buffer.toString();
  }

  @Override
  public IAttributeMeta[] getPerisistentAttributes() {
    return this.peristentAttributes;
  }

  @Override
  public short getPeristentAttributeIndex(String name) {
    return this.perisitentIndex.get(name).shortValue();
  }

  @Override
  public IAttributeMeta getPersistentAttribute(short index) {
    return this.peristentAttributes[index];
  }

  @Override
  public IAttributeMeta[] getReferenceAttributes() {
    return this.referenceAttributes;
  }

  @Override
  public IAttributeMeta getSerializableAttribute(short index) {
    return this.serializableAttributes[index];
  }

  @Override
  public short getSerializableAttributeIndex(String name) {
    return this.serializableIndex.get(name).shortValue();
  }

  @Override
  public IAttributeMeta[] getSerializableAttributes() {
    return this.serializableAttributes;
  }

  @Override
  public IAttributeMeta[] getStaticAttributes() {
    return this.staticAttributes;
  }

  @Override
  public ITableMeta[] getTables() {
    return this.tables;
  }

  @Override
  public IVOMeta getVOMeta() {
    return this.voMeta;
  }

  private void initMultiTableColumnMapping(IVOMeta meta) {
    // ����Ҫ�����������ݿ�Ļ���ֻ��һ�����ݿ���Ӧ�ģ�����Ҫ���д���
    if (this.tables.length < 2) {
      return;
    }
    // ���������������Ӧ�Ĺ����ֶε����Խ���ӳ�䡣��VO�У����Ǹ���VO�е�ͬһ����������
    ITableMeta mainTable = meta.getPrimaryAttribute().getColumn().getTable();
    for (ITableMeta table : this.tables) {
      if (table.equals(mainTable)) {
        continue;
      }
      IColumnMeta childColumn = mainTable.getChildForeignKey(table);
      IColumnMeta fatherColumn = mainTable.getAssociateColumn(table);
      String key = this.getColumnKey(childColumn);
      IAttributeMeta attribute = this.getAttribute(fatherColumn);
      this.tableColumnIndex.put(key, attribute);
    }

  }

  private void initPersistentAttribute(IVOMeta meta) {
    IAttributeMeta[] attributes = meta.getAttributes();
    List<IAttributeMeta> list = new ArrayList<IAttributeMeta>();
    for (IAttributeMeta attribute : attributes) {
      if (attribute.isPersistence()) {
        list.add(attribute);
        String key = this.getColumnKey(attribute.getColumn());
        this.tableColumnIndex.put(key, attribute);
      }
    }
    int size = list.size();
    this.peristentAttributes = new IAttributeMeta[size];
    this.peristentAttributes = list.toArray(this.peristentAttributes);
    for (short i = 0; i < size; i++) {
      this.perisitentIndex.put(this.peristentAttributes[i].getName(),
          Short.valueOf(i));
    }
  }

  private void initReferenceAttribute(IVOMeta meta) {
    IAttributeMeta[] attributes = meta.getAttributes();
    List<IAttributeMeta> list = new ArrayList<IAttributeMeta>();
    for (IAttributeMeta attribute : attributes) {
      if (attribute.getReferenceDoc() != null) {
        list.add(attribute);
      }
    }
    int size = list.size();
    this.referenceAttributes = new IAttributeMeta[size];
    this.referenceAttributes = list.toArray(this.referenceAttributes);
  }

  private void initSerializableAttribute(IVOMeta meta) {
    IAttributeMeta[] attributes = meta.getAttributes();
    List<IAttributeMeta> list = new ArrayList<IAttributeMeta>();
    for (IAttributeMeta attribute : attributes) {
      if (!attribute.isPersistence() && attribute.isSerializable()) {
        list.add(attribute);
      }
    }
    int size = list.size();
    this.serializableAttributes = new IAttributeMeta[size];
    this.serializableAttributes = list.toArray(this.serializableAttributes);
    for (short i = 0; i < size; i++) {
      this.serializableIndex.put(this.serializableAttributes[i].getName(),
          Short.valueOf(i));
    }
  }

  private void initStaticAttribute(IVOMeta meta) {
    IAttributeMeta[] attributes = meta.getAttributes();
    List<IAttributeMeta> list = new ArrayList<IAttributeMeta>();
    for (IAttributeMeta attribute : attributes) {
      if (attribute.isStatic()) {
        list.add(attribute);
      }
    }
    int size = list.size();
    this.staticAttributes = new IAttributeMeta[size];
    this.staticAttributes = list.toArray(this.staticAttributes);
  }

  private void initTables(IVOMeta meta) {
    Map<String, ITableMeta> index = new HashMap<String, ITableMeta>();
    IAttributeMeta[] attributes = meta.getAttributes();
    for (IAttributeMeta attribute : attributes) {
      // ���ǳ������ֶ�
      if (!attribute.isPersistence()) {
        continue;
      }
      IColumnMeta column = attribute.getColumn();
      ITableMeta table = column.getTable();
      // ��ǰ��һ�������ֶ�
      if (table == null) {
        continue;
      }
      if (!index.containsKey(table.getName())) {
        index.put(table.getName(), table);
      }
    }
    int size = index.size();
    this.tables = new ITableMeta[size];
    this.tables = index.values().toArray(this.tables);
  }

/**
 * @return the tableColumnIndex
 */
public Map<String, IAttributeMeta> getTableColumnIndex() {
	return tableColumnIndex;
}

/**
 * @param tableColumnIndex the tableColumnIndex to set
 */
public void setTableColumnIndex(Map<String, IAttributeMeta> tableColumnIndex) {
	this.tableColumnIndex = tableColumnIndex;
}
}
