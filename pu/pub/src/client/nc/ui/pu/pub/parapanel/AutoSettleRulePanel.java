/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 上午09:23:20
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>PO86参数（自动结算规则默认值）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 上午09:23:20
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
    // 初始化放到getPanel试试，可能会对结算节点产生影响
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
   * 父类方法重写
   * 
   * @see nc.ui.pub.para.ISysInitPanel#getPanel()ComplicatedParaContext context
   */
  @Override
  public UIPanel getPanel(ComplicatedParaContext context) {
    this.context = context;
    this.initPanel(); // 业务组自己的初始化panel逻辑
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
   * 父类方法重写
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
   *          要设置的 invoicestockPanel
   */
  public void setInvoicestockPanel(BillCardPanel invoicestockPanel) {
    this.invoicestockPanel = invoicestockPanel;
  }

  /**
   * @param rbinvoicePanel
   *          要设置的 rbinvoicePanel
   */
  public void setRbinvoicePanel(BillCardPanel rbinvoicePanel) {
    this.rbinvoicePanel = rbinvoicePanel;
  }

  /**
   * @param rbstockPanel
   *          要设置的 rbstockPanel
   */
  public void setRbstockPanel(BillCardPanel rbstockPanel) {
    this.rbstockPanel = rbstockPanel;
  }

  /**
   * @param uiTabbedPane
   *          要设置的 uiTabbedPane
   */
  public void setUiTabbedPane(UITabbedPane uiTabbedPane) {
    this.uiTabbedPane = uiTabbedPane;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午09:50:49
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
      // 红蓝入库单默认值
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
      // 红蓝发票默认值
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
      // 入库单发票默认值
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
      // 如果有数据，查询数据库，设值
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
   * 方法功能描述：接口
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午09:50:42
   */
  private IAutoSettleRule getService() {
    return NCLocator.getInstance().lookup(IAutoSettleRule.class);
  }

  /**
   * 方法功能描述：由规则VO得到三个VO的主键组合值
   * <p>
   * <b>参数说明</b>
   * 
   * @param ruleVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午09:50:45
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
   * 方法功能描述：初始化panel和值
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午07:25:23
   */
  // private void initialize() {
  // this.initPanel();
  //
  // this.initValue();
  // }

  /**
   * 方法功能描述：初始化panel，并通过参数初始化值
   * <p>
   * <b>参数说明</b>
   * 
   * @param resultVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-23 下午08:07:30
   */
  private void initializeByValues(SuperVO[] resultVOs) {

    this.initPanel();

    this.setPanelValue(resultVOs);
  }

  /**
   * 方法功能描述：自动结算之发票入库单Panel
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 下午03:33:01
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
        "4004000_0", "04004000-0041")/* @res "系统内置结算顺序：" */);
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
            "04004000-0042")/* @res "1 发票与来源入库单结算" */;
    final String memo2 =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0043")/* @res "2 发票与来源于同一订单下的入库单结算" */;
    final String memo3 =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0044")/* @res "3 满足自动结算条件的其他发票与入库单结算" */;
    UILabel label1 = new UILabel(memo1);
    UILabel label2 = new UILabel(memo2);
    UILabel label3 = new UILabel(memo3);
    memoPanel.add(label1);
    memoPanel.add(label2);
    memoPanel.add(label3);
    invoicestockTabPanel.add(memoPanel, BorderLayout.SOUTH);

    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0045")/* @res "发票与入库单结算" */,
        invoicestockTabPanel);
  }

  /**
   * 方法功能描述：初始化Panel
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午07:25:26
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
   * 方法功能描述：自动结算之红蓝发票Panel
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 下午03:32:55
   */
  private void initRbinvoicePanel() {
    // 自动结算之红蓝发票 pk_billtemplet='1002Z81000000000LEVX'
    // pk_billtypecode='as_rbinv'
    this.rbinvoicePanel = new BillCardPanel();
    this.rbinvoicePanel.loadTemplet("1002Z81000000000LEVX");
    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0046")/* @res "红蓝发票结算" */,
        this.rbinvoicePanel);
  }

  /**
   * 方法功能描述：自动结算之红蓝入库单Panel
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 下午03:32:46
   */
  private void initRbstockPanel() {
    // 自动结算之红蓝入库单 pk_billtemplet='1002Z81000000000LEVE'
    // pk_billtypecode='as_rbsto'
    this.rbstockPanel = new BillCardPanel();
    this.rbstockPanel.loadTemplet("1002Z81000000000LEVE");
    this.uiTabbedPane.addTab(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004000_0", "04004000-0047")/* @res "红蓝入库单结算" */,
        this.rbstockPanel);
  }

  /**
   * 方法功能描述：初始化数据
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午07:25:55
   */
  // private void initValue() {
  // this.initVO = PUSysParamUtil.getSysInitVOPO86(this.pkOrg);
  // SuperVO[] resultVOs = PUSysParamUtil.getPO86_v(this.pkOrg);
  // this.setPanelValue(resultVOs);
  // }

  /**
   * 方法功能描述：设置Panel值
   * <p>
   * <b>参数说明</b>
   * 
   * @param resultVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 上午09:50:57
   */
  private void setPanelValue(SuperVO[] resultVOs) {
    this.rbstockPanel.getBillData().setHeaderValueVO(resultVOs[0]);

    this.rbinvoicePanel.getBillData().setHeaderValueVO(resultVOs[1]);

    this.invoicestockPanel.getBillData().setHeaderValueVO(resultVOs[2]);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-18 下午08:34:03
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

    // 如果为空，新增数据，将pks设到initVO中
    // 不为空，值不变，所以不用设置新值
    // if (StringUtil.isEmptyWithTrim(value)) {
    String newValue = this.getValue(ruleVOs);
    this.initVO.setValue(newValue);
    this.newParaValue = newValue;
    // }
  }

}
