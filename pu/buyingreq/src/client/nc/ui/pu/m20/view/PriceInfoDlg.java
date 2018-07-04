package nc.ui.pu.m20.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import nc.ui.pu.m20.action.PraybillPricePrintAction;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pu.m20.entity.PrayPriceInfoVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.uif2.LoginContext;

/**
 * 请购单价格论证表对话框
 * 
 * @since 6.0
 * @version 2011-3-17 下午07:01:07
 * @author 田锋涛
 */
public class PriceInfoDlg extends UIDialog implements ActionListener {

  private static final long serialVersionUID = -5303340215631840315L;

  private UIButton btnClose = null;

  private UIButton btnPrint = null;

  private UIPanel buttonpanel;

  private LoginContext loginContext;

  private int priceDecimal = 2;

  private BillCardPanel priceInfoCardPanel = null;

  /* 其它变量定义 */
  private PrayPriceInfoVO[] priceInfoVos = null;

  public PriceInfoDlg(Container parent, PrayPriceInfoVO[] vos) {
    super(parent,true);
    this.setResizable(true);
    this.priceInfoVos = vos;
    this.initialize();
  }

  /**
   * CDlg1 构造子注解。
   * 
   * @param owner
   *          java.awt.Frame
   */
  public PriceInfoDlg(Container parent, PrayPriceInfoVO[] vos, int nPriceDigit) {
    super(parent,true);
    this.setResizable(true);
    this.priceInfoVos = vos;
    this.priceDecimal = nPriceDigit;
    this.initialize();
  }

  public PriceInfoDlg(Container parent, PrayPriceInfoVO[] vos, LoginContext ctx) {
    super(parent,true);
    this.setResizable(true);
    this.priceInfoVos = vos;
    this.initialize();
    this.loginContext = ctx;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(PriceInfoDlg.this.getBtnPrint())) {
      this.onPrint();
    }
    else if (e.getSource().equals(PriceInfoDlg.this.getBtnClose())) {
      this.onClose();
    }
  }

  public UIPanel getButtonPanel() {
    if (this.buttonpanel == null) {
      this.buttonpanel = new UIPanel();
      this.buttonpanel.add(this.getBtnPrint());
      this.buttonpanel.add(this.getBtnClose());
    }
    return this.buttonpanel;
  }

  public LoginContext getLoginContext() {
    return this.loginContext;
  }

  public int getPriceDecimal() {
    return this.priceDecimal;
  }

  public PrayPriceInfoVO[] getPriceInfoVos() {
    return this.priceInfoVos;
  }

  public void refreshData() {
    /* 数据 */
    this.getPriceCardPanel().getBillModel()
        .setBodyDataVO(this.getPriceInfoVos());
    this.getPriceCardPanel().getBillModel().loadLoadRelationItemValue();
    this.getPriceCardPanel().updateUI();
    /* 对话框刷新 */
    this.repaint();
  }

  /**
   * 设置价格的精度
   */
  private void setPriceScale() {
    BillScaleProcessor scale =
        new CardPaneScaleProcessor(AppContext.getInstance().getPkGroup(),
            this.getPriceCardPanel());
    // 价格
    String[] pricekeys =
        new String[] {
          PrayPriceInfoVO.NLATESTPRICE, PrayPriceInfoVO.NQUOTEPRICE1,
          PrayPriceInfoVO.NQUOTEPRICE2, PrayPriceInfoVO.NQUOTEPRICE3
        };
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        PrayPriceInfoVO.CCURRENCYID, PosEnum.body, null);
    scale.process();
  }

  public void setPriceDecimal(int priceDecimal) {
    this.priceDecimal = priceDecimal;
  }

  public void setPriceInfoVos(PrayPriceInfoVO[] priceInfoVos) {
    this.priceInfoVos = priceInfoVos;
  }

  private JButton getBtnClose() {
    if (this.btnClose == null) {
      this.btnClose = new UIButton();
      this.btnClose.setName("m_btnClose");
      this.btnClose.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004020_0", "04004020-0021")/* @res "关闭" */);
    }
    return this.btnClose;
  }

  private JButton getBtnPrint() {
    if (this.btnPrint == null) {
      this.btnPrint = new UIButton();
      this.btnPrint.setName("m_btnPrint");
      this.btnPrint.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("common", "UC001-0000007")/* @res "打印" */);
    }
    return this.btnPrint;
  }

  /**
   * 功能：根据当前界面存货取得价格论证卡片
   * 
   * @return nc.ui.pub.bill.BillCardPanel
   */
  private BillCardPanel getPriceCardPanel() {
    if (this.priceInfoCardPanel == null) {
      this.priceInfoCardPanel = new BillCardPanel();
      this.priceInfoCardPanel.loadTemplet("1003Z810000000006OR1");
    }
    return this.priceInfoCardPanel;
  }

  /**
   * 初始化对话框并显示数据
   */
  private void initialize() {
    /* 对话框 */
    this.setName("m_dlgPriceInfo");
    this.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004020_0", "04004020-0022")/* @res "价格论证表" */);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    this.setSize(900, 400);

    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(this.getPriceCardPanel(), BorderLayout.CENTER);
    this.getContentPane().add(this.getButtonPanel(), BorderLayout.SOUTH);

    /* 监听 */
    this.initListeners();

    // 设置价格精度
    this.setPriceScale();

    this.refreshData();
  }

  /**
   * 初始化监听。
   */
  private void initListeners() {
    this.getBtnPrint().addActionListener(this);
    this.getBtnClose().addActionListener(this);
  }

  /**
   * 关闭
   */
  private void onClose() {
    this.dispose();
  }

  /**
   * 打印
   */
  private void onPrint() {
    PraybillPricePrintAction action =
        new PraybillPricePrintAction(this, this.getPriceInfoVos());
    try {
      action.doAction(null);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
