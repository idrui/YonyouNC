/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-12 下午01:49:39
 */
package nc.pubimpl.pu.position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.position.query.PositionMarBaseQueryBP;
import nc.bs.pu.position.query.PositionMarPuQueryBP;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.pu.position.IQueryPosition;
import nc.pubitf.pu.position.PositionQueryVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

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
 * @time 2010-7-12 下午01:49:39
 */
public class QueryPositionImpl implements IQueryPosition {

  @Override
  public boolean checkPO85Para(SysInitVO vo) throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select count(*) from po_position where ");
    String pk_group = BSContext.getInstance().getGroupID();
    sql.append(PositionHeaderVO.PK_GROUP, pk_group);
    sql.append(" and " + PositionHeaderVO.DR, 0);
    sql.append(" and " + PositionHeaderVO.FPOSITIONTYPE,
        PositionHeaderVO.purchasePosition);
    IRowSet rs = new DataAccessUtils().query(sql.toString());
    if (0 == rs.size()) {
      return true;
    }
    while (rs.next()) {
      if (rs.getInt(0) > 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public ArrayList<String> filterMaterialByPlanner(String pkOrg,
      String pkOperator, ArrayList<String> pkMaterials)
      throws BusinessException {
    try {
      return new QueryPlanMaterialAction().filterMaterialByPlanner(pkOrg,
          pkOperator, pkMaterials);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public String getMaterialSqlByPkPlan(String pkOrg, String pkPlan) {

    return this.getPlanMaterialSqlByPkPlan(pkOrg, new String[] {
      pkPlan
    });
  }

  @Override
  public String getMaterialSqlByPurchaser(String pk_org)
      throws BusinessException {
    try {
      String pk_user = BSContext.getInstance().getUserID();
      String[] pkPlans =
          new QueryPlanMaterialAction().queryPosForUser(pk_org, pk_user, 1);
      if (null == pkPlans) {
        return null;
      }
      return this.getPlanMaterialSqlByPkPlan(pk_org, pkPlans);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, String> getPurchaser(String[] pkOrg, String[] pk_stocks,
      String[] pkMaterial) throws BusinessException {
    try {
      return new QueryPurMaterialAction().getPurchaser(pkOrg, pk_stocks,
          pkMaterial);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public PositionQueryVO[] getPurPosition(PositionQueryVO[] vos)
      throws BusinessException {
    try {
      String pk_group = AppContext.getInstance().getPkGroup();
      po85 type = PUSysParamUtil.getPO85(pk_group);

      if (po85.base_marclass.equals(type)) {
        return this.getPurPositionForBaseClass(vos);
      }
      return this.getPurPositionForPuClass(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public String queryPlanMaterialsForUser(String pk_org)
      throws BusinessException {
    try {
      String pk_user = BSContext.getInstance().getUserID();
      String[] pkPlans =
          new QueryPlanMaterialAction().queryPosForUser(pk_org, pk_user, 0);
      if (null == pkPlans) {
        return null;
      }
      return this.getPlanMaterialSqlByPkPlan(pk_org, pkPlans);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String getPlanMaterialSqlByPkPlan(String pkOrg, String[] pkPlans) {
    SqlBuilder sql = new SqlBuilder();
    // 按物料
    sql.append(" select m.pk_material ");
    sql.append("    from po_position_b b ");
    sql.append("    inner join bd_material m on m.pk_source= b.pk_srcmaterial ");
    sql.append("    Where b.pk_org='" + pkOrg + "'");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pos_06.name());
    String plansCond = builder.buildSQL(" ", pkPlans);
    sql.append(" and pk_position " + plansCond);
    sql.append("    and fflag=1 and b.dr=0 and isnull(b.pk_srcmaterial,'~')<>'~' ");
    // 按物料分类
    sql.append("    Union all");
    sql.append("    select m2.pk_material ");
    sql.append("    from po_position_t t,bd_material m2");
    sql.append("    Where t.pk_marclass = m2.pk_marbasclass");
    sql.append("      and t.pk_org='" + pkOrg + "'");
    sql.append("      and t.pk_position" + plansCond);
    sql.append("      and t.dr =0 and m2.dr=0 ");
    sql.append("      and not exists (select p.pk_srcmaterial");
    sql.append("      from po_position_b p ");
    sql.append("      where p.pk_org='" + pkOrg + "'");
    sql.append("        and p.pk_position" + plansCond);
    sql.append("        and p.fflag=2 and dr=0 ");
    sql.append("        and p.pk_srcmaterial = m2.pk_source ");
    sql.append("    ) ");
    return sql.toString();
  }

  private PositionQueryVO[] getPurPositionForBaseClass(PositionQueryVO[] vos) {
    Set<String> purchseorgset = new HashSet<String>();
    Set<String> materialset = new HashSet<String>();
    Set<String> marbaseset = new HashSet<String>();
    for (PositionQueryVO vo : vos) {
      purchseorgset.add(vo.getPk_purchaseorg());
      if (vo.getPk_srcmaterial() != null) {
        materialset.add(vo.getPk_srcmaterial());
      }
      else if (vo.getPk_marbaseclass() != null) {
        marbaseset.add(vo.getPk_marbaseclass());
      }
    }
    Map<String, String> map =
        new PositionMarBaseQueryBP().queryPosition(
            purchseorgset.toArray(new String[purchseorgset.size()]),
            materialset.toArray(new String[materialset.size()]),
            marbaseset.toArray(new String[marbaseset.size()]),
            PositionHeaderVO.purchasePosition);
    if (map.size() == 0) {
      return vos;
    }
    for (PositionQueryVO vo : vos) {
      String key = null;
      if (vo.getPk_srcmaterial() != null) {
        key = vo.getPk_purchaseorg() + vo.getPk_srcmaterial();
      }
      else if (vo.getPk_marbaseclass() != null) {
        key = vo.getPk_purchaseorg() + vo.getPk_marbaseclass();
      }
      if (key == null) {
        continue;
      }
      String pk_position = map.get(key);
      if (pk_position != null) {
        vo.setPk_position(pk_position);
      }
    }
    return vos;
  }

  private PositionQueryVO[] getPurPositionForPuClass(PositionQueryVO[] vos) {
    List<String> purchseorgList = new ArrayList<String>();
    List<String> materialList = new ArrayList<String>();
    List<String> stockorgList = new ArrayList<String>();
    Set<String> orgtypeset = new HashSet<String>();
    for (PositionQueryVO vo : vos) {
      if (vo.getPk_srcmaterial() == null) {
        purchseorgList.add(null);
        materialList.add(null);
        stockorgList.add(null);
        continue;
      }
      materialList.add(vo.getPk_srcmaterial());
      String pk_purchaseorg = vo.getPk_purchaseorg();
      purchseorgList.add(pk_purchaseorg);
      String pk_stockorg = vo.getPk_stockorg();
      if (pk_stockorg != null) {
        stockorgList.add(pk_stockorg);
      }
      else if (orgtypeset.contains(pk_purchaseorg)) {
        stockorgList.add(pk_purchaseorg);
      }
      else if (OrgUnitPubService.isTypeOf(pk_purchaseorg,
          IOrgConst.STOCKORGTYPE)) {
        stockorgList.add(pk_purchaseorg);
        orgtypeset.add(pk_purchaseorg);
      }
      else {
        stockorgList.add(null);
      }
    }
    String[] values =
        new PositionMarPuQueryBP().queryPuPosition(
            purchseorgList.toArray(new String[purchseorgList.size()]),
            stockorgList.toArray(new String[stockorgList.size()]),
            materialList.toArray(new String[materialList.size()]));
    int length = vos.length;
    for (int i = 0; i < length; ++i) {
      vos[i].setPk_position(values[i]);
    }
    return vos;
  }
}
