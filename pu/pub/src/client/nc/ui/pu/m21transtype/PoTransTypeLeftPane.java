package nc.ui.pu.m21transtype;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import nc.md.model.impl.MDEnum;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.border.UITitledBorder;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.lang.UFBoolean;

/**
 * �ɹ�������������UI������
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����10:39:58
 */
public class PoTransTypeLeftPane extends UIScrollPane {
  private static final Integer noselect = Integer.valueOf(0);

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -4061701751038172101L;

  private static final String space = " ";

  // ArrivePlan�Ƿ���е����ƻ�����
  private UICheckBox m_ArrivebComeinBill = null;

  private UIComboBox m_cbbBegin = null;

  private UIComboBox m_cbbEnd = null;

  private UIComboBox m_cbbPray = null;

  // CheckGroup�Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ
  private UICheckBox m_CheckbComeinBill = null;

  // Direct�Ƿ�ֱ�˲ɹ�
  private UICheckBox m_DirectbComeinBill = null;

  private int m_hspace = 5;

  // IC�Ƿ�Ӧ�̼Ĵ�
  private UICheckBox m_ICbComeinBill = null;

  private UILabel m_lbArrive = null;

  // OnWayBegin��;��ʼ
  private UILabel m_lbBegin = null;

  private UILabel m_lbCheck = null;

  private UILabel m_lbDirect = null;

  // OnWayEnd��;����
  private UILabel m_lbEnd = null;

  private UILabel m_lbIC = null;

  private UILabel m_lboverpay = null;

  // CreateOrdermode�빺�����ɶ������Ʒ�ʽ
  private UILabel m_lbPray = null;

  // Overpay������������
  private UICheckBox m_overpay = null;

  private int m_vspace = 5;

  /**
   * PoTransTypeLeftPane �Ĺ�����
   */
  public PoTransTypeLeftPane() {
    this.setName("UIRightPane");
    this.setBorder(new UITitledBorder(""));

    JPanel outerPane = new JPanel();
    outerPane.setLayout(new BorderLayout());

    JPanel scrPane = new JPanel();
    scrPane.setLayout(null);
    scrPane.setPreferredSize(new Dimension(300, 260));
    outerPane.add(scrPane);

    scrPane.add(this.getCheckBoxIC());
    scrPane.add(this.getLabelIC());
    scrPane.add(this.getCheckBoxDirect());
    scrPane.add(this.getLabelDirect());
    scrPane.add(this.getCheckBoxCheck());
    scrPane.add(this.getLabelCheck());
    scrPane.add(this.getLabelOverpay());
    scrPane.add(this.getCheckBoxOverpay());
    scrPane.add(this.getLabelPray());
    scrPane.add(this.getCbbPray());
    scrPane.add(this.getCheckBoxArrive());
    scrPane.add(this.getLabelArrive());
    scrPane.add(this.getLabelBegin());
    scrPane.add(this.getCbbBegin());
    scrPane.add(this.getLabelEnd());
    scrPane.add(this.getCbbEnd());

    this.setViewportView(outerPane);
  }

  /**
   * ��������������ȡ����;��ʼ������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   *         ��;��ʼ������
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:41:40
   */
  public UIComboBox getCbbBegin() {
    if (this.m_cbbBegin == null) {
      this.m_cbbBegin = new UIComboBox();
      this.m_cbbBegin.setName("ComboBoxBegin");
      this.m_cbbBegin.setTranslate(true);
      this.m_cbbBegin.setLocation(this.getLabelBegin().getX()
          + this.getLabelBegin().getWidth() + this.m_hspace, this
          .getLabelBegin().getY());
      this.m_cbbBegin.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0003")/* @res "����" */,
          (Integer) OnwayStatus.STATUS_AUDIT.value()));
    }
    return this.m_cbbBegin;
  }

  /**
   * ��������������ȡ����;����������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   *         ��;����������
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:42:37
   */
  public UIComboBox getCbbEnd() {
    if (this.m_cbbEnd == null) {
      this.m_cbbEnd = new UIComboBox();
      this.m_cbbEnd.setName("ComboBoxEnd");
      this.m_cbbEnd.setTranslate(true);
      this.m_cbbEnd.setLocation(this.getLabelEnd().getX()
          + this.getLabelEnd().getWidth() + this.m_hspace, this.getLabelEnd()
          .getY());
    }
    return this.m_cbbEnd;
  }

  /**
   * ��������������ȡ�����а�ťѡ��״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          ���а�ťѡ��״̬
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:43:10
   */
  public PoTransTypeVO getSelectStatus(PoTransTypeVO vo) {
    if (this.getCheckBoxIC().isSelected()) {
      vo.setBvmi(UFBoolean.TRUE);
    }
    else {
      vo.setBvmi(UFBoolean.FALSE);
    }
    if (this.getCheckBoxDirect().isSelected()) {
      vo.setBdirect(UFBoolean.TRUE);
    }
    else {
      vo.setBdirect(UFBoolean.FALSE);
    }
    if (this.getCheckBoxCheck().isSelected()) {
      vo.setBcheckcenpur(UFBoolean.TRUE);
    }
    else {
      vo.setBcheckcenpur(UFBoolean.FALSE);
    }
    if (this.getCheckBoxArrive().isSelected()) {
      vo.setBreceiveplan(UFBoolean.TRUE);
    }
    else {
      vo.setBreceiveplan(UFBoolean.FALSE);
    }
    if (this.getCheckBoxOverpay().isSelected()) {
      vo.setBoverpay(UFBoolean.TRUE);
    }
    else {
      vo.setBoverpay(UFBoolean.FALSE);
    }
    vo.setIprtopolimit(Integer.valueOf(this.getCbbPray().getSelectedIndex()));
    vo.setIonwaybegin((Integer) this.getCbbBegin().getSelectdItemValue());
    vo.setIonwayend((Integer) this.getCbbEnd().getSelectdItemValue());
    return vo;
  }

  /**
   * ���������������������ɱ༭��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:44:44
   */
  public void setEditable() {
    this.setEditStatus(true);
  }

  /**
   * ���������������������а�ť��ѡ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:44:28
   */
  public void setNullStatus() {
    this.getCheckBoxIC().setSelected(false);
    this.getCheckBoxDirect().setSelected(false);
    this.getCheckBoxCheck().setSelected(false);
    this.getCheckBoxArrive().setSelected(false);
    this.getCheckBoxOverpay().setSelected(false);
    this.getCbbPray().setSelectedIndex(0);
    this.setEditStatus(false);
  }

  /**
   * ���������������������а�ťѡ��״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   *          �ɹ���������������չ����VO
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:43:50
   */
  public void setSelectStatus(PoTransTypeVO vo) {
    this.getCheckBoxIC().setSelected(
        vo.getBvmi() == null ? false : vo.getBvmi().booleanValue());
    this.getCheckBoxDirect().setSelected(
        vo.getBdirect() == null ? false : vo.getBdirect().booleanValue());
    this.getCheckBoxCheck().setSelected(
        vo.getBcheckcenpur() == null ? false : vo.getBcheckcenpur()
            .booleanValue());
    this.getCheckBoxArrive().setSelected(
        vo.getBreceiveplan() == null ? false : vo.getBreceiveplan()
            .booleanValue());
    this.getCheckBoxOverpay().setSelected(
        vo.getBoverpay() == null ? false : vo.getBoverpay().booleanValue());
    this.getCbbPray().setSelectedIndex(
        vo.getIprtopolimit() == null ? 0 : vo.getIprtopolimit().intValue());
    this.getCbbBegin().setSelectedItem(
        new StatusItem(MDEnum.valueOf(OnwayStatus.class, vo.getIonwaybegin())
            .getName(), vo.getIonwaybegin()));

    if (null == vo.getIonwayend() || vo.getIonwayend().intValue() <= 0) {
      this.getCbbEnd().setSelectedItem(
          new StatusItem(PoTransTypeLeftPane.space,
              PoTransTypeLeftPane.noselect));
    }
    else {
      this.getCbbEnd().setSelectedItem(
          new StatusItem(MDEnum.valueOf(OnwayStatus.class, vo.getIonwayend())
              .getName(), vo.getIonwayend()));

    }

  }

  /**
   * ��������������ˢ�°�ťѡ��״̬��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   *          <p>
   *          �ɹ���������������չ����VO
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:45:05
   */
  public void updateStatus(PoTransTypeVO vo) {
    // �����;��ʼ״̬
    this.addOnwayStatus(vo, this.getCbbBegin(), true);
    // �����;����״̬
    this.addOnwayStatus(vo, this.getCbbEnd(), false);
  }

  /**
   * �����;״̬
   * 
   * @param vo
   *          ����������չVO
   * @param addComboBox
   *          ��Ҫ��ӵĿؼ�
   * @param isCbbBegin
   *          �Ƿ�����;��ʼ
   */
  private void addOnwayStatus(PoTransTypeVO vo, UIComboBox addComboBox,
      boolean isCbbBegin) {
    if (null == vo) {
      return;
    }
    if (addComboBox.getComponentCount() != 0) {
      addComboBox.removeAllItems();
      if (isCbbBegin) {
        addComboBox.addItem(new StatusItem(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
                "04004000-0003")/* @res "����" */,
            (Integer) OnwayStatus.STATUS_AUDIT.value()));
      }
      else {
        addComboBox.addItem(new StatusItem(PoTransTypeLeftPane.space,
            PoTransTypeLeftPane.noselect));
      }
    }
    if (vo.getBoutput().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0004")/* @res "���" */,
          (Integer) OnwayStatus.STATUS_OUTPUT.value()));
    }
    if (vo.getBconfirm().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0005")/* @res "ȷ��" */,
          (Integer) OnwayStatus.STATUS_CONFIRM.value()));
    }
    if (vo.getBconsign().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0006")/* @res "����" */,
          (Integer) OnwayStatus.STATUS_SENDOUT.value()));
    }
    if (vo.getBload().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0007")/* @res "װ��" */,
          (Integer) OnwayStatus.STATUS_SHIP.value()));
    }
    if (vo.getBcustom().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0008")/* @res "����" */,
          (Integer) OnwayStatus.STATUS_COMEIN.value()));
    }
    if (vo.getBoutcustom().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0009")/* @res "����" */,
          (Integer) OnwayStatus.STATUS_GETOUT.value()));
    }
    if (vo.getBarrive().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0010")/* @res "����" */,
          (Integer) OnwayStatus.STATUS_ARRIVE.value()));
    }
    if (vo.getBstore().booleanValue()) {
      addComboBox.addItem(new StatusItem(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
              "04004000-0011")/* @res "���" */,
          (Integer) OnwayStatus.STATUS_STORE.value()));
    }
  }

  private UIComboBox getCbbPray() {
    if (this.m_cbbPray == null) {
      this.m_cbbPray = new UIComboBox();
      this.m_cbbPray.setName("ComboBoxBegin");
      this.m_cbbPray.setLocation(this.getLabelPray().getX()
          + this.getLabelPray().getWidth() + this.m_hspace, this.getLabelPray()
          .getY());
      this.m_cbbPray.setSize(170, 22);
      this.m_cbbPray.addItem(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0012")/* @res "������" */);
      this.m_cbbPray.addItem(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0013")/* @res "������Ч��Ӧ�̼۸��������" */);
      this.m_cbbPray.addItem(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0014")/* @res "�����۸�������������" */);
      this.m_cbbPray.setSelectedIndex(0);
    }
    return this.m_cbbPray;
  }

  private UICheckBox getCheckBoxArrive() {
    if (this.m_ArrivebComeinBill == null) {
      this.m_ArrivebComeinBill = new UICheckBox();
      this.m_ArrivebComeinBill.setText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0015")/*
                                                                   * @res
                                                                   * "�Ƿ���е����ƻ�����"
                                                                   */);
      this.m_ArrivebComeinBill.setSize(20, 22);
      this.m_ArrivebComeinBill.setLocation(this.getLabelPray().getX(), this
          .getLabelPray().getY()
          + this.getLabelPray().getHeight()
          + this.m_hspace);
    }
    return this.m_ArrivebComeinBill;
  }

  private UICheckBox getCheckBoxCheck() {
    if (this.m_CheckbComeinBill == null) {
      this.m_CheckbComeinBill = new UICheckBox();
      this.m_CheckbComeinBill.setText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0016")/*
                                                                   * @res
                                                                   * "�ɹ������Ƿ���ɹ�ҵ��ί�й�ϵ"
                                                                   */);
      this.m_CheckbComeinBill.setSize(20, 22);
      this.m_CheckbComeinBill.setLocation(this.getCheckBoxDirect().getX(), this
          .getCheckBoxDirect().getY()
          + this.getCheckBoxDirect().getHeight()
          + this.m_hspace);
    }
    return this.m_CheckbComeinBill;
  }

  private UICheckBox getCheckBoxDirect() {
    if (this.m_DirectbComeinBill == null) {
      this.m_DirectbComeinBill = new UICheckBox();
      this.m_DirectbComeinBill.setText(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0017")/*
                                                                   * @res
                                                                   * "�Ƿ�ֱ�˲ɹ�"
                                                                   */);
      this.m_DirectbComeinBill.setSize(20, 22);
      this.m_DirectbComeinBill.setLocation(this.getCheckBoxIC().getX(), this
          .getCheckBoxIC().getY()
          + this.getCheckBoxIC().getHeight()
          + this.m_hspace);
    }
    return this.m_DirectbComeinBill;
  }

  // ICCheckBox
  private UICheckBox getCheckBoxIC() {
    if (this.m_ICbComeinBill == null) {
      this.m_ICbComeinBill = new UICheckBox();
      this.m_ICbComeinBill.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0018")/* @res "�Ƿ�Ӧ�̼Ĵ�" */);
      this.m_ICbComeinBill.setSize(20, 22);
      this.m_ICbComeinBill.setLocation(30, 10);
    }
    return this.m_ICbComeinBill;
  }

  private UICheckBox getCheckBoxOverpay() {
    if (this.m_overpay == null) {
      this.m_overpay = new UICheckBox();
      this.m_overpay.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0019")/* @res "������������" */);
      this.m_overpay.setSize(20, 22);
      this.m_overpay.setLocation(this.getCheckBoxCheck().getX(), this
          .getCheckBoxCheck().getY()
          + this.getCheckBoxCheck().getHeight()
          + this.m_hspace);
    }
    return this.m_overpay;
  }

  private UILabel getLabelArrive() {
    if (this.m_lbArrive == null) {
      this.m_lbArrive = new UILabel();
      this.m_lbArrive.setName("m_lbDirect");
      this.m_lbArrive.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0015")/* @res "�Ƿ���е����ƻ�����" */);
      this.m_lbArrive.setSize(180, 22);
      this.m_lbArrive.setLocation(this.getCheckBoxArrive().getX()
          + this.getCheckBoxArrive().getWidth() + this.m_vspace, this
          .getCheckBoxArrive().getY());
    }
    return this.m_lbArrive;
  }

  private UILabel getLabelBegin() {
    if (this.m_lbBegin == null) {
      this.m_lbBegin = new UILabel();
      this.m_lbBegin.setName("labelbegin");
      this.m_lbBegin.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0020")/* @res "��;��ʼ" */);
      this.m_lbBegin.setSize(70, 22);
      this.m_lbBegin.setLocation(this.getCheckBoxArrive().getX(), this
          .getCheckBoxArrive().getY()
          + this.getCheckBoxArrive().getHeight()
          + (int) 1.5 * this.m_vspace);
    }
    return this.m_lbBegin;
  }

  private UILabel getLabelCheck() {
    if (this.m_lbCheck == null) {
      this.m_lbCheck = new UILabel();
      this.m_lbCheck.setName("m_lbDirect");
      this.m_lbCheck
          .setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004000_0", "04004000-0016")/* @res "���Ʋɹ������Ƿ���ɹ�ҵ��ί�й�ϵ" */);
      this.m_lbCheck.setSize(220, 22);
      this.m_lbCheck.setLocation(this.getCheckBoxCheck().getX()
          + this.getCheckBoxCheck().getWidth() + this.m_vspace, this
          .getCheckBoxCheck().getY());
    }
    return this.m_lbCheck;
  }

  private UILabel getLabelDirect() {
    if (this.m_lbDirect == null) {
      this.m_lbDirect = new UILabel();
      this.m_lbDirect.setName("m_lbDirect");
      this.m_lbDirect.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0017")/* @res "�Ƿ�ֱ�˲ɹ�" */);
      this.m_lbDirect.setSize(150, 22);
      this.m_lbDirect.setLocation(this.getCheckBoxDirect().getX()
          + this.getCheckBoxDirect().getWidth() + this.m_vspace, this
          .getCheckBoxDirect().getY());
    }
    return this.m_lbDirect;
  }

  private UILabel getLabelEnd() {
    if (this.m_lbEnd == null) {
      this.m_lbEnd = new UILabel();
      this.m_lbEnd.setName("labelend");
      this.m_lbEnd.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0021")/* @res "��;����" */);
      this.m_lbEnd.setSize(this.getLabelBegin().getSize());
      this.m_lbEnd.setLocation(this.getLabelBegin().getX(), this
          .getLabelBegin().getY()
          + this.getLabelBegin().getHeight()
          + (int) 1.5 * this.m_vspace);

    }
    return this.m_lbEnd;
  }

  // IClabel
  private UILabel getLabelIC() {
    if (this.m_lbIC == null) {
      this.m_lbIC = new UILabel();
      this.m_lbIC.setName("m_lbIC");
      this.m_lbIC.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0018")/* @res "�Ƿ�Ӧ�̼Ĵ�" */);
      this.m_lbIC.setSize(150, 22);
      this.m_lbIC.setLocation(this.getCheckBoxIC().getX()
          + this.getCheckBoxIC().getWidth() + this.m_vspace, this
          .getCheckBoxIC().getY());
    }
    return this.m_lbIC;
  }

  private UILabel getLabelOverpay() {
    if (this.m_lboverpay == null) {
      this.m_lboverpay = new UILabel();
      this.m_lboverpay.setName("m_lboverpay");
      this.m_lboverpay.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0019")/* @res "������������" */);
      this.m_lboverpay.setSize(180, 22);
      this.m_lboverpay.setLocation(this.getCheckBoxOverpay().getX()
          + this.getCheckBoxOverpay().getWidth() + this.m_vspace, this
          .getCheckBoxOverpay().getY());
    }
    return this.m_lboverpay;
  }

  private UILabel getLabelPray() {
    // �빺�����ɶ������Ʒ�ʽ
    if (this.m_lbPray == null) {
      this.m_lbPray = new UILabel();
      this.m_lbPray.setName("labelpray");
      this.m_lbPray.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0022")/* @res "�빺�����ɶ������Ʒ�ʽ" */);
      this.m_lbPray.setSize(150, 22);
      this.m_lbPray.setLocation(this.getCheckBoxOverpay().getX(), this
          .getCheckBoxOverpay().getY()
          + (this.getCheckBoxOverpay().getHeight() + (int) 1.5 * this.m_vspace)
          * 2);
    }
    return this.m_lbPray;
  }

  private void setEditStatus(boolean status) {
    this.getCheckBoxIC().setEnabled(status);
    this.getCheckBoxDirect().setEnabled(status);
    this.getCheckBoxCheck().setEnabled(status);
    this.getCheckBoxArrive().setEnabled(status);
    this.getCheckBoxOverpay().setEnabled(status);
    this.getCbbPray().setEnabled(status);
    this.getCbbBegin().setEnabled(status);
    this.getCbbEnd().setEnabled(status);
  }
}
