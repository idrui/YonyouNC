/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 下午01:32:17
 */
package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              可用量更新前置动作
 * @scene
 *        采购订单删除、入库关闭、修改保存
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:40:17
 * @author luojw
 */
public class ATPBeforeUpdateRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    // 删除
    if (ArrayUtils.isEmpty(vos)) {
      ATPServices.modifyATPBefore(POBillType.Order.getCode(), originVOs);
    }
    else {
      ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);
    }
  }
}
