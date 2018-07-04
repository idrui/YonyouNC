package nc.ui.pu.est.rule;

import java.util.Map;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.est.rule.FeeEstVatValue;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;

/**
 * 费用行编辑后设置税码及联动计算
 * 
 * @since 6.0
 * @version 2012-3-24 上午11:01:42
 * @author wuxla
 */
public class FeeEstValueAndReCal {
  /**
   * 费用行编辑后设置税码及联动计算
   * <p>
   * 使用场景：
   * <ul>
   * <li>编辑后事件
   * </ul>
   * 
   * @param event
   * @param relaitems
   */
  public void setVatAndReCal(CardBodyAfterEditEvent event,
      IRelationForItems relaitems) {
    BillCardPanel bcp = event.getBillCardPanel();
    int row = event.getRow();
    IDataSetForCal dataset =
        new BillModelDataSet(bcp.getBillModel(), row, relaitems);
    String pk_fiorg = dataset.getPk_org();
    CardEditorHelper editor = new CardEditorHelper(bcp);
    int[] rows = new int[] {
      row
    };
    Map<Integer, String> map =
        new FeeEstVatValue().setVatValue(editor, pk_fiorg, rows);
    if (null == map) {
      return;
    }
    String changeAttr = map.get(Integer.valueOf(row));
    if (null == changeAttr) {
      return;
    }
    String pk_group = dataset.getPk_group();
    EstRelationCalcUtil calcUtil = new EstRelationCalcUtil(pk_group);
    calcUtil.calcDataSetForFee(dataset, changeAttr);
  }
}
