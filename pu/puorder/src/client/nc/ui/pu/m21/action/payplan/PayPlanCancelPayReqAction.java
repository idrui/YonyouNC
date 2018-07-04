package nc.ui.pu.m21.action.payplan;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderPayPlan;
import nc.ui.scmf.payplan.action.CancelPayAppAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 取消付款申请
 * 
 * @since 6.0
 * @version 2011-1-20 下午06:52:53
 * @author wuxla
 */

public class PayPlanCancelPayReqAction extends CancelPayAppAction {

  private static final long serialVersionUID = -8791035405560351151L;

  @Override
  protected Object[] cancelPayApp(Object[] objs) {
    PayPlanViewVO[] views = ArrayUtil.convertArrayType(objs);
    IOrderPayPlan service = NCLocator.getInstance().lookup(IOrderPayPlan.class);
    try {
      views = service.cancelPayReq(views);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return views;
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
