package nc.vo.pu.report.pub.smart.model.descriptor;

import nc.pub.smart.model.descriptor.SortDescriptor;
import nc.pub.smart.model.descriptor.SortItem;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

import org.apache.commons.lang.ArrayUtils;

/**
 * 报表排序描述器
 * 
 * @since 6.0
 * @version 2011-10-13 上午09:27:56
 * @author 田锋涛
 */

public class PuSortDescriptor<T extends PuQueryInfoPara> extends SortDescriptor {

  private static final long serialVersionUID = 2009025039774708127L;

  /**
   * 构造函数
   * 
   * @param para - 报表参数
   * @param desc 是否降序
   */
  public PuSortDescriptor(T para, boolean desc) {
    String[] sortKeys = para.getSortKeys();
    if (ArrayUtils.isEmpty(sortKeys)) {
      return;
    }
    for (String key : sortKeys) {
      SortItem info = new SortItem(key, desc);
      this.addSort(info);
    }
  }
}
