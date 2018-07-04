package nc.ui.pu.m21.view;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.pub.view.PUBillListView;

public class OrderListView extends PUBillListView {

  private static final long serialVersionUID = -917460393847701020L;

  @Override
  public void initUI() {
    super.initUI();

    new OrderScaleSetter(this.getModel().getContext().getPk_group())
        .setListScale(this.getBillListPanel());
  }
}
