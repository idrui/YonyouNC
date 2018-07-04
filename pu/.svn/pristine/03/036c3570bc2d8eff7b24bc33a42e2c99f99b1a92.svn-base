package nc.ui.pu.uif2;

import java.util.List;

import nc.ui.pu.pub.action.PUEditAction;
import nc.ui.pu.pub.action.PUReviseInApprovingAction;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.actions.ActionContributors;
import nc.ui.uif2.actions.IActionContributor;
import nc.ui.uif2.model.AppEventConst;

/**
 * 审批中修订的按钮调整器
 * 
 * @since 6.0
 * @version 2011-12-1 下午1:45:23
 * @author zhaoyha
 */
public class ReviseInAppoveActionProcesser implements
    IAppEventHandler<AppEvent> {

  /** 动作提供器,保存了卡片和列表的动作 **/
  private ActionContributors actionContributor;

  private boolean processed = false;

  private PUReviseInApprovingAction reviseAction;

  public ActionContributors getActionContributor() {
    return this.actionContributor;
  }

  public PUReviseInApprovingAction getReviseAction() {
    return this.reviseAction;
  }

  @Override
  public void handleAppEvent(AppEvent e) {
    // 非初化，或是从消息中心打开，则不处理（即保留修订按钮）
    if (this.processed
        || !AppEventConst.MODEL_INITIALIZED.equals(e.getType())
        || PUEditAction.isOpenByApproveFlow(this.getReviseAction().getModel()
            .getContext())) {
      return;
    }
    this.processed = true;
    this.removeReviseAction();
  }

  public void removeReviseAction() {
    List<IActionContributor> ctrs =
        this.getActionContributor().getContributors();
    for (IActionContributor ctr : ctrs) {
      ctr.removeAction(this.getReviseAction());
      // 如果默认未显示，就中需求调整（如果非要调整会造成重复按钮）
      if (!ctr.isActived()) {
        continue;
      }
      ctr.setActived(false);
      ctr.setActived(true);
    }
  }

  public void setActionContributor(ActionContributors actionContributor) {
    this.actionContributor = actionContributor;
  }

  public void setReviseAction(PUReviseInApprovingAction reviseAction) {
    this.reviseAction = reviseAction;
  }

}
