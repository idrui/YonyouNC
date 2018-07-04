/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午04:22:45
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单弃审时检查预留数量
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:04:05
 * @author luojw
 */
public class ReservedNumChkRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (itemVO.getNsuprsnum() != null
            && itemVO.getNsuprsnum().compareTo(UFDouble.ZERO_DBL) > 0) {
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004030_0", "04004030-0273", null, new String[] {
                vo.getHVO().getVbillcode()
              })/* 订单{0}已经预留，不能弃审 */);
        }
      }
    }
  }

}
