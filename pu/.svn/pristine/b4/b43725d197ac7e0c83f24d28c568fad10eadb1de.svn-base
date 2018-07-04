/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-13 上午09:41:22
 */
package nc.ui.pu.est.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.Action;
import javax.swing.BorderFactory;

import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.rule.feediv.AbstractUIFeeDivide;
import nc.ui.pu.est.util.EstScaleUtil;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.dialog.StandardUIDialog;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.TemplateContainer;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.rule.FeeEstVatValue;
import nc.vo.pu.est.util.EstVODefualtValueUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.util.AggVOHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估费用分摊对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-13 上午09:41:22
 */
@SuppressWarnings("serial")
public class EstFeeDistUIDialog extends StandardUIDialog implements ActionListener {
  private BillCardPanel bcp;

  private EstEditor estEditor;
  
  /** 刷新按钮, 用来刷新费用项 */
  private UIButton btnRefresh;

  private AbstractUIFeeDivide feeDivider;

  /**
   * EstFeeDistUIDialog 的构造子
   * 
   * @param parent
   * @param title
   */
  public EstFeeDistUIDialog(EstEditor estEditor, String title) {
    super(estEditor, title);
    this.setReset(true);
    this.setResizable(true);
    this.estEditor = estEditor;
    this.initUI();
    this.bcp = this.createBillCardPanel();
    this.contentPanel.add(this.bcp, BorderLayout.CENTER);
    this.getEastBtnPanel().add(this.getBtnRefresh());
    this.initBillCardPanel(this.bcp);
    this.initScale();
    this.initDefaultEstFees();
    // 2012-06-28 tianft 根据需求，屏蔽批改填充属性
    BillCardPanelUtils cardUtils = new BillCardPanelUtils(this.bcp);
    cardUtils.disableItemsFill();
  }

  public UIButton getBtnRefresh() {
    if(this.btnRefresh == null){
      this.btnRefresh = new UIButton();
      this.btnRefresh.setName("btnRefresh");
      this.btnRefresh.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("common", "UC001-0000009")/* @res "刷新" */);
      this.btnRefresh.addActionListener(this);
    }
    return this.btnRefresh;
  }

  @Override
  public void receiveMsg(int msgID, String msg) {
    // 屏蔽父类的处理
  }

  /**
   * @param feeDivider 要设置的 feeDivider
   */
  public void setFeeDivider(AbstractUIFeeDivide feeDivider) {
    this.feeDivider = feeDivider;
  }

  @Override
  public void whenError(String message) {
    ShowStatusBarMsgUtil.showStatusBarMsg(message, this.estEditor.getModel()
        .getContext());
    ExceptionUtils.wrappBusinessException(message);
  }

  private List<Action> cloneEditorBodyActions() {
    List<Action> actions = this.estEditor.getBodyActions();
    List<Action> cactions = new ArrayList<Action>(actions.size());
    for (Action act : actions) {
      try {
        cactions.add(((AbstractBodyTableExtendAction) act).clone());
      }
      catch (CloneNotSupportedException e) {
        ExceptionUtils.wrappException(e);

      }
    }
    return cactions;
  }

  private BillCardPanel createBillCardPanel() {
    TemplateContainer tc = this.estEditor.getTemplateContainer();
    BillTempletVO tempvo =
        tc.getTemplate(this.estEditor.getNodekey(), this.estEditor.getPos(),
            this.estEditor.getTabCode());
    BillCardPanel bcp1 = new BillCardPanel();
    bcp1.setBillData(new BillData(tempvo));
    bcp1.setBorder(BorderFactory.createEtchedBorder());
    return bcp1;
  }

  private FeeEstVO[] getDefaultEstFees() {
    EstUIContext context =
        (EstUIContext) this.estEditor.getModel().getContext();
    CostfactorViewVO[] factors = context.getFactors();
    if (ArrayUtils.isEmpty(factors)) {
      return null;
    }
    Map<Integer, CostfactorViewVO> map =
        new TreeMap<Integer, CostfactorViewVO>();
    for (CostfactorViewVO factor : factors) {
      boolean isshow = ValueUtils.getBoolean(factor.getBshow());
      if (!isshow) {
        continue;
      }
      Integer showOrder = factor.getIshoworder();
      if (null == showOrder) {
        showOrder = Integer.valueOf(0);
      }
      map.put(showOrder, factor);
    }
    if (map.isEmpty()) {
      return null;
    }
    EstVO estVo = (EstVO) this.estEditor.getModel().getSelectedData();
    List<FeeEstVO> fees = new ArrayList<FeeEstVO>(map.size());
    String bodyClassName =
        this.bcp.getBillModel().getTabvo().getBillMetaDataBusinessEntity()
            .getFullClassName();
    for (Entry<Integer, CostfactorViewVO> entry : map.entrySet()) {
      try {
        FeeEstVO feevo =
            (FeeEstVO) Constructor.construct(Class.forName(bodyClassName));
        CostfactorViewVO factor = entry.getValue();
        EstVODefualtValueUtil.setFeeEstDefValue(feevo, estVo.getParentVO(),
            context.getPk_loginUser());
        feevo.setPk_srcfeematerial(factor.getPk_srcmaterial());
        feevo.setPk_feematerial(factor.getPk_material());
        fees.add(feevo);
      }
      catch (ClassNotFoundException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    EstVO aggvo = new EstVO();
    FeeEstVO[] feevos = new ListToArrayTool<FeeEstVO>().convertToArray(fees);
    aggvo.setChildrenVO(feevos);
    IKeyValue value = new AggVOHelper<EstVO>(aggvo);
    new FeeEstVatValue().setVatValue(new IKeyValue[] {
      value
    }, estVo.getParentVO().getPk_financeorg());
    return feevos;
  }

  private Object getVOs() {
    String bodyClassName =
        this.bcp.getBillModel().getTabvo().getBillMetaDataBusinessEntity()
            .getFullClassName();
    return this.bcp.getBillData().getBodyValueChangeVOs(bodyClassName);
  }

  private void initBillCardPanel(BillCardPanel initBcp) {
    List<Action> bodyAcitons = this.cloneEditorBodyActions();
    // 因为没有货物卡片，直接传null
    EstCardPanelInitializer cardInit =
        new EstCardPanelInitializer(initBcp, null, this.estEditor.getModel());
    cardInit.initCardEventTransfer();
    cardInit.initBodyLineActions(bodyAcitons);
    initBcp.setPosMaximized(IBillItem.BODY);
    initBcp.addNew();
    initBcp.setRowNOShow(true);
    initBcp.setEnabled(true);
    this.setBodyActionEdit(bodyAcitons);
  }

  private void initDefaultEstFees() {
    FeeEstVO[] fees = this.getDefaultEstFees();
    if (ArrayUtils.isEmpty(fees)) {
      return;
    }
    BillModel bm = this.bcp.getBillModel();
    bm.setBodyDataVO(fees);
    bm.loadLoadRelationItemValue();
    bm.execLoadFormula();
    bm.loadEditRelationItemValue(0, fees.length - 1, FeeEstVO.PK_FEEMATERIAL);
  }

  private void initScale() {
    EstScaleUtil util =
        new EstScaleUtil(this.bcp, EstVOUtil.getFeeEstScaleKeyInfo(),
            AppContext.getInstance().getPkGroup());
    util.initFeeScale();
  }

  private void initUI() {
    this.setSize(1000, 400);
    this.themePanel.setPreferredSize(new Dimension(45, 45));
    this.themePanel.setTheme(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
        .getStrByID("4004060_0", "04004060-0011")/* @res "请录入暂估的费用项及金额" */);
  }

  private void setBodyActionEdit(List<Action> bodyAcitons) {
    if (ListUtil.isEmpty(bodyAcitons)) {
      return;
    }
    for (Action act : bodyAcitons) {
      act.setEnabled(true);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pub.beans.dialog.StandardUIDialog#complete()
   */
  @Override
  protected void complete() throws Exception {
    try {
      // 非空检查
      this.bcp.dataNotNullValidate();
      // 使用分摊器进行分摊
      this.feeDivider.feeDiv(this.estEditor.getModel(), this.getVOs());
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0012")/*
                                                                   * @res
                                                                   * "费用分摊成功"
                                                                   */,
          this.estEditor.getModel().getContext());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == this.btnRefresh){
      EstUIContext context =
          (EstUIContext) this.estEditor.getModel().getContext();
      context.refreshFactors();
      this.initDefaultEstFees();
    }
  }
  
}
