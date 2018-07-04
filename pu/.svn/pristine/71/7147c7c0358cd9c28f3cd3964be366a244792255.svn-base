/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-25 下午08:34:04
 */
package nc.bs.pu.m21.writeback;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.PUSysParamUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单回写自动关闭工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-25 下午08:34:04
 */
public class OrderWriteBackCloseUtil {

  private static OrderWriteBackCloseUtil instance =
      new OrderWriteBackCloseUtil();

  private OrderWriteBackCloseUtil() {
    // 私有
  }

  /**
   * @return instance
   */
  public static OrderWriteBackCloseUtil getInstance() {
    return OrderWriteBackCloseUtil.instance;
  }

  /**
   * 方法功能描述：得到视图VO对应的参数
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-1 下午08:44:42
   */
  public Map<String, PUParaValue.po09> getPO09Map(OrderCloseVO[] vos) {
    Map<String, PUParaValue.po09> map = new HashMap<String, PUParaValue.po09>();
    for (OrderCloseVO closeVO : vos) {
      String pkOrg = closeVO.getPk_org();
      if (map.containsKey(pkOrg)) {
        continue;
      }
      PUParaValue.po09 value = PUSysParamUtil.getPO09(pkOrg);
      map.put(pkOrg, value);
    }

    return map;
  }

  /**
   * 方法功能描述：根据参数分单
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param map
   * @param instanceSet
   * @param ontimeSet <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-1 下午09:11:18
   */
  public void splitVO(OrderCloseVO[] vos, Map<String, PUParaValue.po09> map,
      Set<OrderCloseVO> instanceSet, Set<OrderCloseVO> ontimeSet) {
    for (OrderCloseVO closeVO : vos) {
      PUParaValue.po09 para = map.get(closeVO.getPk_org());
      if (PUParaValue.po09.instance.equals(para)) {
        instanceSet.add(closeVO);
      }
      else {
        ontimeSet.add(closeVO);
      }
    }
  }

}
