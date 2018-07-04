/**
 * $构造"结算"按钮$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m27.match.model.SettleUIState;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.view.MatchProcessListPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchExecType;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"结算"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class MatchAction extends MatchDefaultAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private MatchProcessListPanel listView;

  /**
   * InvoiceAction 的构造子
   */
  public MatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_MATCH);
    // this.setBtnName("结算");
    // this.setCode("btnMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 使用结算执行器，进行真正的结算
    MatchExecutor me =
        new MatchExecutor(this.getModel(), this.getListView(),
            MatchExecType.real);
    SettleBillVO[] vos = me.executeMatch();
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.getModel().initSettleBillVOs(vos);
    // 结算成功设置UI为可费用（发票）分摊状态
    this.getModel().setFeeDistUIState(DistState.not_dist);
    this.getModel().setInvcDistUIState(DistState.not_dist);
  }

  public MatchProcessListPanel getListView() {
    return this.listView;
  }

  public void setListView(MatchProcessListPanel listView) {
    this.listView = listView;
  }

  @Override
  protected boolean isActionEnable() {
    boolean flag = super.isActionEnable();
    EnumSettleType st = this.getModel().getSettleEnvironment().getSettleType();
    SettleUIState state = this.getModel().getAppUiState();
    // 异物料结算进行费用分摊或者发票分摊按钮操作后，该结算按钮才可用
    // 费用结算进行费用分摊按钮操作后，该结算按钮才可用
    return flag
        && (EnumSettleType.DIFFERENT_MATERIAL == st
            && (DistState.dist == state.getIdstate() || DistState.dist == state
                .getFdstate()) || EnumSettleType.FEE == st
            && DistState.dist == state.getFdstate()
            || EnumSettleType.SAME_MATERIAL == st || EnumSettleType.WITHOUT_INVOICE == st);
  }

}
