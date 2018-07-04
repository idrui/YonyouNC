/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 下午04:13:26
 */
package nc.pubitf.pu.m4t.pub;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>公共查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 下午04:13:26
 */
public interface IInitialEstPubQuery {
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   *          来源订单的行ID数组
   * @return Y-存在由来源订单生成的期初暂估单; N-不存在由来源订单生成的期初暂估单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-19 下午04:23:33
   */
  public boolean isExistFromOrder(String[] bid) throws BusinessException;
}
