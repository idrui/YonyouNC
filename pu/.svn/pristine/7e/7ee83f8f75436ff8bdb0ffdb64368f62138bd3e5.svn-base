package nc.ui.pu.m27.match.model;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.uif2.UIState;

/**
 * 结算的界面状态（费用分摊等）
 * 
 * @since 6.0
 * @version 2011-1-27 下午05:26:10
 * @author zhaoyha
 */
public class SettleUIState extends AppUiState {
  /** 匹配界面－费用(异物料发票)分摊状态 **/
  public static enum DistState {
    /** 已经做过分摊 **/
    dist,
    /** 未做过分摊 **/
    not_dist;
  }

  private DistState fdstate;

  private DistState idstate;

  /**
   * @param fdstate 费用分摊状态
   * @param idstate 发票分分摊状态（异物料结算）
   */
  public SettleUIState(DistState fdstate, DistState idstate) {
    super(UIState.NOT_EDIT);
    this.fdstate = fdstate;
    this.idstate = idstate;
  }

  /**
   * 是否进行过费用分摊
   * 
   * @return
   */
  public DistState getFdstate() {
    return this.fdstate;
  }

  /**
   * 是否进行过发票分摊
   * 
   * @return
   */
  public DistState getIdstate() {
    return this.idstate;
  }

  public void setFdstate(DistState fdstate) {
    this.fdstate = fdstate;
  }

  public void setIdstate(DistState idstate) {
    this.idstate = idstate;
  }

}
