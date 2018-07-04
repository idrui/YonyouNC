/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-21 上午11:06:35
 */
package nc.ui.pu.est.util;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.scmpub.ref.FilterSupplierRefUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估的参照过滤工具
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-21 上午11:06:35
 */
public class EstRefFilterUtil {
  private BillCardPanel bcp;

  /**
   * EstRefFilterUtil 的构造子
   * 
   * @param bcp
   */
  public EstRefFilterUtil(BillCardPanel bcp) {
    this.bcp = bcp;
  }

  /**
   * 方法功能描述：过滤供应商。
   * <p>
   * <b>参数说明</b>
   * 
   * @param key 供应商字段名称
   * @param pk_fiorg 财务组织
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-21 上午11:11:42
   */
  public void filterSupplier(String key, String pk_fiorg) {
    BillItem item = this.bcp.getBodyItem(key);
    new FilterSupplierRefUtils(item).filterItemRefByOrg(pk_fiorg);
  }

}
