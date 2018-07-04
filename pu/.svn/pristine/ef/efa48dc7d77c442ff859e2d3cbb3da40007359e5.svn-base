/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-12 下午01:51:35
 */
package nc.pubimpl.pu.position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.UserVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-12 下午01:51:35
 */
public class QueryPlanMaterialAction {
  /**
   * 方法功能描述：过滤掉当前操作员(计划员)没有有权限的物料(即查询当前有权限的物料)。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkOrg
   * @param pkOperator
   * @param pkMaterials
   * @return <p>
   *         有权限的物料vid
   * @since 6.0
   * @author GGR
   * @time 2010-7-12 下午01:52:55
   */
  public ArrayList<String> filterMaterialByPlanner(String pkOrg,
      String pkOperator, ArrayList<String> pkMaterials) {
    // 根据vid查询oid
    List<String> pkSrcMaterials = this.getMaterialOids(pkMaterials);
    ArrayList<String> ret = new ArrayList<String>();
    // 物料oid，vid 映射
    MapList<String, String> oidVidMap =
        this.buildOidVidMap(pkMaterials, pkSrcMaterials);
    // 根据物料(oid)查询
    String[][] materials = this.queryPlanByMaterial(pkOrg, pkSrcMaterials);
    HashSet<String> used = new HashSet<String>();
    if (!ArrayUtils.isEmpty(materials)) {
      for (String[] row : materials) {
        if (pkOperator.equals(row[1])
            && EnumUseMove.USE == Integer.parseInt(row[2])) {
          ret.addAll(oidVidMap.get(row[0]));
        }
        // 已经根据此物料查询处理过
        used.addAll(oidVidMap.get(row[0]));
      }
    }

    // 根据物料分类查询
    ret.addAll(this.getByClass(pkOrg, pkOperator, pkMaterials, used));

    return ret;
  }

  /**
   * 根据操作员查询其对应的业务员（计划员）所对应的计划岗ID
   * 
   * @param pk_org 库存组织
   * @param pk_user 操作员（用户）
   * @param positype 岗位类型 ：0 计划岗， 1 采购岗
   * @return 计划岗ID数组（如果操作员没有设置业务员，则返回null,如果操作员对应的业务员未设置计划岗，也返回null)
   */
  public String[] queryPosForUser(String pk_org, String pk_user, int positype) {
    String pk_psn = null;
    UserVO user = UserManageQuery.queryUserVOByUserid(pk_user);
    pk_psn = user.getPk_base_doc();
    if (null == pk_psn) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("select " + PositionHeaderVO.PK_POSITION);
    sql.append(" from " + PUEntity.POSITION_TAB_H);
    sql.append(" where dr=0 and ");
    sql.append(PositionHeaderVO.PK_ORG, pk_org);
    sql.append(" and ");
    sql.append(PositionHeaderVO.FPOSITIONTYPE, positype);
    sql.append(" and ");
    sql.append(PositionHeaderVO.CEMPLOYEEID, pk_psn);
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    if (rs.size() == 0) {
      return null;
    }
    return rs.toOneDimensionStringArray();
  }

  private MapList<String, String> buildOidVidMap(List<String> pkMaterials,
      List<String> pkSrcMaterials) {
    MapList<String, String> oidVidMap = new MapList<String, String>();
    for (String oid : pkSrcMaterials) {
      for (String vid : pkMaterials) {
        oidVidMap.put(oid, vid);
      }

    }
    return oidVidMap;
  }

  private ArrayList<String> getByClass(String pkOrg, String pkOperator,
      ArrayList<String> pkMaterials, HashSet<String> used) {

    ArrayList<String> ret = new ArrayList<String>();
    // 取得未查询过的物料PK
    ArrayList<String> notUse = new ArrayList<String>();
    for (String marerial : pkMaterials) {
      if (!used.contains(marerial)) {
        notUse.add(marerial);
      }
    }
    // 根据物料PK查找物料分类
    Map<String, MaterialVO> map = this.getMaterialClass(notUse);
    HashSet<String> marClass = new HashSet<String>();
    if (map != null) {
      for (Map.Entry<String, MaterialVO> material : map.entrySet()) {
        marClass.add(material.getValue().getPk_marbasclass());
      }
    }

    // 查找物料分类是否被计划员应用
    HashSet<String> usemarClass =
        this.queryPlanByClass(pkOrg, pkOperator, marClass);
    if (null != usemarClass && usemarClass.size() > 0 && map != null) {
      for (Map.Entry<String, MaterialVO> material : map.entrySet()) {
        if (usemarClass.contains(material.getValue().getPk_marbasclass())) {
          ret.add(material.getKey());
        }
      }
    }

    return ret;
  }

  private Map<String, MaterialVO> getMaterialClass(ArrayList<String> pkMaterials) {
    return MaterialPubService.queryMaterialBaseInfo(
        pkMaterials.toArray(new String[pkMaterials.size()]), new String[] {
          MaterialVO.PK_MARBASCLASS, MaterialVO.PK_MATERIAL
        });
  }

  private List<String> getMaterialOids(ArrayList<String> pkMaterials) {
    List<String> oids = new ArrayList<String>();
    Map<String, MaterialVO> vidMap =
        MaterialPubService.queryMaterialBaseInfo(
            pkMaterials.toArray(new String[pkMaterials.size()]), new String[] {
              MaterialVO.PK_SOURCE, MaterialVO.PK_MATERIAL
            });
    for (Entry<String, MaterialVO> entry : vidMap.entrySet()) {
      oids.add(entry.getValue().getPk_source());
    }
    return oids;
  }

  private HashSet<String> queryPlanByClass(String pkOrg, String pkOperator,
      HashSet<String> marClass) {
    if (marClass == null || marClass.size() == 0) {
      return null;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct t.pk_marclass ");
    sql.append(" from po_position_t t ");
    sql.append(" where  t.fpositiontype = 0 and ");
    sql.append("t.pk_org", pkOrg);
    sql.append(" and ");
    sql.append("t.cemployeeid", pkOperator);
    sql.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_08.name());
    sql.append(builder.buildSQL("t.pk_marclass",
        marClass.toArray(new String[marClass.size()])));

    String[] result =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();

    HashSet<String> ret = new HashSet<String>();
    if (!ArrayUtils.isEmpty(result)) {
      for (String row : result) {
        ret.add(row);
      }
    }

    return ret;
  }

  private String[][] queryPlanByMaterial(String pkOrg, List<String> pkMaterials) {

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct b.pk_srcmaterial,h.cemployeeid,b.fflag ");
    sql.append(" from po_position_b b,po_position h  ");
    sql.append(" where  b.pk_position = h.pk_position and h.dr =0 and ");
    sql.append(" h.fpositiontype = 0 and ");
    sql.append("h.pk_org", pkOrg);
    sql.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_05.name());
    sql.append(builder.buildSQL("b.pk_srcmaterial",
        pkMaterials.toArray(new String[pkMaterials.size()])));

    return new DataAccessUtils().query(sql.toString())
        .toTwoDimensionStringArray();
  }
}
