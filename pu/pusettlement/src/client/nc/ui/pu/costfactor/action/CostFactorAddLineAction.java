/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-18 下午02:52:55
 */
package nc.ui.pu.costfactor.action;

import nc.ui.pubapp.uif2app.actions.billlist.AddLineAction;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-18 下午02:52:55
 */
public class CostFactorAddLineAction extends AddLineAction {

  /**
     *
     */
  private static final long serialVersionUID = -897123544486351409L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.billlist.AddLineAction#onAddLine()
   */
  @Override
  protected void onAddLine() {
    int iLastRow =
        this.getListEditor().getBillListPanel().getBodyBillModel()
            .getRowCount() - 1;
    if (iLastRow >= 0) {
      // 费用存货
      Object feeId =
          this.getListEditor().getBillListPanel().getBodyBillModel()
              .getValueAt(iLastRow, "pk_material");
      if (feeId == null || feeId.toString().trim().length() == 0) {
        return;
      }
    }

    if (this.getModel().getSelectedRow() < 0
        && UIState.EDIT == this.getModel().getUiState()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0000")/*
                                                                   * @res
                                                                   * "没有选中成本要素行！"
                                                                   */);
    }
    this.getListEditor().getBillListPanel().getBodyBillModel().addLine();
  }

}
