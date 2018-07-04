package nc.vo.pu.m21.vochange;

import nc.vo.pu.m21.entity.OrderVO;

/**
 * 生产订单订单到采购订单vo交换处理类
 * 
 * @since 6.0
 * @version 2011-12-8 下午04:40:29
 * @author 田锋涛
 */

public class ChangeVOAdjust55C2To21 extends AbstractOrderChangeVOAdjust {

  //
  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // 放到推式保存接口补全
    return;
  }
}
