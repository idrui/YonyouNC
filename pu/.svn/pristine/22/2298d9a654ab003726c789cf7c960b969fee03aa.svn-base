/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-23 下午01:03:44
 */
package nc.ui.pu.costfactor.view.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.vo.pu.costfactor.entity.CostfactorItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>上移、下移、置顶、置底
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-23 下午01:03:44
 */
public class ChgListItemOrder {

  private List<CostfactorItemVO> listItems;

  public ChgListItemOrder(List<CostfactorItemVO> listItems) {
    this.listItems = listItems;
  }

  // 下移操作
  public int[] backward(CostfactorItemVO[] factorItems) {
    CostfactorItemVO[] toBeMoved = factorItems;
    if ((toBeMoved == null) || (toBeMoved.length == 0)) {
      return new int[0];
    }

    ArrayList<CostfactorItemVO> al = new ArrayList<CostfactorItemVO>();
    for (int i = 0; i < this.listItems.size(); i++) {
      al.add(this.listItems.get(this.listItems.size() - 1 - i));
    }
    CostfactorItemVO[] r = new CostfactorItemVO[toBeMoved.length];
    for (int i = 0; i < toBeMoved.length; i++) {
      r[i] = toBeMoved[toBeMoved.length - 1 - i];
    }
    toBeMoved = r;
    int[] indics = this.getIndexOf(al, toBeMoved);

    al.removeAll(Arrays.asList(toBeMoved));

    al.addAll(indics[0] - 1 < 0 ? 0 : indics[0] - 1, Arrays.asList(toBeMoved));

    for (int i = 0; i < this.listItems.size(); i++) {
      this.listItems.set(i, al.get(this.listItems.size() - 1 - i));
    }
    return this.getIndexOf(this.listItems, toBeMoved);
  }

  // 置底操作
  public int[] bottom(CostfactorItemVO[] toBeMoved) {
    if ((toBeMoved == null) || (toBeMoved.length == 0)) {
      return new int[0];
    }
    this.listItems.removeAll(Arrays.asList(toBeMoved));

    this.listItems.addAll(Arrays.asList(toBeMoved));

    return this.getIndexOf(this.listItems, toBeMoved);
  }

  // 上移操作
  public int[] forward(CostfactorItemVO[] toBeMoved) {
    if ((toBeMoved == null) || (toBeMoved.length == 0)) {
      return new int[0];
    }
    int[] indics = this.getIndexOf(this.listItems, toBeMoved);

    this.listItems.removeAll(Arrays.asList(toBeMoved));

    this.listItems.addAll(indics[0] - 1 < 0 ? 0 : indics[0] - 1,
        Arrays.asList(toBeMoved));

    return this.getIndexOf(this.listItems, toBeMoved);
  }

  public int[] getIndexOf(List<CostfactorItemVO> al, Object[] objs) {
    int[] indics = new int[objs.length];
    for (int i = 0; i < indics.length; i++) {
      indics[i] = al.indexOf(objs[i]);
    }
    Arrays.sort(indics);
    return indics;
  }

  // 置顶操作
  public int[] top(CostfactorItemVO[] toBeMoved) {
    if ((toBeMoved == null) || (toBeMoved.length == 0)) {
      return new int[0];
    }
    this.listItems.removeAll(Arrays.asList(toBeMoved));
    this.listItems.addAll(0, Arrays.asList(toBeMoved));

    return this.getIndexOf(this.listItems, toBeMoved);
  }

}
