/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午06:38:30
 */
package nc.ui.pu.m25.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.CommitScriptAction;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-27 下午06:38:30
 */
public class InvoiceSendApproveAction extends CommitScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = 4186202174286624642L;

  public InvoiceSendApproveAction() {
    super();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (AppUiState.ADD == this.getModel().getAppUiState()
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState()
        || AppUiState.EDIT == this.getModel().getAppUiState()
        || AppUiState.COPY_ADD == this.getModel().getAppUiState()) {
      return true;
    }

    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    InvoiceVO selectedVo = (InvoiceVO) this.getModel().getSelectedData();
    if (selectedVo == null) {
      return false;
    }
    boolean enable = AppUiState.NOT_EDIT == this.getModel().getAppUiState();
    if (enable) {
      enable &= ApproveFlowUtil.isCanSendApprove(selectedVo);
    }
    return enable;
  }
}
