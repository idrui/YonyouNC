package nc.ui.pu.m24.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * @since 6.0
 * @version 2011-5-27 ÏÂÎç12:17:03
 * @author wuxla
 */

public class PricestlUnApproveAction extends UNApproveScriptAction {

  private static final long serialVersionUID = -1427359066632838934L;

  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      PricestlVO vo = (PricestlVO) this.model.getSelectedData();
      isEnabled =
          this.model.getAppUiState() == AppUiState.NOT_EDIT
              && ApproveFlowUtil.isCanUnApprove(vo);
    }

    return isEnabled;
  }
}
