/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-7 下午03:05:54
 */
package nc.ui.pu.position.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.model.BillListManageModel;
import nc.ui.pubapp.uif2app.view.OrgPanel;
import nc.ui.uif2.UIState;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-7 下午03:05:54
 */
public class PositionDelAction extends
    nc.ui.pubapp.uif2app.actions.DeleteAction {

  private static final long serialVersionUID = 2853366828271396751L;

  private OrgPanel refpanel;

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getUiState() == UIState.NOT_EDIT
        && ((BillListManageModel) this.getModel()).getSelectedOperaDatas() != null;
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (null == this.getModel().getContext().getPk_org()) {
      String err =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004080_0",
              "04004080-0000")/* @res "请选择主组织，否则无法进行此操作！" */;
      this.getRefpanel().requestFocus();
      ExceptionUtils.wrappBusinessException(err);
    }
    super.doAction(e);
  }

  public OrgPanel getRefpanel() {
    return this.refpanel;
  }

  public void setRefpanel(OrgPanel refpanel) {
    this.refpanel = refpanel;
  }

}
