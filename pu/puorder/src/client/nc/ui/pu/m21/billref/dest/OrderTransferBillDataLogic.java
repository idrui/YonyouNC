package nc.ui.pu.m21.billref.dest;

import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.billref.dest.DefaultBillDataLogic;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * @since 6.0
 * @version 2011-6-27 ÏÂÎç03:27:41
 * @author wuxla
 */

public class OrderTransferBillDataLogic extends DefaultBillDataLogic {

  private AbstractAppModel model = null;

  @Override
  public void doTransferAddLogic(Object selectedData) {
    super.doTransferAddLogic(selectedData);
    this.contractLink();
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private void contractLink() {
    BillCardPanel panel = this.getBillForm().getBillCardPanel();
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
