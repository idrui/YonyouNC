package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.CancelAction;
import nc.ui.pubapp.uif2app.view.BillForm;

/**
 * ����ȡ����������ݣ��޸�ȡ�������ֵδ�ص��޸�ǰ
 * 
 * @since 6.5
 * @version 2014-1-16 ����08:37:11
 * @author mengjian
 */
public class RCancelAction extends CancelAction {

  private static final long serialVersionUID = 3504474616130461308L;

  private BillForm billForm;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    Object selectedData = this.getModel().getSelectedData();
    this.billForm.setValue(selectedData);
  }

  public BillForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
  }
}
