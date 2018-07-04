package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m20.view.PraybillRBillForm;
import nc.ui.pu.pub.action.PUReviseInApprovingAction;

/**
 * �빺���������޶��Ķ���
 * 
 * @since 6.0
 * @version 2011-12-1 ����3:47:39
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
