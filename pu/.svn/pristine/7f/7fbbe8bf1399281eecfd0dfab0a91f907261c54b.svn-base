package nc.ui.pu.est.editor.body.after;

import java.util.Map;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.est.rule.FeeEstVatValue;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;

public class Feematerial implements ICardBodyAfterEditEventListener {
  private IRelationForItems relaitems;

  public Feematerial(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    BillCardPanel bcp = event.getBillCardPanel();
    IDataSetForCal dataset =
        new BillModelDataSet(bcp.getBillModel(), row, this.relaitems);
    String pk_fiorg = dataset.getPk_org();
    IKeyValue keyValue = new CardEditorHelper(bcp);
    FeeEstVatValue vatvalue = new FeeEstVatValue();
    Map<Integer, String> map =
        vatvalue.setCountryAndVatValue(keyValue, pk_fiorg, new int[] {
          row
        });
    if (null == map) {
      return;
    }
    String changeAttr = map.get(Integer.valueOf(row));
    if (null == changeAttr) {
      return;
    }
    String pk_group = dataset.getPk_group();
    // º∆À„π§æﬂ
    EstRelationCalcUtil calcUtil = new EstRelationCalcUtil(pk_group);
    calcUtil.calcDataSetForFee(dataset, changeAttr);
  }

}
