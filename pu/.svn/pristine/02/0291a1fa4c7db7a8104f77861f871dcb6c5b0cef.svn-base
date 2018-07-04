/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 上午08:48:01
 */
package nc.ui.pu.m422x.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
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
 * @time 2010-10-15 上午08:48:01
 */
public class StoreReqAppApproveAction extends ApproveScriptAction {

  private static final long serialVersionUID = 7074159459833227538L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if ((objs != null) && (objs.length > 1)) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      StoreReqAppVO vo = (StoreReqAppVO) this.model.getSelectedData();
      int status = vo.getHVO().getFbillstatus().intValue();
      isEnabled =
          (this.model.getAppUiState() == AppUiState.NOT_EDIT)
              && ((status == POEnumBillStatus.FREE.toInt())
                  || (status == POEnumBillStatus.APPROVING.toInt()) || (status == POEnumBillStatus.COMMIT
                  .toInt()));
    }

    return isEnabled;
  }
}
