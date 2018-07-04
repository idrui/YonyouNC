package nc.itf.pu.m27;

import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单的维护服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-31 下午07:58:03
 */
public interface ISettleBillMaintain {

  /**
   * 方法功能描述：结算单取消传存货
   * <p>
   * <b>参数说明</b>
   * 
   * @param settleBillVOs
   *          将要取消传存货的结算单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-3 下午08:06:48
   */
  public SettleBillVO[] cancelToIA(SettleBillVO[] settleBillVOs)
      throws BusinessException;

  /**
   * 结算单删除
   * <p>
   * <b>参数说明</b>
   * 
   * @param settleBillVOs
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-26 上午10:39:31
   */
  public void deleteSettleBills(SettleBillVO[] settleBillVOs)
      throws BusinessException;

  /**
   * 方法功能描述：结算单传存货
   * <p>
   * <b>参数说明</b>
   * 
   * @param settleBillVOs
   *          需要传存货的结算单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-3 下午08:06:20
   */
  public SettleBillVO[] toIA(SettleBillVO[] settleBillVOs)
      throws BusinessException;
}
