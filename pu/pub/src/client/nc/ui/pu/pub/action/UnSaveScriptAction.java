package nc.ui.pu.pub.action;

import nc.ui.pubapp.uif2app.actions.pflow.UnCommitScriptAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.UIState;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收回按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-12 下午05:02:50
 */
public class UnSaveScriptAction extends UnCommitScriptAction {

  /** 审批人 */
  public static final String APPROVER = "approver";

  private static final long serialVersionUID = 4563500169870906046L;

  public UnSaveScriptAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_UNCOMMIT);
  }

  @Override
  protected String extractApprover(AggregatedValueObject vo) {
    return (String) vo.getParentVO().getAttributeValue(
        UnSaveScriptAction.APPROVER);
  }

  @Override
  protected boolean isActionEnable() {
    // 一定要加，效率问题否则
    if (UIState.ADD == this.getModel().getUiState()
        || UIState.EDIT == this.getModel().getUiState()) {
      return false;
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    AggregatedValueObject selectedData =
        (AggregatedValueObject) this.model.getSelectedData();
    int status = POEnumBillStatus.FREE.toInt();
    String approver = null;
    if (selectedData != null) {
      status = this.extractApproveStatus(selectedData).intValue();
      approver = this.extractApprover(selectedData);
    }
    // 根据公共需求2011.6.25
    // 审批中状态、审批人为空的单据可以收回
    boolean isEnable =
        selectedData != null && status == POEnumBillStatus.APPROVING.toInt()
            && StringUtils.isBlank(approver);
    return isEnable;
  }

}
