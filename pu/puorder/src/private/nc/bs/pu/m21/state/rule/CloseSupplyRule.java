/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午10:08:58
 */
package nc.bs.pu.m21.state.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ReserveServices;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.pub.OrderCloseVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              预留为采购提供的服务：关闭
 * @scene
 *        采购订单入库打开
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:00:00
 * @author luojw
 */
public class CloseSupplyRule implements IRule<OrderCloseVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderCloseVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    OrderCloseVOUtil util = OrderCloseVOUtil.getInstance();
    String[] bids = util.getBIds(vos);
    ReserveServices.closeSupply(bids);
  }
}
