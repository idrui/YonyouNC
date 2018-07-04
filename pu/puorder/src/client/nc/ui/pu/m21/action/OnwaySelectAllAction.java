/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����10:28:03
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬ȫѡ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 ����10:28:03
 */
public class OnwaySelectAllAction extends NCAction {

  private static final long serialVersionUID = -6672626824252800432L;

  private IEditor editor;

  private BillManageModel model;

  public OnwaySelectAllAction() {
    super();
    SCMActionInitializer.initializeAction(this, IActionCode.SELALL);
//    this.setBtnName("ȫѡ");
//    this.setCode("selectAll");
//    this.putValue(Action.SHORT_DESCRIPTION, "ȫѡ(Ctrl+A)");
//    this.putValue(Action.ACCELERATOR_KEY,
//        KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    // ѡ��������
    BillCardPanel billCardPanel =
        ((BillForm) this.getEditor()).getBillCardPanel();
    int nLen = billCardPanel.getRowCount();
    if (nLen <= 0) {
      return;
    }
    billCardPanel.getBillTable().setRowSelectionInterval(0, nLen - 1);

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
