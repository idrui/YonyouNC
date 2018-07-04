/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午04:15:14
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单单据状态合法性检查、关闭检查、冻结检查
 * @scene
 *        采购订单取消审核
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:03:19
 * @author luojw
 */
public class UnApproveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);

    // 关闭检查
    this.orderCloseStatusCheck(vos);

    // 订单冻结的检查
    this.orderFreezeCheck(vos);

  }

  /**
   * 方法功能描述：表体关闭检查
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-10 下午04:15:42
   */
  private void itemVOCloseStatusCheck(OrderVO orderVO, StringBuilder sb) {
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (OrderItemVO itemVO : itemVOs) {
      if (itemVO.getFisactive().equals(EnumActive.CLOSE.value())) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0", "04004030-0157")/* @res "单据存在关闭的行，不能操作！\n" */);
        return;
      }
    }
  }

  /**
   * 方法功能描述：订单整单关闭或者有一行关闭则不能弃审
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-10 下午04:04:08
   */
  private void orderCloseStatusCheck(OrderVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      OrderHeaderVO headerVO = vo.getHVO();
      if (headerVO.getBfinalclose().booleanValue()) {
        sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0", "04004030-0158")/* @res "单据已最终关闭，不能操作！\n" */);
        continue;
      }
      this.itemVOCloseStatusCheck(vo, sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private void orderFreezeCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 订单已经冻结，则不能取消审批
      if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0159")/*
                                                                     * @res
                                                                     * "单据已经冻结，不能取消审批！"
                                                                     */);
      }
    }
  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 状态是自由态的单据不能取消审批也不能收回
      if (vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0160")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能操作！"
                                                                     */);
      }
    }
  }

}
