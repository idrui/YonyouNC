/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 下午03:22:32
 */
package nc.ui.pu.m4t.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 下午03:22:32
 */
public class InitialEstSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = 313053845231764391L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#isResume(nc.itf.pubapp.pub.exception.IResumeException)
   */
  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(this.getFlowContext().getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return false;
    }

    InitialEstContext env = null;
    PfUserObject pfuo = this.getFlowContext().getUserObj();
    if (pfuo == null) {
      env = new InitialEstContext();
      pfuo = new PfUserObject();
    }
    else {
      env = (InitialEstContext) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AskNumException) {
      env.setUserComfirm(true);
    }
    else {
      return super.isResume(resumeInfo);
    }

    pfuo.setUserObject(env);
    this.getFlowContext().setUserObj(pfuo);
    return true;
  }
}
