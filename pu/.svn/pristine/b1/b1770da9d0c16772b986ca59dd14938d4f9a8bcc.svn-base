package nc.impl.pu.m21.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pu.m21.action.rule.maintain.PayLimitRule;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.pub.PayPlanDataUtil;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单审批新增付款计划
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午4:51:35
 * @author luojw
 */

public class InsertPayPlanRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    PayPlanVO[] payplanVOs = PayPlanDataUtil.getPayPlanData(vos);
    if (ArrayUtils.isEmpty(payplanVOs)) {
      return;
    }

    this.prepayLimit(vos, payplanVOs);

    VOInsert<PayPlanVO> insert = new VOInsert<PayPlanVO>();
    payplanVOs = insert.insert(payplanVOs);

    MapList<String, PayPlanVO> mapList = new MapList<String, PayPlanVO>();
    for (PayPlanVO payplanVO : payplanVOs) {
      mapList.put(payplanVO.getPk_order(), payplanVO);
    }

    for (OrderVO vo : vos) {
      String hid = vo.getHVO().getPk_order();
      List<PayPlanVO> payplayList = mapList.get(hid);
      vo.setChildren(PayPlanVO.class,
          payplayList.toArray(new PayPlanVO[payplayList.size()]));
    }
  }

  private void prepayLimit(OrderVO[] approveVOs, PayPlanVO[] payplanVOs) {
    List<OrderHeaderVO> headVOList = new ArrayList<OrderHeaderVO>();
    for (OrderVO vo : approveVOs) {
      headVOList.add(vo.getHVO());
    }
    OrderHeaderVO[] headVOs =
        headVOList.toArray(new OrderHeaderVO[headVOList.size()]);
    BillComposite<AggPayPlanVO> bc =
        new BillComposite<AggPayPlanVO>(AggPayPlanVO.class);
    AggPayPlanVO tempVO = new AggPayPlanVO();
    bc.append(tempVO.getMetaData().getParent(), headVOs);
    bc.append(tempVO.getMetaData().getVOMeta(PayPlanVO.class), payplanVOs);
    AggPayPlanVO[] vos = bc.composite();
    new PayLimitRule().process(vos);

  }

}
