package nc.ui.pu.uif2;

import nc.ui.pubapp.uif2app.model.BillManageModel;

/**
 * 采购组BillManageModel
 * 
 * @since 6.0
 * @version 2011-12-1 上午11:16:27
 * @author zhaoyha
 */
public class PUBillManageModel extends BillManageModel {

  /** 采购组的前台状态 **/
  private PUUIState puUIState = PUUIState.NOT_EDIT;

  /** 采购组的前台状态 **/
  public PUUIState getPuUIState() {
    return this.puUIState;
  }

  /** 采购组的前台状态 **/
  public void setPuUIState(PUUIState puUIState) {
    this.puUIState = puUIState;
    this.setAppUiState(puUIState.getAppUiState());
  }

}
