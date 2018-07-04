package nc.ui.pu.m27.match.model;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.uif2.UIState;

/**
 * ����Ľ���״̬�����÷�̯�ȣ�
 * 
 * @since 6.0
 * @version 2011-1-27 ����05:26:10
 * @author zhaoyha
 */
public class SettleUIState extends AppUiState {
  /** ƥ����棭����(�����Ϸ�Ʊ)��̯״̬ **/
  public static enum DistState {
    /** �Ѿ�������̯ **/
    dist,
    /** δ������̯ **/
    not_dist;
  }

  private DistState fdstate;

  private DistState idstate;

  /**
   * @param fdstate ���÷�̯״̬
   * @param idstate ��Ʊ�ַ�̯״̬�������Ͻ��㣩
   */
  public SettleUIState(DistState fdstate, DistState idstate) {
    super(UIState.NOT_EDIT);
    this.fdstate = fdstate;
    this.idstate = idstate;
  }

  /**
   * �Ƿ���й����÷�̯
   * 
   * @return
   */
  public DistState getFdstate() {
    return this.fdstate;
  }

  /**
   * �Ƿ���й���Ʊ��̯
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
