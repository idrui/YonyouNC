/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 上午08:30:51
 */
package nc.ui.pu.m21.action;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pflow.PFReturnObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>弃审按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-15 上午08:30:51
 */
public class OrderUnApproveAction extends UNApproveScriptAction {

  private static final long serialVersionUID = 6758518838761391849L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.model.getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    boolean isEnabled = false;
    if (this.model.getSelectedData() != null) {
      isEnabled = this.model.getAppUiState() == AppUiState.NOT_EDIT;
    }

    return isEnabled
        && ApproveFlowUtil.isCanUnApprove((AbstractBill) this.model
            .getSelectedData());
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {
    Object[] retObj = pretObj;
    if (retObj == null || retObj.length == 0) {
      if (PfUtilClient.isSuccess()) {
        this.model.setAppUiState(AppUiState.NOT_EDIT);
      }
      return;
    }
    if (pretObj instanceof PFReturnObject[]) {
      retObj = ((PFReturnObject) pretObj[0]).getBills();
    }
    if (PfUtilClient.isSuccess() && retObj[0] instanceof AggregatedValueObject) {
      OrderVO[] retVOs = (OrderVO[]) retObj;
      for (OrderVO vo : retVOs) {
        vo.setChildren(PayPlanVO.class, null);
      }
      new ClientBillCombinServer<IBill>().combine((IBill[]) this
          .getFullOldVOs(), (IBill[]) retObj);
      OrderVO[] vos = (OrderVO[]) this.getFullOldVOs();
      for (OrderVO vo : vos) {
        vo.setChildren(PayPlanVO.class, null);
      }
      this.model.directlyUpdate(vos);
      this.model.setAppUiState(AppUiState.NOT_EDIT);

    }
    if (this.getMultibillScriptRunner().isTaskSuccessful()) {
      this.showSuccessInfo();
    }
    if (!this.getMultibillScriptRunner().isTaskSuccessful()
        && retObj.length > 1) {
      this.showFailedInfo();
    }

  }
}
