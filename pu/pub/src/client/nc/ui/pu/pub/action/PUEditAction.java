package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.pu.uif2.PUUIState;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.uif2.LoginContext;

/**
 * �ɹ��༭��ť
 * 
 * @since 6.0
 * @version 2011-4-25 ����04:08:55
 * @author wuxla
 */

public class PUEditAction extends EditAction {
  private static final long serialVersionUID = 539284743774749946L;

  private String permissioncode;

  private boolean powercheck;

  /**
   * �жϵ�ǰ�ڵ��Ƿ�����������Ĵ�
   * 
   * @param context
   * @return
   */
  public static boolean isOpenByApproveFlow(LoginContext context) {
    Object obj = context.getInitData();
    if (obj instanceof FuncletInitData) {
      int type = ((FuncletInitData) obj).getInitType();
      if (ILinkType.LINK_TYPE_APPROVE == type) {
        // �����ǰ������ͨ����Ϣ���Ĵ򿪣��򷵻�true
        return true;
      }
    }
    return false;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    IBill bill = (IBill) this.getModel().getSelectedData();
    if (this.isPowercheck()) {
      PowerCheckUtils.checkHasPermission(new IBill[] {
        bill
      }, this.getPermissioncode(), PowerActionEnum.EDIT.getActioncode(),
          PUQueryConst.VBILLCODE);
    }
    super.doAction(e);
    if (this.getModel() instanceof PUBillManageModel) {
      // ����Ϊ�޸�
      ((PUBillManageModel) this.getModel()).setPuUIState(PUUIState.EDIT_NORM);
    }
  }

  public String getPermissioncode() {
    return this.permissioncode;
  }

  public boolean isPowercheck() {
    return this.powercheck;
  }

  public void setPermissioncode(String permissioncode) {
    this.permissioncode = permissioncode;
  }

  public void setPowercheck(boolean powercheck) {
    this.powercheck = powercheck;
  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getUiState() == UIState.NOT_EDIT
        && this.getModel().getSelectedData() != null;
  }

}
