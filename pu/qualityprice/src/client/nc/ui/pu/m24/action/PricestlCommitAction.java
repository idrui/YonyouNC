package nc.ui.pu.m24.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * @since 6.0
 * @version 2011-5-27 ÉÏÎç11:58:31
 * @author wuxla
 */

public class PricestlCommitAction extends CommitScriptAction {

  private static final long serialVersionUID = -2868016422718153238L;

  @Override
  protected boolean isActionEnable() {
    if (AppUiState.ADD == this.getModel().getAppUiState()
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState()
        || AppUiState.EDIT == this.getModel().getAppUiState()) {
      return true;
    }

    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnable = true;
    isEnable &=
        AppUiState.NOT_EDIT == this.getModel().getAppUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      PricestlVO vo = (PricestlVO) this.getModel().getSelectedData();
      isEnable &= ApproveFlowUtil.isCanSendApprove(vo);
    }
    return isEnable;
  }
}
