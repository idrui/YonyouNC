package nc.pubitf.pu.it;

import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;

/**
 * 给进口提供的相关服务
 * 
 * @since 6.31
 * @version 2013-11-21 下午01:34:00
 * @author mengjian
 */
public interface ISettleBillMaintainForIT {

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
  public SettleBillVO[] cancelToIA4IT(SettleBillVO[] settleBillVOs)
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
  public void deleteSettleBills4IT(SettleBillVO[] settleBillVOs)
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
  public SettleBillVO[] toIA4IT(SettleBillVO[] settleBillVOs)
      throws BusinessException;
}
