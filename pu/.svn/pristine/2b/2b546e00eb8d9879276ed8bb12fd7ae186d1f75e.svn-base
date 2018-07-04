/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 下午03:07:01
 */
package nc.itf.pu.est.m50;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 下午03:07:01
 */
public interface IVMIEstQuery {

  /**
   * 方法功能描述：查询可取消暂估的消耗汇总单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          用户选择的条件
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-6 下午03:09:41
   */
  public VmiEstVO[] cancelEstQuery(IQueryScheme queryScheme, String pk_fiorg)
      throws BusinessException;

  /**
   * 方法功能描述：查询可取消暂估的消耗汇总单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   *          用户选择的条件
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-6 下午03:09:41
   */
  public VmiEstVO[] cancelEstQuery(String cond, String pk_fiorg)
      throws BusinessException;

  /**
   * 方法功能描述：查询可费用暂估的消耗汇总单
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          用户选择的条件
   * @param incFeeEstFlag
   *          true - 包含已经进行费用暂估的单据；false - 不包含已经进行费用暂估的单据
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-10 下午04:08:08
   */
  public VmiEstVO[] feeEstQuery(IQueryScheme queryScheme,
      UFBoolean incFeeEstFlag, String pk_fiorg) throws BusinessException;

  /**
   * 方法功能描述：查询可费用暂估的消耗汇总单
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   *          用户选择的条件
   * @param incFeeEstFlag
   *          true - 包含已经进行费用暂估的单据；false - 不包含已经进行费用暂估的单据
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-10 下午04:08:08
   */
  public VmiEstVO[] feeEstQuery(String sqlWhere, UFBoolean incFeeEstFlag,
      String pk_fiorg) throws BusinessException;

  /**
   * 方法功能描述：查询可货物暂估（可能同时进行费用暂估）的消耗汇总单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   *          用户选择的条件
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-6 下午03:09:41
   */
  public VmiEstVO[] goodsEstQuery(IQueryScheme queryScheme, String pk_fiorg)
      throws BusinessException;

  /**
   * 方法功能描述：查询可货物暂估（可能同时进行费用暂估）的消耗汇总单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   *          用户选择的条件
   * @param pk_fiorg
   *          本次操作的结算财务组织
   * @return 查询到的暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-6 下午03:09:41
   */
  public VmiEstVO[] goodsEstQuery(String cond, String pk_fiorg)
      throws BusinessException;
}
