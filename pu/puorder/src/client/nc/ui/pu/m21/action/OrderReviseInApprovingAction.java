package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.view.OrderReviseBillForm;
import nc.ui.pu.pub.action.PUReviseInApprovingAction;

/**
 * 采购订单审批中修订的动作
 * 
 * @since 6.0
 * @version 2011-12-1 下午3:47:39
 * @author zhaoyha
 */
public class OrderReviseInApprovingAction extends PUReviseInApprovingAction {

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    OrderReviseBillForm.setDefaultReviseItems(this.getBillForm()
        .getBillCardPanel());
  }
}
