/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 上午09:00:49
 */
package nc.ui.pu.m21.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderLinkBillQuery;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.IinitData2;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pu.m21.entity.AvgSaleQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销量查询界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-23 上午09:00:49
 */
public class AvgSaleQueryUI extends UIDialog implements ActionListener,
    IinitData2 {

  private static final long serialVersionUID = -3317610130011803435L;

  private BillCardPanel ivjBillCardPanel;

  private UIButton ivjbtnRefresh;

  private UILabel ivjlblLeft;

  private UILabel ivjlblRight;

  private UIPanel ivjTopPanel;

  private UITextField ivjtxtDay;

  private UIPanel ivjUIPanel;

  public AvgSaleQueryUI(Container parent) {
    super(parent);
    this.initialize();
  }

  
  public AvgSaleQueryUI(Container parent,boolean reset){
	  super(parent,reset);
	  this.initialize();
  }
  public AvgSaleQueryUI(Container parent, String title) {
    super(parent, title);
    this.initialize();
  }

  public AvgSaleQueryUI(Frame owner) {
    super(owner);
    this.initialize();
  }

  public AvgSaleQueryUI(Frame owner, String title) {
    super(owner, title);
    this.initialize();
  }

  /**
   * 父类方法重写
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.ivjbtnRefresh) {
      this.btnRefresh_ActionPerformed();
    }
  }

  public void btnRefresh_ActionPerformed() {
    Integer iDay = new Integer(this.gettxtDay().getText().toString());

    AvgSaleQueryVO[] vos =
        (AvgSaleQueryVO[]) this.ivjBillCardPanel.getBillModel()
            .getBodyValueVOs("nc.vo.pu.m21.entity.AvgSaleQueryVO");
    AvgSaleQueryVO[] results = null;
    UFDate dquerydate = AppContext.getInstance().getBusiDate();
    for (int i = 0; i < vos.length; i++) {
      vos[i].setIqueryday(iDay);
      vos[i].setDquerydate(dquerydate);
    }
    try {
      results =
          NCLocator.getInstance().lookup(IOrderLinkBillQuery.class)
              .querySaleData(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    this.ivjBillCardPanel.getBillData().setBodyValueVO(results);
    this.ivjBillCardPanel.getBillModel().loadLoadRelationItemValue();
  }

  /**
   * @return ivjtxtDay
   */
  public UITextField getIvjtxtDay() {
    return this.ivjtxtDay;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.pf.IinitData2#initData(java.lang.Object)
   */
  @Override
  public void initData(Object userObj) {
    AvgSaleQueryVO[] vos = (AvgSaleQueryVO[]) userObj;
    try {
      for (int i = 0; i < vos.length; ++i) {
        vos[i].setIqueryday(new Integer(this.gettxtDay().getText().trim()));
        vos[i].validate();
      }
      AvgSaleQueryVO[] results =
          NCLocator.getInstance().lookup(IOrderLinkBillQuery.class)
              .querySaleData(vos);

      this.ivjBillCardPanel.getBillData().setBodyValueVO(results);
      this.ivjBillCardPanel.getBillModel().loadLoadRelationItemValue();
    }
    catch (NumberFormatException e) {
      ExceptionUtils.wrappException(e);
    }
    catch (ValidationException e) {
      ExceptionUtils.wrappException(e);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * @param ivjtxtDay
   *          要设置的 ivjtxtDay
   */
  public void setIvjtxtDay(UITextField ivjtxtDay) {
    this.ivjtxtDay = ivjtxtDay;
  }

  public void setTxtDayText(Integer iday) {
    this.gettxtDay().setText(iday.toString());
  }

  private nc.ui.pub.beans.UIButton getbtnRefresh() {
    if (this.ivjbtnRefresh == null) {
      this.ivjbtnRefresh = new UIButton();
      this.ivjbtnRefresh.setName("btnRefresh");
      this.ivjbtnRefresh.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("common", "UC001-0000009")/* @res "刷新" */);
      this.ivjbtnRefresh.addActionListener(this);
    }
    return this.ivjbtnRefresh;
  }

  private BillCardPanel getIvjBillCardPanel() {
    if (this.ivjBillCardPanel == null) {
      this.ivjBillCardPanel = new BillCardPanel();
      // PUB_BILLTEMPLET NODECODE='40040422' pk_templet='1002Z810000000015SP3'
      this.ivjBillCardPanel.loadTemplet("1002Z810000000015SP3");
    }
    return this.ivjBillCardPanel;
  }

  private UILabel getlblLeft() {
    if (this.ivjlblLeft == null) {
      this.ivjlblLeft = new UILabel();
      this.ivjlblLeft.setName("lblLeft");
      this.ivjlblLeft.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0091")/* @res "最近" */);
      this.ivjlblLeft.setHorizontalAlignment(SwingConstants.CENTER);
      this.ivjlblLeft
          .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
      this.ivjlblLeft.setPreferredSize(new Dimension(40, 20));
    }
    return this.ivjlblLeft;
  }

  private UILabel getlblRight() {
    if (this.ivjlblRight == null) {
      this.ivjlblRight = new UILabel();
      this.ivjlblRight.setName("lblRight");
      this.ivjlblRight.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0092")/* @res "天销量" */);
      this.ivjlblRight.setComponentOrientation(ComponentOrientation.UNKNOWN);
      this.ivjlblRight.setPreferredSize(new Dimension(100, 20));
    }
    return this.ivjlblRight;
  }

  private UIPanel getTopPanel() {
    if (this.ivjTopPanel == null) {
      this.ivjTopPanel = new UIPanel();
      this.ivjTopPanel.setName("TopPanel");
      this.ivjTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      this.ivjTopPanel.setComponentOrientation(ComponentOrientation.UNKNOWN);
      this.getTopPanel().add(this.getlblLeft(), this.getlblLeft().getName());
      this.getTopPanel().add(this.gettxtDay(), this.gettxtDay().getName());
      this.getTopPanel().add(this.getlblRight(), this.getlblRight().getName());
      this.getTopPanel().add(this.getbtnRefresh(), this.getbtnRefresh().getName());
    }
    return this.ivjTopPanel;
  }

  private UITextField gettxtDay() {
    if (this.ivjtxtDay == null) {
      this.ivjtxtDay = new UITextField();
      this.ivjtxtDay.setName("txtDay");
      // this.ivjtxtDay.setMaxValue(100);
      this.ivjtxtDay.setText("7");
      this.ivjtxtDay.setAllowOtherCharacter(false);
      this.ivjtxtDay.setAllowAlphabetic(false);

      // this.ivjtxtDay.setMinValue(0);
      this.ivjtxtDay.setMaxLength(20);
      this.ivjtxtDay.setPreferredSize(new Dimension(40, 20));
    }
    return this.ivjtxtDay;
  }

  private UIPanel getUIPanel() {
    if (this.ivjUIPanel == null) {
      this.ivjUIPanel = new UIPanel();
      this.ivjUIPanel.setName("UIPanel");
      this.ivjUIPanel.setLayout(new BorderLayout());
      this.ivjUIPanel.add(this.getTopPanel(), BorderLayout.NORTH);
      this.ivjUIPanel.add(this.getIvjBillCardPanel(), BorderLayout.CENTER);
    }
    return this.ivjUIPanel;
  }

  private void initialize() {
    this.setName("AvgSaleQueryUI");
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(800, 600);
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0093")/* @res "平均销售量查询" */);
    this.setContentPane(this.getUIPanel());
    this.setScale();
  }

  private void setScale() {
    String pk_group = AppContext.getInstance().getPkGroup();
    CardPaneScaleProcessor processer =
        new CardPaneScaleProcessor(pk_group, this.getIvjBillCardPanel());
    processer.setNumCtlInfo(new String[] {
      "ninvoicenum", "nordernum", "noutnum"
    }, PosEnum.body, null, "cunitid", PosEnum.body, null);
    processer.process();
  }
}
