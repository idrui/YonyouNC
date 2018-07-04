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
 * ����vat����������
 * 
 * @since 6.0
 * @version 2012-3-24 ����09:57:33
 * @author wuxla
 */
public class InitialEstVatRecRule {
  /**
   * ����Vat����������
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>�༭�¼�
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
