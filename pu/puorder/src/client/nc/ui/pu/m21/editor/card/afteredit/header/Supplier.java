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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ�̵ı༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����11:33:47
 */
public class Supplier implements ICardHeadTailAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCallSrv =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
  	event.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    String newValue = ValueUtils.getString(event.getValue());
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    // 1.��չ�Ӧ��ʱ
    if (StringUtil.isEmptyWithTrim(newValue)) {
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.PK_BANKDOC, null);
      event.getBillCardPanel().setHeadItem(OrderHeaderVO.VBANKACCOUNT, null);
      this.clearVendorMaterInfo(editor);
      return;
    }
    Integer[] rows = this.getNotNullMaterialRow(editor);
    // ============= ��һ��ע��Զ�̵��ã������Զ�̵��ò����������� =====
    this.registerFirstRemoteCallService(editor, rows);
    // ��¼ԭʼ���֣���Ӧ�̵��±��ָı�ʱ��յ���
    String oldCurrency =
        editor.getHeadStringValue(OrderHeaderVO.CORIGCURRENCYID);
    // ��ù�Ӧ����Ϣ,������Ӧ�߼�����
    this.processSupplierInfo(editor);
    // ���ݹ�Ӧ�����Ϲ�ϵ����������Ϣ
    this.processVendorMaterialRelation();

    this.processSupArea();

    // ���ֻ������¼��㣬ԭ���ǹ�Ӧ�̿��ܸı��˱���
    this.setCurrencyAndExchangerate(editor, event.getBillCardPanel());
    // ��ձ����ͬ��Ϣ
    this.setCntNull(event.getBillCardPanel());
    // // ���ù�Ӧ�̹���
    // this.processCountryRemoteCall(editor,
    // (OrderVatValueFillRule) this.remoteCallSrv
    // .get(OrderSupplierCountrySetter.class.getName()));
    // ������ͬ�������ϴ��������ݣ����ܵ�������
    this.processContractLinker(event, rows);
    // ������±��֣���Ӧ�̵��±��ָı�ʱ��յ���
    String newCurrency =
        editor.getHeadStringValue(OrderHeaderVO.CORIGCURRENCYID);
    if (oldCurrency == null || !oldCurrency.equals(newCurrency)) {
      Map<Integer, String> map = this.clearPrice(event);
      RelationCalculateAfterQuoter tool =
          new RelationCalculateAfterQuoter(event.getBillCardPanel());
      tool.relationCalculate(map);
    }
    // ============ �ڶ���Զ�̵���ע�� ==============================
    this.registerSecondeemoteCallService(editor, rows, event);
    this.processSecondRemoteCall(editor, rows, event);
    // mengjian ���ݲ���PO16�Զ�ѯ�������Զ�ѯ��
    // ��������ʱ���������ͻ�Ӱ��۸�ļ��㣬���Խ�ѯ�۷ŵ�����
    this.setDefaultPrice(event);

  }

  /**
   * ������ͬ�Ĵ��������༭�¼���
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
   * ��ձ��嵥��
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
    // ��Ӧ�������͵��ֶΣ���������һ�¹������ֹ������ʾ��������
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
   * ���ù�Ӧ�̹���
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
   * �ڶ���Զ�̵��ô���
   * 
   * @param editor
   * @param rows
   * @param event
   */
  private void processSecondRemoteCall(CardEditorHelper editor, Integer[] rows,
      CardHeadTailAfterEditEvent event) {
    // ���Ҵ���
    this.processCountryRemoteCall(editor,
        (OrderVatValueFillRule) this.remoteCallSrv
            .get(OrderSendAddressCountrySetter.class.getName()));
  }

  private void processSupArea() {
    this.remoteCallSrv.get(SupplierDefaultInfo.class.getName()).process();
  }

  /**
   * ��Ӧ����Ϣ����
   * 
   * @param editor
   */
  private void processSupplierInfo(CardEditorHelper editor) {
    SupplierFreeCustInfoUtil supplierUtil =
        (SupplierFreeCustInfoUtil) this.remoteCallSrv
            .get(SupplierFreeCustInfoUtil.class.getName());
    // ��ù�Ӧ����Ϣ
    SupplierInfo supplier = supplierUtil.getSupplierInfo(editor);
    // ���ù�Ӧ�̵�Ĭ��ֵ
    SupplierDefaultValue vendorDefaultValue = new SupplierDefaultValue(editor);
    vendorDefaultValue.setDefaultValue(supplier);
    // IKeyValue �ӿ�д��̫���ģ���Ӧ�̵�Ĭ�ϸ���Э��ı��ڴ˵�������
    this.dealPayMent(editor);

    // ����ɢ���Ŀɱ༭��,�������˺�
    supplierUtil.setSuppliername(OrderHeaderVO.PK_INVCSUPLLIER);
    supplierUtil.setFreeCustBankAcc(editor);
  }

  /**
   * ���ݹ�Ӧ�����Ϲ�ϵ����������Ϣ
   */
  private void processVendorMaterialRelation() {
    this.remoteCallSrv.get(VendorMaterial.class.getName()).process();
  }

  // /**
  // * ��һ��Զ�̵���
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
   * ע��Զ�̵��ã������Զ�̵����໥������������
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

    // ���ݹ�Ӧ�����Ϲ�ϵ����������Ϣ
    VendorMaterial vmRule =
        new VendorMaterial(editor, ArrayUtils.toPrimitive(rows));
    vmRule.prepare();

    this.remoteCallSrv.put(SupplierFreeCustInfoUtil.class.getName(),
        supplierUtil);
    this.remoteCallSrv.put(VendorMaterial.class.getName(), vmRule);
    // ��Ӧ�̹���
    // this.registerCountryRemoteCall(editor);

    SupplierDefaultInfo supRule =
        new SupplierDefaultInfo(editor, ArrayUtils.toPrimitive(rows));
    supRule.prepare();
    this.remoteCallSrv.put(SupplierDefaultInfo.class.getName(), supRule);

  }

  /**
   * �ڶ���Զ�̵���ע��
   * 
   * @param editor
   * @param rows
   * @param event
   */
  private void registerSecondeemoteCallService(CardEditorHelper editor,
      Integer[] rows, CardHeadTailAfterEditEvent event) {

    // ���ݷ�����ַ����һ��vat���,�ٸ��ݹ�Ӧ�����ù���
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
   * ��������������������ĺ�ͬ��Ϣ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 ����06:25:13
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
   * �Զ�ѯ��
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
