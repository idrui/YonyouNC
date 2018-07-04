/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 下午12:05:13
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态过滤空行类（此处是以是否选中过滤）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-10 下午12:05:13
 */
public class OnwayBlankChilrenFilter implements IBlankChildrenFilter {

  /**
   * 父类方法重写
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
