/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 下午02:13:12
 */
package nc.pubitf.pu.m21.mm.m55a2;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产制造：生产订单推式保存采购订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 下午02:13:12
 */
public interface IOrderPushSaveFor55A2 {

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>生产订单推式保存采购订单
   * </ul>
   * 
   * @param orderVOs 订单vo数组
   * @param SourceReturnVOs 寻源vo数组，与订单vo数组一一对应，用于下游后续补充单价等
   * @throws BusinessException
   */
  OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException;
}
