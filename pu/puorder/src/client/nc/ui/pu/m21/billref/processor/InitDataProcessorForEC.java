package nc.ui.pu.m21.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m21.rule.ReferenceFilterByOrg;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-28 上午08:19:43
 * @author wuxla
 */

public class InitDataProcessorForEC implements IInitDataProcessor {
  private TransferViewProcessor transferProcessor;

  public TransferViewProcessor getTransferProcessor() {
    return this.transferProcessor;
  }

  @Override
  public void process(FuncletInitData data) {
    this.getTransferProcessor().getBillForm().getModel().initModel(null);
    OrderVO[] orderVOs = (OrderVO[]) data.getInitData();

    if (ArrayUtils.isEmpty(orderVOs)) {
      return;
    }
    // this.setDefValueWhenFromEC(orderVOs);
    this.getTransferProcessor().processBillTransfer(orderVOs);
    // 过滤
    new ReferenceFilterByOrg(this.getTransferProcessor().getBillForm())
        .filter();

    // 触发事件设置按钮状态，修改按钮不可用
    this.getTransferProcessor().getBillForm().getModel()
        .fireEvent(new AppEvent(AppEventConst.MODEL_INITIALIZED));
  }

  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  // private void calculate(OrderVO[] orderVOs) {
  // // 计算
  // for (OrderVO orderVO : orderVOs) {
  // new RelationCalculate().calculate(orderVO, OrderItemVO.NQTUNITNUM);
  // }
  // }

  // private SupplierInfo getSupplierInfo(IKeyValue keyValue) {
  // String pk_supplier =
  // (String) keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
  // if (pk_supplier == null) {
  // return null;
  // }
  // String pk_purchaseorg =
  // (String) keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
  // SupplierInfo supplier = null;
  // try {
  // supplier =
  // NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
  // .querySupplier(pk_supplier, pk_purchaseorg);
  // }
  // catch (BusinessException e) {
  // // 日志异常
  // ExceptionUtils.wrappException(e);
  //
  // }
  // return supplier;
  // }

  // private void setDefValueWhenFromEC(OrderVO[] orderVOs) {
  // for (OrderVO vo : orderVOs) {
  // BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
  // OrderItemVO[] items = vo.getBVO();
  // int[] rows = new int[items.length];
  // for (int i = 0; i < items.length; i++) {
  // rows[i] = i;
  // }
  //
  // // 补充组织相关的信息
  // new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
  //
  // // 获得供应商信息
  // SupplierInfo supplier = this.getSupplierInfo(helper);
  //
  // // 设置供应商的默认值
  // SupplierDefaultValue vendorDefaultValue =
  // new SupplierDefaultValue(helper);
  // vendorDefaultValue.setDefaultValue(supplier);
  //
  // // 补充本位币和汇率的相关信息
  // new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  // }
  //
  // // 计算
  // this.calculate(orderVOs);
  // }
}
