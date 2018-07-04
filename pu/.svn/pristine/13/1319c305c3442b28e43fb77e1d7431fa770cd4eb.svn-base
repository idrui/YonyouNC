/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午10:37:07
 */
package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单保存后更新可用量
 * @scene
 *        采购订单删除、入库关闭、修改保存
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:42:54
 * @author luojw
 */
public class ATPUpdateRule implements ICompareRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    // 删除
    if (ArrayUtils.isEmpty(vos)) {
      ATPServices.modifyATPAfter(POBillType.Order.getCode(), originVOs);
    }
    else {
      ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
    }
  }

}
