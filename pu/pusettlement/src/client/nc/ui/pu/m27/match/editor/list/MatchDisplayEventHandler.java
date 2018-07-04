package nc.ui.pu.m27.match.editor.list;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m27.match.editor.event.MatchDisplayEvent;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.model.SettleUIState;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.util.CostfactorDynamicDisplayUtil;
import nc.ui.pu.m27.match.view.MatchProcessListPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchBusiSystem;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>匹配界面显示处理，会影响结算专门事件和UI状态变化事件（处理可编辑性）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 上午09:59:56
 */
public class MatchDisplayEventHandler implements IAppEventHandler<AppEvent> {
  private MatchProcessListPanel listView;

  public MatchProcessListPanel getListView() {
    return this.listView;
  }

  @Override
  public void handleAppEvent(AppEvent e) {
    if (e instanceof MatchDisplayEvent) {
      // 第一次显示的处理
      this.displayHandle();
    }
    else if (e instanceof AppUiStateChangeEvent) {
      // 点击费用分摊等按钮刷新
      this.refreshHandle();
    }
  }

  public void setListView(MatchProcessListPanel listView) {
    this.listView = listView;
  }

  private void displayHandle() {
    MatchManageModel model = this.getMatchManageModel();

    BillListPanel panel = this.getListView().getBillListPanel();
    // 动态显示成本要素信息
    CostfactorDynamicDisplayUtil.initCostfactorDisplay(model
        .getSettleEnvironment().getCostFactorVOs(), panel.getParentListPanel(),
        MatchMaterialVO.NCOSTFACTOR1.substring(0, 11));

    // 1、设置入库单、货物发票
    panel.getHeadBillModel().setBodyDataVO(model.getMatchMaterialVOs());

    // 2、费用、折扣类发票
    FeeDiscountSettleVO[] fees = model.getMatchFeeInvoices();
    FeeDiscountSettleVO[] discounts = model.getMatchDiscountInvoices();
    List<FeeDiscountSettleVO> invoices = new ArrayList<FeeDiscountSettleVO>();
    if (fees != null) {
      for (FeeDiscountSettleVO fee : fees) {
        invoices.add(fee);
      }
    }
    if (discounts != null) {
      for (FeeDiscountSettleVO discount : discounts) {
        invoices.add(discount);
      }
    }
    FeeDiscountSettleVO[] totals = invoices.toArray(new FeeDiscountSettleVO[0]);
    panel.getBodyBillModel().setBodyDataVO(totals);

    // 执行表头表体显示公式
    panel.getHeadBillModel().execLoadFormula();
    panel.getBodyBillModel().execLoadFormula();

    // 对界面上的匹配数据再进行处理
    this.processDispMatchData();

    // 设置可编辑性
    this.handleHeadItemsEditable();

    // 显示当前视图
    this.listView.showMeUp();
  }

  private MatchManageModel getMatchManageModel() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();
    return model;
  }

  private int getRowCount() {
    int rows =
        this.getListView().getBillListPanel().getHeadBillModel().getRowCount();
    return rows;
  }

  private EnumSettleType getSettletype() {
    MatchManageModel model = this.getMatchManageModel();
    EnumSettleType st = model.getSettleEnvironment().getSettleType();
    return st;
  }

  /**
   * 字段可编辑性处理
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author tianft
   * @time 2010-1-25 下午08:14:22
   */
  private void handleHeadItemsEditable() {
    int rows = this.getRowCount();

    for (int i = 0; i < rows; i++) {
      // 先都设置成不可编辑
      this.handleNotEditable(i);
      // 发票行的可编辑性
      this.handleInvoiceEditable(i);
      // 入库单行的可编辑性
      this.handleStockEditable(i);
    }
  }

  private void handleInvoiceEditable(int row) {
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    boolean isInovice =
        ValueUtils.getBoolean(bm.getValueAt(row, MatchMaterialVO.BINVOICE));
    if (isInovice) {
      // 本次发票结算数量
      bm.setCellEditable(row, MatchMaterialVO.NCURINVOICESETTLENUM, true);
      MatchBusiSystem busisys =
          SettleEnvironment.getMatchBusiSystem(this.getMatchManageModel()
              .getSelectedStockSettleVOs());
      UFDouble canStlMny =
          (UFDouble) bm.getValueAt(row, MatchMaterialVO.NCANSETTLEMNY);
      if (EnumSettleType.SAME_MATERIAL == this.getSettletype()
          && MatchBusiSystem.po == busisys
          && MathTool.greaterThan(canStlMny, UFDouble.ZERO_DBL)) {
        // 合理损耗量,必须是同物料才能编辑，
        // 消耗汇总结算也不支持编辑合理损耗（根据需求wangyf的意见，先遵循V5X的功能）
        // 另外，负数发票V5X实现的逻辑其它也不支持输入合理损耗
        bm.setCellEditable(row, MatchMaterialVO.NREASONWASTENUM, true);
      }
    }
  }

  private void handleNotEditable(int row) {
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    // 本次发票结算数量
    bm.setCellEditable(row, MatchMaterialVO.NCURINVOICESETTLENUM, false);
    // 本次发票结算金额
    bm.setCellEditable(row, MatchMaterialVO.NCURINVOICESETTLEMNY, false);
    // 合理损耗量
    bm.setCellEditable(row, MatchMaterialVO.NREASONWASTENUM, false);
    // 本次入库结算数量
    bm.setCellEditable(row, MatchMaterialVO.NCURSTOCKSETTLENUM, false);
    // 单价
    bm.setCellEditable(row, MatchMaterialVO.NPRICE, false);
    // 本次结算金额
    bm.setCellEditable(row, MatchMaterialVO.NCURSEETLEMNY, false);
  }

  private void handleStockEditable(int row) {
    EnumSettleType st = this.getSettletype();
    if (EnumSettleType.FEE == st) {
      return;// 如果是费用结算，均不可编辑
    }
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    boolean isStock = this.isStockRow(row, bm);
    if (isStock) {
      // 本次入库结算数量
      bm.setCellEditable(row, MatchMaterialVO.NCURSTOCKSETTLENUM, true);

      // 单价、本次结算金额
      if (EnumSettleType.WITHOUT_INVOICE == st) {
        bm.setCellEditable(row, MatchMaterialVO.NPRICE, true);
        bm.setCellEditable(row, MatchMaterialVO.NCURSEETLEMNY, true);
      }
      // 异物料结算允许编辑入库单行的本次发票结算金额
      if (EnumSettleType.DIFFERENT_MATERIAL == st) {
        bm.setCellEditable(row, MatchMaterialVO.NCURINVOICESETTLEMNY, true);
      }
    }
  }

  private void handleStockEditAfterInvcDist(int row) {
    this.handleStockEditable(row);// 先用通用逻辑处理单元格编辑态
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    boolean isStock = this.isStockRow(row, bm);
    if (isStock) {
      // 本次入库结算数量
      bm.setCellEditable(row, MatchMaterialVO.NCURSTOCKSETTLENUM, false);
    }
  }

  private boolean isStockRow(int row, BillModel bm) {
    boolean isStock =
        ValueUtils.getBoolean(bm.getValueAt(row, MatchMaterialVO.BSTOCK));
    return isStock;
  }

  private void processDispMatchData() {
    int rows = this.getRowCount();
    for (int row = 0; row < rows; row++) {
      UFDouble canStlMny =
          (UFDouble) this.getListView().getBillListPanel().getHeadBillModel()
              .getValueAt(row, MatchMaterialVO.NCANSETTLEMNY);
      UFDouble reasonnum =
          (UFDouble) this.getListView().getBillListPanel().getHeadBillModel()
              .getValueAt(row, MatchMaterialVO.NREASONWASTENUM);
      // 退库单(负发票)不处理损耗；发票上的负合理损耗界面结算也不支持;只有同物料处理损耗
      if (MathTool.lessThan(canStlMny, UFDouble.ZERO_DBL)
          || MathTool.lessThan(reasonnum, UFDouble.ZERO_DBL)
          || EnumSettleType.SAME_MATERIAL != this.getSettletype()) {
        this.getListView().getBillListPanel().getHeadBillModel().setValueAt(
            null, row, MatchMaterialVO.NREASONWASTENUM);
      }
    }
  }

  private void refreshHandle() {
    EnumSettleType stype = this.getSettletype();
    SettleUIState uist = this.getMatchManageModel().getAppUiState();
    if (EnumSettleType.FEE == stype || DistState.dist != uist.getFdstate()
        && DistState.dist != uist.getIdstate()) {
      return;
    }
    int rows = this.getRowCount();
    for (int row = 0; row < rows; row++) {
      this.handleNotEditable(row);
      if (DistState.dist != uist.getFdstate()
          && DistState.dist == uist.getIdstate()) {
        this.handleStockEditAfterInvcDist(row);// 设置异物料结算的特殊编辑态
      }
    }
  }
}
