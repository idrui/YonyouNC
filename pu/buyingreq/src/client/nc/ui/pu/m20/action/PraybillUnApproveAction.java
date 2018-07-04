package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 ����08:05:30
 */
public class PraybillUnApproveAction extends UNApproveScriptAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 742050375280468501L;

  /**
   * ���෽����д ���ư�ť״̬�Ƿ����
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
