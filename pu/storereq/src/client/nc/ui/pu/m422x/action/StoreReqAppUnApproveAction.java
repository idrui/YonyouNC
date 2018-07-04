/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 ����08:51:59
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-15 ����08:51:59
 */
public class StoreReqAppUnApproveAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 1548307172563586315L;

  /**
   * ���෽����д
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
      // ��Դ��ά�޼ƻ��ĵ��ݲ���ȡ������
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
