/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-21 下午06:33:47
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划分货界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-21 下午06:33:47
 */
public class ReceivePlanAllotUI extends UIPanel implements BillEditListener,
    ActionListener {

  private static final long serialVersionUID = 4840629310660232029L;

  private UIButton btnCancel = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000008")/* @res "取消" */);

  // 按钮
  private UIButton btnSave = new UIButton(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("common", "UC001-0000044")/* @res "确定" */);

  // 在调用display时的容器
  private Container cont = null;

  // 容纲此panel的Dialog
  private UIDialog dlg = null;

  // 到货计划单据模板
  private BillCardPanel pnlBillCard = null;

  // 订单行VO
  private OrderItemVO voItem = null;

  // 最后已分配好的VO
  private OrderReceivePlanAllotVO[] voResultRPAllot = null;

  // 当前到货计划VO
  private OrderReceivePlanVO voRP = null;

  public ReceivePlanAllotUI(OrderItemVO voItem, OrderReceivePlanVO voRP) {
    super();
    this.setBodyVO(voItem);
    this.setRPVO(voRP);

    if (null == this.getRPVO()) {
      // 提示
      // 不存在到货计划！
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0103")/*
                                                                   * @res
                                                                   * "不存在到货计划！"
                                                                   */);
    }

    this.initi();
  }

  /**
   * 父类方法重写
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
   * 父类方法重写
   * 
   * @see nc.ui.pub.bill.BillEditListener#afterEdit(nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public void afterEdit(BillEditEvent e) {
  	/*
		 * 到货计划分货界面表体增加了分配辅数量字段，增加相应的编辑后事件，完善功能
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
   * 父类方法重写
   * 
   * @see nc.ui.pub.bill.BillEditListener#bodyRowChange(nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public void bodyRowChange(BillEditEvent e) {
    return;
  }

  /**
   * 方法功能描述：显示到货计划界面
   * <p>
   * <b>参数说明</b>
   * 
   * @param container <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午08:05:21
   */
  public void display(Container container) {

    this.setContainer(container);
    this.getDlg().showModal();
  }

  /**
   * 方法功能描述：得到分货VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 下午03:08:53
   */
  public OrderReceivePlanAllotVO[] getVoResultRPAllot() {
    return this.voResultRPAllot;
  }

  /**
   * 方法功能描述：设置到货计划VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param voResultRPAllot <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 下午03:10:06
   */
  public void setVoResultRPAllot(OrderReceivePlanAllotVO[] voResultRPAllot) {
    this.voResultRPAllot = voResultRPAllot;
  }

  /**
   * 方法功能描述：显示到货计划界面
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:15:40
   */
  private void displayVOsToPanel() {
    if (null == this.getRPVO()) {
      return;
    }

    // 得到库存组织ID数组
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
   * 方法功能描述：得到到货单据Panel
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:11:13
   */
  private BillCardPanel getBillCardPanel() {

    if (this.pnlBillCard == null) {

      // 加载模板
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
   * 方法功能描述：得到行VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-3 下午02:59:04
   */
  private OrderItemVO getBodyVO() {
    return this.voItem;
  }

  /**
   * 方法功能描述：得到显示时的容器
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午08:00:45
   */
  private Container getCont() {
    return this.cont;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:44:44
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
   * 方法功能描述：得到到货计划VO
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午06:44:48
   */
  private OrderReceivePlanVO getRPVO() {
    return this.voRP;
  }

  /**
   * 方法功能描述：返回界面的标题
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午08:01:52
   */
  private String getTitle() {
    return this.getBillCardPanel().getTitle();
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午08:02:40
   */
  private UIButton[] getUIButtons() {

    return new UIButton[] {
      this.btnSave, this.btnCancel
    };
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:10:30
   */
  private void initi() {
    // 加载卡片
    this.setLayout(new java.awt.BorderLayout());
    this.add(this.getBillCardPanel(), java.awt.BorderLayout.CENTER);
    this.initScale();

    // 显示
    this.displayVOsToPanel();

    // 按钮监听
    this.initiButtonListener();

    // 编辑
    this.getBillCardPanel().setEnabled(true);
  }

  /**
   * 方法功能描述：增加按钮监听
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:35:47
   */
  private void initiButtonListener() {
    this.btnCancel.addActionListener(this);
    this.btnSave.addActionListener(this);
  }

  /**
   * 方法功能描述：初始化订单卡片
   * <p>
   * <b>参数说明</b>
   * 
   * @param bd <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:12:53
   */
  @SuppressWarnings("deprecation")
  private void initiPnl(BillData bd) {

    this.pnlBillCard.setBillData(bd);

    this.pnlBillCard.getBodyPanel().addTableSortListener();
    this.pnlBillCard.getBillModel().setRowSort(true);
    // 是否显示合计行
    this.pnlBillCard.setTatolRowShow(true);

    // 体菜单不可见
    this.pnlBillCard.setBodyMenuShow(false);

    // 监听
    this.pnlBillCard.addEditListener(this);

    // 设置界面可编辑性
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
   * 方法功能描述：取消按钮相应函数
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:45:20
   */
  private void onCancel() {
    this.voResultRPAllot = null;
    this.getDlg().closeCancel();
  }

  /**
   * 方法功能描述：保存按钮相应函数
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午07:47:39
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
   * 方法功能描述：设置行VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param voItem <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午06:42:42
   */
  private void setBodyVO(OrderItemVO voItem) {
    this.voItem = voItem;
  }

  /**
   * 方法功能描述：设置显示时的容器
   * <p>
   * <b>参数说明</b>
   * 
   * @param cont <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午08:05:17
   */
  private void setContainer(Container cont) {
    this.cont = cont;
  }

  /**
   * 方法功能描述：设置到货计划VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRP <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-21 下午06:43:23
   */
  private void setRPVO(OrderReceivePlanVO voRP) {
    this.voRP = voRP;
  }

}
