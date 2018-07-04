package nc.pubimpl.pu.position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.jdbc.framework.util.DBConsts;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购岗查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-12 下午01:51:35
 */
public class QueryPurMaterialAction {

  /**
   * 根据采购组织＋物料查询采购岗物料设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org
   *          采购组织PK数组
   * @param pk_material
   *          物料PK数组
   * @return HashMap<pk_org+pk_material,cemployeeid(采购员信息)>
   * @throws BusinessException
   *           <p>
   * @author GGR
   * @time 2009-12-22 上午08:53:40
   */
  public HashMap<String, String> getPurchaser(String[] pkOrg,
      String[] pk_stocks, String[] pkMaterials) {

    if (ArrayUtils.isEmpty(pkOrg) || ArrayUtils.isEmpty(pkMaterials)) {
      return null;
    }
    String[] pkOrgs = pkOrg;
    if (pkOrg.length == 1) {
      pkOrgs = this.dealOrg(pkOrg[0], pkMaterials.length);
    }
    HashMap<String, String> ret = new HashMap<String, String>();
    MapList<String, String> oidVidMap = new MapList<String, String>();
    String[] pkSrcMaterials = this.getMaterialOids(pkMaterials, oidVidMap);
    // modify by wangweir，去掉原来物料vid->oid的映射的创建逻辑
    // oidVidMap =
    // this.buildOidVidMap(pkMaterials, pkSrcMaterials);
    // 根据物料查询
    String[][] materials = this.queryPsnByMaterial(pkOrgs, pkSrcMaterials);
    HashMap<String, String> move = new HashMap<String, String>();// 排除的物料
    HashSet<String> used = new HashSet<String>();
    if (!ArrayUtils.isEmpty(materials)) {
      for (String[] row : materials) {
        if (EnumUseMove.USE == Integer.parseInt(row[3])) {
          for (String vid : oidVidMap.get(row[1])) {
            // 组织+物料vid=>采购员
            ret.put(row[0] + vid, row[2]);
            used.add(row[0] + vid);
          }
        }
        else {
          for (String vid : oidVidMap.get(row[1])) {
            move.put(row[0] + vid, row[2]);
          }
        }
      }
    }

    // 根据物料分类查询
    HashMap<String, String> retcls =
        this.getByClass(pkOrgs, pk_stocks, pkMaterials, used, move);
    if (null != retcls && retcls.size() > 0) {
      ret.putAll(retcls);
    }

    return ret;
  }

  @SuppressWarnings("unused")
  @Deprecated
  private MapList<String, String> buildOidVidMap(String[] pkMaterials,
      String[] pkSrcMaterials) {
    MapList<String, String> oidVidMap = new MapList<String, String>();
    for (String oid : pkSrcMaterials) {
      for (String vid : pkMaterials) {
        oidVidMap.put(oid, vid);
      }

    }
    return oidVidMap;
  }

  private String buildSQL(String[] pkOrg, String[] pkClasses) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct t.pk_org,t.pk_marclass,t.cemployeeid ");
    sql.append(" from po_position_t t ");
    int length = pkOrg.length;
    if (length <= IDExQueryBuilder.getMaxInCount()) {
      sql.append(" where t.fpositiontype = 1 and ((");
      sql.append("t.pk_org", pkOrg[0]);
      sql.append(" and ");

      sql.append("t.pk_marclass", pkClasses[0]);
      sql.append(" ) ");
      for (int i = 1, len = pkOrg.length; i < len; i++) {
        sql.append(" or ( ");
        sql.append("t.pk_org", pkOrg[i]);
        sql.append(" and ");
        sql.append("t.pk_marclass", pkClasses[i]);
        sql.append(" ) ");
      }
      sql.append(" )");
    }
    else {
      sql.append(" join  ");
      String temptable = new TempTableDefine().get(pkOrg, pkClasses);
      sql.append(temptable);
      sql.append(" on  " + temptable + ".id1 = t.pk_org and ");
      sql.append(temptable + ".id2 = t.pk_marclass ");
      sql.append(" where t.fpositiontype = 1 ");
    }
    return sql.toString();
  }

  /**
   * 防止出现pkOrg只传一个值,而物料多值的情况
   * @author luojw
   */
  private String[] dealOrg(String pkOrg, int length) {
    String[] pkOrgs = new String[length];
    for (int i = 0; i < length; i++) {
      pkOrgs[i] = pkOrg;
    }
    return pkOrgs;
  }

  private HashMap<String, String> getByClass(String[] pkOrg,
      String[] pk_stocks, String[] pkMaterials, HashSet<String> used,
      HashMap<String, String> move) {

    HashMap<String, String> ret = new HashMap<String, String>();
    // PO85参数 是否查询物料采购分类
    String pk_group = BSContext.getInstance().getGroupID();
    po85 type = PUSysParamUtil.getPO85(pk_group);
    if (po85.base_marclass.equals(type)) {
      return this.getMapByBaseClass(pkOrg, pkMaterials, used, move);
    }
    else if (po85.pu_marclass == type) {
      Set<String> stockorgset = new HashSet<String>();
      for (String pk_stock : pk_stocks) {
        stockorgset.add(pk_stock);
      }
      for (String pk_stock : stockorgset) {
        // 物料采购分类
        ret.putAll(this.getMapByPuClass(pkOrg, pk_stock, pkMaterials, used,
            move));
      }
      return ret;
    }
    return new HashMap<String, String>();
  }

  private HashMap<String, String> getMapByBaseClass(String[] pkOrg,
      String[] pkMaterials, HashSet<String> used, HashMap<String, String> move) {
    // 取得未查询过的物料PK
    ArrayList<MaterialVO> notUsemarVOs = new ArrayList<MaterialVO>();
    ArrayList<String> notUsemar = new ArrayList<String>();
    for (int i = 0, len = pkOrg.length; i < len; i++) {
      if (!used.contains(pkOrg[i] + pkMaterials[i])) {
        notUsemar.add(pkMaterials[i]);
        MaterialVO vo = new MaterialVO();
        vo.setPk_org(pkOrg[i]);
        vo.setPk_material(pkMaterials[i]);
        notUsemarVOs.add(vo);
      }
    }

    if (notUsemarVOs.size() == 0) {
      return null;
    }

    // 根据物料PK查找物料分类
    Map<String, MaterialVO> map = this.getMaterialClass(notUsemar);
    for (MaterialVO vo : notUsemarVOs) {
      MaterialVO marclass = map.get(vo.getPk_material());
      if (null != marclass) {
        vo.setPk_marbasclass(marclass.getPk_marbasclass());
      }
    }

    // 查找物料分类是否被采购员应用
    return this.queryPsnByClass(notUsemarVOs, move);
  }

  /**
   * 采购分类
   * 
   * @param pkOrg
   * @param pkMaterials
   * @param used
   * @param move
   * @return
   */
  private HashMap<String, String> getMapByPuClass(String[] pkOrg,
      String pk_stock, String[] pkMaterials, HashSet<String> used,
      HashMap<String, String> move) {
    // 取得未查询过的物料PK
    ArrayList<MaterialStockVO> notUsemarVOs = new ArrayList<MaterialStockVO>();
    ArrayList<String> notUsemar = new ArrayList<String>();
    for (int i = 0, len = pkOrg.length; i < len; i++) {
      for (int j = 0; j < pkMaterials.length; j++) {
        if (!used.contains(pkOrg[i] + pkMaterials[j])) {
          notUsemar.add(pkMaterials[j]);
          MaterialStockVO vo = new MaterialStockVO();
          vo.setPk_org(pkOrg[i]);
          vo.setPk_material(pkMaterials[j]);
          notUsemarVOs.add(vo);
        }
      }
    }

    if (notUsemarVOs.size() == 0) {
      return new HashMap<String, String>();
    }

    // 根据物料查询采购分类
    Map<String, MaterialStockVO> map = this.getStockInfo(notUsemar, pk_stock);
    for (MaterialStockVO vo : notUsemarVOs) {
      MaterialStockVO marclass = map.get(vo.getPk_material());
      if (null != marclass && !StringUtils.isBlank(marclass.getPk_marpuclass())) {
        vo.setPk_marpuclass(marclass.getPk_marpuclass());
      }
    }

    // 查找物料分类是否被采购员应用
    return this.queryPsnByPuClass(notUsemarVOs, move);
  }

  private Map<String, MaterialVO> getMaterialClass(ArrayList<String> pkMaterials) {
    return MaterialPubService.queryMaterialBaseInfo(
        pkMaterials.toArray(new String[pkMaterials.size()]), new String[] {
          MaterialVO.PK_MARBASCLASS, MaterialVO.PK_MATERIAL
        });
  }

  private String[] getMaterialOids(String[] pkMaterials,
      MapList<String, String> oidVidMap) {
    List<String> oids = new ArrayList<String>();
    Map<String, MaterialVO> vidMap =
        MaterialPubService.queryMaterialBaseInfo(pkMaterials, new String[] {
          MaterialVO.PK_SOURCE, MaterialVO.PK_MATERIAL
        });
    for (Entry<String, MaterialVO> entry : vidMap.entrySet()) {
      oids.add(entry.getValue().getPk_source());
      // modify by wangweir，创建物料vid->oid的映射
      oidVidMap.put(entry.getKey(), entry.getValue().getPk_source());
      // end
    }
    return oids.toArray(new String[oids.size()]);
  }

  private Map<String, MaterialStockVO> getStockInfo(
      ArrayList<String> notUsemar, String pk_stock) {
    return MaterialPubService.queryMaterialStockInfo(
        notUsemar.toArray(new String[notUsemar.size()]), pk_stock,
        new String[] {
          MaterialStockVO.PK_MATERIAL, MaterialStockVO.PK_MARPUCLASS
        });
  }

  private HashMap<String, String> queryPsnByClass(ArrayList<MaterialVO> marVOs,
      HashMap<String, String> move) {

    int len = marVOs.size();
    String[] pkOrg = new String[len];
    String[] pkClasses = new String[len];
    MapList<String, String> maplist = new MapList<String, String>();

    for (int i = 0; i < len; i++) {
      pkOrg[i] = marVOs.get(i).getPk_org();
      pkClasses[i] = marVOs.get(i).getPk_marbasclass();
      maplist.put(pkOrg[i] + pkClasses[i], marVOs.get(i).getPk_material());
    }

    String[][] result =
        new DataAccessUtils().query(this.buildSQL(pkOrg, pkClasses))
            .toTwoDimensionStringArray();

    HashMap<String, String> ret = new HashMap<String, String>();

    if (!ArrayUtils.isEmpty(result)) {
      for (String[] row : result) {
        String mapkey = row[0] + row[1];
        String cemployeeid = row[2];
        List<String> materials = maplist.get(mapkey);
        if (materials.size() > 1) {
          for (String pk : materials) {

            String key = row[0] + pk;
            if (move.containsKey(key) && move.get(key).equals(cemployeeid)) {
              continue;
            }
            ret.put(key, cemployeeid);
          }
        }
        else {
          String key = row[0] + materials.get(0);
          if (move.containsKey(key) && move.get(key).equals(cemployeeid)) {
            continue;
          }
          ret.put(key, cemployeeid);
        }
      }

    }

    return ret;
  }

  private String[][] queryPsnByMaterial(String[] pkOrg, String[] pkMaterial) {
    // TODO 此处逻辑还有问题，需要进一步确认
    // 两列主键临时表,两组主键的长度必须一致,否则少数据,扩展pkorg数组(实际pkOrg的长度为1)
    String[] tmpPkOrg = new String[pkMaterial.length];
    for (int i = 0; i < pkMaterial.length; i++) {
      tmpPkOrg[i] = pkOrg[0];
    }
    String tempName = new TempTableDefine().get(tmpPkOrg, pkMaterial);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select distinct h.pk_org,b.pk_srcmaterial,h.cemployeeid,b.fflag ");
    sql.append(" from po_position_b b,po_position h , ");
    sql.append(tempName);
    sql.append(" where  b.pk_position = h.pk_position and h.dr =0 and ");
    sql.append(" h.fpositiontype = 1 ");
    sql.append(" and ");
    sql.append(tempName);
    sql.append(".id1 = ");
    sql.append(" h.pk_org  ");
    sql.append(" and ");
    sql.append(tempName);
    sql.append(".id2 = ");
    sql.append(" b.pk_srcmaterial  ");
    return new DataAccessUtils().query(sql.toString())
        .toTwoDimensionStringArray();
  }

  private HashMap<String, String> queryPsnByPuClass(
      ArrayList<MaterialStockVO> marVOs, HashMap<String, String> move) {

    int len = marVOs.size();
    String[] pkOrg = new String[len];
    String[] pkClasses = new String[len];
    // 组织+采购材料分类-> 物料
    MapList<String, String> maplist = new MapList<String, String>();

    for (int i = 0; i < len; i++) {
      pkOrg[i] = marVOs.get(i).getPk_org();
      if (StringUtils.isBlank(marVOs.get(i).getPk_marpuclass())) {
        pkClasses[i] = DBConsts.NULL_WAVE;
      }
      else {
        pkClasses[i] = marVOs.get(i).getPk_marpuclass();
      }
      maplist.put(pkOrg[i] + pkClasses[i], marVOs.get(i).getPk_material());
    }

    String[][] result =
        new DataAccessUtils().query(this.buildSQL(pkOrg, pkClasses))
            .toTwoDimensionStringArray();

    HashMap<String, String> ret = new HashMap<String, String>();

    if (!ArrayUtils.isEmpty(result)) {
      for (String[] row : result) {
        String mapkey = row[0] + row[1];
        String cemployeeid = row[2];
        List<String> materials = maplist.get(mapkey);
        if (materials.size() > 1) {
          for (String pk : materials) {

            String key = row[0] + pk;
            if (move.containsKey(key) && move.get(key).equals(cemployeeid)) {
              continue;
            }
            ret.put(key, cemployeeid);
          }
        }
        else {
          String key = row[0] + materials.get(0);
          if (move.containsKey(key) && move.get(key).equals(cemployeeid)) {
            continue;
          }
          ret.put(key, cemployeeid);
        }
      }

    }

    return ret;
  }
}
