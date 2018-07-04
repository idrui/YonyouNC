/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-29 ����03:24:34
 */
package nc.ui.pu.m25.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.m25.editor.TotalValueHanlder;
import nc.ui.pu.m25.model.InvoiceBillManageModel;
import nc.ui.pu.m25.rule.PurchaseOrgEditableHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.view.BillOrgPanel;
import nc.ui.scmpub.ref.FilterDeptRefUtils;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.ui.scmpub.ref.FilterPaytermRefUtils;
import nc.ui.scmpub.ref.FilterProjectRefUtils;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.uif2.UIState;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.pub.InvoiceTypeSetter;
import nc.vo.pu.pub.util.PubSysParamUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.para.NCPara;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ��Ƭ�༭��
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-6-29 ����03:24:34
 */
@SuppressWarnings("serial")
public class InvoiceBillForm extends PUBillForm {

  private BillOrgPanel billOrgPanel;

  /**
   * �����ϼ���Ϣ������,��Ϊ�˼�ʱ��ʾ֮��; �����Ч������������Ͽ�,��ȫ������������ʱ�ټ���
   **/
  private TotalValueHanlder totalValueHandler;

  @Override
  public BillOrgPanel getBillOrgPanel() {
    if (null == this.billOrgPanel && this.isShowOrgPanel()) {
      this.billOrgPanel = this.createDefaultBillOrgPanel();
      this.billOrgPanel.setClearpkorgWhenAdd(true);
    }
    return this.billOrgPanel;
  }

  /**
   * @return totalValueHandler
   */
  public TotalValueHanlder getTotalValueHandler() {
    return this.totalValueHandler;
  }

  /**
   * @param totalValueHandler Ҫ���õ� totalValueHandler
   */
  public void setTotalValueHandler(TotalValueHanlder totalValueHandler) {
    this.totalValueHandler = totalValueHandler;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.editor.BillForm#setValue(java.lang.Object)
   */
  @Override
  public void setValue(Object object) {
    // �ȳ�ʼ������,�ٽ�VO���õ�����,��ִ����������
    String freecustid = "";
    if (object != null) {
      freecustid = ((InvoiceVO) object).getParentVO().getPk_freecust();
    }
    boolean isFreeCust = StringUtils.isEmpty(freecustid) ? false : true;
    SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
    custBankUtil.initCustBankRefPanel(this.getBillCardPanel(), isFreeCust);
    super.setValue(object);
    custBankUtil.initCustBankValue(this.getBillCardPanel(), isFreeCust);
    // �ϼ���Ϣ�ľ�������
    // if(null!=getTotalValueHandler()) getTotalValueHandler().reCalAll();
    // ����VO����ģ��Ŀɱ༭��
    this.handleItemsEnable((InvoiceVO) object);
  }

  @Override
  public void showMeUp() {
    super.showMeUp();
  }

  private BillOrgPanel createDefaultBillOrgPanel() {
    BillOrgPanel orgPanel = new InvoiceBillOrgPanel();
    orgPanel.setModel(this.getModel());
    orgPanel.initUI();
    return orgPanel;
  }

  private String getCurrentPk_org() {
    return this.getModel().getContext().getPk_org();
  }

  /**
   * ����������������֯������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-4-22 ����04:53:20
   */
  private void handleBillOrgPanel() {
    InvoiceBillManageModel model = (InvoiceBillManageModel) this.getModel();
    if (model.getInvoiceUIState() == InvoiceUIState.FEE_INVC) {
      // ��֯���ı༭��
      ((InvoiceBillOrgPanel) this.getBillOrgPanel()).setFeeShowing(true);
      // ����֯
      InvoiceVO normInvoice = model.getNormInvoice();
      // ��������֯����Ϊ������ʱʹ��Oid��֯���գ�������ʱʹ��Vid��֯����
      // ���÷�Ʊ��Ȼ������������֯����ǲ��ɱ༭�ģ�Ҫ���ָ����﷢Ʊһ��
      ((InvoiceBillOrgPanel) this.getBillOrgPanel())
          .setOrgFromNormalInvoice(normInvoice.getParentVO().getPk_org_v());

    }
    else if (model.getInvoiceUIState() == InvoiceUIState.NORM_INVC) {
      ((InvoiceBillOrgPanel) this.getBillOrgPanel()).setFeeShowing(false);
    }
  }

  /**
   * �������������������ֶ�Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-3-19 ����05:11:14
   */
  private void handleItemsDefaultValue() {
    InvoiceBillManageModel model = (InvoiceBillManageModel) this.getModel();
    if (model.getInvoiceUIState() == InvoiceUIState.FEE_INVC) {
      // �Ƿ���÷�Ʊ
      this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.BFEE)
          .setValue(UFBoolean.TRUE);
      InvoiceVO normInvoice = model.getNormInvoice();
      // ����֯
      this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_ORG)
          .setValue(normInvoice.getParentVO().getPk_org());

      // ���÷�Ʊ��Ӧ�Ļ��﷢Ʊ
      this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_PARENTINVOICE)
          .setValue(normInvoice.getParentVO().getPk_invoice());
      this.getBillCardPanel().execHeadLoadFormulas();
    }
    // add by liangchen1 NC631����������ͨ�ɹ�������ڲɹ�
    this.setInvoiceType();

    // �����ͷ��������֯���п�����ԣ�Ĭ��=��������֯��
    String pk_org =
        (String) this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_ORG)
            .getValueObject();
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }
    if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
      this.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_STOCKORG, pk_org);
      String pk_org_v = OrgUnitPubService.getNewVIDByOrgID(pk_org);
      this.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_STOCKORG_V,
          pk_org_v);
    }
  }

  /**
   * �����������������ݵ�ǰҪ���õ�VO������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-7 ����11:44:10
   */
  private void handleItemsEnable(InvoiceVO vo) {
    if (UIState.ADD == this.getModel().getUiState()
        || UIState.EDIT == this.getModel().getUiState()) {
      // �ɹ���֯�ɱ༭��
      new PurchaseOrgEditableHandler(this.getBillCardPanel()).setEditable();
    }
  }

  /**
   * ��ʼ����ʱ��ԭ�ұ��ұ�����һ�µģ�����ֻ�����Ƿ�����Ĭ��ֵ1
   */
  private void initGroupAndGlobalExchangeRate() {
    String pk_group = this.getModel().getContext().getPk_group();
    // ���ű�λ��
    String nc001 = PubSysParamUtil.getNC001(pk_group);
    // ȫ�ֱ�λ��
    String nc002 = PubSysParamUtil.getNC002();

    if (NCPara.NC002_NOUSEGLOBALCURRTYPE.getName().equals(nc002)) {
      this.getBillCardPanel().setHeadItem(InvoiceHeaderVO.NGLOBALEXCHGRATE,
          null);
    }
    else {
      this.getBillCardPanel().setHeadItem(InvoiceHeaderVO.NGLOBALEXCHGRATE,
          UFDouble.ONE_DBL);
    }

    if (NCPara.NC001_NOUSEGROUPCURRTYPE.getName().equals(nc001)) {
      this.getBillCardPanel()
          .setHeadItem(InvoiceHeaderVO.NGROUPEXCHGRATE, null);
    }
    else {
      this.getBillCardPanel().setHeadItem(InvoiceHeaderVO.NGLOBALEXCHGRATE,
          UFDouble.ONE_DBL);
    }
  }

  private void setPk_org(String pk_org) {
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }
    // ����������Ϣ
    this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_ORG)
        .setValue(pk_org);
    // ���������ŵ���Ϣ
    this.getBillCardPanel().getBillData()
        .loadEditHeadRelation(InvoiceHeaderVO.PK_ORG);
    // ���ñ���
    String pk_currtype = OrgUnitPubService.queryOrgCurrByPk(pk_org);
    this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.CORIGCURRENCYID)
        .setValue(pk_currtype);
    this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.CCURRENCYID)
        .setValue(pk_currtype);
    // ��ʼ�����ź�ȫ�ֱ�λ�һ���
    this.initGroupAndGlobalExchangeRate();
    // ���òɹ���֯
    if (OrgUnitPubService.isTypeOf(pk_org, IOrgConst.PURCHASEORGTYPE)) {
      this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG)
          .setValue(pk_org);
    }

    // ��������
    this.initRef();

  }

  @Override
  protected void enableFillableItems() {
    super.enableFillableItems();
    // ��˰���ۡ���˰���ۡ�˰��
    // ��Ŀ��
    // ��ע���Զ�����
    String[] enableItems =
        new String[] {
          InvoiceItemVO.NTAXRATE, InvoiceItemVO.NASTORIGPRICE,
          InvoiceItemVO.NASTORIGTAXPRICE, InvoiceItemVO.CPROJECTID,
          InvoiceItemVO.VMEMOB, InvoiceItemVO.VBDEF1, InvoiceItemVO.VBDEF2,
          InvoiceItemVO.VBDEF3, InvoiceItemVO.VBDEF4, InvoiceItemVO.VBDEF5,
          InvoiceItemVO.VBDEF6, InvoiceItemVO.VBDEF7, InvoiceItemVO.VBDEF8,
          InvoiceItemVO.VBDEF9, InvoiceItemVO.VBDEF10, InvoiceItemVO.VBDEF11,
          InvoiceItemVO.VBDEF12, InvoiceItemVO.VBDEF13, InvoiceItemVO.VBDEF14,
          InvoiceItemVO.VBDEF15, InvoiceItemVO.VBDEF16, InvoiceItemVO.VBDEF17,
          InvoiceItemVO.VBDEF18, InvoiceItemVO.VBDEF19, InvoiceItemVO.VBDEF20
        };
    BillCardPanelUtils cardUtils =
        new BillCardPanelUtils(this.getBillCardPanel());
    // �ſ��ֶε�����
    cardUtils.enableItemsFill(enableItems);
  }

  protected void initRef() {
    // �������κŲ���
    BatchRefPane pane = new BatchRefPane();
    this.getBillCardPanel().getBodyItem(InvoiceItemVO.VBATCHCODE)
        .setComponent(pane);
    // ��Ӧ��
    BillItem item =
        this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_SUPPLIER);
    new FilterSupplierRefUtils(item)
        .filterItemRefByOrg(this.getCurrentPk_org());
    // ��ʼ�������˻�����
    new SupplierFreeCustInfoUtil().initCustBankRefPanel(
        this.getBillCardPanel(), UFBoolean.FALSE.booleanValue());
    // ���λ
    item =
        this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_PAYTOSUPPLIER);
    new FilterSupplierRefUtils(item)
        .filterItemRefByOrg(this.getCurrentPk_org());
    // ����Э��
    item = this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_PAYTERM);
    new FilterPaytermRefUtils(item).filterItemRefByOrg(this.getCurrentPk_org());

    new FilterSupplierRefUtils(item)
        .filterItemRefByOrg(this.getCurrentPk_org());
    // ����
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_MATERIAL);
    new FilterMaterialRefUtils((UIRefPane) item.getComponent())
        .filterItemRefByOrg(this.getCurrentPk_org());
    // ���ϣ�OID��
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_SRCMATERIAL);
    new FilterMaterialRefUtils((UIRefPane) item.getComponent())
        .filterItemRefByOrg(this.getCurrentPk_org());
    UIRefPane panel =
        (UIRefPane) this.getBillCardPanel()
            .getBodyItem(InvoiceItemVO.PK_MATERIAL).getComponent();
    panel.setMultiSelectedEnabled(true);

    // �ֿ�(����)
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_STORDOC);
    new FilterWareHouseRefUtils((UIRefPane) item.getComponent())
        .filterItemRefByOrg(this.getCurrentPk_org());
    // ʹ�ò���(����)
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_USEDEPT);
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(
        (UIRefPane) item.getComponent()).filterItemRefByOrg(
        this.getCurrentPk_org());
    // ʹ�ò���(����vid)
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_USEDEPT_V);
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(
        (UIRefPane) item.getComponent()).filterItemRefByOrg(
        this.getCurrentPk_org());
    // ��Ŀ(����)
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.CPROJECTID);
    if (null != item) {
      new FilterProjectRefUtils(item).filterItemRefByOrg(this
          .getCurrentPk_org());
    }
    // ��Ŀ(��֧��Ŀ)
    item = this.getBillCardPanel().getBodyItem(InvoiceItemVO.PK_COSTSUBJ);
    new FilterProjectRefUtils(item)
        .filterCostSubjByOrg(this.getCurrentPk_org());
    // ���òɹ���֯��صĲ���
    this.initRefByPurOrg();
  }

  protected void initRefByPurOrg() {
    // �ɹ�����
    BillItem item =
        this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_DEPT);
    String pk_purorg =
        (String) this.getBillCardPanel()
            .getHeadItem(InvoiceHeaderVO.PK_PURCHASEORG).getValueObject();
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(
        (UIRefPane) item.getComponent()).filterItemRefByOrg(pk_purorg);
    // �ɹ����ţ�vid��
    item = this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_DEPT_V);
    FilterDeptRefUtils.createFilterDeptRefUtilsOfPU(
        (UIRefPane) item.getComponent()).filterItemRefByOrg(pk_purorg);

    // �ɹ�Ա(��Ա)
    item = this.getBillCardPanel().getHeadItem(InvoiceHeaderVO.PK_BIZPSN);
    FilterPsndocRefUtils.createFilterPsndocRefUtilsOfPU(
        (UIRefPane) item.getComponent()).filterItemRefByOrg(pk_purorg);
  }

  @Override
  protected void onAdd() {
    this.handleBillOrgPanel();
    super.onAdd();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.editor.BillForm#onEdit()
   */
  @Override
  protected void onEdit() {
    super.onEdit();
    // ����ģ��ɱ༭����
    this.handleItemsEnable((InvoiceVO) this.getModel().getSelectedData());
  }

  /**
   * ������������������Ҫ���������ǰ�ټ���һ�������ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param value <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-31 ����10:51:14
   */
  protected void reCalcTotalValue(Object value) {
    if (UIState.ADD != this.getModel().getUiState()
        && UIState.EDIT != this.getModel().getUiState()) {
      return;
    }
    if (null == this.getTotalValueHandler()) {
      return;
    }
    Map<String, String> bodyHeadMap =
        this.getTotalValueHandler().getBodyHeadMap();
    AggregatedValueObject bill = (AggregatedValueObject) value;
    if (null == bill || null == bill.getParentVO()
        || null == bill.getChildrenVO()) {
      return;
    }
    int rowCnt = bill.getChildrenVO().length;
    Map<String, UFDouble> allTotal = new HashMap<String, UFDouble>();
    for (Entry<String, String> entry : bodyHeadMap.entrySet()) {
      allTotal.put(entry.getValue(), UFDouble.ZERO_DBL);
    }
    for (int i = 0; i < rowCnt; ++i) {
      for (Entry<String, String> entry : bodyHeadMap.entrySet()) {
        String bodyName = entry.getKey();
        String headName = entry.getValue();
        UFDouble total = allTotal.get(headName);
        CircularlyAccessibleValueObject item = bill.getChildrenVO()[i];
        total =
            MathTool.add(total, (UFDouble) item.getAttributeValue(bodyName));
        allTotal.put(headName, total);
      }
    }
    for (Entry<String, UFDouble> entry : allTotal.entrySet()) {
      String headName = entry.getKey();
      UFDouble total = entry.getValue();
      bill.getParentVO().setAttributeValue(headName, total);
    }
  }

  @Override
  protected void setDefaultValue() {
    this.setPk_org(this.getModel().getContext().getPk_org());
    this.handleItemsDefaultValue();
  }

  /**
   * ������ͨ�ɹ�������ڲɹ�
   * liangchen1
   */
  protected void setInvoiceType() {
    new InvoiceTypeSetter(InvoicePuImportEnum.PUINVOICE)
        .setInvocieType(new CardEditorHelper(this.getBillCardPanel()));
  }
}
