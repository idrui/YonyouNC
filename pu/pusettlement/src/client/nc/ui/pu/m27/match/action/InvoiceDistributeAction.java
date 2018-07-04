package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.model.SettleUIState;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.view.MatchProcessListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>异物料结算时的分摊按钮事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-21 下午01:11:45
 */
public abstract class InvoiceDistributeAction extends SCMTpaAction {
  private static final long serialVersionUID = -3536577370018306715L;

  private MatchProcessListPanel listView;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 发票结算金额合计
    UFDouble invoiceMny = this.sumInvoiceMny();
    // 入库单分摊依据合计
    UFDouble stockDistributedBy = this.sumStockDistributedBy();
    // 进行分摊
    this.distribute(invoiceMny, stockDistributedBy);
    // 设置为已经进行发票分摊态
    ((MatchManageModel) this.getListView().getModel())
        .setInvcDistUIState(DistState.dist);

  }

  public MatchProcessListPanel getListView() {
    return this.listView;
  }

  public void setListView(MatchProcessListPanel listView) {
    this.listView = listView;
    this.setContext(this.listView.getModel().getContext());
    this.listView.getModel().addAppEventListener(this);
  }

  private void distribute(UFDouble invoiceMny, UFDouble stockDistributedBy) {
    ScaleUtils su = new ScaleUtils(AppContext.getInstance().getPkGroup());
    ListPanelValueUtils lpvu =
        new ListPanelValueUtils(this.getListView().getBillListPanel());
    String pk_currency =
        lpvu.getHeadStringValueAt(0, MatchMaterialVO.CCURRENCYID);
    UFDouble sum = UFDouble.ZERO_DBL;
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    int stockcount = this.getStockCount();
    for (int i = 0; i < stockcount; i++) {
      // 如果是最后一行，则进行倒挤
      if (i == stockcount - 1) {
        UFDouble mny = MathTool.sub(invoiceMny, sum);
        bm.setValueAt(mny, i, MatchMaterialVO.NCURINVOICESETTLEMNY);
        bm.setValueAt(mny, i, MatchMaterialVO.NCURSEETLEMNY);
        break;
      }

      UFDouble stockBy = this.getDistributedStockBy(bm, i);
      UFDouble stockSettleMny =
          su.adjustMnyScale(invoiceMny.multiply(stockBy)
              .div(stockDistributedBy), pk_currency);
      bm.setValueAt(stockSettleMny, i, MatchMaterialVO.NCURINVOICESETTLEMNY);
      bm.setValueAt(stockSettleMny, i, MatchMaterialVO.NCURSEETLEMNY);
      sum = MathTool.add(sum, stockSettleMny);
    }
  }

  private int getStockCount() {
    int count = 0;
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    int rowcount = bm.getRowCount();
    for (int i = 0; i < rowcount; i++) {
      boolean stock =
          ValueUtils.getBoolean(bm.getValueAt(i, MatchMaterialVO.BSTOCK));
      if (stock) {
        count++;
      }
      else {
        break;
      }
    }
    return count;
  }

  private UFDouble sumInvoiceMny() {
    UFDouble sum = UFDouble.ZERO_DBL;
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    int rowcount = bm.getRowCount();
    for (int i = 0; i < rowcount; i++) {
      boolean invoice =
          ValueUtils.getBoolean(bm.getValueAt(i, MatchMaterialVO.BINVOICE));
      boolean fee =
          ValueUtils.getBoolean(bm.getValueAt(i, MatchMaterialVO.BFEE));
      if (invoice && !fee) {
        UFDouble invoiceMny =
            ValueUtils.getUFDouble(bm.getValueAt(i,
                MatchMaterialVO.NCURINVOICESETTLEMNY));
        sum = MathTool.add(sum, invoiceMny);
      }
    }
    return sum;
  }

  private UFDouble sumStockDistributedBy() {
    UFDouble sum = UFDouble.ZERO_DBL;
    BillModel bm = this.getListView().getBillListPanel().getHeadBillModel();
    int rowcount = bm.getRowCount();
    for (int i = 0; i < rowcount; i++) {
      boolean stock =
          ValueUtils.getBoolean(bm.getValueAt(i, MatchMaterialVO.BSTOCK));
      if (stock) {
        UFDouble stockDistributedBy = this.getDistributedStockBy(bm, i);
        sum = MathTool.add(sum, stockDistributedBy);
      }
      else {
        break;
      }
    }
    if (MathTool.compareTo(UFDouble.ZERO_DBL, sum) == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0017")/*
                                                                   * @res
                                                                   * "分摊依据的合计值为零，不能进行分摊！"
                                                                   */);
    }
    return sum;
  }

  /**
   * 方法功能描述：获得分摊依据的数值
   * 1、按数量分摊，则取入库结算数量
   * 2、按金额分摊，则取入库结算数量和单价算出的金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return 分摊依据的数值
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-21 下午01:15:56
   */
  protected abstract UFDouble getDistributedStockBy(BillModel bm, int i);

  @Override
  protected boolean isActionEnable() {
    MatchManageModel model = (MatchManageModel) this.getListView().getModel();
    // 如果是异物料结算，并且没有分摊过该按钮可用
    EnumSettleType type = model.getSettleEnvironment().getSettleType();
    SettleUIState state = model.getAppUiState();
    return EnumSettleType.DIFFERENT_MATERIAL == type
        && DistState.dist != state.getIdstate();
  }

}
