/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 上午10:51:55
 */
package nc.itf.pu.est.m45;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>入库单暂估的查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-17 上午10:51:55
 */
public interface IPurchaseInEstQuery {
  /**
   * 方法功能描述：费用暂估的查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme 查询条件queryScheme
   * @param includefeeEstimated 是否查询已经做过费用暂估的记录
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午10:07:17
   */
  public PurchaseInEstVO[] feeEstQuery(IQueryScheme queryScheme,
      UFBoolean includefeeEstimated) throws BusinessException;

  /**
   * 方法功能描述：费用暂估的查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond 查询条件
   * @param includefeeEstimated 是否查询已经做过费用暂估的记录
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午10:07:17
   */
  public PurchaseInEstVO[] feeEstQuery(String cond,
      UFBoolean includefeeEstimated) throws BusinessException;

  /**
   * 方法功能描述：货物暂估(可同时进行费用暂估)的查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme 查询条件
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午10:08:08
   */
  public PurchaseInEstVO[] goodsEstQuery(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：货物暂估(可同时进行费用暂估)的查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午10:08:08
   */
  public PurchaseInEstVO[] goodsEstQuery(String cond) throws BusinessException;

  /**
   * 方法功能描述：取消暂估的查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme 查询条件
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-1 上午10:08:08
   */
  public PurchaseInEstVO[] unEstQuery(IQueryScheme queryScheme)
      throws BusinessException;

  public PurchaseInEstVO[] unEstQuery(String cond) throws BusinessException;

}
