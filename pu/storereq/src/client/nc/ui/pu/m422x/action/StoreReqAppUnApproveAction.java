/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 上午08:51:59
 */
package nc.ui.pu.m422x.action;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>弃审
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-15 上午08:51:59
 */
public class StoreReqAppUnApproveAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 1548307172563586315L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      StoreReqAppVO vo = (StoreReqAppVO) this.model.getSelectedData();
      isEnabled =
          this.model.getAppUiState() == AppUiState.NOT_EDIT
              && ApproveFlowUtil.isCanUnApprove(vo);
      // 来源于维修计划的单据不能取消审批
      StoreReqAppItemVO[] items = vo.getBVO();

      if (items.length > 0
          && items[0].getCsourcetypecode() != null
          && items[0].getCsourcetypecode().equalsIgnoreCase(
              PuRefBillTypeIdEnum.M4B32.getBillTypeId())) {
        isEnabled = false;
      }
    }

    return isEnabled;
  }
}
