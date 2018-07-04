package nc.ui.pu.m4t.rule;

import java.util.List;
import java.util.Map.Entry;

import nc.ui.pu.m4t.editor.body.after.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m4t.rule.InitialEstVatValue;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 设置vat和联动计算
 * 
 * @since 6.0
 * @version 2012-3-24 上午09:57:33
 * @author wuxla
 */
public class InitialEstVatRecRule {
  /**
   * 设置Vat并联动计算
   * <p>
   * 使用场景：
   * <ul>
   * <li>编辑事件
   * </ul>
   */
  public void setVatAndReCal(BillCardPanel panel, int[] rows) {
    MapList<String, Integer> mapList =
        new InitialEstVatValue().setVatValue(new CardEditorHelper(panel), rows);
    if (null == mapList || mapList.size() == 0) {
      return;
    }
    RelationCalculate cal = new RelationCalculate();
    for (Entry<String, List<Integer>> entry : mapList.entrySet()) {
      String key = entry.getKey();
      List<Integer> list = entry.getValue();
      Integer[] rowArray = list.toArray(new Integer[list.size()]);
      int[] changerows = ArrayUtils.toPrimitive(rowArray);
      cal.calculate(panel, changerows, key);
    }
  }
}
