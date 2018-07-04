package nc.ui.pu.m23.action.approve;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 审核 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class ApproveUIAction extends ApproveScriptAction {

  private static final long serialVersionUID = 4708092167440452935L;

  public ApproveUIAction() {
    super();
    // this.setBtnName("审批");
  }

  @Override
  protected boolean isActionEnable() {
    // 如果正在编辑单据，不允许审核
    if (this.model.getAppUiState() == AppUiState.EDIT) {
      return false;
    }

    if (this.model.getSelectedData() == null) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      if (!POEnumBillStatus.APPROVE.value().equals(
          ((ArriveVO) this.model.getSelectedData()).getHVO().getFbillstatus())) {
        return true;
      }
      return false;
    }

    if (objs.length > 1) {
      return true;
    }

    if (POEnumBillStatus.APPROVE.value().equals(
        ((ArriveVO) objs[0]).getHVO().getFbillstatus())) {
      return false;
    }
    return true;
  }

  // @Override
  // protected void showSuccessInfo() {
  // ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UCH010")/*@res
  // "审核成功"*/, this.getModel().getContext());
  // }
}
