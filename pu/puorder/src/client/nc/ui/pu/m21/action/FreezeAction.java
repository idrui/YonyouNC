package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.pu.m21.IOrderMaintain;
import nc.ui.pu.pub.action.MultiBillAction;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderVOMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单冻结按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-21 上午11:35:11
 */
public class FreezeAction extends MultiBillAction {
  private static final long serialVersionUID = -3693924969566821899L;

  private String freezeReason;

  public FreezeAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.FREEZE);
    // this.setBtnName("冻结");
    // this.setCode("btnFrozen");
    // // ctrl+J
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 输入冻结原因
    IVOMeta meta =
        BillMetaFactory.getInstance().getBillMeta(OrderVOMeta.class)
            .getParent();
    int reasonLength =
        meta.getAttribute(OrderHeaderVO.VFROZENREASON).getColumn().getLength();
    Object reason =
        MessageDialog.showInputDlg(
            this.getModel().getContext().getEntranceUI(),
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000444")/* @res "冻结原因" */, nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4004030_0", "04004030-0040")/*
                                                                         * @res
                                                                         * "请输入冻结原因："
                                                                         */,
            null, reasonLength);
    // 用户选择取消
    if (null == reason) {
      return;
    }

    this.freezeReason = (String) reason;
    super.doAction(e);
  }

  /**
   * @return freezeReason
   */
  public String getFreezeReason() {
    return this.freezeReason;
  }

  /**
   * @param freezeReason
   *          要设置的 freezeReason
   */
  public void setFreezeReason(String freezeReason) {
    this.freezeReason = freezeReason;
  }

  @Override
  protected ISingleBillService<Object> getSingleBillService() {
    ISingleBillService<Object> service = new ISingleBillService<Object>() {
      @Override
      public OrderVO operateBill(Object bill) throws Exception {
        IOrderMaintain maintainService =
            NCLocator.getInstance().lookup(IOrderMaintain.class);
        OrderVO[] returnOrders = maintainService.freezeOrder(new OrderVO[] {
          (OrderVO) bill
        }, FreezeAction.this.getFreezeReason());
        return returnOrders == null ? (OrderVO) bill : returnOrders[0];
      }
    };
    return service;
  }

  @Override
  protected String getSuccessInfo() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
        "04004030-0041")/* @res "订单冻结成功。" */;
  }

  @Override
  protected String getTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
        "04004030-0042")/* @res "订单冻结" */;
  }

  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.getModel().getUiState()
        || UIState.EDIT == this.getModel().getUiState()) {
      return false;
    }
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.FALSE.equals(vo.getHVO().getBfrozen())
        && UFBoolean.FALSE.equals(vo.getHVO().getBfinalclose());
  }

}
