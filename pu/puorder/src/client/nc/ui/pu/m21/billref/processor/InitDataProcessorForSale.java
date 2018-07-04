/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 上午10:54:10
 */
package nc.ui.pu.m21.billref.processor;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.m21.rule.ReferenceFilterByOrg;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.billref.push.NodeOpenBillInitData;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.editor.BillForm;
import nc.ui.uif2.model.AppEventConst;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售补货直运生成采购订单节点监听处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-25 上午10:54:10
 */
public class InitDataProcessorForSale implements IInitDataProcessor {

  private TransferViewProcessor transferProcessor;

  /**
   * @return transferProcessor
   */
  public TransferViewProcessor getTransferProcessor() {
    return this.transferProcessor;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor#process(nc.funcnode.ui.FuncletInitData)
   */
  @Override
  public void process(FuncletInitData data) {
    // this.getTransferProcessor().getBillForm().getBillOrgPanel().setPkOrg(null);
    this.getTransferProcessor().getBillForm().getModel().initModel(null);
    if (!(data instanceof NodeOpenBillInitData)) {
      return;
    }

    NodeOpenBillInitData initData = (NodeOpenBillInitData) data;
    OrderVO[] orderVOs = (OrderVO[]) initData.getInitData();
    if (ArrayUtils.isEmpty(orderVOs)) {
      return;
    }
    String sourceBillType = initData.getSourceBillType();
    // this.setDefaultValue(orderVOs, initData.getOrgId());

    String pk_group =
        this.getTransferProcessor().getBillForm().getModel().getContext()
            .getPk_group();
    OrderScaleSetter scale = new OrderScaleSetter(pk_group);
    scale.setCardScale(this.getTransferProcessor().getBillForm()
        .getBillCardPanel());
    // scale.setListScale(this.getTransferProcessor().getListView()
    // .getBillListPanel());

    if (SOBillType.Order.getCode().equals(sourceBillType)) {

      this.getTransferProcessor().processBillTransfer(orderVOs);
      this.setEnabledFromSO();
    }
    else if (TOBillType.TransOrder.getCode().equals(sourceBillType)
        || ETBillType.CONTRACT.getCode().equals(sourceBillType)) {
      // this.setDefValueForTO(orderVOs);
      this.getTransferProcessor().processBillTransfer(orderVOs);
    }

    // 过滤
    new ReferenceFilterByOrg(this.getTransferProcessor().getBillForm())
        .filter();

    // 触发事件设置按钮状态，修改按钮不可用
    this.getTransferProcessor().getBillForm().getModel()
        .fireEvent(new AppEvent(AppEventConst.MODEL_INITIALIZED));

  }

  /**
   * @param transferProcessor
   *          要设置的 transferProcessor
   */
  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  /**
   * 方法功能描述：设置组织默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param orgId
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-26 上午09:22:02
   */
  // private void setDefaultValue(OrderVO[] orderVOs, String orgId) {
  // for (OrderVO orderVO : orderVOs) {
  // OrderHeaderVO headerVO = orderVO.getHVO();
  // headerVO.setPk_org(orgId);
  //
  // BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
  // OrderItemVO[] items = orderVO.getBVO();
  // int[] rows = new int[items.length];
  // for (int i = 0; i < items.length; i++) {
  // rows[i] = i;
  // }
  //
  // // 补充供应商
  // // 单位
  // // 补充组织相关的信息
  // // new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
  // new PurchaseOrgValue(helper).setPurchaseOrgValue();
  //
  // // 补充本位币和汇率的相关信息
  // new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  //
  // // 默认单位 批量
  // new AutoSwitchPUAssistUnit().process(new OrderVO[] {
  // orderVO
  // });
  // // 设置数量
  // new NumValueSetter(helper).setNastnum(rows);
  //
  // // 补充订单类型
  // // 暂时这样处理，问问晓东可不可以直接取直运标记
  // UFBoolean direct =
  // M30SOServices.queryIsDirectPO(orderVO.getBVO()[0]
  // .getVsourcetrantype());
  // if (UFBoolean.TRUE.equals(direct)) {
  // new TrantypeValue(helper).setDirectTrantypeValue();
  // }
  // else {
  // new TrantypeValue(helper).setTrantypeValue();
  // }
  //
  // // 收货仓库
  // for (int i = 0; i < rows.length; ++i) {
  // new WarehouseDefaultValue(helper).setWarehouseDefaultValue(i);
  // }
  // }
  // }

  // private void setDefValueForTO(OrderVO[] orderVOs) {
  // for (OrderVO orderVO : orderVOs) {
  // OrderItemVO[] items = orderVO.getBVO();
  // int[] rows = new int[items.length];
  // for (int i = 0; i < items.length; i++) {
  // rows[i] = i;
  // }
  // BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
  // new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
  // }
  //
  // }

  /**
   * 方法功能描述：设置字段的可编辑性和必输性
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-11 上午09:17:03
   */
  private void setEnabledFromSO() {
    BillForm billForm = this.getTransferProcessor().getBillForm();
    BillCardPanel panel = billForm.getBillCardPanel();
    for (int i = 0; i < panel.getRowCount(); ++i) {
      panel.setCellEditable(i, OrderItemVO.PK_MATERIAL, false);
      panel.setCellEditable(i, OrderItemVO.PK_ARRVSTOORG, false);
    }
  }
}
