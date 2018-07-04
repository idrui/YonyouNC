/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 ����10:54:10
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۲���ֱ�����ɲɹ������ڵ����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-25 ����10:54:10
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
   * ���෽����д
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

    // ����
    new ReferenceFilterByOrg(this.getTransferProcessor().getBillForm())
        .filter();

    // �����¼����ð�ť״̬���޸İ�ť������
    this.getTransferProcessor().getBillForm().getModel()
        .fireEvent(new AppEvent(AppEventConst.MODEL_INITIALIZED));

  }

  /**
   * @param transferProcessor
   *          Ҫ���õ� transferProcessor
   */
  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  /**
   * ��������������������֯Ĭ��ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVOs
   * @param orgId
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-26 ����09:22:02
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
  // // ���乩Ӧ��
  // // ��λ
  // // ������֯��ص���Ϣ
  // // new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
  // new PurchaseOrgValue(helper).setPurchaseOrgValue();
  //
  // // ���䱾λ�Һͻ��ʵ������Ϣ
  // new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
  //
  // // Ĭ�ϵ�λ ����
  // new AutoSwitchPUAssistUnit().process(new OrderVO[] {
  // orderVO
  // });
  // // ��������
  // new NumValueSetter(helper).setNastnum(rows);
  //
  // // ���䶩������
  // // ��ʱ�����������������ɲ�����ֱ��ȡֱ�˱��
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
  // // �ջ��ֿ�
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
   * �������������������ֶεĿɱ༭�Ժͱ�����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-11 ����09:17:03
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
