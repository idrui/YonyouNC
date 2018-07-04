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
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceServiceUtil;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票解冻按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:29:00
 */
public class InvoiceUnFrozenAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoiceUnFrozenAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.UNFREEZE);
    // this.setBtnName("解冻");
    // this.setCode("btnUnFrozen");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.ALT_MASK));
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

    selectedVOs = this.pickupUnFrozenVOs(selectedVOs);

    ClientBillToServer<InvoiceVO> tool = new ClientBillToServer<InvoiceVO>();
    InvoiceVO[] lightVOs = tool.construct(selectedVOs, selectedVOs);
    // 解冻结操作
    InvoiceVO[] returnVos =
        InvoiceServiceUtil.getMaintainSerivce().unFreezeByLightVOs(lightVOs);
    new ClientBillCombinServer<InvoiceVO>().combine(selectedVOs, returnVos);
    this.getModel().directlyUpdate(selectedVOs);
    if (StringUtil.isEmptyWithTrim(this.getMessage())) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004050_0", "04004050-0116")/*
                                                                           * 发票解冻成功。
                                                                           */,
          this.getModel().getContext());
    }
    this.showMessage();
  }

  private InvoiceVO[] pickupUnFrozenVOs(InvoiceVO[] selectedVOs) {
    List<InvoiceVO> canBeUnFrozenVOs = new ArrayList<InvoiceVO>();
    StringBuffer message = new StringBuffer();// message.length()
    for (int i = 0; i < selectedVOs.length; i++) {
      if (selectedVOs[i].getParentVO().getBfrozen() == null
          || !selectedVOs[i].getParentVO().getBfrozen().booleanValue()) {
        int num = i + 1;
        message.append(NCLangRes.getInstance().getStrByID("4004050_0",
            "04004050-0106", null, new String[] {
              String.valueOf(num)
            })/* 第{0}条数据操作失败！失败原因：该记录已经处于未被冻结状态！\n */);
        continue;
      }
      canBeUnFrozenVOs.add(selectedVOs[i]);
    }
    this.setMessage(message.toString());
    return canBeUnFrozenVOs.toArray(new InvoiceVO[canBeUnFrozenVOs.size()]);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    // 发票冻结，按钮可用
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }
    return this.canBeUnFrozen(this.getSelectedVOs());
  }

}
