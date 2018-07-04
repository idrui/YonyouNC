package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.pf.AbstractOverBudget;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.tbb.OrderBudgetCtrlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 订单超预算数量
 * 订单超预算金额
 * 
 * @since 6.0
 * @version 2011-4-22 下午03:07:11
 * @author wuxla
 */

public class OrderOverBudget extends AbstractOverBudget {

  @Override
  protected IAccessableBusiVO[] getBusivo(AggregatedValueObject aggVO) {
    OrderVO vo = (OrderVO) aggVO;
    List<IAccessableBusiVO> budgetList = new ArrayList<IAccessableBusiVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (OrderItemVO item : vo.getBVO()) {
      for (String exec : execbilltypes) {
        budgetList.add(new OrderBudgetCtrlVO(vo.getHVO(), item,
            BillOperationEnum.APPROVE.getValue(), exec));
      }
    }
    return budgetList.toArray(new IAccessableBusiVO[budgetList.size()]);
  }

}
