/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午01:40:18
 */
package nc.ui.pu.m25.view;

import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.event.AppUiStateChangeEvent;
import nc.ui.pubapp.uif2app.view.BillOrgPanel;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票自己的组织面板
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-22 下午01:40:18
 */
public class InvoiceBillOrgPanel extends BillOrgPanel {

  private static final long serialVersionUID = -8365923866261308918L;

  private boolean feeShowing = false;

  private String orgFromNormalInvoice = null;

  /**
   * @return orgFromNormalInvoice
   */
  public String getOrgFromNormalInvoice() {
    return this.orgFromNormalInvoice;
  }

  /**
   * @return feeShowing
   */
  public boolean isFeeShowing() {
    return this.feeShowing;
  }

  /**
   * @param feeShowing
   *          要设置的 feeShowing
   */
  public void setFeeShowing(boolean feeShowing) {
    this.feeShowing = feeShowing;
  }

  /**
   * @param orgFromNormalInvoice
   *          要设置的 orgFromNormalInvoice
   */
  public void setOrgFromNormalInvoice(String orgFromNormalInvoice) {
    this.orgFromNormalInvoice = orgFromNormalInvoice;
  }

  @Override
  protected void handleUiStateChanged(AppUiStateChangeEvent event) {
    super.handleUiStateChanged(event);
    if (event.getNewAppUiState() == AppUiState.ADD) {
      try {
        // 新增费用发票时，设置来源主组织值
        if (this.isFeeShowing()) {
          this.setPkOrg(this.getOrgFromNormalInvoice());
        }
        //原super方法中已包含设置
        // 正常新增时，设置默认主组织值
//        else {
//          this.setPkOrg(OrgSettingAccessor.getDefaultOrgUnit());
//        }
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }

      // 对于单据型的节点，只有新增状态下才能编辑
      // 录入费用发票时不让编辑
      this.getRefPane().setEnabled(!this.feeShowing);
    }
    else {
      this.getRefPane().setEnabled(false);
    }
  }

}
