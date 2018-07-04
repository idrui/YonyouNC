/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午07:36:21
 */
package nc.bs.pu.m21.rp;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对于订单行的到货计划全部打开，用于订单行关闭打开时同时操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-22 下午07:36:21
 */
public class OpenWhenItemOpen {

  public void openWhenItemOpen(OrderReceivePlanVO[] rpVOs) {
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    Integer active = (Integer) EnumActive.ACTIVE.value();
    for (OrderReceivePlanVO itemVO : rpVOs) {
      itemVO.setFisactive(active);
    }

    VOUpdate<OrderReceivePlanVO> update = new VOUpdate<OrderReceivePlanVO>();
    update.update(rpVOs, new String[] {
      OrderReceivePlanVO.FISACTIVE
    });
  }
}
