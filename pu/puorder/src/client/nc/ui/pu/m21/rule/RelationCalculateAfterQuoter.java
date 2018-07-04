package nc.ui.pu.m21.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>询价后的关联计算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-28 下午02:14:50
 */
public class RelationCalculateAfterQuoter {
  private BillCardPanel panel;

  public RelationCalculateAfterQuoter(BillCardPanel panel) {
    this.panel = panel;
  }

  public void relationCalculate(Map<Integer, String> map) {
    CardEditorHelper card = new CardEditorHelper(this.panel);
    int[] rows = new int[map.size()];
    int i = 0;
    for(Integer set : map.keySet()){
      rows[i++] = set.intValue();
    }
    WeightVolumePieceCalc weightVolumePieceCal =
        new WeightVolumePieceCalc(card, rows);
    RelationCalculate rc = new RelationCalculate();
    
    /* 2015年12月9日 wangweir 修改原因:修改为批量计算，提升效率 Begin*/
    MapList<String, Integer> zeroRows = new MapList<String, Integer>();
    MapList<String, Integer> notzeroRows = new MapList<String, Integer>();
    for (Entry<Integer, String> entry : map.entrySet()) {
      int row = entry.getKey().intValue();
      String itemKey = entry.getValue();
      UFDouble nnum =
          (UFDouble) this.panel.getBodyValueAt(row, OrderItemVO.NNUM);
      if (0 == MathTool.compareTo(nnum, UFDouble.ZERO_DBL)) {
        zeroRows.put(itemKey, row);
      }
      else {
        notzeroRows.put(itemKey, row);
      }

    }

    if (zeroRows.size() != 0) {
      calculateZeroNumRows(weightVolumePieceCal, rc, zeroRows);
    }
    if (notzeroRows.size() != 0) {
      calculateNotZeroNumRows(rc, notzeroRows);
    }
    /* 2015年12月9日 wangweir End*/
  }

  protected void calculateNotZeroNumRows(RelationCalculate rc,
      MapList<String, Integer> notzeroRows) {
    for (Entry<String, List<Integer>> entry : notzeroRows.entrySet()) {
      String itemKey = entry.getKey();
      List<Integer> calcRows = entry.getValue();
      rc.calculate(this.panel, ArrayUtil.toPrimitive(calcRows), itemKey,
          null);
    }
  }

  protected void calculateZeroNumRows(
      WeightVolumePieceCalc weightVolumePieceCal, RelationCalculate rc,
      MapList<String, Integer> zeroRows) {
    for (Entry<String, List<Integer>> entry : zeroRows.entrySet()) {
      String itemKey = entry.getKey();
      List<Integer> calcRows = entry.getValue();

      Map<Integer, UFDouble> rowPrice = new HashMap<Integer, UFDouble>();
      for (Integer row : calcRows) {
        UFDouble price = (UFDouble) this.panel.getBodyValueAt(row, itemKey);
        rowPrice.put(row, price);
        this.panel.setBodyValueAt(UFDouble.ONE_DBL, row, OrderItemVO.NNUM);
      }
      rc.calculate(this.panel, ArrayUtil.toPrimitive(calcRows),
          OrderItemVO.NNUM, weightVolumePieceCal);

      for (Entry<Integer, UFDouble> priceEntry : rowPrice.entrySet()) {
        this.panel.setBodyValueAt(priceEntry.getValue(), priceEntry.getKey(),
            itemKey);
      }

      rc.calculate(this.panel, ArrayUtil.toPrimitive(calcRows), itemKey, null);

      for (Integer row : calcRows) {
        this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row, OrderItemVO.NNUM);
        this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row, OrderItemVO.NASTNUM);
        this.panel.setBodyValueAt(UFDouble.ZERO_DBL, row,
            OrderItemVO.NQTUNITNUM);
      }

      rc.calculate(this.panel, ArrayUtil.toPrimitive(calcRows),
          OrderItemVO.NNUM, weightVolumePieceCal);
    }
  }
}
