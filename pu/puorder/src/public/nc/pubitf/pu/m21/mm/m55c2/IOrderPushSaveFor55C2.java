package nc.pubitf.pu.m21.mm.m55c2;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;

/**
 * 为离散制造提供的接口：生产订单产能分流推式创建采购订单
 * 
 * @since 6.0
 * @version 2012-10-26 上午10:37:20
 * @author liuyand
 */
public interface IOrderPushSaveFor55C2 {

  /**
   * 生产订单产能分流推式创建采购订单
   * 
   * @param orderVOs 采购订单聚合VO数组
   * @param sourceReturnVOs 生产寻源返回VO数组
   * @return
   * @throws BusinessException
   */
  public OrderVO[] pushSave(OrderVO[] orderVOs, SourceReturnVO[] SourceReturnVOs)
      throws BusinessException;

}
