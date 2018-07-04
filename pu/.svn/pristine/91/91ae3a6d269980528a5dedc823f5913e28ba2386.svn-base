package nc.ui.pu.pub.print;

import java.awt.Container;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.print.combination.CombiningConfigDialog;
import nc.vo.pub.AggregatedValueObject;

@SuppressWarnings("restriction")
public class PUCombiningConfigDialog extends CombiningConfigDialog {

  private static final long serialVersionUID = -7900587880976937717L;

  private IBillScale billScale;

  public PUCombiningConfigDialog(Container container, BillCardPanel cardPanel,
      AggregatedValueObject bill) {
    super(container, cardPanel, bill);
  }

  public IBillScale getBillScale() {
    return this.billScale;
  }

  public void setBillScale(IBillScale billscale) {
    this.billScale = billscale;
  }

  @Override
  protected void produceMergedVO(AggregatedValueObject bill) {
    super.produceMergedVO(bill);
    AggregatedValueObject billvo = this.getCombinedBill();
    if (this.getBillScale() != null) {
      this.getBillScale().processBillScale(billvo);
    }

  }

}
