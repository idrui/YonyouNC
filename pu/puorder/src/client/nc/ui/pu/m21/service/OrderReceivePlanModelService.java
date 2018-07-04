/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 下午04:35:00
 */
package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderReceivePlan;
import nc.ui.pubapp.pub.smart.SmartBatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划ModelService
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-12 下午04:35:00
 */
public class OrderReceivePlanModelService extends SmartBatchAppModelService {

  /**
   * 方法功能描述：保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @param orderVO
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 下午12:50:35
   */
  public Object[] batchSave(BatchOperateVO batchVO, OrderVO orderVO,
      UFBoolean confirm) throws BusinessException {
    IOrderReceivePlan service =
        (IOrderReceivePlan) NCLocator.getInstance()
            .lookup(this.getServiceItf());
    return service.batchSave(batchVO, orderVO, confirm);
  }

  /**
   * 方法功能描述：根据表体主键获取到货计划VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param hid
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-19 下午07:14:03
   */
  public OrderReceivePlanVO[] queryPlanVOsByHId(String hid)
      throws BusinessException {
    IOrderReceivePlan service =
        (IOrderReceivePlan) NCLocator.getInstance()
            .lookup(this.getServiceItf());
    return service.queryPlanVOsByHId(hid);
  }
}
