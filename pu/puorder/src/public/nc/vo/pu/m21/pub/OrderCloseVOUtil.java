/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午10:37:58
 */
package nc.vo.pu.m21.pub;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pu.m21.entity.OrderCloseVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单视图VO工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 上午10:37:58
 */
public class OrderCloseVOUtil {
  private static OrderCloseVOUtil instance = new OrderCloseVOUtil();

  private OrderCloseVOUtil() {
    // 私有
  }

  public static OrderCloseVOUtil getInstance() {
    return OrderCloseVOUtil.instance;
  }

  /**
   * 方法功能描述：得到订单视图VO的子表主键
   * <p>
   * <b>参数说明</b>
   * 
   * @param views
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午10:50:34
   */
  public String[] getBIds(OrderCloseVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    Set<String> set = new HashSet<String>();
    for (OrderCloseVO view : views) {
      set.add(view.getPk_order_b());
    }

    return set.toArray(new String[set.size()]);
  }
}
