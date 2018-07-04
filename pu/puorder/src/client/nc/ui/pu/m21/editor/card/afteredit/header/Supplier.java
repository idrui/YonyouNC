package nc.ui.pu.m21.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.m21.rule.RelationCalculateAfterQuoter;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.PaymentInfo;
import nc.vo.pu.m21.rule.SupplierDefaultInfo;
import nc.vo.pu.m21.rule.SupplierDefaultValue;
import nc.vo.pu.m21.rule.VendorMaterial;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSupplierCountrySetter;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 上午11:33:47
 */
public class Supplier implements ICardHeadTailAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCallSrv =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
  	event.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    String newValue = ValueUtils.getString(event.getValue());
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    // 1.清空供应商时
    if (StringUtil.isEmptyWithTrim(newValue)) {
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.PK_BANKDOC, null);
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.VBANKACCOUNT, null);
      this.clearVendorMaterInfo(editor);
      return;
    }
    Integer[] rows = this.getNotNullMaterialRow(editor);
    // ============= 第一次注册远程调用，这里的远程调用不能有依赖性 =====
    this.registerFirstRemoteCallService(editor, rows);
    // 记录原始币种，供应商导致币种改变时清空单价
    String oldCurrency =
        editor.getHeadStringValue(OrderHeaderVO.CORIGCURRENCYID);
    // 获得供应商信息,并做相应逻辑处理
    this.processSupplierInfo(editor);
    // 根据供应商物料关系设置物料信息
    this.processVendorMaterialRelation();

    this.processSupArea();

    // 币种汇率重新计算，原因是供应商可能改变了币种
    this.setCurrencyAndExchangerate(editor, event.getBillCardPanel());
    // 清空表体合同信息
    this.setCntNull(event.getBillCardPanel());
    // // 设置供应商国家
    // this.processCountryRemoteCall(editor,
    // (OrderVatValueFillRule) this.remoteCallSrv
    // .get(OrderSupplierCountrySetter.class.getName()));
    // 关联合同依赖以上处理后的数据，不能单独处理
    this.processContractLinker(event, rows);
    // 获得最新币种，供应商导致币种改变时清空单价
    String newCurrency =
        editor.getHeadStringValue(OrderHeaderVO.CORIGCURRENCYID);
    if (oldCurrency == null || !oldCurrency.equals(newCurrency)) {
      Map<Integer, String> map = this.clearPrice(event);
      RelationCalculateAfterQuoter tool =
          new RelationCalculateAfterQuoter(event.getBillCardPanel());
      tool.relationCalculate(map);
    }
    // ============ 第二次远程调用注册 ==============================
    this.registerSecondeemoteCallService(editor, rows, event);
    this.processSecondRemoteCall(editor, rows, event);
    // mengjian 根据参数PO16自动询价条件自动询价
    // 联动计算时，购销类型会影响价格的计算，所以将询价放到后面
    this.setDefaultPrice(event);

  }

  /**
   * 关联合同的处理，包括编辑事件等
   * 
   * @param event
   * @param rows
   */
  public void processContractLinker(CardHeadTailAfterEditEvent event,
      Integer[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    ContractLinker contractLinker = new ContractLinker(event);
    contractLinker.contractLink(rows, false, false);
    new EditableSetter(event.getBillCardPanel())
        .setEditableByContract(ArrayUtils.toPrimitive(rows));
  }

  /**
   * 清空表体单价
   * 
   * @param event
   */
  private Map<Integer, String> clearPrice(CardHeadTailAfterEditEvent event) {
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    Map<Integer, String> map = new HashMap<Integer, String>();
    int count = editor.getEditor().getRowCount();
    for (int i = 0; i < count; i++) {
      editor.clearBodyValue(i, new String[] {
        OrderItemVO.NNETPRICE
      });
      map.put(Integer.valueOf(i), OrderItemVO.NNETPRICE);
    }
    return map;
  }

  private void clearVendorMaterInfo(IKeyValue editor) {
    for (int i = 0; i < editor.getItemCount(); i++) {
      editor.setBodyValue(i, OrderItemVO.VVENDINVENTORYCODE, null);
      editor.setBodyValue(i, OrderItemVO.VVENDINVENTORYNAME, null);
    }
  }

  private void dealPayMent(CardEditorHelper editor) {
    CardEditorHelper helper = new CardEditorHelper(editor.getEditor());
    String pk_payterm = helper.getHeadStringValue(OrderHeaderVO.PK_PAYTERM);
    if (pk_payterm == null || pk_payterm.isEmpty()) {
      return;
    }
    OrderPaymentVO[] paymentVOs = PaymentInfo.getOrderPaymentVOs(pk_payterm);
    BillModel model = editor.getEditor().getBillModel(OrderPaymentVO.TABSNAME);

    model.clearBodyData();
    model.setBodyDataVO(paymentVOs);
    // 对应参照类型的字段，重新设置一下关联项，防止出现显示主键现象
    model.loadLoadRelationItemValue(0, paymentVOs.length, new String[] {
      OrderPaymentVO.PK_PAYPERIOD, OrderPaymentVO.PK_BALATYPE,
      OrderPaymentVO.PK_RATE
    });
  }

  private int[] getCardRows(CardEditorHelper editor) {
    int[] countryRows = new int[editor.getItemCount()];
    for (int i = 0; i < editor.getItemCount(); i++) {
      countryRows[i] = i;
    }
    return countryRows;
  }

  private Integer[] getNotNullMaterialRow(CardEditorHelper editor) {
    List<Integer> rowsList = new ArrayList<Integer>();
    for (int i = 0; i < editor.getItemCount(); i++) {
      if (editor.getBodyValue(i, OrderItemVO.PK_MATERIAL) != null) {
        rowsList.add(Integer.valueOf(i));
      }
    }
    Integer[] rows = rowsList.toArray(new Integer[rowsList.size()]);
    return rows;
  }

  /**
   * 设置供应商国家
   * 
   * @param editor
   */
  private void processCountryRemoteCall(CardEditorHelper editor,
      OrderVatValueFillRule vatrule) {
    vatrule.process();
    OrderCalculatorUtils.calculate(editor.getEditor(),
        vatrule.getValueChangeObject());
  }

  /**
   * 第二次远程调用处理
   * 
   * @param editor
   * @param rows
   * @param event
   */
  private void processSecondRemoteCall(CardEditorHelper editor, Integer[] rows,
      CardHeadTailAfterEditEvent event) {
    // 国家处理
    this.processCountryRemoteCall(editor,
        (OrderVatValueFillRule) this.remoteCallSrv
            .get(OrderSendAddressCountrySetter.class.getName()));
  }

  private void processSupArea() {
    this.remoteCallSrv.get(SupplierDefaultInfo.class.getName()).process();
  }

  /**
   * 供应商信息处理
   * 
   * @param editor
   */
  private void processSupplierInfo(CardEditorHelper editor) {
    SupplierFreeCustInfoUtil supplierUtil =
        (SupplierFreeCustInfoUtil) this.remoteCallSrv
            .get(SupplierFreeCustInfoUtil.class.getName());
    // 获得供应商信息
    SupplierInfo supplier = supplierUtil.getSupplierInfo(editor);
    // 设置供应商的默认值
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(editor);
    vendorDefaultValue.setDefaultValue(supplier);
    // IKeyValue 接口写的太恶心，供应商的默认付款协议改变在此单独处理
    this.dealPayMent(editor);

    // 设置散户的可编辑性,及银行账号
    supplierUtil.setSuppliername(OrderHeaderVO.PK_INVCSUPLLIER);
    supplierUtil.setFreeCustBankAcc(editor);
  }

  /**
   * 根据供应商物料关系设置物料信息
   */
  private void processVendorMaterialRelation() {
    this.remoteCallSrv.get(VendorMaterial.class.getName()).process();
  }

  // /**
  // * 第一次远程调用
  // *
  // * @param editor
  // * @param rows
  // */
  // private void registerCountryRemoteCall(CardEditorHelper editor) {
  // int[] countryRows = this.getCardRows(editor);
  // List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
  // OrderSupplierCountrySetter setter =
  // new OrderSupplierCountrySetter(CountryType.sendCountry, editor,
  // countryRows);
  // setter.setFromSourcebill(false);
  // countryList.add(setter);
  // OrderVatValueFillRule vatrule =
  // new OrderVatValueFillRule(editor, countryRows, countryList);
  // vatrule.prepare();
  // this.remoteCallSrv.put(OrderSupplierCountrySetter.class.getName(),
  // vatrule);
  // }

  /**
   * 注册远程调用，这里的远程调用相互不能有依赖性
   * 
   * @param editor
   * @param rows
   */
  private void registerFirstRemoteCallService(CardEditorHelper editor,
      Integer[] rows) {
    // CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    SupplierFreeCustInfoUtil supplierUtil =
        new SupplierFreeCustInfoUtil(OrderHeaderVO.PK_BANKDOC,
            OrderHeaderVO.PK_FREECUST, OrderHeaderVO.VBANKACCOUNT);
    supplierUtil.registerCombineRemoteCall(editor);

    // 根据供应商物料关系设置物料信息
    VendorMaterial vmRule =
        new VendorMaterial(editor, ArrayUtils.toPrimitive(rows));
    vmRule.prepare();

    this.remoteCallSrv.put(SupplierFreeCustInfoUtil.class.getName(),
        supplierUtil);
    this.remoteCallSrv.put(VendorMaterial.class.getName(), vmRule);
    // 供应商国家
    // this.registerCountryRemoteCall(editor);

    SupplierDefaultInfo supRule =
        new SupplierDefaultInfo(editor, ArrayUtils.toPrimitive(rows));
    supRule.prepare();
    this.remoteCallSrv.put(SupplierDefaultInfo.class.getName(), supRule);

  }

  /**
   * 第二次远程调用注册
   * 
   * @param editor
   * @param rows
   * @param event
   */
  private void registerSecondeemoteCallService(CardEditorHelper editor,
      Integer[] rows, CardHeadTailAfterEditEvent event) {

    // 根据发货地址处理一次vat相关,再根据供应商设置国家
    int[] countryRows = this.getCardRows(editor);
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    OrderSendAddressCountrySetter addrSetter =
        new OrderSendAddressCountrySetter(CountryType.sendCountry, editor,
            countryRows);
    addrSetter.setFromSourcebill(false);
    OrderSupplierCountrySetter supSetter =
        new OrderSupplierCountrySetter(CountryType.sendCountry, editor,
            countryRows);
    supSetter.setFromSourcebill(false);
    countryList.add(addrSetter);
    countryList.add(supSetter);
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(editor, countryRows, countryList);
    vatrule.prepare();
    this.remoteCallSrv.put(OrderSendAddressCountrySetter.class.getName(),
        vatrule);
  }

  /**
   * 方法功能描述：将表体的合同信息清空
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午06:25:13
   */
  private void setCntNull(BillCardPanel panel) {
    for (int i = 0; i < panel.getRowCount(); ++i) {
      panel.setBodyValueAt(null, i, OrderItemVO.CCONTRACTID);
      panel.setBodyValueAt(null, i, OrderItemVO.CCONTRACTROWID);
      panel.setBodyValueAt(null, i, OrderItemVO.VCONTRACTCODE);
    }
  }

  private void setCurrencyAndExchangerate(CardEditorHelper editor,
      BillCardPanel panel) {
    new CurrencyAndExchangerate(editor).setCurrencyAndExchangeRate();
    int count = editor.getItemCount();
    int[] rows = new int[count];
    for (int i = 0; i < count; ++i) {
      rows[i] = i;
    }
    boolean oldneedCalFlag = panel.getBillModel().isNeedCalculate();
    panel.getBillModel().setNeedCalculate(false);

    RelationCalculate tool = new RelationCalculate();
    tool.calculate(panel, rows, OrderItemVO.NEXCHANGERATE);
    panel.getBillModel().setNeedCalculate(oldneedCalFlag);
  }

  /**
   * 自动询价
   * mengjian
   * 
   * @param event
   * @param rows
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardHeadTailAfterEditEvent event) {
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    int[] rows = new int[editor.getItemCount()];
    for (int i = 0; i < editor.getItemCount(); ++i) {
      rows[i] = i;
    }
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(),
        PriceParam.Supplier, rows);
  }

}
