package nc.ui.pu.m23.view.quickarr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 到货单上面快速收货的对话框
 * 
 * @since 6.0
 * @version 2010-11-10 下午02:32:24
 * @author hanbin
 */
public class QuickArrDlg extends UIDialog implements ActionListener {
  private static final long serialVersionUID = -4025548938694234136L;

  // 取消按钮
  private JButton btnCancel;

  // 确定按钮
  private JButton btnOK;

  private JPanel contentPanel;

  private QuickArrEditor editor;

  // 保存前是否浏览
  private JComboBox<DefaultConstEnum> isBrowserBeforeSaveBox;

  private JLabel isBrowserBeforeSaveLabel;

  // 采购订单号
  private JTextField orderCodeField;

  private JLabel orderCodeLabel;

  public QuickArrDlg(ArriveCardForm arrUI) {
    super(arrUI);

    this.setName("QueryOrdDlg");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(350, 150);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004040_0", "04004040-0062")/* @res "采购订单快速收货查询" */);
    this.setContentPane(this.getContentPanel());
    // 添加按钮监听
    this.getBtnOK().addActionListener(this);
    this.getBtnCancel().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.getBtnOK()) {
      this.onOK();
    }
    if (e.getSource() == this.getBtnCancel()) {
      super.closeCancel();
    }
  }

  public String getBillCodeValue() {
    return this.getOrderCodeField().getText();
  }

  public QuickArrEditor getEditor() {
    if (this.editor == null) {
      this.editor = new QuickArrEditor();
    }
    return this.editor;
  }

  public ArriveVO[] getResultVOArray() {
    return this.getEditor().getResultVOArray(this.getBillCodeValue());
  }

  public boolean isBrowserBeforeSave() {
    DefaultConstEnum item =
        (DefaultConstEnum) this.getIsBrowseCheckBox().getSelectedItem();
    return ((UFBoolean) item.getValue()).booleanValue();
  }

  public void onOK() {
    super.closeOK();
  }

  public void setBillCodeValue(String sBillcode) {
    this.getOrderCodeField().setText(sBillcode);
  }

  private JButton getBtnCancel() {
    if (this.btnCancel == null) {
      this.btnCancel = new JButton();
      this.btnCancel.setName("BtnCancel");
      this.btnCancel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("common", "UC001-0000008")/* @res "取消" */);
      this.btnCancel.setBounds(190, 101, 68, 27);
    }
    return this.btnCancel;
  }

  private JButton getBtnOK() {
    if (this.btnOK == null) {
      this.btnOK = new JButton();
      this.btnOK.setName("BtnOK");
      this.btnOK.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "common", "UC001-0000044")/* @res "确定" */);
      this.btnOK.setBounds(90, 101, 68, 27);
    }
    return this.btnOK;
  }

  private JPanel getContentPanel() {
    if (this.contentPanel == null) {
      this.contentPanel = new JPanel();
      this.contentPanel.setName("UIDialogContentPane");
      this.contentPanel.setLayout(null);
      // 采购订单号
      this.contentPanel.add(this.getOrderCodeField(), this.getOrderCodeField()
          .getName());
      this.contentPanel.add(this.getOrderCodeLabel(), this.getOrderCodeLabel()
          .getName());
      // 是否保存前浏览
      this.contentPanel.add(this.getIsBrowseCheckBox(), this
          .getIsBrowseCheckBox().getName());
      this.contentPanel.add(this.getIsBrowserBeforeSaveLabel(), this
          .getIsBrowserBeforeSaveLabel().getName());
      // 确定按钮、取消按钮
      this.contentPanel.add(this.getBtnOK(), this.getBtnOK().getName());
      this.contentPanel.add(this.getBtnCancel(), this.getBtnCancel().getName());
    }
    return this.contentPanel;
  }

  private JComboBox<DefaultConstEnum> getIsBrowseCheckBox() {
    if (this.isBrowserBeforeSaveBox == null) {
      this.isBrowserBeforeSaveBox = new JComboBox<>();
      this.isBrowserBeforeSaveBox.setName("IsBrowseCheckBox");
      this.isBrowserBeforeSaveBox.addItem(new DefaultConstEnum(UFBoolean.TRUE,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0221")/*
                               * @res
                               * "是"
                               */));
      this.isBrowserBeforeSaveBox.addItem(new DefaultConstEnum(UFBoolean.FALSE,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0222")/*
                               * @res
                               * "否"
                               */));
      this.isBrowserBeforeSaveBox.setBounds(148, 58, 166, 24);
    }
    return this.isBrowserBeforeSaveBox;
  }

  private JLabel getIsBrowserBeforeSaveLabel() {
    if (this.isBrowserBeforeSaveLabel == null) {
      this.isBrowserBeforeSaveLabel = new JLabel();
      this.isBrowserBeforeSaveLabel.setName("JLabel1");
      this.isBrowserBeforeSaveLabel.setText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0063")/*
                                                                   * @res
                                                                   * "保存前是否浏览"
                                                                   */);
      this.isBrowserBeforeSaveLabel.setToolTipText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0063")/*
                                                                   * @res
                                                                   * "保存前是否浏览"
                                                                   */);
      this.isBrowserBeforeSaveLabel.setBounds(21, 63, 140, 16);
    }
    return this.isBrowserBeforeSaveLabel;
  }

  private JTextField getOrderCodeField() {
    if (this.orderCodeField == null) {
      this.orderCodeField = new JTextField();
      this.orderCodeField.setName("TxtBillCode");
      this.orderCodeField.setBounds(148, 19, 166, 20);
    }
    return this.orderCodeField;
  }

  private JLabel getOrderCodeLabel() {
    if (this.orderCodeLabel == null) {
      this.orderCodeLabel = new JLabel();
      this.orderCodeLabel.setName("JLabel11");
      this.orderCodeLabel.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004040_0", "04004040-0064")/* @res "采购订单号" */);
      this.orderCodeLabel.setBounds(46, 22, 140, 16);
      this.orderCodeLabel.setToolTipText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0064")/*
                                                                   * @res
                                                                   * "采购订单号"
                                                                   */);
    }
    return this.orderCodeLabel;
  }
}
