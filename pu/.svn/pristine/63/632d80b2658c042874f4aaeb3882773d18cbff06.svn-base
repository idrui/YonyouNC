package nc.ui.pu.m21.util;

import java.util.List;
import java.util.Map.Entry;

import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.OrderValueChangeObject;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单联动计算前台工具类
 * 
 * @since 6.0
 * @version 2012-2-28 上午10:41:11
 * @author tianft
 */
public class OrderCalculatorUtils {

  /**
   * 依据OrderValueChangeObject的联动计算
   * 
   * @param cardPanel
   * @param valueChangeObject
   */
  @SuppressWarnings("boxing")
  public static void calculate(BillCardPanel cardPanel,
      OrderValueChangeObject[] valueChangeObject) {
    if (!ArrayUtils.isEmpty(valueChangeObject)) {
      MapList<String, Integer> rows = new MapList<String, Integer>();
      for (OrderValueChangeObject ob : valueChangeObject) {
        rows.put(ob.getChangeKey(), ob.getRow());
      }
      RelationCalculate relationCalculate = new RelationCalculate();
      for (Entry<String, List<Integer>> entry : rows.entrySet()) {
        relationCalculate.calculate(cardPanel,
            ArrayUtil.toPrimitive(entry.getValue()), entry.getKey());
      }
    }
  }

}
