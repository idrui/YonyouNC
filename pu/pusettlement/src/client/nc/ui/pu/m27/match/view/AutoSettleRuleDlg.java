/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 ����10:07:47
 */
package nc.ui.pu.m27.match.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.pu.pub.parapanel.AutoSettleRulePanel;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pub.SuperVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Զ�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-18 ����10:07:47
 */
public class AutoSettleRuleDlg extends UIDialog implements ActionListener {

  private static final long serialVersionUID = -6314305080448968119L;

  private UIButton btnCancel = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000008")/* @res "ȡ��" */);

  // ��ť
  private UIButton btnSave = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000044")/* @res "ȷ��" */);

  private AutoSettleRulePanel panel = null;

  private SuperVO[] ruleVOs;

  public AutoSettleRuleDlg(Container parent, String pkOrg) {
    super(parent, true);

    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0037")/* @res "�Զ��������" */);

    this.setLayout(new BorderLayout());
    this.setSize(640, 340);
    this.setResizable(true);
    this.panel = new AutoSettleRulePanel(pkOrg);
    this.add(this.panel, BorderLayout.CENTER);

    this.addButtonAndListener();
  }

  public AutoSettleRuleDlg(Container parent, SuperVO[] resultVOs) {
    super(parent, true);
    this.setResizable(true);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004060_0", "04004060-0037")/* @res "�Զ��������" */);

    this.setLayout(new BorderLayout());
    this.setSize(640, 340);

    this.panel = new AutoSettleRulePanel(resultVOs);
    this.add(this.panel, BorderLayout.CENTER);

    this.addButtonAndListener();
  }

  /**
   * ���෽����д
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.btnCancel) {
      this.onCancel();
    }
    else if (e.getSource() == this.btnSave) {
      this.onSave();
    }
  }

  /**
   * @return ruleVOs
   */
  public SuperVO[] getRuleVOs() {
    return this.ruleVOs;
  }

  /**
   * @param ruleVOs
   *          Ҫ���õ� ruleVOs
   */
  public void setRuleVOs(SuperVO[] ruleVOs) {
    this.ruleVOs = ruleVOs;
  }

  /**
   * ����������������Ӱ�ť�ͼ���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-23 ����08:11:23
   */
  private void addButtonAndListener() {
    UIPanel btnPanel = new UIPanel();
    btnPanel.setLayout(new BorderLayout());
    UIPanel eastBtnPanel = new UIPanel();
    FlowLayout fLayout = new FlowLayout();
    eastBtnPanel.setLayout(fLayout);
    fLayout.setAlignment(FlowLayout.RIGHT);
    UIButton[] btns = this.getUIButtons();
    int iLen = btns.length;
    for (int i = 0; i < iLen; i++) {
      eastBtnPanel.add(btns[i]);
    }
    btnPanel.add(eastBtnPanel, BorderLayout.EAST);
    this.add(btnPanel, BorderLayout.SOUTH);

    // ��ť����
    this.initiButtonListener();
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����02:06:38
   */
  private SuperVO[] getData() {
    BillCardPanel rbstockPanel = this.panel.getRbstockPanel();
    RBStockOptionableVO rbstockVO =
        (RBStockOptionableVO) rbstockPanel.getBillData().getHeaderValueVO(
            RBStockOptionableVO.class.getName());

    BillCardPanel rbinvoicePanel = this.panel.getRbinvoicePanel();
    RBInvoiceOptionableVO rbinvoiceVO =
        (RBInvoiceOptionableVO) rbinvoicePanel.getBillData().getHeaderValueVO(
            RBInvoiceOptionableVO.class.getName());

    BillCardPanel invoicestockPanel = this.panel.getInvoicestockPanel();
    InvoiceStockOptionableVO invoicestockVO =
        (InvoiceStockOptionableVO) invoicestockPanel.getBillData()
            .getHeaderValueVO(InvoiceStockOptionableVO.class.getName());

    return new SuperVO[] {
      rbstockVO, rbinvoiceVO, invoicestockVO
    };
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����01:45:42
   */
  private UIButton[] getUIButtons() {

    return new UIButton[] {
      this.btnSave, this.btnCancel
    };
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����01:33:14
   */
  private void initiButtonListener() {
    this.btnCancel.addActionListener(this);
    this.btnSave.addActionListener(this);
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-5-19 ����01:39:04
   */
  private void onCancel() {
    this.closeCancel();
  }

  private void onSave() {
    this.ruleVOs = this.getData();

    this.closeOK();
  }

}
