/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����11:07:39
 */
package nc.ui.pu.m4t.action;

import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�޸�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 ����11:07:39
 */
public class InitialestEditAction extends EditAction {

  private static final long serialVersionUID = -7953653651692561677L;

  private BillForm editor;

  /**
   * @return editor
   */
  public BillForm getEditor() {
    return this.editor;
  }

  /**
   * @param editor
   *          Ҫ���õ� editor
   */
  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.EditAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }

    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if ((objs != null) && (objs.length > 1)) {
      return false;
    }

    InitialEstVO vo = (InitialEstVO) this.getModel().getSelectedData();
    return (vo != null)
        && vo.getHeader().getFbillstatus().equals(InitialEstStatus.FEE.value());
  }
}
