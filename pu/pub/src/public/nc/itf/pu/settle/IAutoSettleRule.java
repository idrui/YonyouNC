/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 下午08:37:14
 */
package nc.itf.pu.settle;

import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算规则接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 下午08:37:14
 */
public interface IAutoSettleRule {

  /**
   * 方法功能描述：查询规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   *          0：红蓝入库单结算 1：红蓝发票结算 2：发票与入库单结算
   * @return SuperVO[] 0：红蓝入库单结算 1：红蓝发票结算 2：发票与入库单结算
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午08:38:42
   */
  public SuperVO[] queryAutoSettleRule(String[] pks) throws BusinessException;

  /**
   * 方法功能描述：保存规则（添加、更新）
   * <p>
   * <b>参数说明</b>
   * 
   * @param rules
   *          0：红蓝入库单结算 1：红蓝发票结算 2：发票与入库单结算
   * @return SuperVO[] 0：红蓝入库单结算 1：红蓝发票结算 2：发票与入库单结算
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午08:39:47
   */
  public SuperVO[] saveAutoSettleRule(SuperVO[] rules) throws BusinessException;
}
