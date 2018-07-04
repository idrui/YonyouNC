/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 下午05:00:22
 */
package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务检查函数工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-20 下午05:00:22
 */
public class OrderFunctionUtil {

  private static OrderFunctionUtil instance = new OrderFunctionUtil();

  private OrderFunctionUtil() {
    return;
  }

  public static OrderFunctionUtil getInstance() {
    return OrderFunctionUtil.instance;
  }

  /**
   * 滤掉了DELETE的行及历史行
   * 
   * @param vo
   * @return
   */
  public OrderVO getCheckVO(OrderVO vo) {
    List<OrderItemVO> list = new ArrayList<OrderItemVO>();
    for (OrderItemVO itemVO : vo.getBVO()) {
      if (!this.isDeleteOrRevise(itemVO)) {
        list.add(itemVO);
      }
    }
    if (0 == list.size()) {
      return null;
    }

    OrderVO newVO = (OrderVO) vo.clone();
    OrderItemVO[] itemVOs = list.toArray(new OrderItemVO[list.size()]);
    newVO.setBVO(itemVOs);
    return newVO;
  }

  /**
   * 方法功能描述：判断行是否为删除的行或者历史行
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-20 下午04:53:54
   */
  public boolean isDeleteOrRevise(OrderItemVO itemVO) {
    if (null == itemVO) {
      return false;
    }

    if (VOStatus.DELETED == itemVO.getStatus()) {
      return true;
    }

    if (EnumActive.REVISEHISTORY.value().equals(itemVO.getFisactive())) {
      return true;
    }

    return false;
  }
}
