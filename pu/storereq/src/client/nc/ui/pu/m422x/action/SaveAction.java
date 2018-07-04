/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午04:31:58
 */
package nc.ui.pu.m422x.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.pf.PUPFParameter;
import nc.vo.pu.pub.exception.PUBudgetControlCheckException;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 下午04:31:58
 */
public class SaveAction extends SaveScriptAction {

  private static final long serialVersionUID = -224173013411231508L;

  private boolean isExecuted = true;

  @Override
  public boolean isExecuted() {
    return this.isExecuted;
  }

  public void setExecuted(boolean isExecuted) {
    this.isExecuted = isExecuted;
  }

  private boolean isResumePIM(IResumeException e) {
    if (!SysInitGroupQuery.isPIMEnabled()) {
      return false;
    }
    if (MessageDialog.showYesNoDlg(
        this.model.getContext().getEntranceUI(),
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4008001_0",
            "04008001-0054")/* @res "警告" */,
        ((BusinessException) e).getMessage()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4008001_0", "04008001-0058")/* @res "是否继续？" */) == nc.ui.pub.beans.UIDialog.ID_YES) {
      PUPFParameter pfparam =
          (PUPFParameter) this.getFlowContext().getUserObj();
      if (pfparam == null) {
        pfparam = new PUPFParameter();
      }
      pfparam.setbBudgetCheckkFlag(true);
      this.getFlowContext().setUserObj(pfparam);
      return true;
    }
    return false;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    boolean executed = false;
    if (resumeInfo instanceof PUBudgetControlCheckException) {
      executed = this.isResumePIM(resumeInfo);
      this.setExecuted(executed);
    }
    return executed;
  }
}
