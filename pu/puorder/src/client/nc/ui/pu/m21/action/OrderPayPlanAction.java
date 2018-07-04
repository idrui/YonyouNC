package nc.ui.pu.m21.action;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.itf.pu.m21.IOrderQuery;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.m21.pub.PayPlanDataUtil;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款计划
 * 
 * @since 6.0
 * @version 2011-1-4 下午04:30:54
 * @author wuxla
 */

public class OrderPayPlanAction extends NCAction {
  private static final long serialVersionUID = 115233054641886530L;

  private BillForm billForm;

  private BillManageModel model;

  public OrderPayPlanAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_PAYPLAN);
    // String str = "付款计划";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.SHORT_DESCRIPTION, str);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (UIState.ADD == this.getModel().getUiState()
        || UIState.EDIT == this.getModel().getUiState()) {
      this.editOpen();
    }
    else {
      this.notEditOpen();
    }
  }

  public BillForm getBillForm() {
    return this.billForm;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void editOpen() throws Exception {
    OrderVO vo =
        (OrderVO) this
            .getBillForm()
            .getBillCardPanel()
            .getBillValueVO(OrderVO.class.getName(),
                OrderHeaderVO.class.getName(), OrderItemVO.class.getName());
    OrderHeaderVO headVO = vo.getHVO();
    if (null == headVO) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004030_0", "04004030-0047")/* @res "表头为空" */);
    }

    if (ArrayUtils.isEmpty(vo.getBVO())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0048")/*
                                                                   * @res "表体为空"
                                                                   */);
    }

    OrderPaymentVO[] valueVOs =
        (OrderPaymentVO[]) this.billForm
            .getBillCardPanel()
            .getBillData()
            .getBodyValueVOs(OrderPaymentVO.TABSNAME,
                OrderPaymentVO.class.getName());
    if (valueVOs == null || valueVOs.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0378")/*
                                                                   * 付款协议页签没有内容，
                                                                   * 无法查看付款计划！
                                                                   */);
    }
    vo.setChildren(OrderPaymentVO.class, valueVOs);

    BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
    sum.setBlargessField(OrderItemVO.BLARGESS);
    sum.sum();

    OrderVO[] vos = new OrderVO[] {
      vo
    };
    PayPlanVO[] payplanVOs = PayPlanDataUtil.getPayPlanData(vos);

    PayPlanViewVO[] views = new PayPlanViewVO[payplanVOs.length];
    for (int i = 0; i < payplanVOs.length; ++i) {
      views[i] = new PayPlanViewVO();
      views[i].setVO(headVO);
      views[i].setVO(payplanVOs[i]);
    }

    this.openPayPlanDLG(views);
  }

  private void notEditOpen() throws Exception {
    OrderVO vo = (OrderVO) this.getModel().getSelectedData();
    if (null == vo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
    }
    if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
        .intValue()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0049")/*
                                                                   * @res
                                                                   * "订单未审批"
                                                                   */);
    }
    String pk_order = vo.getHVO().getPk_order();
    IOrderPayPlanQuery service =
        NCLocator.getInstance().lookup(IOrderPayPlanQuery.class);
    AggPayPlanVO[] payplanVOs = service.queryPayPlanVOs(new String[] {
      pk_order
    });

    PayPlanViewVO[] views = AggPayPlanVO.getPayPlanViewVO(payplanVOs);

    this.openPayPlanDLG(views);

    if (POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getForderstatus())) {
      IOrderQuery orderquery =
          NCLocator.getInstance().lookup(IOrderQuery.class);
      this.getModel().directlyUpdate(
          orderquery.queryOrderVOsByIds(new String[] {
            pk_order
          }, UFBoolean.FALSE));
    }
  }

  private void openPayPlanDLG(PayPlanViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0050")/*
                                                                   * @res
                                                                   * "没有付款计划"
                                                                   */);
    }
    FuncletInitData initData = new FuncletInitData();
    initData.setInitData(views);
    initData.setInitType(21);

    IFuncRegisterQueryService service =
        NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    FuncRegisterVO funvo = null;
    try {
      funvo = service.queryFunctionByCode(PuNodeCode.n40040418.code());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == funvo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0051")/*
                                                                   * @res
                                                                   * "付款计划未启用!"
                                                                   */);
    }
    // UIDialog.setReSet(true);
    // FuncletWindowLauncher.openFuncNodeForceModalDialog(WorkbenchEnvironment
    // .getInstance().getWorkbench(), funvo, initData, null, true);

    // Dimension d = new Dimension(800, 600);
    // new UIDialog().setReset(true);
    // FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment.getInstance()
    // .getWorkbench(), funvo, initData, null, true, true, d);
    FuncletWindowLauncher.openFuncNodeForceModalDialog(WorkbenchEnvironment
        .getInstance().getWorkbench(), funvo, initData, null, true,
        new Dimension(800, 600), true);
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getBillForm().getModel().getUiState() == UIState.EDIT
        || this.getBillForm().getModel().getUiState() == UIState.ADD) {
      return false;
    }

    OrderVO vo = (OrderVO) this.model.getSelectedData();
    if (null == vo) {
      return false;
    }
    if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
        .intValue()) {
      return false;
    }
    return true;

  }
}
