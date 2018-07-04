/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 上午10:26:34
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据组织和物料ID查询成本要素VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 上午10:26:34
 */
public interface IFactorOrdByOrgMaterial {

  /**
   * 方法功能描述：根据组织和物料ID查询成本要素VO(不会添加任何额外的过滤条件)(费用结算时使用)
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkOrg
   * @param pkSrcmaterials
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 下午03:49:41
   */
  public CostfactorViewVO[] queryFactorViewVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException;

  /**
   * 方法功能描述：根据组织和物料ID查询成本要素VO(是否进入存货成本=Y)(费用暂估时使用)
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkOrg
   * @param pkSrcmaterials
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 下午03:50:26
   */
  public CostfactorViewVO[] queryVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException;
}
