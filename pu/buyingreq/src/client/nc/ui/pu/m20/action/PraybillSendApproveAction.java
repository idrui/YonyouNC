/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 上午11:12:26
 */
package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单送审Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-2 上午11:12:26
 */
public class PraybillSendApproveAction extends CommitScriptAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 4033563682418987107L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (AppUiState.ADD == this.getModel().getAppUiState()
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState()
        || AppUiState.EDIT == this.getModel().getAppUiState()
        || AppUiState.COPY_ADD == this.getModel().getAppUiState()) {
      return true;
    }

    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    PraybillVO selectedVo = (PraybillVO) this.getModel().getSelectedData();
    boolean enable =
        AppUiState.NOT_EDIT == this.getModel().getAppUiState()
            && null != selectedVo;
    if (enable && null != selectedVo) {
      enable &= ApproveFlowUtil.isCanSendApprove(selectedVo);
    }
    return enable;
  }
}
