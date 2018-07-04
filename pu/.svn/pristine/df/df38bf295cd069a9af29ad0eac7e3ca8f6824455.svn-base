package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.pubapp.uif2app.actions.DeleteAction;
import nc.ui.uif2.model.IMultiRowSelectModel;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pub.power.PowerActionEnum;

/**
 * @since 6.0
 * @version 2011-4-25 下午06:14:33
 * @author wuxla
 */

public class PUDeleteAction extends DeleteAction {

  private static final long serialVersionUID = 7198589956574899193L;

  private String permissioncode;

  private boolean powercheck;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (this.powercheck) {
      Object[] tempData = null;
      if (this.getSingleBillView() != null
          && this.getSingleBillView().isComponentVisible()) {
        // 卡片显示时，只删除当前选中的一行数据
        if (null != this.getModel().getSelectedData()) {
          tempData = new Object[] {
            this.getModel().getSelectedData()
          };
        }
      }
      else if (this.getModel() instanceof IMultiRowSelectModel) {
        tempData =
            ((IMultiRowSelectModel) this.getModel()).getSelectedOperaDatas();
      }

      IBill[] bills = ArrayUtil.convertArrayType(tempData);
      PowerCheckUtils.checkHasPermission(bills, this.getPermissioncode(),
          PowerActionEnum.DELETE.getActioncode(), PUQueryConst.VBILLCODE);
    }

    super.doAction(e);
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
}
