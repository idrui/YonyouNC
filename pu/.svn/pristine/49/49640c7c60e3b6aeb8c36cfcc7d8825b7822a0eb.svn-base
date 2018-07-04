package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单弃审Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午08:05:30
 */
public class PraybillUnApproveAction extends UNApproveScriptAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 742050375280468501L;

  /**
   * 父类方法重写 控制按钮状态是否可用
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean enabled1 = false;
    if (this.model.getSelectedData() == null) {
      enabled1 = false;
    }
    else {
      PraybillVO vo = (PraybillVO) this.model.getSelectedData();
      enabled1 =
          ApproveFlowUtil.isCanUnApprove(vo)
              && this.model.getUiState() == UIState.NOT_EDIT;
    }
    return enabled1;
  }
}
