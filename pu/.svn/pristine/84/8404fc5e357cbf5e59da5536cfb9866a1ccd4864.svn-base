/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-12 下午04:12:32
 */
package nc.ui.pu.costfactor.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import nc.ui.pu.costfactor.view.util.ActionEventHandler;
import nc.ui.pu.costfactor.view.util.ChgListItemOrder;
import nc.ui.pu.costfactor.view.util.DataListViewer;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UICheckBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIList;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIScrollPane;
import nc.ui.pub.query.tools.ImageIconAccessor;
import nc.ui.pub.workflownote.VerticalFlowLayout;
import nc.ui.trade.component.DefaultItemChooserModel;
import nc.ui.trade.component.IItemChooserModel;
import nc.ui.trade.component.IListDataViewer;
import nc.ui.trade.component.IListDataViewerFactory;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对话框列表中的显示数据上移、下移、置顶、置底操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-12 下午04:12:32
 */
public class ListShowPanel extends UIDialog implements Observer {

  /**
   * <p>
   * <b>本类主要完成以下功能：</b>
   * <ul>
   * <li>findbugs 实现序列化
   * </ul>
   * <p>
   * <p>
   * 
   * @version 6.0
   * @since 6.0
   * @author tianft
   * @time 2010-8-13 下午01:21:52
   */
  private static class CostFactorListDataViewerFactory implements
      IListDataViewerFactory, Serializable {

    private static final long serialVersionUID = -761080955991074436L;

    public CostFactorListDataViewerFactory() {
      //
    }

    @Override
    public IListDataViewer createIListDataViwer() {
      return new DataListViewer();
    }
  }

  public static int VERTI_HEIGHT = 15; // 按钮竖直间距

  private static int BUTTON_PANEL_HEIGHT = 200;

  private static int BUTTON_PANEL_WIDHT = 55;

  private static int LIST_PANEL_HEIGHT = 300;

  private static int LIST_PANEL_WIDTH = 400;

  private static final long serialVersionUID = 2480563101493040282L;

  private UIButton bottomBt = null;

  private UIButton cancelBt = null;

  private ChgListItemOrder chgItemOrder = null;

  private UIButton downBt = null;

  private UICheckBox isShowChk = null;

  private List<CostfactorItemVO> listCostfactorItemVO =
      new ArrayList<CostfactorItemVO>();

  private List<String> listMaterialName = new ArrayList<String>();

  private UIPanel listPanel = null;

  private String listTitle = null;

  private transient IItemChooserModel model = null;

  private UIPanel movePane = null;

  private UIButton okBt = null;

  private UIPanel okPane = null;

  private IListDataViewer showList = null;

  private IListDataViewerFactory showListdataViewerFactory = null;

  private UIButton topBt = null;

  private UIButton upBt = null;

  // 初始化监听事件
  ActionEventHandler eventHandler = new ActionEventHandler(this);

  public ListShowPanel(List<CostfactorItemVO> listItems, List<String> listNames) {

    this(null, null, listItems, listNames, null);

  }

  public ListShowPanel(String listTitle, IItemChooserModel itemModel,
      List<CostfactorItemVO> listItems, List<String> listNames,
      IListDataViewerFactory lf) {
    super((Container) null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004060_0", "04004060-0006")/* @res "调整费用暂估界面费用项显示顺序" */);
    this.listTitle = listTitle;

    // 初始化远程调用
    if (lf != null) {
      this.showListdataViewerFactory = lf;
    }
    else {
      this.showListdataViewerFactory = new CostFactorListDataViewerFactory();
    }
    this.initLayout();

    // 设置列表值和物料名称
    this.listCostfactorItemVO = listItems;

    this.listMaterialName = listNames;

    this.chgItemOrder = new ChgListItemOrder(this.listCostfactorItemVO);

    if (this.listMaterialName != null) {
      this.getShowList().setListData(this.listMaterialName.toArray());
    }

    if (itemModel != null) {
      this.model = itemModel;
    }
    else {
      this.model =
          new DefaultItemChooserModel(null, this.listMaterialName.toArray());
    }
    this.model.addObserver(this);

    if (this.model instanceof Observable) {
      ((Observable) this.model).notifyObservers();
    }

    // 监听
    this.initListeners();
    this.setSize(ListShowPanel.LIST_PANEL_WIDTH,
        ListShowPanel.LIST_PANEL_HEIGHT);

  }

  // 置底
  public UIButton getBottomBt() {
    if (this.bottomBt == null) {
      this.bottomBt =
          new UIButton(ImageIconAccessor.getIcon("tableImages/Bottom.gif"));
      this.bottomBt.setPreferredSize(new Dimension(35, 25));
    }
    return this.bottomBt;
  }

  // 取消
  public UIButton getCancelBt() {
    if (this.cancelBt == null) {
      this.cancelBt =
          new UIButton(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "common", "UC001-0000008")/* @res "取消" */);
      this.cancelBt.setPreferredSize(new Dimension(65, 25));

    }
    return this.cancelBt;
  }

  public ChgListItemOrder getChgItemOrder() {
    if (this.chgItemOrder != null) {
      return this.chgItemOrder;
    }
    return new ChgListItemOrder(this.listCostfactorItemVO);
  }

  // 下移
  public UIButton getDownBt() {
    if (this.downBt == null) {
      this.downBt =
          new UIButton(ImageIconAccessor.getIcon("tableImages/Down.gif"));
      this.downBt.setPreferredSize(new Dimension(35, 25));

    }
    return this.downBt;
  }

  // 是否显示勾选框
  public UICheckBox getIsShowChk() {
    if (this.isShowChk == null) {
      this.isShowChk = new UICheckBox();

      // 设置为选中状态
      this.isShowChk.setPreferredSize(new Dimension(15, 25));
    }
    return this.isShowChk;
  }

  public IItemChooserModel getModel() {
    return this.model;
  }

  // 确定
  public UIButton getOkBt() {
    if (this.okBt == null) {
      this.okBt =
          new UIButton(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "common", "UC001-0000044")/* @res "确定" */);
      this.okBt.setPreferredSize(new Dimension(65, 25));

    }
    return this.okBt;
  }

  // 获取选择的成本要素VO 只能选择一行
  public CostfactorItemVO getSelectCostfactor(int index) {
    CostfactorItemVO costfacVO = null;

    if (index >= 0 && index < this.listCostfactorItemVO.size()) {
      costfacVO = this.listCostfactorItemVO.get(index);
    }
    return costfacVO;
  }

  // 显示物料名称
  public IListDataViewer getShowList() {
    if (this.showList == null) {
      this.showList = this.showListdataViewerFactory.createIListDataViwer();
    }
    return this.showList;
  }

  // 置顶按钮
  public UIButton getTopBt() {
    if (this.topBt == null) {
      this.topBt =
          new UIButton(ImageIconAccessor.getIcon("tableImages/Top.gif"));
      this.topBt.setPreferredSize(new Dimension(35, 25));
    }
    return this.topBt;
  }

  // 上移
  public UIButton getUpBt() {
    if (this.upBt == null) {
      this.upBt = new UIButton(ImageIconAccessor.getIcon("tableImages/Up.gif"));
      this.upBt.setPreferredSize(new Dimension(35, 25));
    }
    return this.upBt;
  }

  // 重置显示顺序
  public void setShowOrder() {
    int nOrder = 1;
    for (CostfactorItemVO vo : this.listCostfactorItemVO) {
      if (ValueUtils.getBoolean(vo.getBshow())) {
        vo.setIshoworder(Integer.valueOf(nOrder++));
      }
    }
  }

  /**
   * 父类方法重写
   * 
   * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
   */
  @Override
  public void update(Observable o, Object arg) {
    this.getShowList().setListData(this.model.getRightData());
  }

  // 将显示的物料名称置于此Panel内
  private UIPanel getListPanel() {
    if (this.listPanel == null) {
      this.listPanel = new UIPanel();
      this.listPanel.setLayout(new BoxLayout(this.listPanel, BoxLayout.Y_AXIS));
      if (this.listTitle == null) {
        this.listTitle = "";
      }
      UILabel label = new UILabel(this.listTitle);
      this.listPanel.add(label);
      UIScrollPane scrollPane =
          new UIScrollPane(this.getShowList().getViewComponent());
      scrollPane.setPreferredSize(new Dimension(ListShowPanel.LIST_PANEL_WIDTH,
          ListShowPanel.LIST_PANEL_HEIGHT));
      this.listPanel.add(scrollPane);
    }
    return this.listPanel;
  }

  // 将移动按钮置于panel上
  private UIPanel getMovePane() {
    if (this.movePane == null) {
      this.movePane = new UIPanel();
      VerticalFlowLayout vf = new VerticalFlowLayout();
      vf.setAlignment(VerticalFlowLayout.TOP);
      vf.setHorizontalFill(false);
      vf.setVgap(ListShowPanel.VERTI_HEIGHT);
      this.movePane.setLayout(vf);
      this.movePane.add(this.getTopBt());
      this.movePane.add(this.getUpBt());
      this.movePane.add(this.getDownBt());
      this.movePane.add(this.getBottomBt());
      UIPanel chkPane = new UIPanel();
      FlowLayout fl = new FlowLayout();
      chkPane.setLayout(fl);
      JLabel showLabel =
          new JLabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004060_0", "04004060-0007")/* @res "显示" */);
      chkPane.add(showLabel);
      chkPane.add(this.getIsShowChk());
      this.movePane.add(chkPane);
      this.movePane.setPreferredSize(new Dimension(
          ListShowPanel.BUTTON_PANEL_WIDHT, ListShowPanel.BUTTON_PANEL_HEIGHT));
    }
    return this.movePane;
  }

  // 将确定、取消按钮置于panel上
  private UIPanel getOkPane() {
    if (this.okPane == null) {
      this.okPane = new UIPanel();
      FlowLayout fl = new FlowLayout();
      fl.setAlignment(FlowLayout.CENTER);
      fl.setHgap(20);
      this.okPane.add(this.getOkBt());
      this.okPane.add(this.getCancelBt());
    }
    return this.okPane;
  }

  // 初始化界面
  private void initLayout() {
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints constrains = new GridBagConstraints();
    constrains.fill = GridBagConstraints.BOTH;
    constrains.anchor = GridBagConstraints.CENTER;
    constrains.gridx = 0;
    constrains.gridy = 0;
    constrains.gridwidth = 1;
    constrains.gridheight = 1;
    constrains.weightx = 0.4;
    constrains.weighty = 1;
    this.setLayout(layout);
    this.add(this.getListPanel(), constrains);
    constrains.weightx = 0;
    constrains.gridx = 1;
    constrains.weightx = 0;
    constrains.weighty = 1;
    constrains.fill = GridBagConstraints.BOTH;
    this.add(this.getMovePane(), constrains);
    constrains.gridx = 0;
    constrains.gridy = 1;
    constrains.gridwidth = 2;
    constrains.gridheight = 1;
    this.add(this.getOkPane(), constrains);
  }

  private void initListeners() {
    this.getUpBt().addActionListener(this.eventHandler);
    this.getDownBt().addActionListener(this.eventHandler);
    this.getTopBt().addActionListener(this.eventHandler);
    this.getBottomBt().addActionListener(this.eventHandler);
    this.getOkBt().addActionListener(this.eventHandler);
    this.getCancelBt().addActionListener(this.eventHandler);
    this.getIsShowChk().addActionListener(this.eventHandler);
    // 列表事件
    ((UIList) this.getShowList()).addListSelectionListener(this.eventHandler);
  }
}
