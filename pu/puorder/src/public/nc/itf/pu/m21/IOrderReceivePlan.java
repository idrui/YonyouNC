/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 上午08:19:42
 */
package nc.itf.pu.m21;

import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-13 上午08:19:42
 */
public interface IOrderReceivePlan extends ISmartService {

  /**
   * 方法功能描述：保存到货计划
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param orderVO
   * @return [0]为BatchOperateVO，[1]为OrderVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 上午10:35:06
   */
  public Object[] batchSave(BatchOperateVO batchVO, OrderVO orderVO,
      UFBoolean confirm) throws BusinessException;

  /**
   * 方法功能描述：为订单头ID查询符合条件的订单到货计划
   * <p>
   * <b>参数说明</b>
   * 
   * @param hid
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午11:21:46
   */
  public OrderReceivePlanVO[] queryPlanVOsByHId(String hid)
      throws BusinessException;

}
