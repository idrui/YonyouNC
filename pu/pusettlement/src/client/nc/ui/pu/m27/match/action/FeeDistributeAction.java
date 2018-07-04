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
 * <b>���÷�̯��ť��������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ͬ���Ͻ��㡢�����Ͻ��㡢���ý�������ѡ���˷��÷�Ʊʱ�ð�ť���á�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-2 ����03:20:25
 */
public class FeeDistributeAction extends MatchDefaultAction {

  private static final long serialVersionUID = -3237862893852368503L;

  private MatchProcessListPanel listView;

  public FeeDistributeAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEDISTRIBUTE);
    // this.setBtnName("���÷�̯");
    // this.setCode("btnFeeDistribute");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.stopEditing();
    // ��ȡ��̯����
    UIFeeDistributeUtil disUtil = this.getDistributeUtil();

    // ��̯�����෢Ʊ,�ۿ��෢Ʊ
    disUtil.distribute();

    // ��̯���������ý���Ϊ�Ѿ���̯״̬
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
    // ֻ��ͬ�����(���ѷ�Ʊ��̯V60��wangyfȷ��)�����ý���ʱ�����Ѿ�ѡ���˷����ۿ۷�Ʊ����δ���й���̯ʱ����
    return (EnumSettleType.SAME_MATERIAL == st
        || EnumSettleType.DIFFERENT_MATERIAL == st
        && DistState.dist == state.getIdstate() || EnumSettleType.FEE == st)
        && DistState.dist != state.getFdstate()
        && (!ArrayUtils.isEmpty(dvos) || !ArrayUtils.isEmpty(fvos));
  }

  private UIFeeDistributeUtil getDistributeUtil() {
    MatchManageModel model = this.getModel();
    // ͬ����ǰModel�������ࡢ�ۿ��෢Ʊ�ĵ�ǰ������
    FeeDiscountSettleVO[] modelFees = model.getMatchFeeInvoices();
    FeeDiscountSettleVO[] modelDiscounts = model.getMatchDiscountInvoices();
    FeeDiscountSettleVO[] uiInvoices =
        (FeeDiscountSettleVO[]) this.getListView().getBillListPanel()
            .getBodyBillModel().getBodyValueVOs(
                FeeDiscountSettleVO.class.getName());
    InvoiceSettleVOUtil.synchFeeAndDiscounts(uiInvoices, modelFees,
        modelDiscounts);
    if (this.isNeedMockMatch()) {
      // ��Ҫ��ģ�����,���̯
      return this.getDistUtilAfterMockMatch();
    }
    // ����Ҫģ��,ֱ��ʹ�÷�̯���߽��з�̯
    return new UIFeeDistributeUtil(model, this.getListView().getBillListPanel());
  }

  private UIFeeDistributeUtil getDistUtilAfterMockMatch() {
    MatchExecutor mexec =
        new MatchExecutor(this.getModel(), this.getListView(),
            MatchExecType.mock);
    // �Ƚ���ģ�����
    SettleBillVO[] sbVos = mexec.executeMatch();
    // ����ģ�����Ľ������ǰ̨�Ŀ�����VO����
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
    // �����ý��㲻��ģ�����
    if (EnumSettleType.FEE.equals(st)) {
      return false;
    }
    // ������ⵥ�о��ݹ���ȷ�Ϲ�Ҳ����ģ�����
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
