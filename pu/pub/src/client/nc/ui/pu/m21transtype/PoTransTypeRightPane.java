package nc.ui.pu.m21transtype;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.beans.border.UITitledBorder;
import nc.ui.pub.transtype.EditorContext;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * �ɹ�������������UI�ұ����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
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
 * @version ���汾��
 * @since ��һ�汾��
 * @author liyc
 * @modify zhaoyha
 * @time 2009-6-17 ����02:10:49
 */
public class PoTransTypeRightPane extends UIScrollPane implements
    ActionListener {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 2881601565943955410L;

  // Arrive����
  private UICheckBox m_ckbArrive = null;

  // Report����
  private UICheckBox m_ckbComein = null;

  private UICheckBox m_ckbComeinBill = null;

  private UICheckBox m_ckbComeinDate = null;

  // Confirm�Է�ȷ��
  private UICheckBox m_ckbConfirm = null;

  private UICheckBox m_ckbConfirmBill = null;

  private UICheckBox m_ckbConfirmDate = null;

  private UICheckBox m_ckbConfirmNumber = null;

  // GetOut����
  private UICheckBox m_ckbGetout = null;

  private UICheckBox m_ckbGetoutBill = null;

  private UICheckBox m_ckbGetoutDate = null;

  // Output���
  private UICheckBox m_ckbOutput = null;

  // Inform����֪ͨ
  private UICheckBox m_ckbSend = null;

  private UICheckBox m_ckbSendBill = null;

  private UICheckBox m_ckbSendDate = null;

  private UICheckBox m_ckbSendNumber = null;

  // Shippingװ��֪ͨ
  private UICheckBox m_ckbShip = null;

  private UICheckBox m_ckbShipBill = null;

  private UICheckBox m_ckbShipDate = null;

  // Store���
  private UICheckBox m_ckbStore = null;

  // OrderStatus����״̬ѡ��
  private UILabel m_lbStatus = null;

  PoTransTypeLeftPane panel = null;

  /**
   * PoTransTypeRightPane �Ĺ�����
   */
  public PoTransTypeRightPane() {
    // ȱʡ���췽��
  }

  /**
   * PoTransTypeRightPane �Ĺ�����
   *
   * @param leftPane
   */
  public PoTransTypeRightPane(PoTransTypeLeftPane leftPane) {
    this.panel = leftPane;
    this.setName("UILeftPane");
    this.setBorder(new UITitledBorder(""));

    JPanel outerPane = new JPanel();
    outerPane.setLayout(new BorderLayout());

    JPanel scrPane = new JPanel();
    GridLayout gridLayout = new GridLayout(10, 4);// 8 ��4��
    scrPane.setLayout(gridLayout);
    outerPane.add(new JLabel("      "), BorderLayout.WEST);
    outerPane.add(scrPane, BorderLayout.CENTER);

    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getLabelStatus());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxOutput());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxConfirm());
    scrPane.add(this.getCheckBoxConfirmBill());
    scrPane.add(this.getCheckBoxConfirmDate());
    scrPane.add(this.getCheckBoxConfirmNumber());
    scrPane.add(this.getCheckBoxSend());
    scrPane.add(this.getCheckBoxSendBill());
    scrPane.add(this.getCheckBoxSendDate());
    scrPane.add(this.getCheckBoxSendNumber());
    scrPane.add(this.getCheckBoxShip());
    scrPane.add(this.getCheckBoxShipBill());
    scrPane.add(this.getCheckBoxShipDate());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxComein());
    scrPane.add(this.getCheckBoxComeinBill());
    scrPane.add(this.getCheckBoxComeinDate());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxGetout());
    scrPane.add(this.getCheckBoxGetoutBill());
    scrPane.add(this.getCheckBoxGetoutDate());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxArrive());
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(new JLabel(""));// ռλ
    scrPane.add(this.getCheckBoxStore());

    this.setViewportView(outerPane);

    this.initListener();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // ���
    if (e.getSource() == this.getCheckBoxOutput()) {
      this.setOnWaySelection();
    }
    // ȷ��
    else if (e.getSource() == this.getCheckBoxConfirm()) {
      if (this.getCheckBoxConfirm().isSelected()) {
        this.setCheckBoxConfirm(true);
      }
      else {
        this.getCheckBoxConfirmBill().setSelected(false);
        this.getCheckBoxConfirmDate().setSelected(false);
        this.getCheckBoxConfirmNumber().setSelected(false);
        this.setCheckBoxConfirm(false);
      }
      this.setOnWaySelection();
    }
    // ����
    else if (e.getSource() == this.getCheckBoxSend()) {
      if (this.getCheckBoxSend().isSelected()) {
        this.setCheckBoxSend(true);
      }
      else {
        this.getCheckBoxSendBill().setSelected(false);
        this.getCheckBoxSendDate().setSelected(false);
        this.getCheckBoxSendNumber().setSelected(false);
        this.setCheckBoxSend(false);
      }
      this.setOnWaySelection();
    }
    // װ��
    else if (e.getSource() == this.getCheckBoxShip()) {
      if (this.getCheckBoxShip().isSelected()) {
        this.setCheckBoxShip(true);
      }
      else {
        this.getCheckBoxShipBill().setSelected(false);
        this.getCheckBoxShipDate().setSelected(false);
        this.setCheckBoxShip(false);
      }
      this.setOnWaySelection();
    }
    // ����
    else if (e.getSource() == this.getCheckBoxComein()) {
      if (this.getCheckBoxComein().isSelected()) {
        this.setCheckBoxComein(true);
      }
      else {
        this.getCheckBoxComeinBill().setSelected(false);
        this.getCheckBoxComeinDate().setSelected(false);
        this.setCheckBoxComein(false);
      }
      this.setOnWaySelection();
    }
    // ����
    else if (e.getSource() == this.getCheckBoxGetout()) {
      if (this.getCheckBoxGetout().isSelected()) {
        this.setCheckBoxGetout(true);
      }
      else {
        this.getCheckBoxGetoutBill().setSelected(false);
        this.getCheckBoxGetoutDate().setSelected(false);
        this.setCheckBoxGetout(false);
      }
      this.setOnWaySelection();
    }

    // ����
    if (e.getSource() == this.getCheckBoxArrive()) {
      this.setOnWaySelection();
    }

    // ���
    if (e.getSource() == this.getCheckBoxStore()) {
      this.setOnWaySelection();
    }
  }

  /**
   * ȡ�ý�������а�ť��ѡ���ֵ
   *
   * @param vo
   * @return ���а�ť��ѡ���ֵ
   */
  public PoTransTypeVO getSelectStatus(PoTransTypeVO vo) {
    if (this.getCheckBoxOutput().isSelected()) {
      vo.setBoutput(UFBoolean.TRUE);
    }
    else {
      vo.setBoutput(UFBoolean.FALSE);
    }
    if (this.getCheckBoxConfirm().isSelected()) {
      vo.setBconfirm(UFBoolean.TRUE);
    }
    else {
      vo.setBconfirm(UFBoolean.FALSE);
    }
    if (this.getCheckBoxConfirmBill().isSelected()) {
      vo.setBconfirmcode(UFBoolean.TRUE);
    }
    else {
      vo.setBconfirmcode(UFBoolean.FALSE);
    }
    if (this.getCheckBoxConfirmDate().isSelected()) {
      vo.setBconfirmdate(UFBoolean.TRUE);
    }
    else {
      vo.setBconfirmdate(UFBoolean.FALSE);
    }
    if (this.getCheckBoxConfirmNumber().isSelected()) {
      vo.setBconfirmnum(UFBoolean.TRUE);
    }
    else {
      vo.setBconfirmnum(UFBoolean.FALSE);
    }
    if (this.getCheckBoxSend().isSelected()) {
      vo.setBconsign(UFBoolean.TRUE);
    }
    else {
      vo.setBconsign(UFBoolean.FALSE);
    }
    if (this.getCheckBoxSendBill().isSelected()) {
      vo.setBconsigncode(UFBoolean.TRUE);
    }
    else {
      vo.setBconsigncode(UFBoolean.FALSE);
    }
    if (this.getCheckBoxSendDate().isSelected()) {
      vo.setBconsigndate(UFBoolean.TRUE);
    }
    else {
      vo.setBconsigndate(UFBoolean.FALSE);
    }
    if (this.getCheckBoxSendNumber().isSelected()) {
      vo.setBconsignnum(UFBoolean.TRUE);
    }
    else {
      vo.setBconsignnum(UFBoolean.FALSE);
    }
    if (this.getCheckBoxShip().isSelected()) {
      vo.setBload(UFBoolean.TRUE);
    }
    else {
      vo.setBload(UFBoolean.FALSE);
    }
    if (this.getCheckBoxShipBill().isSelected()) {
      vo.setBloadcode(UFBoolean.TRUE);
    }
    else {
      vo.setBloadcode(UFBoolean.FALSE);
    }
    if (this.getCheckBoxShipDate().isSelected()) {
      vo.setBloaddate(UFBoolean.TRUE);
    }
    else {
      vo.setBloaddate(UFBoolean.FALSE);
    }
    if (this.getCheckBoxComein().isSelected()) {
      vo.setBcustom(UFBoolean.TRUE);
    }
    else {
      vo.setBcustom(UFBoolean.FALSE);
    }
    if (this.getCheckBoxComeinBill().isSelected()) {
      vo.setBcustomcode(UFBoolean.TRUE);
    }
    else {
      vo.setBcustomcode(UFBoolean.FALSE);
    }
    if (this.getCheckBoxComeinDate().isSelected()) {
      vo.setBcustomdate(UFBoolean.TRUE);
    }
    else {
      vo.setBcustomdate(UFBoolean.FALSE);
    }
    if (this.getCheckBoxGetout().isSelected()) {
      vo.setBoutcustom(UFBoolean.TRUE);
    }
    else {
      vo.setBoutcustom(UFBoolean.FALSE);
    }
    if (this.getCheckBoxGetoutBill().isSelected()) {
      vo.setBoutcustomcode(UFBoolean.TRUE);
    }
    else {
      vo.setBoutcustomcode(UFBoolean.FALSE);
    }
    if (this.getCheckBoxGetoutDate().isSelected()) {
      vo.setBoutcustomdate(UFBoolean.TRUE);
    }
    else {
      vo.setBoutcustomdate(UFBoolean.FALSE);
    }
    if (this.getCheckBoxArrive().isSelected()) {
      vo.setBarrive(UFBoolean.TRUE);
    }
    else {
      vo.setBarrive(UFBoolean.FALSE);
    }
    if (this.getCheckBoxStore().isSelected()) {
      vo.setBstore(UFBoolean.TRUE);
    }
    else {
      vo.setBstore(UFBoolean.FALSE);
    }
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
   * @time 2010-1-9 ����10:48:26
   */
  public void setEditable() {
    this.getCheckBoxOutput().setEnabled(true);
    this.getCheckBoxConfirm().setEnabled(true);
    // getCheckBoxConfirmBill().setEnabled(true);
    // getCheckBoxConfirmDate().setEnabled(true);
    // getCheckBoxConfirmNumber().setEnabled(true);
    this.getCheckBoxSend().setEnabled(true);
    // getCheckBoxSendBill().setEnabled(true);
    // getCheckBoxSendDate().setEnabled(true);
    // getCheckBoxSendNumber().setEnabled(true);
    this.getCheckBoxShip().setEnabled(true);
    // getCheckBoxShipBill().setEnabled(true);
    // getCheckBoxShipDate().setEnabled(true);
    this.getCheckBoxComein().setEnabled(true);
    // getCheckBoxComeinBill().setEnabled(true);
    // getCheckBoxComeinDate().setEnabled(true);
    this.getCheckBoxGetout().setEnabled(true);
    // getCheckBoxGetoutBill().setEnabled(true);
    // getCheckBoxGetoutDate().setEnabled(true);
    this.getCheckBoxArrive().setEnabled(true);
    this.getCheckBoxStore().setEnabled(true);
  }

  /**
   * ���������������������а�ť��ѡ��
   * <p>
   * <b>����˵��</b>
   * <p>
   *
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:48:04
   */
  public void setNullStatus() {
    this.getCheckBoxOutput().setSelected(false);
    this.getCheckBoxConfirm().setSelected(false);
    this.getCheckBoxConfirmBill().setSelected(false);
    this.getCheckBoxConfirmDate().setSelected(false);
    this.getCheckBoxConfirmNumber().setSelected(false);
    this.getCheckBoxSend().setSelected(false);
    this.getCheckBoxSendBill().setSelected(false);
    this.getCheckBoxSendDate().setSelected(false);
    this.getCheckBoxSendNumber().setSelected(false);
    this.getCheckBoxShip().setSelected(false);
    this.getCheckBoxShipBill().setSelected(false);
    this.getCheckBoxShipDate().setSelected(false);
    this.getCheckBoxComein().setSelected(false);
    this.getCheckBoxComeinBill().setSelected(false);
    this.getCheckBoxComeinDate().setSelected(false);
    this.getCheckBoxGetout().setSelected(false);
    this.getCheckBoxGetoutBill().setSelected(false);
    this.getCheckBoxGetoutDate().setSelected(false);
    this.getCheckBoxArrive().setSelected(false);
    this.getCheckBoxStore().setSelected(false);

    this.getCheckBoxOutput().setEnabled(false);
    this.getCheckBoxConfirm().setEnabled(false);
    this.getCheckBoxConfirmBill().setEnabled(false);
    this.getCheckBoxConfirmDate().setEnabled(false);
    this.getCheckBoxConfirmNumber().setEnabled(false);
    this.getCheckBoxSend().setEnabled(false);
    this.getCheckBoxSendBill().setEnabled(false);
    this.getCheckBoxSendDate().setEnabled(false);
    this.getCheckBoxSendNumber().setEnabled(false);
    this.getCheckBoxShip().setEnabled(false);
    this.getCheckBoxShipBill().setEnabled(false);
    this.getCheckBoxShipDate().setEnabled(false);
    this.getCheckBoxComein().setEnabled(false);
    this.getCheckBoxComeinBill().setEnabled(false);
    this.getCheckBoxComeinDate().setEnabled(false);
    this.getCheckBoxGetout().setEnabled(false);
    this.getCheckBoxGetoutBill().setEnabled(false);
    this.getCheckBoxGetoutDate().setEnabled(false);
    this.getCheckBoxArrive().setEnabled(false);
    this.getCheckBoxStore().setEnabled(false);
  }

  /**
   * �����������������ý������԰�ť��ѡ��ֵ��
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   *
   * @param vo
   *          <p>
   * @author zhaoyha
   * @time 2009-6-17 ����04:42:32
   */
  public void setSelectStatus(PoTransTypeVO vo) {
    // ���
    this.getCheckBoxOutput().setSelected(
        vo.getBoutput() == null ? false : vo.getBoutput().booleanValue());
    // ȷ��
    this.getCheckBoxConfirm().setSelected(
        vo.getBconfirm() == null ? false : vo.getBconfirm().booleanValue());
    this.getCheckBoxConfirmBill().setSelected(
        vo.getBconfirmcode() == null ? false : vo.getBconfirmcode()
            .booleanValue());
    this.getCheckBoxConfirmDate().setSelected(
        vo.getBconfirmdate() == null ? false : vo.getBconfirmdate()
            .booleanValue());
    this.getCheckBoxConfirmNumber().setSelected(
        vo.getBconfirmnum() == null ? false : vo.getBconfirmnum()
            .booleanValue());
    // ����
    this.getCheckBoxSend().setSelected(
        vo.getBconsign() == null ? false : vo.getBconsign().booleanValue());
    this.getCheckBoxSendBill().setSelected(
        vo.getBconsigncode() == null ? false : vo.getBconsigncode()
            .booleanValue());
    this.getCheckBoxSendDate().setSelected(
        vo.getBconsigndate() == null ? false : vo.getBconsigndate()
            .booleanValue());
    this.getCheckBoxSendNumber().setSelected(
        vo.getBconsignnum() == null ? false : vo.getBconsignnum()
            .booleanValue());
    // װ��
    this.getCheckBoxShip().setSelected(
        vo.getBload() == null ? false : vo.getBload().booleanValue());
    this.getCheckBoxShipBill().setSelected(
        vo.getBloadcode() == null ? false : vo.getBloadcode().booleanValue());
    this.getCheckBoxShipDate().setSelected(
        vo.getBloaddate() == null ? false : vo.getBloaddate().booleanValue());
    // ����
    this.getCheckBoxComein().setSelected(
        vo.getBcustom() == null ? false : vo.getBcustom().booleanValue());
    this.getCheckBoxComeinBill().setSelected(
        vo.getBcustomcode() == null ? false : vo.getBcustomcode()
            .booleanValue());
    this.getCheckBoxComeinDate().setSelected(
        vo.getBcustomdate() == null ? false : vo.getBcustomdate()
            .booleanValue());
    // ����
    this.getCheckBoxGetout().setSelected(
        vo.getBoutcustom() == null ? false : vo.getBoutcustom().booleanValue());
    this.getCheckBoxGetoutBill().setSelected(
        vo.getBoutcustomcode() == null ? false : vo.getBoutcustomcode()
            .booleanValue());
    this.getCheckBoxGetoutDate().setSelected(
        vo.getBoutcustomdate() == null ? false : vo.getBoutcustomdate()
            .booleanValue());
    // ����
    this.getCheckBoxArrive().setSelected(
        vo.getBarrive() == null ? false : vo.getBarrive().booleanValue());
    // ���
    this.getCheckBoxStore().setSelected(
        vo.getBstore() == null ? false : vo.getBstore().booleanValue());
  }

  /**
   * �����������������ö���״̬ѡ��ڶ���checkbox�ı༭״̬��
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   *
   * @param vo
   * @param context
   *          <p>
   * @author zhaoyha
   * @time 2009-6-17 ����04:54:34
   */
  public void setStatusChkBoxEnable(PoTransTypeVO vo, EditorContext context) {
    if ((context.getEventtype() != EditorContext.TYPE_NEW)
        && (context.getEventtype() != EditorContext.TYPE_EDIT)) {
      return;
    }
    this.setCheckBoxConfirm(vo.getBconfirm() == null ? false : vo.getBconfirm()
        .booleanValue());
    this.setCheckBoxSend(vo.getBconsign() == null ? false : vo.getBconsign()
        .booleanValue());
    this.setCheckBoxShip(vo.getBload() == null ? false : vo.getBload()
        .booleanValue());
    this.setCheckBoxComein(vo.getBcustom() == null ? false : vo.getBcustom()
        .booleanValue());
    this.setCheckBoxGetout(vo.getBoutcustom() == null ? false : vo
        .getBoutcustom().booleanValue());

  }

  /**
   * ȡ�á�������ѡ��ť
   */
  private UICheckBox getCheckBoxArrive() {
    if (this.m_ckbArrive == null) {
      this.m_ckbArrive =
          this.makeCheckBox("arrive",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0010")/*@res "����"*/);
    }
    return this.m_ckbArrive;
  }

  /**
   * ȡ�á����ء�ѡ��ť
   */
  private UICheckBox getCheckBoxComein() {
    if (this.m_ckbComein == null) {
      this.m_ckbComein =
          this.makeCheckBox("comein",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0008")/*@res "����"*/);
    }
    return this.m_ckbComein;
  }

  /**
   * ȡ�á����ء��ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxComeinBill() {
    if (this.m_ckbComeinBill == null) {
      this.m_ckbComeinBill =
          this.makeCheckBox("ckbComeinBill",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000814")/*@res "���ݺ�"*/);
    }
    return this.m_ckbComeinBill;
  }

  /**
   * ȡ�á����ء��ġ��������ڡ�ѡ��ť
   */
  private UICheckBox getCheckBoxComeinDate() {
    if (this.m_ckbComeinDate == null) {
      this.m_ckbComeinDate =
          this.makeCheckBox("ckbComeinDate",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000799")/*@res "��������"*/);
    }
    return this.m_ckbComeinDate;
  }

  /**
   * ȡ�á��Է�ȷ�ϡ�ѡ��ť
   */
  private UICheckBox getCheckBoxConfirm() {
    if (this.m_ckbConfirm == null) {
      this.m_ckbConfirm =
          this.makeCheckBox("confirm",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0023")/*@res "�Է�ȷ��"*/);
    }
    return this.m_ckbConfirm;
  }

  /**
   * ȡ�á��Է�ȷ�ϡ��ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxConfirmBill() {
    if (this.m_ckbConfirmBill == null) {
      this.m_ckbConfirmBill =
          this.makeCheckBox("ckbConfirmBill",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000814")/*@res "���ݺ�"*/);
    }
    return this.m_ckbConfirmBill;
  }

  /**
   * ȡ�á��Է�ȷ�ϡ��ġ��������ڡ�ѡ��ť
   */
  private UICheckBox getCheckBoxConfirmDate() {
    if (this.m_ckbConfirmDate == null) {
      this.m_ckbConfirmDate =
          this.makeCheckBox("ckbConfirmDate",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000799")/*@res "��������"*/);
    }
    return this.m_ckbConfirmDate;
  }

  /**
   * ȡ�á��Է�ȷ�ϡ��ġ��Ƿ�������ѡ��ť
   */
  private UICheckBox getCheckBoxConfirmNumber() {
    if (this.m_ckbConfirmNumber == null) {
      this.m_ckbConfirmNumber =
          this.makeCheckBox("ckbConfirmNumber",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0024")/*@res "�Ƿ�����"*/);
    }
    return this.m_ckbConfirmNumber;
  }

  /**
   * ȡ�á����ء�ѡ��ť
   */
  private UICheckBox getCheckBoxGetout() {
    if (this.m_ckbGetout == null) {
      this.m_ckbGetout =
          this.makeCheckBox("getout",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0009")/*@res "����"*/);
    }
    return this.m_ckbGetout;
  }

  /**
   * ȡ�á����ء��ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxGetoutBill() {
    if (this.m_ckbGetoutBill == null) {
      this.m_ckbGetoutBill =
          this.makeCheckBox("ckbGetoutBill",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000814")/*@res "���ݺ�"*/);
    }
    return this.m_ckbGetoutBill;
  }

  /**
   * ȡ�á����ء��ġ��������ڡ�ѡ��ť
   */
  private UICheckBox getCheckBoxGetoutDate() {
    if (this.m_ckbGetoutDate == null) {
      this.m_ckbGetoutDate =
          this.makeCheckBox("ckbGetoutDate",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000799")/*@res "��������"*/);
    }
    return this.m_ckbGetoutDate;
  }

  /**
   * ȡ�á������ѡ��ť
   */
  private UICheckBox getCheckBoxOutput() {
    if (this.m_ckbOutput == null) {
      this.m_ckbOutput =
          this.makeCheckBox("output",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0004")/*@res "���"*/ );
    }
    return this.m_ckbOutput;
  }

  /**
   * ȡ�á�����֪ͨ��ѡ��ť
   */
  private UICheckBox getCheckBoxSend() {
    if (this.m_ckbSend == null) {
      this.m_ckbSend =
          this.makeCheckBox("send",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0025")/*@res "����֪ͨ"*/);
    }
    return this.m_ckbSend;
  }

  /**
   * ȡ�á�����֪ͨ���ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxSendBill() {
    if (this.m_ckbSendBill == null) {
      // ���ݺ�
      this.m_ckbSendBill =
          this.makeCheckBox("ckbSendBill",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000814")/*@res "���ݺ�"*/);
    }
    return this.m_ckbSendBill;
  }

  /**
   * ȡ�á�����֪ͨ���ġ��������ڡ�ѡ��ť
   */
  private UICheckBox getCheckBoxSendDate() {
    if (this.m_ckbSendDate == null) {
      this.m_ckbSendDate =
          this.makeCheckBox("ckbSendDate",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000799")/*@res "��������"*/);
    }
    return this.m_ckbSendDate;
  }

  /**
   * ȡ�á�����֪ͨ���ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxSendNumber() {
    if (this.m_ckbSendNumber == null) {
      this.m_ckbSendNumber =
          this.makeCheckBox("ckbSendNumber",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0024")/*@res "�Ƿ�����"*/);
    }
    return this.m_ckbSendNumber;
  }

  /**
   * ȡ�á�װ��֪ͨ��ѡ��ť
   */
  private UICheckBox getCheckBoxShip() {
    if (this.m_ckbShip == null) {
      this.m_ckbShip =
          this.makeCheckBox("ship",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0026")/*@res "װ��֪ͨ"*/);
    }
    return this.m_ckbShip;
  }

  /**
   * ȡ�á�װ��֪ͨ���ġ����ݺš�ѡ��ť
   */
  private UICheckBox getCheckBoxShipBill() {
    if (this.m_ckbShipBill == null) {
      this.m_ckbShipBill =
          this.makeCheckBox("ckbShipBill",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000814")/*@res "���ݺ�"*/);
    }
    return this.m_ckbShipBill;
  }

  /**
   * ȡ�á�װ��֪ͨ���ġ��������ڡ�ѡ��ť
   */
  private UICheckBox getCheckBoxShipDate() {
    if (this.m_ckbShipDate == null) {
      this.m_ckbShipDate =
          this.makeCheckBox("ckbShipDate",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000799")/*@res "��������"*/);
    }
    return this.m_ckbShipDate;
  }

  /**
   * ȡ�á���⡱ѡ��ť
   */
  private UICheckBox getCheckBoxStore() {
    if (this.m_ckbStore == null) {
      this.m_ckbStore =
          this.makeCheckBox("Store",
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0011")/*@res "���"*/ );
    }
    return this.m_ckbStore;
  }

  /**
   * ȡ�á�����״̬��ѡ���ǩ
   */
  private UILabel getLabelStatus() {
    if (this.m_lbStatus == null) {
      this.m_lbStatus = new UILabel();
      this.m_lbStatus.setName("labelstatus");
      this.m_lbStatus
          .setText( nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0027")/*@res "����״̬ѡ��"*/);
    }
    return this.m_lbStatus;
  }



  private void initListener() {
    // ���
    this.getCheckBoxOutput().addActionListener(this);
    // ȷ��
    this.getCheckBoxConfirm().addActionListener(this);
    // ����
    this.getCheckBoxSend().addActionListener(this);
    // ����
    this.getCheckBoxComein().addActionListener(this);
    // ����
    this.getCheckBoxGetout().addActionListener(this);
    // װ��
    this.getCheckBoxShip().addActionListener(this);
    // ����
    this.getCheckBoxArrive().addActionListener(this);
    // ���
    this.getCheckBoxStore().addActionListener(this);
  }

  private UICheckBox makeCheckBox(String name, String text) {
    UICheckBox newUICheckBox = new UICheckBox();
    newUICheckBox.setName(name);
    newUICheckBox.setText(text);
    return newUICheckBox;
  }

  private void setCheckBoxComein(boolean benable) {
    this.m_ckbComeinBill.setEnabled(benable);
    this.m_ckbComeinDate.setEnabled(benable);
  }

  private void setCheckBoxConfirm(boolean benable) {
    this.m_ckbConfirmBill.setEnabled(benable);
    this.m_ckbConfirmDate.setEnabled(benable);
    this.m_ckbConfirmNumber.setEnabled(benable);
  }

  private void setCheckBoxGetout(boolean benable) {
    this.m_ckbGetoutBill.setEnabled(benable);
    this.m_ckbGetoutDate.setEnabled(benable);
  }

  private void setCheckBoxSend(boolean benable) {
    this.m_ckbSendBill.setEnabled(benable);
    this.m_ckbSendDate.setEnabled(benable);
    this.m_ckbSendNumber.setEnabled(benable);
  }

  private void setCheckBoxShip(boolean benable) {
    this.m_ckbShipBill.setEnabled(benable);
    this.m_ckbShipDate.setEnabled(benable);
  }

  private void setOnWaySelection() {
    PoTransTypeVO vo = new PoTransTypeVO();
    this.getSelectStatus(vo);
    this.panel.updateStatus(vo);
  }
}