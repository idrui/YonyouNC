package nc.ui.pu.uif2;

import nc.ui.pubapp.uif2app.model.BillManageModel;

/**
 * �ɹ���BillManageModel
 * 
 * @since 6.0
 * @version 2011-12-1 ����11:16:27
 * @author zhaoyha
 */
public class PUBillManageModel extends BillManageModel {

  /** �ɹ����ǰ̨״̬ **/
  private PUUIState puUIState = PUUIState.NOT_EDIT;

  /** �ɹ����ǰ̨״̬ **/
  public PUUIState getPuUIState() {
    return this.puUIState;
  }

  /** �ɹ����ǰ̨״̬ **/
  public void setPuUIState(PUUIState puUIState) {
    this.puUIState = puUIState;
    this.setAppUiState(puUIState.getAppUiState());
  }

}
