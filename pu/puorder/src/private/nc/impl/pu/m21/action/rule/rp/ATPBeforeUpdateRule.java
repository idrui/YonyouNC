/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 下午05:23:30
 */
package nc.impl.pu.m21.action.rule.rp;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.pub.SplitOrderVOUtil;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 下午05:23:30
 */
public class ATPBeforeUpdateRule implements IRule<BatchOperateVO> {

  private OrderVO orderVO;

  public ATPBeforeUpdateRule(OrderVO orderVO) {
    this.orderVO = orderVO;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = OrderReceivePlanUtils.getAllRPVOs(vos[0]);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }
    OrderVO newVO = (OrderVO) this.orderVO.clone();
    OrderVO[] orderVOs =
        SplitOrderVOUtil.getInstance().splitOrderVOByRPVOs(new OrderVO[] {
          newVO
        }, rpVOs);
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), orderVOs);
  }

}
