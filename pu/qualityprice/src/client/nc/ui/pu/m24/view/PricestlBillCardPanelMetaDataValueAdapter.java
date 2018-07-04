package nc.ui.pu.m24.view;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.value.BillCardPanelMetaDataFullValueAdapter;

public class PricestlBillCardPanelMetaDataValueAdapter extends
    BillCardPanelMetaDataFullValueAdapter {

  @Override
  public void setValue(Object object) {
    super.setValue(object);

    BillModel bm =
        ((BillCardPanel) this.getComponent()).getBillData().getBillModel(
            "body_hqhp");
    // bm.setBodyDataVO(null);
    bm.loadLoadRelationItemValue();
    bm.execLoadFormula();
  }

}
