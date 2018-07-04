/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 下午07:31:51
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IEditor;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderOnwayVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态全消
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghui
 * @time 2010-8-10 下午07:31:51
 */
public class OnwayUnSelectAllAction extends NCAction {

  private static final long serialVersionUID = -6452725452698949870L;

  private IEditor editor;

  private BillManageModel model;

  public OnwayUnSelectAllAction() {
    super();
    SCMActionInitializer.initializeAction(this, IActionCode.SELNONE);
//    this.setBtnName("全消");
//    this.setCode("unSelectAll");
//    this.putValue(Action.SHORT_DESCRIPTION, "全消(Alt+A)");
//    this.putValue(Action.ACCELERATOR_KEY,
//        KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    // 取消选择所有行
    BillCardPanel billCardPanel =
        ((BillForm) this.getEditor()).getBillCardPanel();
    int nLen = billCardPanel.getRowCount();
    if (nLen <= 0) {
      return;
    }
    billCardPanel.getBillTable().removeRowSelectionInterval(0, nLen - 1);

  }

  public IEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    OrderOnwayVO vo = (OrderOnwayVO) this.model.getSelectedData();
    if ((null == vo) || ArrayUtils.isEmpty(vo.getBVO())) {
      return false;
    }
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

}
