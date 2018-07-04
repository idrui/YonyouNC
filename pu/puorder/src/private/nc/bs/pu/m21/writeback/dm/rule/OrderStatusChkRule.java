package nc.bs.pu.m21.writeback.dm.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单状态检查
 * @scene
 *        运输单回写订单
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午3:50:55
 * @author luojw
 */

public class OrderStatusChkRule implements IRule<OrderViewVO> {

  @Override
  public void process(OrderViewVO[] views) {
    Set<String> set = new HashSet<String>();
    for (OrderViewVO view : views) {
      if (!POEnumBillStatus.APPROVE.value().equals(view.getForderstatus())) {
        set.add(view.getVbillcode());
      }
    }

    if (set.size() > 0) {
      String[] codes = set.toArray(new String[set.size()]);
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < codes.length; ++i) {
        if (i > 0) {
          sb.append(", ");
        }
        sb.append(codes[i]);
      }
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0252", null, new String[] {
            sb.toString()
          })/* 采购订单{0}不是审核状态，不能生成运输单 */);
    }
  }

}
