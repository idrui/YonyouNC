package nc.ui.pu.m21.view;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.pub.view.PUBillListView;

public class OrderRevisionListView extends PUBillListView {

  private static final long serialVersionUID = 2361307680885946080L;

  @Override
  public void initUI() {
    super.initUI();

    new OrderScaleSetter(this.getModel().getContext().getPk_group())
        .setListScale(this.getBillListPanel());
  }
}
