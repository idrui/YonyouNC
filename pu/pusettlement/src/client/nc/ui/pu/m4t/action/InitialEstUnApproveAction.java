/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 上午09:03:00
 */
package nc.ui.pu.m4t.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>弃审
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-15 上午09:03:00
 */
public class InitialEstUnApproveAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 1942761841514910847L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if ((objs != null) && (objs.length > 1)) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      InitialEstVO vo = (InitialEstVO) this.model.getSelectedData();
      isEnabled =
          (this.model.getAppUiState() == AppUiState.NOT_EDIT)
              && InitialEstStatus.APPROVED.value().equals(
                  vo.getHeader().getFbillstatus());
    }

    return isEnabled;
  }
}
