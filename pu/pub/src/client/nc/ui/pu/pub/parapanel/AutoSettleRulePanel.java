/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 ����09:23:20
 */
package nc.ui.pu.pub.parapanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.para.ComplicatedParaContext;
import nc.itf.pu.settle.IAutoSettleRule;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITabbedPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pub.bill.panel.BillAreaPanel;
import nc.ui.pub.para.ISysInitPanel;
import nc.vo.ml.Language;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pub.bill.BillTabVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>PO86�������Զ��������Ĭ��ֵ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 ����09:23:20
 */
public class AutoSettleRulePanel extends UIPanel implements ISysInitPanel {

  private static final long serialVersionUID = 6532651903379049836L;

  private SysInitVO initVO;

  private ComplicatedParaContext context;

  private BillCardPanel invoicestockPanel;

  private String newParaValue = null;

  // private String pkOrg;

  private BillCardPanel rbinvoicePanel;

  private BillCardPanel rbstockPanel;

  private UITabbedPane uiTabbedPane;

  public AutoSettleRulePanel(String pkorg) {
    super();
    // this.pkOrg = pkOrg;
    // ��ʼ���ŵ�getPanel���ԣ����ܻ�Խ���ڵ����Ӱ��
  }

  public AutoSettleRulePanel(SuperVO[] resultVOs) {
    super();
    this.initializeByValues(resultVOs);
  }

  /**
   * @return invoicestockPanel
   */
  public BillCardPanel getInvoicestockPanel() {
    return this.invoicestockPanel;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pub.para.ISysInitPanel#getPanel()ComplicatedParaContext context
   */
  @Override
  public UIPanel getPanel(ComplicatedParaContext context) {
    this.context = context;
    this.initPanel(); // ҵ�����Լ��ĳ�ʼ��panel�߼�
    SysInitVO[] initVOs = context.getInitData();
    for (SysInitVO sysInitVO : initVOs) {
      String initcode = sysInitVO.getInitcode();
      if (POParas.PO86_V.name().equals(initcode)) {
        this.initVO = sysInitVO;
        SuperVO[] vos = this.getPOValue(sysInitVO.getValue());
        this.setPanelValue(vos);
        break;
      }
    }
    return this;
  }

  /**
   * @return rbinvoicePanel
   */
  public BillCardPanel getRbinvoicePanel() {
    return this.rbinvoicePanel;
  }

  /**
   * @return rbstockPanel
   */
  public BillCardPanel getRbstockPanel() {
    return this.rbstockPanel;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pub.para.ISysInitPanel#getSysInitVOs()
   */
  @Override
  public SysInitVO[] getSysInitVOs() {
    this.setSysInitVOValue();

    // return new SysInitVO[] {
    // this.initVO
    // };
    return context.getInitData();
  }

  /**
   * @return uiTabbedPane
   */
  public UITabbedPane getUiTabbedPane() {
    return this.uiTabbedPane;
  }

  /**
   * @param invoicestockPanel
   *          Ҫ���õ� invoicestockPanel
   */
  public void setInvoicestockPanel(BillCardPanel invoicestockPanel) {
    this.invoicestockPanel = invoicestockPanel;
  }

  /**
   * @param rbinvoicePanel
   *          Ҫ���õ� rbinvoicePanel
   */
  public void setRbinvoicePanel(BillCardPanel rbinvoicePanel) {
    this.rbinvoicePanel = rbinvoicePanel;
  }

  /**
   * @param rbstockPanel
   *          Ҫ���õ� rbstockPanel
   */
  public void setRbstockPanel(BillCardPanel rbstockPanel) {
    this.rbstockPanel = rbstockPanel;
  }

  /**
   * @param uiTabbedPane
   *          Ҫ���õ� uiTabbedPane
   */
  public void setUiTabbedPane(UITabbedPane uiTabbedPane) {
    this.uiTabbedPane = uiTabbedPane;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����09:50:49
   */
  private SuperVO[] getData() {
    RBStockOptionableVO rbstockVO =
        (RBStockOptionableVO) this.rbstockPanel.getBillData().getHeaderValueVO(
            RBStockOptionableVO.class.getName());

    RBInvoiceOptionableVO rbinvoiceVO =
        (RBInvoiceOptionableVO) this.rbinvoicePanel.getBillData()
            .getHeaderValueVO(RBInvoiceOptionableVO.class.getName());

    InvoiceStockOptionableVO invoicestockVO =
        (InvoiceStockOptionableVO) this.invoicestockPanel.getBillData()
            .getHeaderValueVO(InvoiceStockOptionableVO.class.getName());

    return new SuperVO[] {
      rbstockVO, rbinvoiceVO, invoicestockVO
    };
  }

  private SuperVO[] getPOValue(String value) {
    SuperVO[] vos = null;
    if (value == null) {
      vos = new SuperVO[3];
      // ������ⵥĬ��ֵ
      RBStockOptionableVO rbstockVO = new RBStockOptionableVO();
      rbstockVO.setBbatchcodesame(UFBoolean.FALSE);
      rbstockVO.setBbuyersame(UFBoolean.FALSE);
      rbstockVO.setBdeptsame(UFBoolean.TRUE);
      rbstockVO.setBfinanceorgsame(UFBoolean.TRUE);
      rbstockVO.setBfreeitemsame(UFBoolean.FALSE);
      rbstockVO.setBmaterialsame(UFBoolean.TRUE);
      rbstockVO.setBnumabssame(UFBoolean.FALSE);
      rbstockVO.setBordersame(UFBoolean.FALSE);
      rbstockVO.setBorigpricesame(UFBoolean.FALSE);
      rbstockVO.setBproductorsame(UFBoolean.FALSE);
      rbstockVO.setBprojectsame(UFBoolean.FALSE);
      rbstockVO.setBsuppliersame(UFBoolean.TRUE);
      rbstockVO.setBtrantypesame(UFBoolean.FALSE);
      vos[0] = rbstockVO;
      // ������ƱĬ��ֵ
      RBInvoiceOptionableVO rbinvoiceVO = new RBInvoiceOptionableVO();
      rbinvoiceVO.setBbatchcodesame(UFBoolean.FALSE);
      rbinvoiceVO.setBbuyersame(UFBoolean.FALSE);
      rbinvoiceVO.setBdeptsame(UFBoolean.TRUE);
      rbinvoiceVO.setBfinanceorgsame(UFBoolean.TRUE);
      rbinvoiceVO.setBfreeitemsame(UFBoolean.FALSE);
      rbinvoiceVO.setBinvoicetypesame(UFBoolean.TRUE);
      rbinvoiceVO.setBmaterialsame(UFBoolean.TRUE);
      rbinvoiceVO.setBnorigpricesame(UFBoolean.TRUE);
      rbinvoiceVO.setBnumabssame(UFBoolean.FALSE);
      rbinvoiceVO.setBordersame(UFBoolean.FALSE);
      rbinvoiceVO.setBproductorsame(UFBoolean.FALSE);
      rbinvoiceVO.setBprojectsame(UFBoolean.FALSE);
      rbinvoiceVO.setBsuppliersame(UFBoolean.TRUE);
      vos[1] = rbinvoiceVO;
      // ��ⵥ��ƱĬ��ֵ
      InvoiceStockOptionableVO invoicestockVO = new InvoiceStockOptionableVO();
      invoicestockVO.setBbatchcodesame(UFBoolean.FALSE);
      invoicestockVO.setBbuyersame(UFBoolean.FALSE);
      invoicestockVO.setBdeptsame(UFBoolean.TRUE);
      invoicestockVO.setBfinanceorgsame(UFBoolean.TRUE);
      invoicestockVO.setBfreeitemsame(UFBoolean.FALSE);
      invoicestockVO.setBmaterialsame(UFBoolean.TRUE);
      invoicestockVO.setBnumsame(UFBoolean.FALSE);
      invoicestockVO.setBorigpricesame(UFBoolean.FALSE);
      invoicestockVO.setBproductorsame(UFBoolean.FALSE);
      invoicestockVO.setBprojectsame(UFBoolean.FALSE);
      invoicestockVO.setBsuppliersame(UFBoolean.TRUE);
      vos[2] = invoicestockVO;
    }
    else {
      // ��������ݣ���ѯ���ݿ⣬��ֵ
      String[] pks = value.split(",");
      IAutoSettleRule itf =
          NCLocator.getInstance().lookup(IAutoSettleRule.class);
      try {
        vos = itf.queryAutoSettleRule(pks);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return vos;
  }

  /**
   * ���������������ӿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����09:50:42
   */
  private IAutoSettleRule getService() {
    return NCLocator.getInstance().lookup(IAutoSettleRule.class);
  }

  /**
   * ���������������ɹ���VO�õ�����VO���������ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param ruleVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����09:50:45
   */
  private String getValue(SuperVO[] ruleVOs) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < ruleVOs.length; ++i) {
      if (i > 0) {
        sb.append(",");
      }

      sb.append(ruleVOs[i].getPrimaryKey());
    }

    return sb.toString();
  }

  /**
   * ����������������ʼ��panel��ֵ
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 ����07:25:23
   */
  // private void initialize() {
  // this.initPanel();
  //
  // this.initValue();
  // }

  /**
   * ����������������ʼ��panel����ͨ��������ʼ��ֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param resultVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-23 ����08:07:30
   */
  private void initializeByValues(SuperVO[] resultVOs) {

    this.initPanel();

    this.setPanelValue(resultVOs);
  }

  /**
   * ���������������Զ�����֮��Ʊ��ⵥPanel
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����03:33:01
   */
  private void initInvoicestockPanel() {

    UIPanel invoicestockTabPanel = new UIPanel();
    invoicestockTabPanel.setLayout(new BorderLayout());

    this.invoicestockPanel = new BillCardPanel();
    this.invoicestockPanel.loadTemplet("1002Z81000000000LEWD");
    String langcode = InvocationInfoProxy.getInstance().getLangCode();
    if (Language.ENGLISH_CODE.equals(langcode)) {
      this.invoicestockPanel.setPreferredSize(new Dimension(480, 170));
    }
    else {
      this.invoicestockPanel.setPreferredSize(new Dimension(480, 210));
    }
    invoicestockTabPanel.add(this.invoicestockPanel, BorderLayout.NORTH);

    BillTabVO tabVO = new BillTabVO();
    tabVO.setTabname(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004000_0", "04004000-0041")/* @res "ϵͳ���ý���˳��" */);
    BillItem[] items = new BillItem[1];
    items[0] = new BillItem();
    items[0].setDataType(IBillItem.BLANK);
    BillAreaPanel areaPanel =
        new BillAreaPanel(this.invoicestockPanel, tabVO, items);
    areaPanel.setShowGroupTitle(true);
    invoicestockTabPanel.add(areaPanel, BorderLayout.CENTER);

    UIPanel memoPanel = new UIPanel();
    memoPanel.setLayout(new GridLayout(3, 1));
    final String memo1 =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0042")/* @res "1 ��Ʊ����Դ��ⵥ����" */;
    final String memo2 =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0043")/* @res "2 ��Ʊ����Դ��ͬһ�����µ���ⵥ����" */;
    final String memo3 =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0044")/* @res "3 �����Զ�����������������Ʊ����ⵥ����" */;
    UILabel label1 = new UILabel(memo1);
    UILabel label2 = new UILabel(memo2);
    UILabel label3 = new UILabel(memo3);
    memoPanel.add(label1);
    memoPanel.add(label2);
    memoPanel.add(label3);
    invoicestockTabPanel.add(memoPanel, BorderLayout.SOUTH);

    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0045")/* @res "��Ʊ����ⵥ����" */,
        invoicestockTabPanel);
  }

  /**
   * ����������������ʼ��Panel
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 ����07:25:26
   */
  private void initPanel() {
    this.newParaValue = null;
    this.setLayout(new BorderLayout());

    this.uiTabbedPane = new UITabbedPane();
    this.uiTabbedPane.setPreferredSize(new Dimension(480, 300));

    this.initRbstockPanel();
    this.initRbinvoicePanel();
    this.initInvoicestockPanel();

    this.add(this.uiTabbedPane, BorderLayout.CENTER);
  }

  /**
   * ���������������Զ�����֮������ƱPanel
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����03:32:55
   */
  private void initRbinvoicePanel() {
    // �Զ�����֮������Ʊ pk_billtemplet='1002Z81000000000LEVX'
    // pk_billtypecode='as_rbinv'
    this.rbinvoicePanel = new BillCardPanel();
    this.rbinvoicePanel.loadTemplet("1002Z81000000000LEVX");
    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0046")/* @res "������Ʊ����" */,
        this.rbinvoicePanel);
  }

  /**
   * ���������������Զ�����֮������ⵥPanel
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����03:32:46
   */
  private void initRbstockPanel() {
    // �Զ�����֮������ⵥ pk_billtemplet='1002Z81000000000LEVE'
    // pk_billtypecode='as_rbsto'
    this.rbstockPanel = new BillCardPanel();
    this.rbstockPanel.loadTemplet("1002Z81000000000LEVE");
    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0047")/* @res "������ⵥ����" */,
        this.rbstockPanel);
  }

  /**
   * ����������������ʼ������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 ����07:25:55
   */
  // private void initValue() {
  // this.initVO = PUSysParamUtil.getSysInitVOPO86(this.pkOrg);
  // SuperVO[] resultVOs = PUSysParamUtil.getPO86_v(this.pkOrg);
  // this.setPanelValue(resultVOs);
  // }

  /**
   * ������������������Panelֵ
   * <p>
   * <b>����˵��</b>
   * 
   * @param resultVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����09:50:57
   */
  private void setPanelValue(SuperVO[] resultVOs) {
    this.rbstockPanel.getBillData().setHeaderValueVO(resultVOs[0]);

    this.rbinvoicePanel.getBillData().setHeaderValueVO(resultVOs[1]);

    this.invoicestockPanel.getBillData().setHeaderValueVO(resultVOs[2]);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 ����08:34:03
   */
  private void setSysInitVOValue() {
    // String value = this.initVO.getValue();
    if (this.newParaValue != null) {
      return;
    }
    IAutoSettleRule itf = this.getService();
    SuperVO[] ruleVOs = this.getData();
    try {
      ruleVOs = itf.saveAutoSettleRule(ruleVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // ���Ϊ�գ��������ݣ���pks�赽initVO��
    // ��Ϊ�գ�ֵ���䣬���Բ���������ֵ
    // if (StringUtil.isEmptyWithTrim(value)) {
    String newValue = this.getValue(ruleVOs);
    this.initVO.setValue(newValue);
    this.newParaValue = newValue;
    // }
  }

}
