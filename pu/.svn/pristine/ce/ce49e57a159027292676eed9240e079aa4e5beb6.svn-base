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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:22:41
 */
public class Material implements ICardBodyAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 为了效率，先关闭合计
    boolean nc = event.getBillCardPanel().getBillModel().isNeedCalculate();
    event.getBillCardPanel().getBillModel().setNeedCalculate(false);   
    /*
     * change by wandl
     * 多选物料的时候不需要加载关联项，设置标识。避免加载关联项带来的性能问题。
     */
    ClientContext clientContext = (ClientContext) event.getContext();
    clientContext.setNeedLoadRelationItem(false);
    int[] rows = null;
    try {
      // 物料的多选处理
      RefMoreSelectedUtils utils =
          new RefMoreSelectedUtils(event.getBillCardPanel());
      rows =
          utils.refMoreSelected(event.getRow(), PraybillItemVO.PK_MATERIAL,
              true);
    }
    finally {
      // 恢复这个标志。
      clientContext.setNeedLoadRelationItem(true);
    }
    /*
     * end
     */
    // 清空优质优价
    this.setQpbaseschemeNull(rows, event.getBillCardPanel());
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    /*
     * add by wandl
     * 批量设置表体采购组织vid,解决效率问题！
     */
    this.processPurchaseOrgVid(card);
    // 物料信息默认值处理，如单位换算率等
    this.processDefaultMaterialInof(card, rows);
    this.setDefaultBodyFromCntClass(event, rows);
    // 管理合同处理
    ContractLinker contractLinker = new ContractLinker(event);
    Integer[] ctparams =
        this.getContractlinkParams(event.getBillCardPanel(), rows);
    // 远程调用注册 ====================
    this.registerRemoteCall(card, rows);
    // 组织业务委托关系找组织
    this.processDefaultOrganizationValue(rows);
    // 设置需求公司
    new ReqCorpDefaultValue(card).setDefaultValue(rows);
    // 设置物流组织
    this.processFlowStockOrg(rows);
    // 根据供应商物料关系设置物料信息
    this.processVendorMaterialInfo();

    this.processSupInfo();

    // 补充本位币和汇率的相关信息，并设置可编辑性
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(rows);
    // 补全国家信息，依赖物料编辑后的组织设置，所以这里可能会单独调用一次远程
    this.registerCountryRemoteCall(card, rows);
    /*
     * change by wandl
     * 缓存解决0税率设置效率问题
     */
    this.processCountryRemoteCall(card);
    // 自由项置空
    new Vfree(card).setVfreeNull(rows);
    // 批次号置空
    new Batchcode(card).setBatchcodeNull(rows);
    if (!ArrayUtils.isEmpty(ctparams)) {
      // 关联合同
      contractLinker.contractLink(ctparams, false, true);
    }
    // 编辑性处理
    this.setCardEditable(event, rows);

    // mengjian 根据参数PO16自动询价条件自动询价
    this.setDefaultPrice(event, rows);

    // 现打开合计
    event.getBillCardPanel().getBillModel().setNeedCalculate(nc);
    
    event.getBillCardPanel().getBillModel().execEditFormulaByKey(event.getRow(), event.getKey());
  }

  /**
   * 方法功能描述：关联合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param rows <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 下午01:37:32
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
   * 设置供应商国家
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
   * 物料信息默认值处理，如单位换算率等
   * 
   * @param card
   * @param rows
   */
  private void processDefaultMaterialInof(CardEditorHelper card, int[] rows) {
    // 扣税类别
    new TaxTypeSetter(card).setBodyTaxType(rows);
    // 税率
    new TaxRateSetter(card).setBodyTaxRate(rows);
    // 设置单位和报价单位
    new AssitunitAndQtunit(card).setAssistunitAndQtunit(rows);
    // 设置换算率
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
    countryList.add(new OrderSendCountrySetter(editor, rows));// 发货国
    countryList.add(new OrderReceiveCountrySetter(editor, rows));// 收货国
    countryList.add(new OrderTaxCountrySetter(editor, rows));// 报税国
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(editor, rows, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderVatValueFillRule.class.getName(), vatrule);
  }

  private void registerRemoteCall(CardEditorHelper card, int[] rows) {
    // 组织业务委托关系找组织
    OrganizationDefaultValue odv = new OrganizationDefaultValue(card);
    odv.registerCombineRemoteCall(rows);
    this.remoteCaller.put(OrganizationDefaultValue.class.getName(), odv);

    FlowStockOrgValue fsov = new FlowStockOrgValue(card);
    fsov.registerCombineRemoteCall(rows);
    this.remoteCaller.put(FlowStockOrgValue.class.getName(), fsov);

    // 根据供应商物料关系设置物料信息
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
    // 控制跟物料相关的编辑性
    new EditableSetter(event.getBillCardPanel()).setEditableByMaterial(rows);
    // 控制跟合同相关的编辑性
    new EditableSetter(event.getBillCardPanel()).setEditableByContract(rows);
    // 控制跟计量单位相关的编辑性
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
      // 平台修改策略，返回的顺序和63相反了
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
   * 自动询价
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
