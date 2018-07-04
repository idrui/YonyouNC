/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午10:39:46
 */
package nc.ui.pu.position.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.position.view.PositionListEditor;
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
 * @time 2010-7-1 上午10:39:46
 */
public class PositionEditAction extends
    nc.ui.pubapp.uif2app.actions.billlist.EditAction {

  private static final long serialVersionUID = 1777707264468935852L;

  private PositionListEditor editor;

  private OrgPanel refpanel;

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (null == this.getModel().getContext().getPk_org()) {
      String err =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004080_0",
              "04004080-0000")/* @res "请选择主组织，否则无法进行此操作！" */;
      this.getRefpanel().requestFocus();
      ExceptionUtils.wrappBusinessException(err);
    }
    this.getEditor().setMultiSelectionEnable(false);
    this.getEditor().getBillListPanel().getHeadTable().setSortEnabled(false);
    try {
      super.doAction(e);
    }
    catch (Exception ex) {
      this.getEditor().setMultiSelectionEnable(true);
      this.getEditor().getBillListPanel().getHeadTable().setSortEnabled(true);
      throw ex;
    }
  }

  public PositionListEditor getEditor() {
    return this.editor;
  }

  public OrgPanel getRefpanel() {
    return this.refpanel;
  }

  public void setEditor(PositionListEditor editor) {
    this.editor = editor;
  }

  public void setRefpanel(OrgPanel refpanel) {
    this.refpanel = refpanel;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() == UIState.NOT_EDIT) {
      Object[] vos =
          ((BillListManageModel) this.getModel()).getSelectedOperaDatas();
      return null != vos && vos.length == 1;
    }
    return false;
  }
}
