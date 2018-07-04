package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.ct.business.enumeration.Ninvctlstyle;
import nc.vo.ct.entity.CtBusinessVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.AssitunitAndQtunit;
import nc.vo.pu.m21.rule.Batchcode;
import nc.vo.pu.m21.rule.FlowStockOrgValue;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.PurchaseOrgValue;
import nc.vo.pu.m21.rule.ReqCorpDefaultValue;
import nc.vo.pu.m21.rule.SupplierDefaultInfo;
import nc.vo.pu.m21.rule.TaxRateSetter;
import nc.vo.pu.m21.rule.TaxTypeSetter;
import nc.vo.pu.m21.rule.UnitAndChangeRate;
import nc.vo.pu.m21.rule.VendorMaterial;
import nc.vo.pu.m21.rule.Vfree;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderTaxCountrySetter;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.scmpub.res.billtype.CTBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ϵı༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:22:41
 */
public class Material implements ICardBodyAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // Ϊ��Ч�ʣ��ȹرպϼ�
    boolean nc = event.getBillCardPanel().getBillModel().isNeedCalculate();
    event.getBillCardPanel().getBillModel().setNeedCalculate(false);   
    /*
     * change by wandl
     * ��ѡ���ϵ�ʱ����Ҫ���ع�������ñ�ʶ��������ع�����������������⡣
     */
    ClientContext clientContext = (ClientContext) event.getContext();
    clientContext.setNeedLoadRelationItem(false);
    int[] rows = null;
    try {
      // ���ϵĶ�ѡ����
      RefMoreSelectedUtils utils =
          new RefMoreSelectedUtils(event.getBillCardPanel());
      rows =
          utils.refMoreSelected(event.getRow(), PraybillItemVO.PK_MATERIAL,
              true);
    }
    finally {
      // �ָ������־��
      clientContext.setNeedLoadRelationItem(true);
    }
    /*
     * end
     */
    // ��������ż�
    this.setQpbaseschemeNull(rows, event.getBillCardPanel());
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    /*
     * add by wandl
     * �������ñ���ɹ���֯vid,���Ч�����⣡
     */
    this.processPurchaseOrgVid(card);
    // ������ϢĬ��ֵ�����絥λ�����ʵ�
    this.processDefaultMaterialInof(card, rows);
    this.setDefaultBodyFromCntClass(event, rows);
    // �����ͬ����
    ContractLinker contractLinker = new ContractLinker(event);
    Integer[] ctparams =
        this.getContractlinkParams(event.getBillCardPanel(), rows);
    // Զ�̵���ע�� ====================
    this.registerRemoteCall(card, rows);
    // ��֯ҵ��ί�й�ϵ����֯
    this.processDefaultOrganizationValue(rows);
    // ��������˾
    new ReqCorpDefaultValue(card).setDefaultValue(rows);
    // ����������֯
    this.processFlowStockOrg(rows);
    // ���ݹ�Ӧ�����Ϲ�ϵ����������Ϣ
    this.processVendorMaterialInfo();

    this.processSupInfo();

    // ���䱾λ�Һͻ��ʵ������Ϣ�������ÿɱ༭��
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(rows);
    // ��ȫ������Ϣ���������ϱ༭�����֯���ã�����������ܻᵥ������һ��Զ��
    this.registerCountryRemoteCall(card, rows);
    /*
     * change by wandl
     * ������0˰������Ч������
     */
    this.processCountryRemoteCall(card);
    // �������ÿ�
    new Vfree(card).setVfreeNull(rows);
    // ���κ��ÿ�
    new Batchcode(card).setBatchcodeNull(rows);
    if (!ArrayUtils.isEmpty(ctparams)) {
      // ������ͬ
      contractLinker.contractLink(ctparams, false, true);
    }
    // �༭�Դ���
    this.setCardEditable(event, rows);

    // mengjian ���ݲ���PO16�Զ�ѯ�������Զ�ѯ��
    this.setDefaultPrice(event, rows);

    // �ִ򿪺ϼ�
    event.getBillCardPanel().getBillModel().setNeedCalculate(nc);
    
    event.getBillCardPanel().getBillModel().execEditFormulaByKey(event.getRow(), event.getKey());
  }

  /**
   * ��������������������ͬ
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 ����01:37:32
   */
  private Integer[] getContractlinkParams(BillCardPanel panel, int[] rows) {
    List<Integer> notCntIndex = new ArrayList<Integer>();
    for (int row : rows) {
      String sourcetype =
          (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
      if (!ObjectUtils.equals(CTBillType.PurDaily.getCode(), sourcetype)) {
        panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTID);
        panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTROWID);
        panel.setBodyValueAt(null, row, OrderItemVO.VCONTRACTCODE);
        notCntIndex.add(Integer.valueOf(row));
      }
    }
    if (CollectionUtils.isEmpty(notCntIndex)) {
      return null;
    }
    return notCntIndex.toArray(new Integer[notCntIndex.size()]);
  }

  /**
   * ���ù�Ӧ�̹���
   * 
   * @param editor
   */
  private void processCountryRemoteCall(CardEditorHelper editor) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderVatValueFillRule.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(editor.getEditor(),
        vatrule.getValueChangeObject());
  }

  /**
   * ������ϢĬ��ֵ�����絥λ�����ʵ�
   * 
   * @param card
   * @param rows
   */
  private void processDefaultMaterialInof(CardEditorHelper card, int[] rows) {
    // ��˰���
    new TaxTypeSetter(card).setBodyTaxType(rows);
    // ˰��
    new TaxRateSetter(card).setBodyTaxRate(rows);
    // ���õ�λ�ͱ��۵�λ
    new AssitunitAndQtunit(card).setAssistunitAndQtunit(rows);
    // ���û�����
    UnitAndChangeRate rate = new UnitAndChangeRate(card);
    rate.setChangeRate(rows);
    rate.setQtChangeRate(rows);
    this.relationCalculate(card.getEditor(), rows);
  }
  
  private void processPurchaseOrgVid(CardEditorHelper card){
  	new PurchaseOrgValue(card).setPurchaseOrgValue();
  }

  private void processDefaultOrganizationValue(int[] rows) {
    OrganizationDefaultValue odv =
        (OrganizationDefaultValue) this.remoteCaller
            .get(OrganizationDefaultValue.class.getName());
    odv.setDefaultOrganizationValue(rows);
  }

  private void processFlowStockOrg(int[] rows) {
    FlowStockOrgValue fsov =
        (FlowStockOrgValue) this.remoteCaller.get(FlowStockOrgValue.class
            .getName());
    fsov.setFlowStockOrg(rows);
  }

  private void processSupInfo() {
    SupplierDefaultInfo sup =
        (SupplierDefaultInfo) this.remoteCaller.get(SupplierDefaultInfo.class
            .getName());
    sup.setSupplierDefaultInfo();
  }

  private void processVendorMaterialInfo() {
    VendorMaterial vmRule =
        (VendorMaterial) this.remoteCaller.get(VendorMaterial.class.getName());
    vmRule.setMaterialInfo();
  }

  private CtBusinessVO queryCtBusinessByPks(CardBodyAfterEditEvent event,
      String ccontractrowid) {
    ClientContext ctx = (ClientContext) event.getContext();
    return ctx.getCtBusiType(ccontractrowid);
  }

  private void registerCountryRemoteCall(CardEditorHelper editor, int[] rows) {
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    countryList.add(new OrderSendCountrySetter(editor, rows));// ������
    countryList.add(new OrderReceiveCountrySetter(editor, rows));// �ջ���
    countryList.add(new OrderTaxCountrySetter(editor, rows));// ��˰��
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(editor, rows, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderVatValueFillRule.class.getName(), vatrule);
  }

  private void registerRemoteCall(CardEditorHelper card, int[] rows) {
    // ��֯ҵ��ί�й�ϵ����֯
    OrganizationDefaultValue odv = new OrganizationDefaultValue(card);
    odv.registerCombineRemoteCall(rows);
    this.remoteCaller.put(OrganizationDefaultValue.class.getName(), odv);

    FlowStockOrgValue fsov = new FlowStockOrgValue(card);
    fsov.registerCombineRemoteCall(rows);
    this.remoteCaller.put(FlowStockOrgValue.class.getName(), fsov);

    // ���ݹ�Ӧ�����Ϲ�ϵ����������Ϣ
    VendorMaterial vmRule = new VendorMaterial(card, rows);
    vmRule.prepare();
    this.remoteCaller.put(VendorMaterial.class.getName(), vmRule);

    SupplierDefaultInfo supRule = new SupplierDefaultInfo(card, rows);
    supRule.prepare();
    this.remoteCaller.put(SupplierDefaultInfo.class.getName(), supRule);
  }

  private void relationCalculate(BillCardPanel panel, int[] rows) {
    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, rows, OrderItemVO.VCHANGERATE);
    cal.calculate(panel, rows, OrderItemVO.VQTUNITRATE);
  }

  private void setCardEditable(CardBodyAfterEditEvent event, int[] rows) {
    // ���Ƹ�������صı༭��
    new EditableSetter(event.getBillCardPanel()).setEditableByMaterial(rows);
    // ���Ƹ���ͬ��صı༭��
    new EditableSetter(event.getBillCardPanel()).setEditableByContract(rows);
    // ���Ƹ�������λ��صı༭��
    new EditableSetter(event.getBillCardPanel()).setEditableByUnit(rows);
  }

  private void setDefaultBodyFromCntClass(CardBodyAfterEditEvent event,
      int[] rows) {
    if (null == rows || 0 == rows.length) {
      return;
    }
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();

    boolean addLine = row == panel.getBillModel().getRowCount() - rows.length;
    if (!addLine) {
      // ƽ̨�޸Ĳ��ԣ����ص�˳���63�෴��
      row = rows[0];
    }

    String csoucetypecode =
        (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
    if (!ObjectUtils.equals(CTBillType.PurDaily.getCode(), csoucetypecode)) {
      return;
    }

    String ccontractrowid =
        (String) panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID);
    CtBusinessVO ctvo = this.queryCtBusinessByPks(event, ccontractrowid);
    if (ctvo == null
        || !Ninvctlstyle.MARBASCLASS.value().equals(ctvo.getNinvctlstyle())) {
      return;
    }

    for (int i = 1; i < rows.length; ++i) {
      this.setDefaultValueFromCntValue(panel, row, rows[i]);
    }
  }

  /**
   * �Զ�ѯ��
   * mengjian
   * 
   * @param event
   * @param rows
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardBodyAfterEditEvent event, int[] rows) {
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(),
        PriceParam.Material, rows);
  }

  private void setDefaultValueFromCntValue(BillCardPanel panel, int row,
      int targetrow) {
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.CSOURCEBID),
        targetrow, OrderItemVO.CSOURCEBID);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.CSOURCEID),
        targetrow, OrderItemVO.CSOURCEID);
    panel.setBodyValueAt(
        panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE), targetrow,
        OrderItemVO.CSOURCETYPECODE);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.VSOURCECODE),
        targetrow, OrderItemVO.VSOURCECODE);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.VSOURCEROWNO),
        targetrow, OrderItemVO.VSOURCEROWNO);
    panel.setBodyValueAt(
        panel.getBodyValueAt(row, OrderItemVO.VSOURCETRANTYPE), targetrow,
        OrderItemVO.VSOURCETRANTYPE);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.CCONTRACTID),
        targetrow, OrderItemVO.CCONTRACTID);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID),
        targetrow, OrderItemVO.CCONTRACTROWID);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.VCONTRACTCODE),
        targetrow, OrderItemVO.VCONTRACTCODE);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.SOURCEBTS),
        targetrow, OrderItemVO.SOURCEBTS);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.SOURCETS),
        targetrow, OrderItemVO.SOURCETS);

    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.NORIGPRICE),
        targetrow, OrderItemVO.NORIGPRICE);
    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.NORIGTAXPRICE),
        targetrow, OrderItemVO.NORIGTAXPRICE);

    panel.setBodyValueAt(panel.getBodyValueAt(row, OrderItemVO.NQTORIGPRICE),
        targetrow, OrderItemVO.NQTORIGPRICE);
    panel.setBodyValueAt(
        panel.getBodyValueAt(row, OrderItemVO.NQTORIGTAXPRICE), targetrow,
        OrderItemVO.NQTORIGTAXPRICE);

  }

  private void setQpbaseschemeNull(int[] rows, BillCardPanel panel) {
    if (null == rows || rows.length == 0) {
      return;
    }
    for (int i = 0; i < rows.length; ++i) {
      panel.setBodyValueAt(null, rows[i], OrderItemVO.CQPBASESCHEMEID);
    }
  }

}
