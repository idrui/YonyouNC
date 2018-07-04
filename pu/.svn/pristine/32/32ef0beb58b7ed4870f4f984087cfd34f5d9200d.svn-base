package nc.ui.pu.pub.parapanel;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import nc.bs.para.ComplicatedParaContext;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.para.SysInitVO;

public class PO27ListToListPanel extends AbstractParaListToListPanel {
  private static final long serialVersionUID = 859317663154317848L;

  private JCheckBox checkBox;

  private JLabel checkBoxLabel;

  // 计划价物料是否按计划价暂估
  private boolean isByPlanPriceEstimate;

  private ParaListItemInfo[] leftItems;

  private ParaListItemInfo[] rightItems;

  public PO27ListToListPanel(String pk_org) {
    // super(pk_org);
  }

  @Override
  public UIPanel getPanel(ComplicatedParaContext context) {
    // ComplicatedParaContext context
    UIPanel mainPanel = new UIPanel();
    mainPanel.setLayout(new BorderLayout());

    // 添加：计划价物料是否按计划价暂估
    UIPanel northpanel = new UIPanel();
    northpanel.add(this.getCheckBox());
    northpanel.add(this.getCheckBoxLabel());

    // 添加：列表=列表
    // 根据最新需求：计划价物料是否按计划价暂估，的钩选先不允支持 2010.8.13 by zhaoyha
    // 根据最新需求：计划价物料又开始支持按是否近计划价暂估，钩选先打开 2010.11.26 by zhaoyha
    mainPanel.add(northpanel, BorderLayout.NORTH);
    mainPanel.add(this.getListToListPanel(), BorderLayout.CENTER);
    this.initlize(context);
    return mainPanel;
  }

  @Override
  public String getParamValueCode() {
    // 原来是PO27_v 现更改为PO27以适应uap新的变化
    return POParas.PO27.toString();
  }

  public List<String> getPO27PriceList() {
    // 入库单价,订单价,计划价,订单最新价,最新结算价,供应商价目表
    List<String> PO27PriceList = new LinkedList<String>();
    // 入库单价
    PO27PriceList.add(PriceSource.PurchaseInPrice.name());
    // 订单价
    PO27PriceList.add(PriceSource.OrderPice.name());
    // 计划价
    PO27PriceList.add(PriceSource.PlanPrice.name());
    // 订单最新价
    PO27PriceList.add(PriceSource.OrderNewestPrice.name());
    // 最新结算价
    PO27PriceList.add(PriceSource.SettleNewestPrice.name());
    // 供应商价目表
    PO27PriceList.add(PriceSource.SupplierPrice.name());
    return PO27PriceList;
  }

  @Override
  public String getSysInitVOValue(ParaListItemInfo[] retArray) {
    String retValue = super.getSysInitVOValue(retArray);
    if (this.getCheckBox().isSelected()) {
      retValue = UFBoolean.TRUE.toString() + "," + retValue;
    }
    else {
      retValue = UFBoolean.FALSE.toString() + "," + retValue;
    }
    return retValue;
  }

  @Override
  public ParaListItemInfo[] initLeftItems(SysInitVO initVO) {
    if (this.leftItems == null) {
      this.buildInitData(initVO);
    }

    return this.leftItems;
  }

  @Override
  public void initlize(ComplicatedParaContext context) {
    super.initlize(context);
    // 设置：计划价物料是否按计划价暂估
    this.getCheckBox().setSelected(this.isByPlanPriceEstimate);
  }

  @Override
  public ParaListItemInfo[] initRightItems(SysInitVO initVO) {
    if (this.rightItems == null) {
      this.buildInitData(initVO);
    }

    return this.rightItems;
  }

  private void buildInitData(SysInitVO initVO) {

    String[] initValues = initVO.getValue().split(",");
    // 计划价物料是否按计划价暂估
    this.isByPlanPriceEstimate =
        UFBoolean.valueOf(initValues[0]).booleanValue();

    this.rightItems = new ParaListItemInfo[initValues.length - 1];
    List<String> po27PriceList = this.getPO27PriceList();
    for (int i = 1, len = initValues.length; i < len; i++) {

      this.rightItems[i - 1] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(initValues[i]);
      this.rightItems[i - 1].setText(priceEnum.code());
      this.rightItems[i - 1].setValue(priceEnum.name());
      // 删除已被选中的值
      po27PriceList.remove(priceEnum.name());
    }

    this.leftItems = new ParaListItemInfo[po27PriceList.size()];
    for (int j = 0, len = this.leftItems.length; j < len; j++) {

      this.leftItems[j] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(po27PriceList.get(j));
      this.leftItems[j].setText(priceEnum.code());
      this.leftItems[j].setValue(priceEnum.name());
    }
  }

  private JCheckBox getCheckBox() {
    if (this.checkBox == null) {
      this.checkBox = new JCheckBox();
      this.checkBox.setName("JCheckBox");
      this.checkBox.setText("");
      this.checkBox.setBounds(128, 58, 21, 24);
    }
    return this.checkBox;
  }

  private JLabel getCheckBoxLabel() {
    if (this.checkBoxLabel == null) {
      this.checkBoxLabel = new JLabel();
      this.checkBoxLabel.setName("JLabel");
      this.checkBoxLabel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0048")/* @res "计划价物料是否按计划价暂估" */);
      this.checkBoxLabel.setBounds(11, 63, 116, 16);
    }
    return this.checkBoxLabel;
  }
}
