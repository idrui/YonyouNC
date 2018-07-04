/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-21 ����06:33:47
 */
package nc.ui.pu.m21.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.org.IOrgConst;
import nc.pubitf.org.IOrgUnitPubService;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.org.OrgVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanAllotVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleObjectFactory.NumScaleObject;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ƻ��ֻ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-21 ����06:33:47
 */
public class ReceivePlanAllotUI extends UIPanel implements BillEditListener,
    ActionListener {

  private static final long serialVersionUID = 4840629310660232029L;

  private UIButton btnCancel = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000008")/* @res "ȡ��" */);

  // ��ť
  private UIButton btnSave = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000044")/* @res "ȷ��" */);

  // �ڵ���displayʱ������
  private Container cont = null;

  // �ݸٴ�panel��Dialog
  private UIDialog dlg = null;

  // �����ƻ�����ģ��
  private BillCardPanel pnlBillCard = null;

  // ������VO
  private OrderItemVO voItem = null;

  // ����ѷ���õ�VO
  private OrderReceivePlanAllotVO[] voResultRPAllot = null;

  // ��ǰ�����ƻ�VO
  private OrderReceivePlanVO voRP = null;

  public ReceivePlanAllotUI(OrderItemVO voItem, OrderReceivePlanVO voRP) {
    super();
    this.setBodyVO(voItem);
    this.setRPVO(voRP);

    if (null == this.getRPVO()) {
      // ��ʾ
      // �����ڵ����ƻ���
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0103")/*
                                                                   * @res
                                                                   * "�����ڵ����ƻ���"
                                                                   */);
    }

    this.initi();
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
   * ���෽����д
   * 
   * @see nc.ui.pub.bill.BillEditListener#afterEdit(nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public void afterEdit(BillEditEvent e) {
  	/*
		 * �����ƻ��ֻ�������������˷��丨�����ֶΣ�������Ӧ�ı༭���¼������ƹ���
		 */
		int row = e.getRow();
		if (row < 0)
			return;
		if (OrderReceivePlanAllotVO.NALLOTNUM.equals(e.getKey())) {
			if (e.getValue() != null) {
				UFDouble nallotnum = (UFDouble) e.getValue();
				if (this.getRPVO() != null) {
					String rate = getRPVO().getVchangerate();
					UFDouble nallotasitnum = HslParseUtil.hslDivUFDouble(rate, nallotnum);
					this.getBillCardPanel().setBodyValueAt(nallotasitnum, row, OrderReceivePlanAllotVO.NALLOTASTNUM);
				}
			}

		}else if(OrderReceivePlanAllotVO.NALLOTASTNUM.equals(e.getKey())){
			if (e.getValue() != null) {
				UFDouble nallotastnum = (UFDouble) e.getValue();
				if (this.getRPVO() != null) {
					String rate = getRPVO().getVchangerate();
					// UFDouble nallotasitnum =
					// HslParseUtil.hslMultiplyUFDouble(rate, nallotnum);
					UFDouble nallotnum = HslParseUtil.hslMultiplyUFDouble(rate, nallotastnum);
					this.getBillCardPanel().setBodyValueAt(nallotnum, row, OrderReceivePlanAllotVO.NALLOTNUM);
					this.getBillCardPanel().execBodyFormula(row, OrderReceivePlanAllotVO.NALLOTNUM);
				}
			}
		}
    return;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pub.bill.BillEditListener#bodyRowChange(nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public void bodyRowChange(BillEditEvent e) {
    return;
  }

  /**
   * ����������������ʾ�����ƻ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param container <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����08:05:21
   */
  public void display(Container container) {

    this.setContainer(container);
    this.getDlg().showModal();
  }

  /**
   * ���������������õ��ֻ�VO����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 ����03:08:53
   */
  public OrderReceivePlanAllotVO[] getVoResultRPAllot() {
    return this.voResultRPAllot;
  }

  /**
   * �����������������õ����ƻ�VO����
   * <p>
   * <b>����˵��</b>
   * 
   * @param voResultRPAllot <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 ����03:10:06
   */
  public void setVoResultRPAllot(OrderReceivePlanAllotVO[] voResultRPAllot) {
    this.voResultRPAllot = voResultRPAllot;
  }

  /**
   * ����������������ʾ�����ƻ�����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:15:40
   */
  private void displayVOsToPanel() {
    if (null == this.getRPVO()) {
      return;
    }

    // �õ������֯ID����
    OrgVO[] orgVO = null;

    try {
      IOrgUnitPubService orgUnitPubService =
          NCLocator.getInstance().lookup(IOrgUnitPubService.class);
      orgVO =
          orgUnitPubService.getAllOrgVOSByGroupIDAndOrgTypes(
              this.voItem.getPk_group(), new String[] {
                IOrgConst.STOCKORGTYPE
              });
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    if (null == orgVO || 0 == orgVO.length) {
      return;
    }

    int length = orgVO.length;
    OrderReceivePlanAllotVO[] voAllot = new OrderReceivePlanAllotVO[length];
    for (int i = 0; i < length; ++i) {
      voAllot[i] = new OrderReceivePlanAllotVO();
      voAllot[i].setPk_arrvstoorg(orgVO[i].getPk_org());
      voAllot[i].setPk_arrvstoorg_v(orgVO[i].getPk_vid());
      voAllot[i].setNnum(this.getRPVO().getNnum());
    }

    this.getBillCardPanel().getBillData().setHeaderValueVO(this.getRPVO());
    this.getBillCardPanel().setHeadItem("crowno", this.getBodyVO().getCrowno());
    this.getBillCardPanel().setHeadItem(OrderReceivePlanVO.PK_MATERIAL,
        this.getBodyVO().getPk_material());
    this.getBillCardPanel().getBillData().setBodyValueVO(voAllot);

    this.getBillCardPanel().getBillModel().loadLoadRelationItemValue();

  }

  /**
   * ���������������õ���������Panel
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:11:13
   */
  private BillCardPanel getBillCardPanel() {

    if (this.pnlBillCard == null) {

      // ����ģ��
      this.pnlBillCard = new BillCardPanel();

      // 1001Z8100000000064 4004041600
      BillData bd =
          new BillData(this.pnlBillCard.getTempletData("1001Z8100000000064OK"));

      this.initiPnl(bd);

      this.pnlBillCard.setBodyMultiSelect(true);
    }

    return this.pnlBillCard;
  }

  /**
   * ���������������õ���VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 ����02:59:04
   */
  private OrderItemVO getBodyVO() {
    return this.voItem;
  }

  /**
   * ���������������õ���ʾʱ������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����08:00:45
   */
  private Container getCont() {
    return this.cont;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:44:44
   */
  private UIDialog getDlg() {
    if (this.dlg == null) {
      this.dlg = new UIDialog(this.getCont(), this.getTitle());
      this.dlg.setSize(700, 500);

      UIPanel pnlBtn = new UIPanel();
      UIButton[] btnaBtn = this.getUIButtons();
      int iLen = btnaBtn.length;
      for (int i = 0; i < iLen; i++) {
        pnlBtn.add(btnaBtn[i]);
      }
      this.dlg.getContentPane().setLayout(new java.awt.BorderLayout());
      this.dlg.getContentPane().add(pnlBtn, java.awt.BorderLayout.NORTH);
      this.dlg.getContentPane().add(this, java.awt.BorderLayout.CENTER);
    }

    return this.dlg;
  }

  /**
   * ���������������õ������ƻ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����06:44:48
   */
  private OrderReceivePlanVO getRPVO() {
    return this.voRP;
  }

  /**
   * �����������������ؽ���ı���
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����08:01:52
   */
  private String getTitle() {
    return this.getBillCardPanel().getTitle();
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����08:02:40
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
   * @time 2010-4-21 ����07:10:30
   */
  private void initi() {
    // ���ؿ�Ƭ
    this.setLayout(new java.awt.BorderLayout());
    this.add(this.getBillCardPanel(), java.awt.BorderLayout.CENTER);
    this.initScale();

    // ��ʾ
    this.displayVOsToPanel();

    // ��ť����
    this.initiButtonListener();

    // �༭
    this.getBillCardPanel().setEnabled(true);
  }

  /**
   * �����������������Ӱ�ť����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:35:47
   */
  private void initiButtonListener() {
    this.btnCancel.addActionListener(this);
    this.btnSave.addActionListener(this);
  }

  /**
   * ����������������ʼ��������Ƭ
   * <p>
   * <b>����˵��</b>
   * 
   * @param bd <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:12:53
   */
  @SuppressWarnings("deprecation")
  private void initiPnl(BillData bd) {

    this.pnlBillCard.setBillData(bd);

    this.pnlBillCard.getBodyPanel().addTableSortListener();
    this.pnlBillCard.getBillModel().setRowSort(true);
    // �Ƿ���ʾ�ϼ���
    this.pnlBillCard.setTatolRowShow(true);

    // ��˵����ɼ�
    this.pnlBillCard.setBodyMenuShow(false);

    // ����
    this.pnlBillCard.addEditListener(this);

    // ���ý���ɱ༭��
    this.pnlBillCard.setEnabled(false);
  }

  private void initScale() {
    String unit = this.voItem.getCunitid();
    String astunit = this.voItem.getCastunitid();
    NumScaleObject so = new NumScaleObject(2, 4);
    int unitDg = so.getDigit(unit);
    int astUnitDg = so.getDigit(astunit);
    BillItem bi = this.getBillCardPanel().getHeadItem(OrderItemVO.NNUM);
    if (null != bi) {
      bi.setDecimalDigits(unitDg);
    }
    bi = this.getBillCardPanel().getHeadItem(OrderItemVO.NASTNUM);
    if (null != bi) {
      bi.setDecimalDigits(astUnitDg);
    }
    bi = this.getBillCardPanel().getBodyItem(OrderReceivePlanAllotVO.NALLOTNUM);
    if (null != bi) {
      bi.setDecimalDigits(unitDg);
    }
    bi = this.getBillCardPanel().getBodyItem(OrderReceivePlanAllotVO.NNUM);
    if (null != bi) {
      bi.setDecimalDigits(unitDg);
    }
    bi =
        this.getBillCardPanel().getBodyItem(OrderReceivePlanAllotVO.NALLOTRATE);
    if (null != bi) {
      bi.setDecimalDigits(2);
    }
  }

  /**
   * ��������������ȡ����ť��Ӧ����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:45:20
   */
  private void onCancel() {
    this.voResultRPAllot = null;
    this.getDlg().closeCancel();
  }

  /**
   * �����������������水ť��Ӧ����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����07:47:39
   */
  private void onSave() {
    int rowCount = this.getBillCardPanel().getRowCount();
    Set<OrderReceivePlanAllotVO> set = new HashSet<OrderReceivePlanAllotVO>();
    for (int i = 0; i < rowCount; ++i) {
      BillModel billmodel = this.getBillCardPanel().getBillModel();
      if (BillModel.SELECTED != billmodel.getRowState(i)) {
        continue;
      }
      OrderReceivePlanAllotVO voRet =
          (OrderReceivePlanAllotVO) billmodel.getBodyValueRowVO(i,
              OrderReceivePlanAllotVO.class.getName());
      set.add(voRet);
    }

    if (set.isEmpty()) {
      this.voResultRPAllot = null;
    }
    else {
      this.voResultRPAllot = set.toArray(new OrderReceivePlanAllotVO[0]);
    }

    this.getDlg().closeOK();
  }

  /**
   * ��������������������VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param voItem <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����06:42:42
   */
  private void setBodyVO(OrderItemVO voItem) {
    this.voItem = voItem;
  }

  /**
   * ��������������������ʾʱ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cont <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����08:05:17
   */
  private void setContainer(Container cont) {
    this.cont = cont;
  }

  /**
   * �����������������õ����ƻ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @param voRP <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 ����06:43:23
   */
  private void setRPVO(OrderReceivePlanVO voRP) {
    this.voRP = voRP;
  }

}
