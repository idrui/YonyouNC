/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-18 下午04:24:28
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询成本要素定义
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-18 下午04:24:28
 */
public interface ICostFactorQueryService {
  /**
   * 方法功能描述：成本要素费用项是否被引用
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param pk_org
   * @param pk_srcmaterial
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 上午09:32:42
   */
  public boolean beReferenced(String pk_group, String pk_org,
      String pk_srcmaterial) throws BusinessException;

  /**
   * 方法功能描述：成本要素定义查询操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param context 用户登录信息。
   * @return 成本要素定义VO数组
   * @throws BusinessException
   * @since 6.0
   */
  public CostfactorVO[] queryAllCostFactor(LoginContext context)
      throws BusinessException;

  /**
   * 根据可见的财务组织查询成本要素
   * 
   * @param context
   * @param permissonOrgs
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryAllCostFactor(LoginContext context,
      String[] permissonOrgs) throws BusinessException;

  /**
   * 查询指定财务组织下的成本要素
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryCostfacotorIn(String pk_org)
      throws BusinessException;

  /**
   * 查询指定财务组织下的成本要素(不过滤表体为空)
   * 
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public CostfactorVO[] queryCostfacotorInNofilter(String pk_org)
      throws BusinessException;

  /**
   * 方法功能描述：在成本要素中查询可以暂估费用项。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_org　财务组织
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-19 上午09:42:39
   */
  public CostfactorViewVO[] queryEstCostItems(String pk_org)
      throws BusinessException;

}
