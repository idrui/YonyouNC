package nc.bs.pu.position.query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class PositionMarPuQueryBP {

  /**
   * 根据采购组织、库存组织、物料、物料采购分类查询岗位
   * 因为计划岗不走物料采购分类，所以只能查询采购岗
   * 
   * @param pk_purchaseorgs 采购组织
   * @param pk_stockorgs 库存组织
   * @param pk_marterialoids 物料OID
   *          三个参数一一对应，大小一致。
   * @return 和参数数组对应的岗位主键
   */
  public String[] queryPuPosition(String[] pk_purchaseorgs,
      String[] pk_stockorgs, String[] pk_marterialoids) {
    Set<String> stockorgset = new HashSet<String>();
    for (String pk_stockorg : pk_stockorgs) {
      if (pk_stockorg != null) {
        stockorgset.add(pk_stockorg);
      }
    }
    String[] querystockorgs =
        stockorgset.toArray(new String[stockorgset.size()]);

    Map<String, String> map =
        this.queryPuPositionMap(pk_purchaseorgs, querystockorgs,
            pk_marterialoids);
    int length = pk_purchaseorgs.length;
    String[] values = new String[length];
    if (map == null || map.size() == 0) {
      return values;
    }
    for (int i = 0; i < length; ++i) {
      if (pk_marterialoids[i] == null) {
        continue;
      }
      String purmarkey = pk_purchaseorgs[i] + pk_marterialoids[i];
      String pk_positon = map.get(purmarkey);
      if (pk_positon == null && pk_stockorgs[i] != null) {
        String purstockmarkey =
            pk_purchaseorgs[i] + pk_stockorgs[i] + pk_marterialoids[i];
        pk_positon = map.get(purstockmarkey);
      }
      if (pk_positon != null) {
        values[i] = pk_positon;
      }
    }

    return values;
  }

  private String getPositionByMarPuClass(String purorgcond, String marcond,
      String stockorgcond) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pt.pk_org,ms.pk_org pk_stockorg");
    sql.append(",m.pk_source pk_srcmaterial,pt.pk_position ");
    sql.append("from po_position_t pt ");
    sql.append("inner join bd_materialstock ms on pt.pk_marclass=ms.pk_marpuclass ");
    sql.append("inner join bd_material m on m.pk_material=ms.pk_material ");
    sql.append("where ");
    sql.append("pt.pk_org" + purorgcond);
    sql.append(" and m.pk_source " + marcond);
    sql.append(" and ms.pk_org " + stockorgcond);
    sql.append(" and pt.dr", 0);
    sql.append(" and ms.dr", 0);
    sql.append(" and m.dr", 0);
    sql.append(" and m.latest", UFBoolean.TRUE);
    sql.append(" and not exists (");
    sql.append("select 1 from po_position_b pob where ");
    sql.append("pob.pk_srcmaterial = m.pk_source ");
    sql.append(" and pob.dr", 0);
    sql.append(")");
    return sql.toString();
  }

  private String getPuPositionByMar(String purorgcond, String marcond) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pb.pk_org pk_org,pb.pk_srcmaterial pk_srcmaterial,pb.pk_position pk_position ");
    sql.append("from po_position_b pb where ");
    sql.append("pb.pk_org " + purorgcond);
    sql.append(" and isnull(pb.pk_srcmaterial,'~') " + marcond);
    sql.append(" and pb.dr", 0);
    sql.append(" and pb." + PositionItemVO.FFLAG, EnumUseMove.USE);
    return sql.toString();
  }

  /**
   * 根据采购组织、库存组织、物料、物料采购分类查询岗位
   * 因为计划岗不走物料采购分类，所以只能查询采购岗
   * 
   * @param pk_purchaseorgs 采购组织
   * @param pk_stockorgs 库存组织
   * @param pk_marterialoids 物料OID
   * @return 1）key:采购组织+物料OID，value：岗位主键
   *         2）key：采购组织+库存组织+物料OID，value：岗位主键
   */
  private Map<String, String> queryPuPositionMap(String[] pk_purchaseorgs,
      String[] pk_stockorgs, String[] pk_marterialoids) {
    Map<String, String> map = new HashMap<String, String>();
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_01.name());
    String purorgcond = idbuilder.buildSQL(" ", pk_purchaseorgs);
    String marcond = idbuilder.buildAnotherSQL(" ", pk_marterialoids);

    String marsql = this.getPuPositionByMar(purorgcond, marcond);
    String[][] values =
        new DataAccessUtils().query(marsql).toTwoDimensionStringArray();

    if (values != null && values.length > 0) {
      for (String[] value : values) {
        map.put(value[0] + value[1], value[2]);
      }
    }

    if (pk_stockorgs == null || pk_stockorgs.length == 0) {
      return map;
    }

    IDExQueryBuilder stockorgbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_02.name());
    String stockorgcond = stockorgbuilder.buildSQL(" ", pk_stockorgs);
    String puclasssql =
        this.getPositionByMarPuClass(purorgcond, marcond, stockorgcond);
    String[][] puvalues =
        new DataAccessUtils().query(puclasssql).toTwoDimensionStringArray();
    if (puvalues != null && puvalues.length > 0) {
      for (String[] puvalue : puvalues) {
        map.put(puvalue[0] + puvalue[1] + puvalue[2], puvalue[3]);
      }
    }
    return map;
  }
}
