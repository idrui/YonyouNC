package nc.ui.pu.m21.action.payplan;

import nc.ui.pubapp.uif2app.actions.batch.BatchEditAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2011-1-23 上午11:04:33
 * @author wuxla
 */

public class PayPlanEditAction extends BatchEditAction {

  private static final long serialVersionUID = 4601754844339690417L;

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
    // 订单冻结后付款计划不允许修改
    PayPlanViewVO selecteData =
        (PayPlanViewVO) this.getModel().getSelectedData();
    if (selecteData != null && UFBoolean.TRUE.equals(selecteData.getBfrozen())) {
      return false;
    }

    return true;
  }
}
