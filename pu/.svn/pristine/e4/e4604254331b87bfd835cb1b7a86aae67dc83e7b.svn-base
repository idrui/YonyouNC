/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:19:26
 */
package nc.ui.pu.m422x.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:19:26
 */
public class DeleteAction extends DeleteScriptAction {

  private static final long serialVersionUID = 9186904718278301105L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    StoreReqAppVO vo = (StoreReqAppVO) this.getModel().getSelectedData();

    return vo != null && ApproveFlowUtil.isCanDel(vo);
  }
}
