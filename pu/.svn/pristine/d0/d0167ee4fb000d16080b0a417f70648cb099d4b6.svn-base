package nc.pubimpl.pu.position.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.position.entity.PositionTVO;
import nc.vo.pu.position.enumeration.EnumUseMove;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * 分单函数抽象类
 * 
 * @since 6.0
 * @version 2010-11-9 下午01:54:29
 * @author wuxla
 */

public abstract class AbstractSplitByPos {
  public static final String NULLKEY = "null";

  private AggregatedValueObject aggVO;

  private String[] fieldKeys;

  private String[] pk_materials;

  private String[] pk_orgs;

  private po85 PO85;

  private int positiontype;

  public AggregatedValueObject getAggVO() {
    return this.aggVO;
  }

  public String[] getFieldKeys() {
    return this.fieldKeys;
  }

  /**
   * 根据组织和物料得到分单表体Map
   * 
   * @param vo 来源单据VO
   * @param keys 字段名称
   * @return
   */
  public Map<String, String> getItemPositionMap() {

    // key:物料VID value:分类
    Map<String, String> marclassMap = this.getMarClassMap();

    // key:组织|物料分类 value：岗位
    Map<String, String> positionClassMap = null;
    if (marclassMap != null) {
      String[] marclasses =
          marclassMap.values().toArray(new String[marclassMap.size()]);
      positionClassMap = this.getPositionMapByMarClass(marclasses);
    }

    Map<String, String> positionMap =
        this.getItemPostionMapByClass(marclassMap, positionClassMap);
    this.getPositionMapByMar(positionMap);

    return positionMap;
  }

  public abstract Map<String, String> getItemPostionMapByClass(
      Map<String, String> marclassMap, Map<String, String> positionMap);

  public Map<String, String> getMarClassMap() {
    String pk_group = BSContext.getInstance().getGroupID();
    po85 type = PUSysParamUtil.getPO85(pk_group);
    // 设置po85参数
    this.setPO85(type);
    if (PositionHeaderVO.planPosition == this.getPositiontype()) {
      return this.getMarBasClassMap();
    }
    else if (PositionHeaderVO.purchasePosition == this.getPositiontype()) {

      if (po85.base_marclass.equals(type)) {
        return this.getMarBasClassMap();
      }
      else if (po85.pu_marclass == type) {
        return this.getMarPurClassMap();
      }
    }
    return null;
  }

  public abstract Map<String, String> getMarPurClassMap();

  public String[] getPk_materials() {
    return this.pk_materials;
  }

  public String[] getPk_orgs() {
    return this.pk_orgs;
  }

  public po85 getPO85() {
    return this.PO85;
  }

  public int getPositiontype() {
    return this.positiontype;
  }

  public void setAggVO(AggregatedValueObject aggVO) {
    this.aggVO = aggVO;
  }

  public void setFieldKeys(String[] fieldKeys) {
    this.fieldKeys = fieldKeys;
  }

  public void setPk_materials(String[] pkMaterials) {
    this.pk_materials = pkMaterials;
  }

  public void setPk_orgs(String[] pkOrgs) {
    this.pk_orgs = pkOrgs;
  }

  public void setPO85(po85 pO85) {
    this.PO85 = pO85;
  }

  public void setPositiontype(int positiontype) {
    this.positiontype = positiontype;
  }

  /**
   * 根据组织和物料匹配岗位，根据岗位分单
   * 
   * @return
   */
  public List<String> splitByPosition() {
    Map<String, String> positionMap = this.getItemPositionMap();
    return this.splitValue(positionMap);
  }

  public abstract List<String> splitValue(Map<String, String> positionMap);

  private Map<String, String> getMarBasClassMap() {
    if (null == this.getPk_materials()) {
      return null;
    }
    Map<String, String> map = new HashMap<String, String>();
    Map<String, MaterialVO> materialMap =
        MaterialPubService.queryMaterialBaseInfo(this.getPk_materials(),
            new String[] {
              MaterialVO.PK_MARBASCLASS
            });
    for (Map.Entry<String, MaterialVO> entry : materialMap.entrySet()) {
      String key = entry.getKey();
      String pk_marbasclass = entry.getValue().getPk_marbasclass();
      map.put(key, pk_marbasclass);
    }
    return map;
  }

  private void getPositionMapByMar(Map<String, String> positionMap) {
    if (null == this.getPk_materials()) {
      return;
    }
    Map<String, String> used = new HashMap<String, String>();
    Map<String, String> unused = new HashMap<String, String>();
    String sql = this.getPositionSql();
    String[][] result =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();
    for (String[] row : result) {
      if (EnumUseMove.USE == Integer.parseInt(row[3])) {
        used.put(row[0] + "|" + row[1], row[2]);
      }
      else {
        unused.put(row[0] + "|" + row[1], row[2]);
      }
    }

    if (unused.size() > 0) {
      for (String key : unused.keySet()) {
        if (positionMap.containsKey(key)) {
          positionMap.remove(key);
        }
      }
    }
    if (used.size() > 0) {
      for (Map.Entry<String, String> entry : used.entrySet()) {
        String key = entry.getKey();
        if (!positionMap.containsKey(key)) {
          positionMap.put(key, entry.getValue());
        }
      }
    }
  }

  private Map<String, String> getPositionMapByMarClass(String[] marclasses) {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and dr = 0 ");
    sql.append(" and ");
    IDExQueryBuilder idBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pr_01.name());
    sql.append(idBuilder.buildSQL("pk_org", this.getPk_orgs()));
    sql.append(" and ");
    sql.append(idBuilder.buildAnotherSQL("pk_marclass", marclasses));
    sql.append(" and fpositiontype", this.getPositiontype());
    VOQuery<PositionTVO> query =
        new VOQuery<PositionTVO>(PositionTVO.class, new String[] {
          PositionTVO.PK_ORG, PositionTVO.PK_MARCLASS, PositionTVO.PK_POSITION
        });
    PositionTVO[] vos = query.query(sql.toString(), null);
    Map<String, String> map = new HashMap<String, String>();
    for (PositionTVO vo : vos) {
      map.put(vo.getPk_org() + "|" + vo.getPk_marclass(), vo.getPk_position());
    }
    return map;
  }

  private String getPositionSql() {
    SqlBuilder sql = new SqlBuilder();
    IDExQueryBuilder idBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pr_02.name());
    sql.append("select h.pk_org, m.pk_material, h.pk_position, b.fflag ");
    sql.append("from po_position_b b,po_position h,bd_material m ");
    sql.append("where b.pk_position = h.pk_position ");
    sql.append(" and m.pk_source=b.pk_srcmaterial ");
    sql.append(" and h.dr =0 and b.dr = 0");
    sql.append(" and h.fpositiontype", this.getPositiontype());
    sql.append(" and ");
    sql.append(idBuilder.buildSQL("h.pk_org", this.getPk_orgs()));
    sql.append(" and ");
    sql.append(idBuilder.buildAnotherSQL("m.pk_material",
        this.getPk_materials()));
    return sql.toString();
  }
}
