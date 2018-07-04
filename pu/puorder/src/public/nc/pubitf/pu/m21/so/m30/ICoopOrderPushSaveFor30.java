/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午02:01:13
 */
package nc.pubitf.pu.m21.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单生成协同采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午02:01:13
 */
public interface ICoopOrderPushSaveFor30 {

  /**
   * 方法功能描述：销售订单生成协同采购订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param srcVOs
   *          需要生成协同采购订单的销售订单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 下午02:03:50
   */
  public void coopOrderPushSaveFor30(SaleOrderVO[] srcVOs,
      Map<String, Object> result) throws BusinessException;

}
