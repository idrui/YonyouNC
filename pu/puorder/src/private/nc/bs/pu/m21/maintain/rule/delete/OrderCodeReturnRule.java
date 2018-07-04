/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 上午09:51:33
 */
package nc.bs.pu.m21.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              采购订单删除时，采购订单单据号(订单号)回退
 * @scene
 *        采购订单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:45:31
 * @author luojw
 */
public class OrderCodeReturnRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (null != originVOs) {
      PUBillCodeUtils.getOrderCode().returnBillCode(originVOs);
    }
  }

}
