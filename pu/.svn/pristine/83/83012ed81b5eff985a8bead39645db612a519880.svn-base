package nc.ui.pu.m4t.view;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

public class InitialEstBillForm extends PUBillForm {

  private static final long serialVersionUID = -6511542565134146961L;

  @Override
  public void initUI() {
    super.initUI();
    this.setBatchCodeRef();
  }

  private void setBatchCodeRef() {
    BillCardPanel card = this.getBillCardPanel();
    // 初始化表体批次参照
    BatchRefPane pane = new BatchRefPane();
    card.getBodyItem(InitialEstItemVO.VBATCHCODE).setComponent(pane);
  }

}
