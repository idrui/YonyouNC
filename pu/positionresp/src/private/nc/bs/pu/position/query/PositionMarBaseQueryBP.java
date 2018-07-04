package nc.bs.pu.position.query;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionItemVO;
import nc.vo.pu.position.entity.PositionTVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class PositionMarBaseQueryBP {
  /**
   * 根据采购组织和物料、物料基本分类、岗位类型查询岗位
   * 此时岗位设置必须是根据物料基本分类
   * 
   * @param pk_purchaseorgs 采购组织
   * @param pk_materialoids 物料oid
   * @param pk_marbaseclasses 物料基本分类
   * @param fpositiontype 岗位类型
   * @return key:采购组织+物料或者采购组织+物料分类，value：岗位主键
   */
  public Map<String, String> queryPosition(String[] pk_purchaseorgs,
      String[] pk_materialoids, String[] pk_marbaseclasses, int fpositiontype) {
    Map<String, String> map = new HashMap<String, String>();
    if (pk_materialoids != null && pk_materialoids.length > 0) {
      Map<String, String> marmap =
          this.getPositionByMar(pk_purchaseorgs, pk_materialoids, fpositiontype);
      if (marmap.size() > 0) {
        map.putAll(marmap);
      }
    }
    if (pk_marbaseclasses != null && pk_marbaseclasses.length > 0) {
      Map<String, String> baseMap =
          this.getPositionByOnlyMarBaseClass(pk_purchaseorgs,
              pk_marbaseclasses, fpositiontype);
      if (baseMap.size() > 0) {
        map.putAll(baseMap);
      }
    }
    return map;
  }

  private String getAllPositionSqlByMar(String[] pk_purchaseorgs,
      String[] materialoids, int fpositiontype) {
    String matsql = this.getPositionSqlByPositionType(fpositiontype);
    String marbasesql = this.getPositionByMarBaseClass(fpositiontype);
    SqlBuilder sql = new SqlBuilder();
    sql.append("select t.pk_org,t.pk_srcmaterial,t.pk_position ");
    sql.append("from (");
    sql.append(matsql + " union all " + marbasesql);
    sql.append(") t");
    sql.append(" where ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_01.name());
    sql.append(idbuilder.buildSQL(" t.pk_org", pk_purchaseorgs));
    sql.append(idbuilder.buildAnotherSQL(" and t.pk_srcmaterial", materialoids));
    return sql.toString();
  }

  /**
   * 根据采购组织和物料查询岗位
   * 
   * @param pk_purchaseorgs 采购组织
   * @param materialoids 物料OID
   * @return key:采购组织+物料OID，value：岗位
   */
  private Map<String, String> getPositionByMar(String[] pk_purchaseorgs,
      String[] materialoids, int fpositiontype) {
    String sql =
        this.getAllPositionSqlByMar(pk_purchaseorgs, materialoids,
            fpositiontype);
    String[][] values =
        new DataAccessUtils().query(sql).toTwoDimensionStringArray();
    Map<String, String> map = new HashMap<String, String>();
    if (null == values || values.length == 0) {
      return map;
    }
    for (String[] value : values) {
      map.put(value[0] + value[1], value[2]);
    }
    return map;
  }

  private String getPositionByMarBaseClass(int fpositiontype) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pt.pk_org,m.pk_source pk_srcmaterial,pt.pk_position ");
    sql.append("from  po_position_t pt inner join  bd_material m ");
    sql.append("on pt.pk_marclass =m.pk_marbasclass ");
    sql.append(" where ");
    sql.append(" pt.dr", 0);
    sql.append(" and pt." + PositionTVO.FPOSITIONTYPE, fpositiontype);
    sql.append(" and m.dr", 0);
    sql.append(" and m.latest", UFBoolean.TRUE);
    sql.append(" and not exists (");
    sql.append("select 1 from po_position_b pob where ");
    sql.append("pob.pk_srcmaterial = m.pk_source ");
    sql.append(" and pob.dr", 0);
    sql.append(")");
    return sql.toString();
  }

  /**
   * 根据组织和物料基本分类查询岗位
   * 
   * @param pk_purchaseorgs 采购组织
   * @param pk_marbaseclasses 物料基本分类
   * @param fpositiontype 岗位
   * @return
   */
  private Map<String, String> getPositionByOnlyMarBaseClass(
      String[] pk_purchaseorgs, String[] pk_marbaseclasses, int fpositiontype) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pt.pk_org,pt.pk_pk_marclass,pt.pk_position ");
    sql.append("from po_position_t ");
    sql.append("where ");
    sql.append("pt." + PositionTVO.FPOSITIONTYPE, fpositiontype);
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_03.name());
    sql.append(idbuilder.buildSQL(" and pt.pk_org", pk_purchaseorgs));
    sql.append(idbuilder.buildAnotherSQL(" and pt.pk_pk_marclass",
        pk_marbaseclasses));
    sql.append(" and pt.dr", 0);
    String[][] values =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    Map<String, String> map = new HashMap<String, String>();
    if (values == null || values.length == 0) {
      return map;
    }
    for (String[] value : values) {
      map.put(value[0] + value[1], value[2]);
    }
    return map;
  }

  private String getPositionSqlByPositionType(int fpositiontype) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select pb.pk_org pk_org,pb.pk_srcmaterial pk_srcmaterial,pb.pk_position pk_position ");
    sql.append("from po_position_b pb  ");
    sql.append("inner join po_position ph on pb.pk_position = ph.pk_position");
    sql.append(" where ");
    sql.append(" ph.dr", 0);
    sql.append(" and ph." + PositionHeaderVO.FPOSITIONTYPE, fpositiontype);
    sql.append(" and pb.dr", 0);
    sql.append(" and pb." + PositionItemVO.FFLAG, EnumUseMove.USE);
    sql.append(" and isnull(pb.pk_srcmaterial,'~')<>'~'");
    return sql.toString();
  }
}
