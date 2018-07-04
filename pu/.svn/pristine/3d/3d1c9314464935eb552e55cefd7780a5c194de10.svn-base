package nc.impl.pu.m21.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单取消审核过滤规则
 * @scene
 *        采购订单取消审核
 * @param
 *        OrderVO[] originVOs 原始订单vo
 * @since 6.3
 * @version 2014-10-22 上午11:08:49
 * @author luojw
 */

public class OrderUnApproveFilterRule implements IFilterRule<OrderVO> {
  private OrderVO[] originVOs;

  public OrderUnApproveFilterRule(OrderVO[] originVOs) {
    this.originVOs = originVOs;
  }

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(this.originVOs)) {
      return null;
    }

    BillIndex index = new BillIndex(this.originVOs);
    IVOMeta headMeta = vos[0].getMetaData().getParent();

    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      OrderHeaderVO headVO = vo.getHVO();
      String pk_order = headVO.getPk_order();
      OrderHeaderVO origHeadVO = (OrderHeaderVO) index.get(headMeta, pk_order);
      int origStatus = origHeadVO.getForderstatus().intValue();
      if (origStatus == POEnumBillStatus.APPROVE.toInt()
          && origStatus != headVO.getForderstatus().intValue()) {
        list.add(vo);
      }
    }

    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }
    return null;
  }
}
