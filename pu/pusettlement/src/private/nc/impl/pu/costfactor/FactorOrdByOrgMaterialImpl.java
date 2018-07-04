/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 上午10:34:05
 */
package nc.impl.pu.costfactor;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.costfactor.IFactorOrdByOrgMaterial;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据组织和物料ID查询VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 上午10:34:05
 */
public class FactorOrdByOrgMaterialImpl implements IFactorOrdByOrgMaterial {

  @Override
  public CostfactorViewVO[] queryFactorViewVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select b.pk_costfactor_b from po_costfactor a, po_costfactor_b b ");
      sql.append("where a.pk_costfactor=b.pk_costfactor and a.dr=0 and b.dr=0 ");

      if (pkOrg != null) {
        sql.append(" and pk_org", pkOrg);
      }
      if (!ArrayUtils.isEmpty(pkSrcmaterials)) {
        sql.append(" and ");
        sql.append("b.pk_srcmaterial", pkSrcmaterials);
      }
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      if (0 == rowset.size()) {
        return null;
      }
      String[] ids = rowset.toOneDimensionStringArray();
      return new ViewQuery<CostfactorViewVO>(CostfactorViewVO.class).query(ids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.costfactor.IFactorOrdByOrgMaterial#queryVOByOrgMaterial(java.lang.String,
   *      java.lang.String[])
   */
  @Override
  public CostfactorViewVO[] queryVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select b.pk_costfactor_b from po_costfactor a, po_costfactor_b b ");
      sql.append("where a.pk_costfactor=b.pk_costfactor and a.dr=0 and b.dr=0 ");
      sql.append(" and ");
      sql.append(CostfactorHeaderVO.BENTERCOST, UFBoolean.TRUE);
      if (pkOrg != null) {
        sql.append(" and pk_org", pkOrg);
      }
      if (!ArrayUtils.isEmpty(pkSrcmaterials)) {
        sql.append(" and ");
        sql.append("b.pk_srcmaterial", pkSrcmaterials);
      }
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      if (0 == rowset.size()) {
        return null;
      }
      String[] ids = rowset.toOneDimensionStringArray();
      return new ViewQuery<CostfactorViewVO>(CostfactorViewVO.class).query(ids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
