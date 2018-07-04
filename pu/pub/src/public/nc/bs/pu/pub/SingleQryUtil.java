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
 * 单据单表查询工具
 * 
 * @since 6.35
 * @version 2015-5-12 上午10:30:30
 * @author wandl
 */

public class SingleQryUtil {

  private SingleQryUtil() {
    // 工具类不需要构造器
  }

  /**
   * 根据查询语句查询ID,并在内存中过滤重复ID
   * 
   * @param sql 完整SQL语句, Select子句只包含一个字段
   * @return String[] 返回没有重复ID的数组, 如果没查到,则返回零长度数组
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
   * 根据来源行ID查询单据行ID
   * 
   * @param csrcbids 来源单据子表主键数组
   * @return String[] 返回没有重复ID的数组, 如果没查到, 则返回零长度数组
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
   * 根据来源行ID查询单据ID
   * 
   * @param csrcbids 来源单据子表主键数组
   * @return String[] 返回没有重复ID的数组, 如果没查到, 则返回零长度数组
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
   * 根据来源ID查询单据ID
   * 
   * @param csrcids 来源单据主表主键数组
   * @return String[] 返回没有重复ID的数组, 如果没查到, 则返回零长度数组
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
