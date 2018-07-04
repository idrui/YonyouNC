package nc.ui.pu.m21.action.payplan;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.scmf.payplan.action.PayAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.rule.OrderPayChkRule;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 付款
 * 
 * @since 6.0
 * @version 2011-1-21 上午08:42:20
 * @author wuxla
 */

public class PayPlanToPayAction extends PayAction {
  private static final long serialVersionUID = 9221856501839849266L;

  private void checkIfPay(PayPlanViewVO[] views) {
    new OrderPayChkRule().process(views);

  }

  @Override
  protected boolean check(Object[] objs) {
    boolean pass = super.check(objs);
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    // 订单后续应付单是否做过付款或付款申请
    this.checkIfPay(views);
    return pass;
  }

  @Override
  protected AggregatedValueObject[] getDestVOs(Object[] objs) {
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    AggPayPlanVO[] vos = PayPlanViewVO.getAggPayPlanVO(views);
    AggregatedValueObject[] destVOs = null;
    destVOs =
        PfServiceScmUtil.exeVOChangeByBillItfDef(POBillType.Order.getCode(),
            ForeignForPUConstant.F3, vos);
    return this.convertVOs(destVOs);
  }

  @Override
  protected Object[] getUpdateVOs(Object[] objs) {
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    Set<String> bidSet = new HashSet<String>();
    for (PayPlanViewVO view : views) {
      bidSet.add(view.getPk_order_payplan());
    }
    String[] bids = bidSet.toArray(new String[bidSet.size()]);
    IOrderPayPlanQuery service =
        NCLocator.getInstance().lookup(IOrderPayPlanQuery.class);
    try {
      return service.queryPayPlanViews(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }

    if (this.getModel().getRows().isEmpty()) {
      return false;
    }

    PayPlanViewVO view = (PayPlanViewVO) this.getModel().getRow(0);

    if (!POEnumBillStatus.APPROVE.value().equals(view.getForderstatus())) {
      return false;
    }

    return true;
  }

}
