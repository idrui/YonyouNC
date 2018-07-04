/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 下午01:21:54
 */
package nc.ui.pu.est.model;

import java.util.Map;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存一些前台数据结构
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 下午01:21:54
 */
@SuppressWarnings("serial")
public class EstUIContext extends LoginContext {
  /** 暂估类型 **/
  private QueryEstType esttype;

  /** 当前财务组织的成本要素 **/
  private CostfactorViewVO[] factors;

  /** 入库单行对应的采购组织 **/
  private Map<String, String> idPurOrgMap;

  /** 当前查询的主组织（结算财务组织） **/
  private String pk_fiorg;

  /**
   * @return esttype
   */
  public QueryEstType getEsttype() {
    return this.esttype;
  }

  /** 当前财务组织的成本要素 **/
  public CostfactorViewVO[] getFactors() {
    if (null == this.factors) {
      this.factors = EstVOUtil.getCostFactor(this.getPk_fiorg(), null);
      if (ArrayUtils.isEmpty(this.factors)) {
        this.factors = new CostfactorViewVO[0];
      }
    }
    return this.factors;
  }
  
  /**
   * 刷新成本要素
   */
  public void refreshFactors(){
    this.factors = EstVOUtil.getCostFactor(this.getPk_fiorg(), null);
  }

  /** 入库单行对应的采购组织--查询后更新 **/
  public Map<String, String> getIdPurOrgMap() {
    return this.idPurOrgMap;
  }

  /** 当前查询的主组织（结算财务组织）--查询后更新 **/
  public String getPk_fiorg() {
    return this.pk_fiorg;
  }

  /**
   * @param esttype 要设置的 esttype
   */
  public void setEsttype(QueryEstType esttype) {
    this.esttype = esttype;
  }

  /**
   * @param factors 要设置的 factors
   */
  public void setFactors(CostfactorViewVO[] factors) {
    this.factors = factors;
  }

  /**
   * @param idPurOrgMap 要设置的 idPurOrgMap
   */
  public void setIdPurOrgMap(Map<String, String> idPurOrgMap) {
    this.idPurOrgMap = idPurOrgMap;
  }

  /**
   * @param pk_fiorg 要设置的 pk_fiorg
   */
  public void setPk_fiorg(String pk_fiorg) {
    if (pk_fiorg.equals(this.pk_fiorg)) {
      return;
    }
    this.pk_fiorg = pk_fiorg;
    this.factors = null;
  }

}
