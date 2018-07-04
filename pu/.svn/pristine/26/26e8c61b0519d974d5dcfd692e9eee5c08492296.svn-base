package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m20.view.PraybillRBillForm;
import nc.ui.pu.pub.action.PUReviseInApprovingAction;

/**
 * 请购单审批中修订的动作
 * 
 * @since 6.0
 * @version 2011-12-1 下午3:47:39
 * @author zhaoyha
 */
public class PrayBillReviseInApprovingAction extends PUReviseInApprovingAction {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    PraybillRBillForm.setDefaultReviseItems(this.getBillForm()
        .getBillCardPanel());
  }
}
