package nc.ui.pu.m21.action.interceptor;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.editor.IBillCardPanelEditor;

/**
 * @since 6.0
 * @version 2011-6-27 ÉÏÎç10:10:51
 * @author wuxla
 */

public class OrderBillFormInterceptor extends ShowUpComponentInterceptor {
  private IBillCardPanelEditor editor;

  private BillManageModel model;

  @Override
  public void afterDoActionSuccessed(Action action, ActionEvent e) {
    super.afterDoActionSuccessed(action, e);
    if (AppUiState.ADD == this.getModel().getAppUiState()
        || AppUiState.COPY_ADD == this.getModel().getAppUiState()
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState()) {
      this.contractLink();
    }
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  private void contractLink() {
    BillCardPanel panel = this.getEditor().getBillCardPanel();
    int rowcount = panel.getRowCount();
    if (0 == rowcount) {
      return;
    }

    Integer[] rows = new Integer[rowcount];
    for (int i = 0; i < panel.getRowCount(); ++i) {
      rows[i] = Integer.valueOf(i);
    }
    ContractLinker cnt =
        new ContractLinker(panel, this.getModel().getContext());
    cnt.contractLink(rows, false, true);
  }
}
