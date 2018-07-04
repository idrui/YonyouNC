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
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单审批调用采购计划控制后规则，不做控制判断，只更新预占数
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:10:07
 * @author luojw
 */

public class UnApproveBudgetCtrlRule implements IRule<OrderVO> {
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos);
    this.budgetCtrlForRedOrder(vos);
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
      if (UFBoolean.TRUE.equals(vo.getHVO().getBreturn())) {
        continue;
      }
      for (OrderItemVO item : vo.getBVO()) {
        for (String exec : execbilltypes) {
          budgets.add(new OrderBudgetCtrlVO(vo.getHVO(), item,
              BillOperationEnum.UNAPPROVE.getValue(), exec));
        }
      }
    }
    if (0 == budgets.size()) {
      return;
    }
    // 调用预算接口，检查和更新预占数
    TbbCtrlServices.noCheckUpdateExe(budgets
        .toArray(new OrderBudgetCtrlVO[budgets.size()]));

  }

  private void budgetCtrlForRedOrder(OrderVO[] vos) {
    List<OrderBudgetCtrlVO> budgets = new ArrayList<OrderBudgetCtrlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (OrderVO vo : vos) {
      if (!UFBoolean.TRUE.equals(vo.getHVO().getBreturn())) {
        continue;
      }
      for (OrderItemVO item : vo.getBVO()) {
        for (String exec : execbilltypes) {
          budgets.add(new OrderBudgetCtrlVO(vo.getHVO(), item,
              BillOperationEnum.UNAPPROVE.getValue(), exec));
        }
      }
    }

    if (budgets.size() > 0) {
      TbbCtrlServices.getControlInfo(budgets
          .toArray(new OrderBudgetCtrlVO[budgets.size()]));
    }
  }

}
