/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午09:56:17
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ReserveServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              预留为采购提供的服务：弃审
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:11:57
 * @author luojw
 */
public class UnAuditSupplyRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    OrderVO[] reserveVOs = OrderVOUtil.getInsance().getSupplyVO(vos);
    if (ArrayUtils.isEmpty(reserveVOs)) {
      return;
    }
    String[] billids = AggVOUtil.getPrimaryKeys(reserveVOs);
    ReserveServices.unAuditSupply(billids);
  }

}
