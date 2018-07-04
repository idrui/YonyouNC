package nc.ui.pu.m21.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.itf.pu.m21.IOrderMaintain;
import nc.ui.pu.pub.action.MultiBillAction;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单解冻的按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-21 上午11:35:54
 */
public class UnfreezeAction extends MultiBillAction {
  private static final long serialVersionUID = -4174671116072424852L;

  public UnfreezeAction() {
    // this.setBtnName("解冻");
    // this.setCode("btnUnFrozen");
    SCMActionInitializer.initializeAction(this, IActionCode.UNFREEZE);
  }

  @Override
  protected ISingleBillService<Object> getSingleBillService() {
    ISingleBillService<Object> service = new ISingleBillService<Object>() {
      @Override
      public OrderVO operateBill(Object bill) throws Exception {
        IOrderMaintain maintainService =
            NCLocator.getInstance().lookup(IOrderMaintain.class);
        OrderVO[] returnOrders = maintainService.unfreezeOrder(new OrderVO[] {
          (OrderVO) bill
        });
        return returnOrders == null ? (OrderVO) bill : returnOrders[0];
      }
    };
    return service;
  }

  @Override
  protected String getSuccessInfo() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
        "04004030-0070")/* @res "订单解冻成功。" */;
  }

  @Override
  protected String getTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
        "04004030-0071")/* @res "订单解冻" */;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.ADD
        || this.getModel().getAppUiState() == AppUiState.EDIT) {
      return false;
    }
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.TRUE.equals(vo.getHVO().getBfrozen());
  }

}
