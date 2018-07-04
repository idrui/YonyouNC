package nc.ui.pu.m20.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * 删除作废动作事件
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午07:58:36
 */
public class PraybillDeleteAction extends DeleteScriptAction {

  private static final long serialVersionUID = -4934636459505909963L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.DeleteAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {

    boolean isEnable =
        this.getModel().getAppUiState() == AppUiState.NOT_EDIT
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      PraybillVO vo = (PraybillVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();
      isEnable &= ApproveFlowUtil.isCanDel(vo) || null != vos && 1 < vos.length;
    }

    return isEnable;

  }

}
