/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-14 下午03:38:34
 */
package nc.ui.pu.m21.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>审批
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-14 下午03:38:34
 */
public class OrderApproveAction extends ApproveScriptAction {

  private static final long serialVersionUID = 4621646187626902969L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.model.getAppUiState() != AppUiState.NOT_EDIT) {
      return false;
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      OrderVO vo = (OrderVO) this.model.getSelectedData();
      int status = vo.getHVO().getForderstatus().intValue();
      isEnabled =
          status == POEnumBillStatus.FREE.toInt()
                  || status == POEnumBillStatus.APPROVING.toInt() || status == POEnumBillStatus.COMMIT
                  .toInt();
    }

    return isEnabled;
  }
}
