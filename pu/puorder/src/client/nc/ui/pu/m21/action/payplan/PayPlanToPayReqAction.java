package nc.ui.pu.m21.action.payplan;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.scmf.payplan.rule.PayAppEndDateRule;
import nc.impl.scmf.payplan.rule.PayPlanRowChkRule;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.scmf.payplan.action.PayAppAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.rule.OrderPayCloseChkRule;
import nc.vo.pu.m21.rule.OrderPayFreezeChkRule;
import nc.vo.pu.pub.constant.ForeignForPUConstant;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 生成付款申请
 * 
 * @since 6.0
 * @version 2011-1-20 下午06:50:41
 * @author wuxla
 */

public class PayPlanToPayReqAction extends PayAppAction {
  private static final long serialVersionUID = -6007343557047185196L;

  @Override
  protected void check(Object[] objs) {
    if (ArrayUtils.isEmpty(objs)) {
      return;
    }
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    new PayPlanRowChkRule<PayPlanViewVO>().process(views);
    new OrderPayCloseChkRule().process(views);
    new OrderPayFreezeChkRule().process(views);
    new PayAppEndDateRule<PayPlanViewVO>().process(views);
  }

  @Override
  protected AggregatedValueObject[] getDestVOs(Object[] objs) {
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    AggPayPlanVO[] vos = PayPlanViewVO.getAggPayPlanVO(views);
    AggregatedValueObject[] destVOs =
        PfServiceScmUtil.exeVOChangeByBillItfDef(POBillType.Order.getCode(),
            ForeignForPUConstant.CMP_36D1, vos);
    return destVOs;
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
