package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import javax.swing.table.TableCellEditor;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.model.SettleUIState;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.util.UIFeeDistributeUtil;
import nc.ui.pu.m27.match.view.MatchProcessListPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchExecType;
import nc.vo.pu.m27.util.StockSettleInfoUpdate;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>费用分摊按钮，本类主要完成以下功能：</b>
 * <ul>
 * <li>点击同物料结算、异物料结算、费用结算后如果选择了费用发票时该按钮可用。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-2 下午03:20:25
 */
public class FeeDistributeAction extends MatchDefaultAction {

  private static final long serialVersionUID = -3237862893852368503L;

  private MatchProcessListPanel listView;

  public FeeDistributeAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEDISTRIBUTE);
    // this.setBtnName("费用分摊");
    // this.setCode("btnFeeDistribute");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.stopEditing();
    // 获取分摊工具
    UIFeeDistributeUtil disUtil = this.getDistributeUtil();

    // 分摊费用类发票,折扣类发票
    disUtil.distribute();

    // 分摊结束后设置界面为已经分摊状态
    this.getModel().setFeeDistUIState(DistState.dist);
  }

  public MatchProcessListPanel getListView() {
    return this.listView;
  }

  public void setListView(MatchProcessListPanel listView) {
    this.listView = listView;
  }

  @Override
  protected boolean isActionEnable() {
    EnumSettleType st = this.getModel().getSettleEnvironment().getSettleType();
    SettleUIState state = this.getModel().getAppUiState();
    FeeDiscountSettleVO[] dvos = this.getModel().getMatchDiscountInvoices();
    FeeDiscountSettleVO[] fvos = this.getModel().getMatchFeeInvoices();
    // 只有同物、异物(有已发票分摊V60与wangyf确认)、费用结算时，且已经选择了费用折扣发票，且未进行过分摊时可用
    return (EnumSettleType.SAME_MATERIAL == st
        || EnumSettleType.DIFFERENT_MATERIAL == st
        && DistState.dist == state.getIdstate() || EnumSettleType.FEE == st)
        && DistState.dist != state.getFdstate()
        && (!ArrayUtils.isEmpty(dvos) || !ArrayUtils.isEmpty(fvos));
  }

  private UIFeeDistributeUtil getDistributeUtil() {
    MatchManageModel model = this.getModel();
    // 同步当前Model中劳务类、折扣类发票的当前结算金额
    FeeDiscountSettleVO[] modelFees = model.getMatchFeeInvoices();
    FeeDiscountSettleVO[] modelDiscounts = model.getMatchDiscountInvoices();
    FeeDiscountSettleVO[] uiInvoices =
        (FeeDiscountSettleVO[]) this.getListView().getBillListPanel()
            .getBodyBillModel().getBodyValueVOs(
                FeeDiscountSettleVO.class.getName());
    InvoiceSettleVOUtil.synchFeeAndDiscounts(uiInvoices, modelFees,
        modelDiscounts);
    if (this.isNeedMockMatch()) {
      // 需要先模拟结算,则分摊
      return this.getDistUtilAfterMockMatch();
    }
    // 不需要模拟,直接使用分摊工具进行分摊
    return new UIFeeDistributeUtil(model, this.getListView().getBillListPanel());
  }

  private UIFeeDistributeUtil getDistUtilAfterMockMatch() {
    MatchExecutor mexec =
        new MatchExecutor(this.getModel(), this.getListView(),
            MatchExecType.mock);
    // 先进行模拟结算
    SettleBillVO[] sbVos = mexec.executeMatch();
    // 根据模拟结算的结算更新前台的库存结算VO数据
    StockSettleInfoUpdate ssUpdate =
        new StockSettleInfoUpdate(this.getModel().getMatchMaterialVOs());
    ssUpdate.process(sbVos);
    return new UIFeeDistributeUtil(this.getModel().getMatchFeeInvoices(), this
        .getModel().getMatchDiscountInvoices(), ssUpdate.getUpdatedMMVO(), this
        .getListView().getBillListPanel(), this.getModel()
        .getSettleEnvironment());
  }

  private boolean isNeedMockMatch() {
    EnumSettleType st = this.getModel().getSettleEnvironment().getSettleType();
    // 纯费用结算不用模拟结算
    if (EnumSettleType.FEE.equals(st)) {
      return false;
    }
    // 所有入库单行均暂估或确认过也不用模拟结算
    for (MatchMaterialVO mmVo : this.getModel().getMatchMaterialVOs()) {
      if (UFBoolean.TRUE.equals(mmVo.getBstock())
          && !EnumToIAFlag.ConfirmToIA.toInteger().equals(
              mmVo.getStockSettleVO().getFdirtoiatype())
          && MathTool.isZero(mmVo.getStockSettleVO().getNestnum())) {
        return true;
      }
    }
    return false;
  }

  private void stopEditing() {
    TableCellEditor tce =
        this.getListView().getBillListPanel().getBodyTable().getCellEditor();
    if (null != tce) {
      tce.stopCellEditing();
    }
  }
}
