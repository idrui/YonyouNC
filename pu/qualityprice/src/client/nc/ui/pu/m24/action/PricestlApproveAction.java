package nc.ui.pu.m24.action;

import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * @since 6.0
 * @version 2011-5-27 ÉÏÎç11:43:39
 * @author wuxla
 */

public class PricestlApproveAction extends ApproveScriptAction {

  private static final long serialVersionUID = 5460443999399437518L;

  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      PricestlVO bill = (PricestlVO) this.getModel().getSelectedData();
      int status = bill.getHVO().getFbillstatus().intValue();
      return (status == POEnumBillStatus.FREE.toInt()
          || status == POEnumBillStatus.COMMIT.toInt() || status == POEnumBillStatus.APPROVING
          .toInt());
    }
    return isEnabled;
  }
}
