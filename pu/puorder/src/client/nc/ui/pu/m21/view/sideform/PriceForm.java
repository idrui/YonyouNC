/**
 * $�ļ�˵��$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 ����11:35:51
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸�form
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-21 ����11:35:51
 */
public class PriceForm extends OrderSideForm {

  // ��ͬ��
  private static final String CONTRACTPRICE = "contractprice";

  // ������ͼ�
  private static final String ORDERLOWESTPRICE = "orderlowestprice";

  // �������¼�
  private static final String ORDERNEWPRICE = "ordernewprice";

  private static final long serialVersionUID = 8604270506080939388L;

  // ��Ӧ�̼�Ŀ��
  private static final String SUPPLERPRICE = "supplerprice";

  private UIPanel contractPanel;

  // ��ͬ��label
  private UILabel contractPrcLabel;

  // private int state;

  private LayoutManager defaultLayoutMgr;

  // ������ͼ�label
  private UILabel lowestPriceLabel;

  private UIPanel lowestPricePanel;

  // �������¼�label
  private UILabel newPriceLabel;

  private UIPanel newPricePanel;

  private UIPanel panel;

  // ��Ӧ�̼�Ŀ��label
  private UILabel supplerPrcLabel;

  private UIPanel supplerPrcPanel;
  
  private static String[] vaules = new String[]{
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0084")/* @res "�鿴��ǰ�۸�" */,
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0085")/* @res "�鿴��ǰѡ�е��еļ۸�" */
      };

  public PriceForm() {
    super(vaules);
    this.defaultLayoutMgr = this.getLayout();
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0078")/* @res "�۸�" */);
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
   * ��������������ȡ�ú�ͬ��Label
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-21 ����01:22:03
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
   * ��������������ȡ�ö������¼�Lalbel
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 ����10:44:16
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
   * ��������������ȡ�ö�����ͼ�Label
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 ����10:44:35
   */
  private Component getOrderLowestPriceLabel() {
    if (this.lowestPriceLabel == null) {
      this.lowestPriceLabel = new UILabel();
      this.lowestPriceLabel.setName(PriceForm.ORDERLOWESTPRICE);
    }
    return this.lowestPriceLabel;
  }

  /**
   * ��������������ȡ�ù�Ӧ�̼�Ŀ��Label
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 ����10:44:54
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
   * ��ʼ��ͼ�εı߿򼰲���
   * <p>
   * 
   * @return <p>
   * @author xihy1
   * @time 2011-4-20 ����08:33:38
   */
  private void initBorder() {
    this.panel = new UIPanel();
    this.panel.setLayout(new GridLayout(4, 1));
    // ����label�����ݵĲ���
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.weightx = 100;
    constraints.weighty = 100;
    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.anchor = GridBagConstraints.EAST;
    // ��ʼ���߿����Ϊÿ��panel���ñ߿�
    Border etched = BorderFactory.createEtchedBorder();
    Border contractTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0079")/*
                                                                     * @res
                                                                     * "��ͬ��"
                                                                     */);
    ((UIPanel) this.getContractPanel()).add(this.getContractPriceLabel(),
        constraints);
    ((UIPanel) this.getContractPanel()).setBorder(contractTitled);

    Border supplerPrcTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0080")/*
                                                                     * @res
                                                                     * "��Ӧ�̼�Ŀ��"
                                                                     */);
    ((UIPanel) this.getSupplerPrcPanel()).add(this.getSupplerPrcLabelLabel(),
        constraints);
    ((UIPanel) this.getSupplerPrcPanel()).setBorder(supplerPrcTitled);

    Border newPriceTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0081")/*
                                                                     * @res
                                                                     * "�������¼�"
                                                                     */);
    ((UIPanel) this.getNewPricePanel()).add(this.getNewPriceLabelLabel(),
        constraints);
    ((UIPanel) this.getNewPricePanel()).setBorder(newPriceTitled);

    Border lowestPriceTitled =
        BorderFactory.createTitledBorder(etched, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0082")/*
                                                                     * @res
                                                                     * "������ͼ�"
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

      // ��ü۸����Ȳ���
      String purchaseOrgId = orderVO.getHVO().getPk_org();

      PricePriority pp = PUSysParamUtil.getPO28(purchaseOrgId);
      if (pp == null) {
        pp = PricePriority.TAXPRICE_PRIOR_TO_PRICE;
      }

      // ��ͬ��
      OrderSideFormRelCT relCt = new OrderSideFormRelCT();
      String contractPrc = relCt.relatingCT(orderVO, bodyIndex, pp);

      // ѯ��
      OrderSideFormDefPrice defPrice =
          new OrderSideFormDefPrice(orderVO, bodyIndex, pp);
      // �ϲ�Զ�̵���
      defPrice.prepare();
      defPrice.process();
      Map<PriceSource, UFDouble> priceMap = defPrice.getPriceMap();
      ScaleUtils scaleUtils =
          new ScaleUtils(AppContext.getInstance().getPkGroup());
      String origcurrencyid = orderVO.getBVO()[bodyIndex].getCorigcurrencyid();
      if (origcurrencyid == null) {
        origcurrencyid = orderVO.getHVO().getCorigcurrencyid();
      }
      // ������ͼ�
      UFDouble lowestPrice =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.OrderMinPrice), origcurrencyid);
      // �������¼�
      UFDouble newPrice =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.OrderNewestPrice), origcurrencyid);
      // ��Ӧ�̼�Ŀ��
      UFDouble supplerPrc =
          scaleUtils.adjustSoPuPriceScale(
              priceMap.get(PriceSource.SupplierPrice), origcurrencyid);

      // ������ʾֵ
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
