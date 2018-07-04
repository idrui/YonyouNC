package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ApproveScriptAction;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * 审批动作事件
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午08:05:37
 */
public class PraybillApproveAction extends ApproveScriptAction {

  private static final long serialVersionUID = 4171051148975331842L;

  /**
   * 父类方法重写 控制按钮状态是否可用
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if ((objs != null) && (objs.length > 1)) {
      return true;
    }

    boolean enabled1 = false;
    if (this.model.getSelectedData() != null) {
      PraybillVO vo = (PraybillVO) this.model.getSelectedData();
      int status = vo.getHVO().getFbillstatus().intValue();
      enabled1 =
          ((status == POEnumBillStatus.FREE.toInt())
              || (status == POEnumBillStatus.APPROVING.toInt()) || (status == POEnumBillStatus.COMMIT
              .toInt())) && (this.model.getAppUiState() == AppUiState.NOT_EDIT);
    }

    return enabled1;
  }
}
