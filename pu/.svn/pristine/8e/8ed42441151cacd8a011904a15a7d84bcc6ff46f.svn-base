package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.scmmm.vo.scmpub.scale.BillVOScaleCheckProcessor;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrderScaleRule;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单订单精度校验类
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:23:46
 * @author luojw
 */
public class OrderScaleCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = vos[0].getHVO().getPk_group();
    BillVOScaleCheckProcessor scaleChecker =
        new BillVOScaleCheckProcessor(pk_group, vos);
    // 这里会走BillVOScaleCheckProcessor的process方法，也即走的校验方法
    new OrderScaleRule().setScaleForCheck(scaleChecker);

  }

}
