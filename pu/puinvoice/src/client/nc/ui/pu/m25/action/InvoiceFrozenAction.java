/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.uif2.IActionCode;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceServiceUtil;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票冻结按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:29:00
 */
public class InvoiceFrozenAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoiceFrozenAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.FREEZE);
    // this.setBtnName("冻结");
    // this.setCode("btnFrozen");
    // // ctrl+J
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    InvoiceVO[] selectedVOs = this.getSelectedVOs();
    int reasonLength =
        selectedVOs[0].getMetaData().getParent()
            .getAttribute(InvoiceHeaderVO.VFROZENREASON).getColumn()
            .getLength();
    Object reason =
        MessageDialog.showInputDlg(
            null,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000444")/* @res "冻结原因" */, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004050_0", "04004050-0002")/*
                                                                         * @res
                                                                         * "请输入冻结原因："
                                                                         */,
            null, reasonLength);
    // 用户选择取消
    if (null == reason) {
      return;
    }

    // 按钮可用就一定会有可用的vo
    selectedVOs = this.pickupFrozenVOs(selectedVOs);

    InvoiceVO[] oldVOs = new InvoiceVO[selectedVOs.length];// new
    // ArrayUtil.copyArray(selectedVOs);

    for (int i = 0; i < selectedVOs.length; i++) {
      oldVOs[i] = (InvoiceVO) selectedVOs[i].clone();
      if (!ObjectUtil.isEmptyWithTrim(reason)) {
        selectedVOs[i].getParentVO().setVfrozenreason(reason.toString());
      }
    }
    ClientBillToServer<InvoiceVO> tool = new ClientBillToServer<InvoiceVO>();
    InvoiceVO[] lightVOs = tool.construct(oldVOs, selectedVOs);
    // 冻结操作
    InvoiceVO[] returnVos =
        InvoiceServiceUtil.getMaintainSerivce().freezeByLightVOs(lightVOs);
    new ClientBillCombinServer<InvoiceVO>().combine(selectedVOs, returnVos);
    this.getModel().directlyUpdate(selectedVOs);
    if (StringUtil.isEmptyWithTrim(this.getMessage())) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0117")/*
                                                                           * 发票冻结成功。
                                                                           */,
          this.getModel().getContext());
    }
    this.showMessage();
  }

  private InvoiceVO[] pickupFrozenVOs(InvoiceVO[] selectedVOs) {
    List<InvoiceVO> canBefrozenVOs = new ArrayList<InvoiceVO>();
    StringBuffer message = new StringBuffer();// message.length()
    for (int i = 0; i < selectedVOs.length; i++) {
      if (selectedVOs[i].getParentVO().getBfrozen() != null
          && selectedVOs[i].getParentVO().getBfrozen().booleanValue()) {
        int num = i + 1;
        message.append(NCLangRes.getInstance().getStrByID("4004050_0",
            "04004050-0105", null, new String[] {
              String.valueOf(num)
            })/* 第{0}条数据操作失败！失败原因：该记录已经处于被冻结状态！\n */);
        continue;
      }
      canBefrozenVOs.add(selectedVOs[i]);
    }
    this.setMessage(message.toString());
    return canBefrozenVOs.toArray(new InvoiceVO[canBefrozenVOs.size()]);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    // 只要发票未传应付，则该按钮可用
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }
    return this.canBeFrozen(this.getSelectedVOs());
  }

}
