package nc.vo.pu.upgrade.v636.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 请购单交易类型升级
 * 
 * @since 6.0
 * @version 2015-4-10 下午4:25:03
 * @author luojw
 */
public class PrayarrangeUpgrate {

  public static final DataAccessUtils utils = new DataAccessUtils();

  /**
   * 根据旧主键获取新主键
   * 
   * @param pk
   * @param len 向后增加几位
   * @return
   */
  public static String getNewPk(String pk, int len) {
    char[] chars = pk.toCharArray();
    for (int i = 0; i < len; i++) {
      int index = chars.length - 1;
      chars[index]++;
      while (chars[index] == '9' + 1 || chars[index] == 'Z' + 1) {
        if (chars[index] == '9' + 1) {
          chars[index] = 'A';
        }
        if (chars[index] == 'Z' + 1) {
          chars[index] = '0';
          chars[index - 1]++;
        }
        index--;
      }
    }
    return String.copyValueOf(chars);
  }

  /**
   * 升级
   */
  public static void upgrate() {
    List<String> ids = PrayarrangeUpgrate.getIds();
    SqlBuilder insert = new SqlBuilder();
    insert
        .append("insert into po_buyrtrantype (BNEEDARRANGE, CTRANTYPEID, DR, PK_BUYRTRANTYPE, PK_GROUP, TS, VTRANTYPECODE) values ('N', ?, 0, ?, ?, '");
    insert.append(new UFDateTime().toString());
    insert.append("', ?)");
    JavaType[] types = new JavaType[] {
      JavaType.String, JavaType.String, JavaType.String, JavaType.String
    };
    List<List<Object>> datas = PrayarrangeUpgrate.getAllBilltypes(ids);
    PrayarrangeUpgrate.utils.update(insert.toString(), types, datas);
  }

  /**
   * 获取所有需要添加的交易类型
   * 
   * @param datas
   */
  private static List<List<Object>> getAllBilltypes(List<String> ids) {
    List<List<Object>> datas = new ArrayList<List<Object>>();
    String sql =
        "select pk_billtypeid, pk_group, pk_billtypecode from bd_billtype where nodecode = '40040200' and pk_billtypecode <> '20' and pk_group not in ('~','global00000000000000')";
    IRowSet rs = PrayarrangeUpgrate.utils.query(sql);
    while (rs.next()) {
      String ctrantypeid = rs.getString(0);
      if (ids.contains(ctrantypeid)) {
        continue;
      }
      List<Object> data = new ArrayList<Object>();
      datas.add(data);
      String pk_buyrtrantype = PrayarrangeUpgrate.getNewPk(ctrantypeid, 8);
      data.add(ctrantypeid);
      data.add(pk_buyrtrantype);
      data.add(rs.getString(1));
      data.add(rs.getString(2));
    }
    return datas;
  }

  /**
   * 查询扩展表里已经存在的交易类型id
   * 
   * @return
   */
  private static List<String> getIds() {
    List<String> ids = new ArrayList<String>();
    StringBuilder sql = new StringBuilder();
    sql.append("select ctrantypeid from po_buyrtrantype");
    IRowSet rs = PrayarrangeUpgrate.utils.query(sql.toString());
    while (rs.next()) {
      ids.add(rs.getString(0));
    }
    return ids;
  }
}
