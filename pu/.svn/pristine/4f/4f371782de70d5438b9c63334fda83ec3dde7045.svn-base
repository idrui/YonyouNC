package nc.ui.pu.m23.action.maintain;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.exp.AtpNotEnoughException;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单删除按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class DeleteUIAction extends DeleteScriptAction {

  private static final long serialVersionUID = 2759740114803739275L;

  // public void doAction(ActionEvent e) throws Exception {
  // ArriveVO bill = (ArriveVO) getModel().getSelectedData();
  // String transType = bill.getHVO().getVtrantypecode();// 交易类型
  // String coperator = bill.getHVO().getOperator();// 操作员
  //
  // PfUtilClient.runAction(null, "DISCARD", transType, bill, coperator, null,
  // null, null);
  // getModel().delete();
  // showSuccessInfo();
  // // if (UIDialog.ID_OK ==
  // MessageDialog.showOkCancelDlg(getModel().getContext()
  // // .getEntranceUI(), null, "您确定要删除所选数据吗？")) {
  // // model.delete();
  // //
  // // showSuccessInfo();
  // // }
  // // super.doAction(e);
  // }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (ApproveFlowUtil.isCanDel(vo)) {
      return true; // 只有自由态，才允许修改
    }
    return false;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getSelectedData() == null) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();

    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }
    if (objs.length > 1) {
      return true;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(this.getFlowContext().getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return false;
    }

    ArrivalUIToBSEnv env = null;
    PfUserObject pfuo = this.getFlowContext().getUserObj();
    if (pfuo == null) {
      env = new ArrivalUIToBSEnv();
      pfuo = new PfUserObject();
    }
    else {
      env = (ArrivalUIToBSEnv) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AtpNotEnoughException) {
      env.setbConfirm(UFBoolean.TRUE);
    }
    else {
      return super.isResume(resumeInfo);
    }

    pfuo.setUserObject(env);
    this.getFlowContext().setUserObj(pfuo);
    return true;
  }

  @Override
  protected void showSuccessInfo() {
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("common", "UCH006")/* @res "删除成功" */, this
        .getModel().getContext());
  }
}
