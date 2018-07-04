package nc.impl.pu.m21.action.rule.approve;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m21.query.price.cal.OrderPriceDailyMaintainance;
import nc.bs.pu.m21.query.price.cal.PriceCalculatorUtil;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              当数据弃审的时候同时更新询价表
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:47:45
 * @author luojw
 */
public class OrderPriceMaintainRule implements ICompareRule<OrderVO> {
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (PriceCalculatorUtil.addSharedLock()) {
      Set<String> pk_order_bs = new HashSet<String>();
      for (OrderVO originVO : originVOs) {
        if (originVO.getHVO().getForderstatus()
            .equals(POEnumBillStatus.APPROVE.toInteger())) {
          Set<String> itemPks =
              CirVOUtil.getDistinctFieldSet(originVO.getBVO(),
                  OrderItemVO.PK_ORDER_B);
          pk_order_bs.addAll(itemPks);

        }
      }
      if (pk_order_bs.size() < 1) {
        return;
      }
      new OrderPriceDailyMaintainance()
          .updateChangedDataToInvalidateByOrderB(pk_order_bs
              .toArray(new String[pk_order_bs.size()]));
    }
    else {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0353")/* @res "正在进行采购价格表维护，请稍后再试!" */
      );
    }

  }
}
