/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-5 下午05:03:00
 */
package nc.ui.pu.m25.action;

import javax.swing.Action;

import nc.funcnode.ui.action.INCAction;
import nc.funcnode.ui.action.MenuAction;
import nc.ui.uif2.model.AbstractUIAppModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参照增行按钮
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-5 下午05:03:00
 */
public class RefAddRowsMenuAction extends MenuAction {
  /**
   *
   */
  private static final long serialVersionUID = -4949855132602349481L;

  private AbstractUIAppModel model;

  public RefAddRowsMenuAction() {
    super();
    this.putValue(Action.NAME, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0","04004050-0015")/*@res "参照增行"*/);
    this.putValue(INCAction.CODE, "RefAddRows");
  }

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  @Override
  public boolean isEnabled() {
    // if (this.getModel().getSelectedData() == null) {
    // return false;
    // }
    // AbstractBill aggVO = (AbstractBill) this.getModel().getSelectedData();
    // InvoiceItemVO[] itemVOs = (InvoiceItemVO[]) aggVO.getChildrenVO();
    // if (ArrayUtils.isEmpty(itemVOs)) {
    // return false;
    // }
    //
    // if (POBillType.Order.getCode().equals(itemVOs[0].getCsourcetypecode())
    // || ICBillType.PurchaseIn.getCode().equals(
    // itemVOs[0].getCsourcetypecode())) {
    // return this.getModel().getUiState() == UIState.EDIT;
    // }

    return true;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

}