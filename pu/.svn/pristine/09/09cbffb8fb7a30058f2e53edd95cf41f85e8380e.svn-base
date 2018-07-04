package nc.ui.pu.m23.action.approve;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ApproveFlowUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 送审 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class SendUIAction extends CommitScriptAction {

  private static final long serialVersionUID = 9076203557899139959L;

  private boolean isOneVOEnable(ArriveVO vo) {
    return ApproveFlowUtil.isCanSendApprove(vo);
  }

  @Override
  protected boolean isActionEnable() {
    if ((AppUiState.ADD == this.getModel().getAppUiState())
        || (AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState())
        || (AppUiState.EDIT == this.getModel().getAppUiState())) {
      return true;
    }

    if (this.getModel().getSelectedData() == null) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();
    if ((this.model.getSelectedData() != null) && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }

    if (objs.length > 1) {
      return true;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }
}
