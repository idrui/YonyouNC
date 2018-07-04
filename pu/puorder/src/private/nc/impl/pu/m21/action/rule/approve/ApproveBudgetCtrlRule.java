package nc.impl.pu.m21.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.tbb.OrderBudgetCtrlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              订单审批调用采购计划控制前规则，调用控制，并更新预占数
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午4:46:34
 * @author luojw
 */

public class ApproveBudgetCtrlRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos);
  }

  /**
   * 判断是否超预算，并且修改预占数
   * 
   * @param vos
   */
  private void budgetCtrl(OrderVO[] vos) {
    List<OrderBudgetCtrlVO> budgets = new ArrayList<OrderBudgetCtrlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        for (String exec : execbilltypes) {
          budgets.add(new OrderBudgetCtrlVO(vo.getHVO(), item,
              BillOperationEnum.APPROVE.getValue(), exec));
        }
      }
    }
    // 调用预算接口，检查和更新预占数
    TbbCtrlServices.getControlInfo(budgets
        .toArray(new OrderBudgetCtrlVO[budgets.size()]));

  }
}
