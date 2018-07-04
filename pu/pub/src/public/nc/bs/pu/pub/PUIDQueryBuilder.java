package nc.bs.pu.pub;

import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

/**
 * ���ݴ����ID�����������ѯsql������ ���ID�٣���in SQL ���ID�࣬����ʱ�� ID�в����п�ֵ
 * 
 * @since 6.36
 * @version 2015-5-12 ����10:28:53
 * @author wandl
 */
public class PUIDQueryBuilder {

  private static final String PU_TEMP_TABLE_PREFIX = "TEMP_PO_";

  /**
   * ���ݴ����IDֵ�����ѯ�����������ID��ֵ���� �ظ���Ҳ����Ϊ�ա�
   * <p>
   * �����ǵĳ����У����������Ҫ��ѯ����ID����ʱ��ֻ��һ����ʱ���ǲ����õġ� ������Ĭ��һ���µ���ʱ���Ա�ҵ �����ʹ��.
   * 
   * @param name sql�ֶ���
   * @param ids Ҫ��ѯ��ID����
   * @return ���صĲ�ѯ������������ and��ʼ
   */
  public String buildAnotherSQL(String name, String[] ids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUIDQueryBuilder.PU_TEMP_TABLE_PREFIX);
    String anotherSql = builder.buildAnotherSQL(name, ids);
    return anotherSql;
  }

  /**
   * ���ݴ����IDֵ�����ѯ�����������ID��ֵ�����ظ���Ҳ����Ϊ��
   * 
   * @param name sql�ֶ���
   * @param ids Ҫ��ѯ��ID����
   * @return ���صĲ�ѯ������������ and��ʼ
   */
  public String buildSQL(String name, String[] ids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUIDQueryBuilder.PU_TEMP_TABLE_PREFIX);
    String sql = builder.buildSQL(name, ids);
    return sql;
  }
}
