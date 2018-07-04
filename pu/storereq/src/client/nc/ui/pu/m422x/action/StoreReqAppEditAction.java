/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:14:34
 */
package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m422x.rule.EditableByUnit;
import nc.ui.pu.pub.action.PUEditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>修改
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:14:34
 */
public class StoreReqAppEditAction extends PUEditAction {

  private static final long serialVersionUID = -9164440054401005924L;

  private BillForm editor;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.EditAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    new EditableByUnit(this.editor.getBillCardPanel()).setEditableAll();
  }

  public BillForm getEditor() {
    return this.editor;
  }

  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.EditAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }

    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return false;
    }
    // 根据V5X，物资需求申请支持审批中修改
    // 审批流通用功能：送审后变为审批中状态，在没有审批前可改
    // 如果支持审批中修改：则在支持上述功能外，在已经有审批人的情况下也要允许修改
    StoreReqAppVO vo = (StoreReqAppVO) this.getModel().getSelectedData();
    return vo != null && ApproveFlowUtil.isCanAppovingEdit(vo);
  }
}
