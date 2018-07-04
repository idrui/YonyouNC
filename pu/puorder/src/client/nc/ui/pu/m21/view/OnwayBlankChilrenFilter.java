/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 ����12:05:13
 */
package nc.ui.pu.m21.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.value.IBlankChildrenFilter;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬���˿����ࣨ�˴������Ƿ�ѡ�й��ˣ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-10 ����12:05:13
 */
public class OnwayBlankChilrenFilter implements IBlankChildrenFilter {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.view.value.IBlankChildrenFilter#filter(nc.ui.pub.bill.BillCardPanel,
   *      java.lang.Object)
   */
  @Override
  public Map<String, Integer[]> filter(BillCardPanel cardPanel, Object obj) {

    Map<String, Integer[]> blankMap = new HashMap<String, Integer[]>();

    int rows = cardPanel.getBodyPanel().getTable().getRowCount();

    int[] selectRows = cardPanel.getBodyPanel().getTable().getSelectedRows();

    // int[] irows = cardPanel.getBillTable().getSelectedRows();

    Set<Integer> rowSet = new HashSet<Integer>();

    for (int i = 0; i < rows; i++) {
      if (ArrayUtils.contains(selectRows, i)) {
        continue;
      }
      rowSet.add(Integer.valueOf(i));
    }

    blankMap.put(cardPanel.getCurrentBodyTableCode(),
        rowSet.toArray(new Integer[0]));

    return blankMap;
  }
}
