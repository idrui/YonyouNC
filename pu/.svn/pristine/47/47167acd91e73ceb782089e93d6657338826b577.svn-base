/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午02:20:51
 */
package nc.ui.pu.m4t.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.DeleteScriptAction;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单删除
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午02:20:51
 */
public class InitialestDeleteAction extends DeleteScriptAction {

  private static final long serialVersionUID = -1222290630296266868L;

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
    if ((objs != null) && (objs.length > 1)) {
      return true;
    }

    InitialEstVO vo = (InitialEstVO) this.getModel().getSelectedData();

    return (vo != null)
        && InitialEstStatus.FEE.value().equals(vo.getHeader().getFbillstatus());
  }
}
