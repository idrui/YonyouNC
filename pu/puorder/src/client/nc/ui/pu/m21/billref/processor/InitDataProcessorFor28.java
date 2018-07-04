/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-2 下午02:36:59
 */
package nc.ui.pu.m21.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格审批单推订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-2 下午02:36:59
 */
public class InitDataProcessorFor28 implements IInitDataProcessor {

  private TransferViewProcessor transferProcessor;

  public TransferViewProcessor getTransferProcessor() {
    return this.transferProcessor;
  }

  /**
   * @return transferProcessor
   */
  // public TransferBillViewProcessor getTransferProcessor() {
  // return this.transferProcessor;
  // }

  @Override
  public void process(FuncletInitData data) {
    // this.getTransferProcessor().getBillForm().getBillOrgPanel().setPkOrg(null);
    this.getTransferProcessor().getBillForm().getModel().initModel(null);
    OrderVO[] orderVOs = (OrderVO[]) data.getInitData();

    String pk_group =
        this.getTransferProcessor().getBillForm().getModel().getContext()
            .getPk_group();
    new OrderScaleSetter(pk_group).setCardScale(this.getTransferProcessor()
        .getBillForm().getBillCardPanel());

    // this.setDefValueWhenFromAskBill(orderVOs);

    this.getTransferProcessor().processBillTransfer(orderVOs);
    // this.getTransferProcessor().getBillForm().getModel()
    // .setUiState(UIState.ADD);
    // 过滤
    // new ReferenceFilterByOrg(this.getTransferProcessor().getBillForm())
    // .filter();

    // 触发事件设置按钮状态，修改按钮不可用
    // this.getTransferProcessor().getBillForm().getModel()
    // .fireEvent(new AppEvent(AppEventConst.MODEL_INITIALIZED));
  }

  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  /**
   * @param transferProcessor
   *          要设置的 transferProcessor
   */
  // public void setTransferProcessor(TransferBillViewProcessor
  // transferProcessor) {
  // this.transferProcessor = transferProcessor;
  // }

  /**
   * 方法功能描述：计算
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-2 下午03:25:15
   */
  // private void calculate(OrderVO[] orderVOs) {
  // // 计算
  // for (OrderVO orderVO : orderVOs) {
  // new RelationCalculate().calculate(orderVO, OrderItemVO.NQTORIGTAXPRICE);
  // }
  // }

  /**
   * 方法功能描述：设置默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-2 下午03:24:58
   */
  // private void setDefValueWhenFromAskBill(OrderVO[] orderVOs) {
  // for (OrderVO orderVO : orderVOs) {
  // BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
  // OrderItemVO[] items = orderVO.getBVO();
  // int[] rows = new int[items.length];
  // for (int i = 0; i < items.length; i++) {
  // rows[i] = i;
  // }
  // // 补充组织相关的信息
  // new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
  //
  // // 补充本位币和汇率的相关信息
  // new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  //
  // // 计划到货日期
  // new PlanArriveDate(helper).setPlanArriveDate(0, items.length - 1);
  //
  // // 以后改成批量的
  // new TrantypeValue(helper).setTrantypeValue();
  // }
  //
  // // 计算
  // this.calculate(orderVOs);
  // }

}
