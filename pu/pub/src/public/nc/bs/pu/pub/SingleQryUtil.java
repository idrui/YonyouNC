package nc.bs.pu.pub;

import java.util.HashSet;
import java.util.Set;

import nc.vo.ia.pub.util.ToArrayUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.model.tool.MetaTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * ���ݵ����ѯ����
 * 
 * @since 6.35
 * @version 2015-5-12 ����10:30:30
 * @author wandl
 */

public class SingleQryUtil {

  private SingleQryUtil() {
    // �����಻��Ҫ������
  }

  /**
   * ���ݲ�ѯ����ѯID,�����ڴ��й����ظ�ID
   * 
   * @param sql ����SQL���, Select�Ӿ�ֻ����һ���ֶ�
   * @return String[] ����û���ظ�ID������, ���û�鵽,�򷵻��㳤������
   */
  public static String[] getUniqueIDs(String sql) {
    DataAccessUtils dao = new DataAccessUtils();
    IRowSet rowset = dao.query(sql);
    Set<String> set = new HashSet<String>();
    while (rowset.next()) {
      set.add(rowset.getString(0));
    }
    String[] cbillids = new String[0];
    if (set.size() > 0) {
      cbillids = ToArrayUtil.convert(set);
    }
    return cbillids;
  }

  /**
   * ������Դ��ID��ѯ������ID
   * 
   * @param csrcbids ��Դ�����ӱ���������
   * @return String[] ����û���ظ�ID������, ���û�鵽, �򷵻��㳤������
   */
  public static String[] qryBIDBySRCBIDs(Class<? extends ISuperVO> voClazz,
      String[] csrcids, String[] csrcbids) {
    String table = MetaTool.getMainTable(voClazz).getName();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select cbill_bid from ");
    sql.append(table);
    sql.append(" where dr = 0 ");
    PUIDQueryBuilder tool = new PUIDQueryBuilder();
    sql.append(tool.buildSQL("and csrcid", csrcids));
    sql.append(tool.buildAnotherSQL("and csrcbid", csrcbids));

    String[] cbillids = SingleQryUtil.getUniqueIDs(sql.toString());

    return cbillids;
  }

  /**
   * ������Դ��ID��ѯ����ID
   * 
   * @param csrcbids ��Դ�����ӱ���������
   * @return String[] ����û���ظ�ID������, ���û�鵽, �򷵻��㳤������
   */
  public static String[] qryBillIDBySRCBIDs(Class<? extends ISuperVO> voClazz,
      String[] csrcbids) {
    String table = MetaTool.getMainTable(voClazz).getName();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select cbillid from ");
    sql.append(table);
    sql.append(" where dr = 0 ");
    PUIDQueryBuilder tool = new PUIDQueryBuilder();
    sql.append(tool.buildSQL("and csrcbid", csrcbids));

    String[] cbillids = SingleQryUtil.getUniqueIDs(sql.toString());

    return cbillids;
  }

  /**
   * ������ԴID��ѯ����ID
   * 
   * @param csrcids ��Դ����������������
   * @return String[] ����û���ظ�ID������, ���û�鵽, �򷵻��㳤������
   */
  public static String[] qryBillIDBySRCIDs(Class<? extends ISuperVO> voClazz,
      String[] csrcids) {
    String table = MetaTool.getMainTable(voClazz).getName();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select cbillid from ");
    sql.append(table);
    sql.append(" where dr = 0 ");
    PUIDQueryBuilder tool = new PUIDQueryBuilder();
    sql.append(tool.buildSQL("and csrcid", csrcids));

    String[] cbillids = SingleQryUtil.getUniqueIDs(sql.toString());

    return cbillids;
  }
}
