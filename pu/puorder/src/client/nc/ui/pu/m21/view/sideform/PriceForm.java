/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 上午11:35:51
 */
package nc.ui.pu.m21.view.sideform;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.uif2.components.widget.WidgetInfo;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.trade.checkrule.VOChecker;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格form
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-21 上午11:35:51
 */
public class PriceForm extends OrderSideForm {

  // 合同价
  private static final String CONTRACTPRICE = "contractprice";

  // 订单最低价
  private static final String ORDERLOWESTPRICE = "orderlowestprice";

  // 订单最新价
  private static final String ORDERNEWPRICE = "ordernewprice";

  private static final long serialVersionUID = 8604270506080939388L;

  // 供应商价目表
  private static final String SUPPLERPRICE = "supplerprice";

  private UIPanel contractPanel;

  // 合同价label
  private UILabel contractPrcLabel;

  // private int state;

  private LayoutManager defaultLayoutMgr;

  // 订单最低价label
  private UILabel lowestPriceLabel;

  private UIPanel lowestPricePanel;

  // 订单最新价label
  private UILabel newPriceLabel;

  private UIPanel newPricePanel;

  private UIPanel panel;

  // 供应商价目表label
  private UILabel supplerPrcLabel;

  private UIPanel supplerPrcPanel;
  
  private static String[] vaules = new String[]{
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0084")/* @res "查看当前价格" */,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0085")/* @res "查看当前选中的行的价格" */
      };

  public PriceForm() {
    super(vaules);
    this.defaultLayoutMgr = this.getLayout();
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0078")/* @res "价格" */);
    return info;
  }

  private Component getContractPanel() {
    if (this.contractPanel == null) {
      this.contractPanel = new UIPanel(new GridBagLayout());
      this.contractPanel.setName("contractPanel");
    }
    return this.contractPanel;
  }

  /**
   * 方法功能描述：取得合同价Label
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-21 下午01:22:03
   */
  private Component getContractPriceLabel() {
    if (this.contractPrcLabel == null) {
      this.contractPrcLabel = new UILabel();
      this.contractPrcLabel.setName(PriceForm.CONTRACTPRICE);
    }
    return this.contractPrcLabel;
  }

  private Component getLowestPricePanel() {
    if (this.lowestPricePanel == null) {
      this.lowestPricePanel = new UIPanel(new GridBagLayout());
      this.lowestPricePanel.setName("lowestPricePanel");
    }
    return this.lowestPricePanel;
  }

  /**
   * 方法功能描述：取得订单最新价Lalbel
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午10:44:16
   */
  private Component getNewPriceLabelLabel() {
    if (this.newPriceLabel == null) {
      this.newPriceLabel = new UILabel();
      this.newPriceLabel.setName(PriceForm.ORDERNEWPRICE);
    }
    return this.newPriceLabel;
  }

  private Component getNewPricePanel() {
    if (this.newPricePanel == null) {
      this.newPricePanel = new UIPanel(new GridBagLayout());
      this.newPricePanel.setName("newPricePanel");
    }
    return this.newPricePanel;
  }

  /**
   * 方法功能描述：取得订单最低价Label
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午10:44:35
   */
  private Component getOrderLowestPriceLabel() {
    if (this.lowestPriceLabel == null) {
      this.lowestPriceLabel = new UILabel();
      this.lowestPriceLabel.setName(PriceForm.ORDERLOWESTPRICE);
    }
    return this.lowestPriceLabel;
  }

  /**
   * 方法功能描述：取得供应商价目表Label
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 上午10:44:54
   */
  private Component getSupplerPrcLabelLabel() {
    if (this.supplerPrcLabel == null) {
      this.supplerPrcLabel = new UILabel();
      this.supplerPrcLabel.setName(PriceForm.SUPPLERPRICE);
    }
    return this.supplerPrcLabel;
  }

  private Component getSupplerPrcPanel() {
    if (this.supplerPrcPanel == null) {
      this.supplerPrcPanel = new UIPanel(new GridBagLayout());
      this.supplerPrcPanel.setName("supplerPrcPanel");
    }
    return this.supplerPrcPanel;
  }

  /**
   * 初始化图形的边框及布局
   * <p>
   * 
   * @return <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  private void initBorder() {
    this.panel = new UIPanel();
    this.panel.setLayout(new GridLayout(4, 1));
    // 设置label中内容的布局
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 100;
    constraints.weighty = 100;
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.EAST;
    // 初始化边框对象并为每个panel设置边框
    Border etched = BorderFactory.createEtchedBorder();
    Border contractTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0079")/*
                                                                     * @res
                                                                     * "合同价"
                                                                     */);
    ((UIPanel) this.getContractPanel()).add(this.getContractPriceLabel(),
        constraints);
    ((UIPanel) this.getContractPanel()).setBorder(contractTitled);

    Border supplerPrcTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0080")/*
                                                                     * @res
                                                                     * "供应商价目表"
                                                                     */);
    ((UIPanel) this.getSupplerPrcPanel()).add(this.getSupplerPrcLabelLabel(),
        constraints);
    ((UIPanel) this.getSupplerPrcPanel()).setBorder(supplerPrcTitled);

    Border newPriceTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0081")/*
                                                                     * @res
                                                                     * "订单最新价"
                                                                     */);
    ((UIPanel) this.getNewPricePanel()).add(this.getNewPriceLabelLabel(),
        constraints);
    ((UIPanel) this.getNewPricePanel()).setBorder(newPriceTitled);

    Border lowestPriceTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0082")/*
                                                                     * @res
                                                                     * "订单最低价"
                                                                     */);
    ((UIPanel) this.getLowestPricePanel()).add(this.getOrderLowestPriceLabel(),
        constraints);
    ((UIPanel) this.getLowestPricePanel()).setBorder(lowestPriceTitled);

    this.panel.add(this.getContractPanel());
    this.panel.add(this.getSupplerPrcPanel());
    this.panel.add(this.getNewPricePanel());
    this.panel.add(this.getLowestPricePanel());
    this.add(this.panel);
  }

  @Override
  protected void initUI() {
    this.setPreferredSize(new Dimension(80, 200));
    super.initUI();
  }

  @Override
  protected void notifyUpdateData() {
    this.setLayout(new BorderLayout());
    this.initBorder();
    OrderVO orderVO = null;
    int bodyIndex = this.sfMediator.getCurrentRow();

    Object obj =
        this.sfMediator.getBillCardPanel().getBillData()
            .getBillObjectByMetaData();
    if (!VOChecker.isEmpty(obj)) {
      orderVO = (OrderVO) obj;

      if (!OrderSideFormUtil
          .isNeedFindPrice(bodyIndex, orderVO.getBVO().length)) {
        return;
      }

      // 获得价格优先策略
      String purchaseOrgId = orderVO.getHVO().getPk_org();

      PricePriority pp = PUSysParamUtil.getPO28(purchaseOrgId);
      if (pp == null) {
        pp = PricePriority.TAXPRICE_PRIOR_TO_PRICE;
      }

      // 合同价
      OrderSideFormRelCT relCt = new OrderSideFormRelCT();
      String contractPrc = relCt.relatingCT(orderVO, bodyIndex, pp);

      // 询价
      OrderSideFormDefPrice defPrice =
          new OrderSideFormDefPrice(orderVO, bodyIndex, pp);
      // 合并远程调用
      defPrice.prepare();
      defPrice.process();
      Map<PriceSource, UFDouble> priceMap = defPrice.getPriceMap();
      ScaleUtils scaleUtils =
          new ScaleUtils(AppContext.getInstance().getPkGroup());
      String origcurrencyid = orderVO.getBVO()[bodyIndex].getCorigcurrencyid();
      if (origcurrencyid == null) {
        origcurrencyid = orderVO.getHVO().getCorigcurrencyid();
      }
      // 订单最低价
      UFDouble lowestPrice =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.OrderMinPrice), origcurrencyid);
      // 订单最新价
      UFDouble newPrice =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.OrderNewestPrice), origcurrencyid);
      // 供应商价目表
      UFDouble supplerPrc =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.SupplierPrice), origcurrencyid);

      // 设置显示值
      Component[] parent = this.getComponents();
      UIPanel uipanel = (UIPanel) parent[0];
      Component[] cps = uipanel.getComponents();

      for (Component cp : cps) {
        if ("contractPanel".equals(cp.getName())) {
          UILabel label = (UILabel) this.getContractPriceLabel();
          label.setText(contractPrc);
        }
        else if ("lowestPricePanel".equals(cp.getName())) {
          UILabel label = (UILabel) this.getOrderLowestPriceLabel();
          label.setText(lowestPrice == null ? "" : lowestPrice.toString());
        }
        else if ("newPricePanel".equals(cp.getName())) {
          UILabel label = (UILabel) this.getNewPriceLabelLabel();
          label.setText(newPrice == null ? "" : newPrice.toString());
        }
        else if ("supplerPrcPanel".equals(cp.getName())) {
          UILabel label = (UILabel) this.getSupplerPrcLabelLabel();
          label.setText(supplerPrc == null ? "" : supplerPrc.toString());
        }
      }
    }
    this.updateUI();
  }

  @Override
  public void resetUI() {
    this.removeAll();
    this.setLayout(this.defaultLayoutMgr);
    this.initUI();
  }

}
