package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.pu.uif2.PUUIState;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.AggregatedValueObject;

/**
 * 采购组审批中修订的动作
 * 
 * @since 6.0
 * @version 2011-12-1 上午11:31:17
 * @author zhaoyha
 */
public class PUReviseInApprovingAction extends PUReviseAction {
  private static final long serialVersionUID = 2304096794538298693L;

  private PUBillForm billForm;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    // 设置为审批中修订
    ((PUBillManageModel) this.getModel()).setPuUIState(PUUIState.EDIT_REVISE);
  }

  public PUBillForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(PUBillForm billForm) {
    this.billForm = billForm;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }
    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (objs == null || objs.length != 1 || null == objs[0]) {
      return false;
    }
    return ApproveFlowUtil.isCanAppovingRevise((AggregatedValueObject) objs[0]);
  }
}
