package nc.ui.pu.m21.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m21.util.OrderResumeUtils;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>送审按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-11 上午11:14:43
 */
public class SendApproveAction extends CommitScriptAction {
  private static final long serialVersionUID = -1106180678472478216L;

  // public SendApproveAction() {
  // super();
  // this.putValue(Action.ACCELERATOR_KEY,
  // KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.ALT_MASK));
  // this.putValue(Action.SHORT_DESCRIPTION, "提交审批");
  //
  // }

  @Override
  protected boolean isActionEnable() {
    if (AppUiState.ADD == this.getModel().getAppUiState()
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState()
        || AppUiState.EDIT == this.getModel().getAppUiState()
        || AppUiState.COPY_ADD == this.getModel().getAppUiState()) {
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
      OrderVO vo = (OrderVO) this.getModel().getSelectedData();
      isEnable &=
          ApproveFlowUtil.isCanSendApprove(vo)
              && UFBoolean.FALSE.equals(vo.getHVO().getBfrozen());
    }
    return isEnable;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    // 主要处理保存提交的情况
    UFBoolean isResume =
        OrderResumeUtils.isResume(resumeInfo, this.getFlowContext());
    if (isResume == null) {
      return super.isResume(resumeInfo);
    }
    return isResume.booleanValue();

  }
}
