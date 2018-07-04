package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.ic.general.view.SetPartDlg;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * @since 6.0
 * @version 2011-1-26 下午06:02:37
 * @author wuxla
 */

public class KitQueryAction extends NCAction {
  private static final long serialVersionUID = 4795495826901852365L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  public KitQueryAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SETPARTINFO);
    // String str = "成套件信息";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.SHORT_DESCRIPTION, str);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_MASK));
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel panel = this.editor.getBillCardPanel();
    // 选中行
    int selectrow = panel.getBillTable().getSelectedRow();

    String pk_material =
        (String) panel.getBodyValueAt(selectrow, OrderItemVO.PK_MATERIAL);

    // 和chenlla确认去掉
    // if (pk_material == null) {
    //
    // MessageDialog.showHintDlg(panel, null, "没有选择物料");
    // return;
    // }

    String pk_corp_v =
        (String) panel.getBodyValueAt(selectrow, OrderItemVO.PK_ORG_V);

    // InvBasVO invbasevo =
    //
    // InvInfoUIQuery.getInstance().getInvInfoQuery().getInvBasVO(pk_material);
    //
    // if (invbasevo == null) {
    //
    // MessageDialog.showHintDlg(panel, null, "物料不存在");
    // return;
    //
    // }
    //
    // if (invbasevo.getSetpartsflag() == null
    //
    // || !invbasevo.getSetpartsflag().booleanValue()) {
    //
    // MessageDialog.showHintDlg(panel, null, "物料不是成套件");
    // return;
    //
    // }

    SetPartDlg dlg = new SetPartDlg(this.model.getContext().getEntranceUI());

    dlg.initUI(pk_material, pk_corp_v);
    dlg.setResizable(true);
    dlg.setReset(true);

    dlg.showModal();

  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }
}
