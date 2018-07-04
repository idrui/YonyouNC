/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午04:21:40
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单审批合法性规则
 * @scene
 *        采购订单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-20 下午2:45:53
 * @author luojw
 */
public class ApproveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);

    // 订单冻结的检查
    this.orderFreezeCheck(vos);

  }

  private void orderFreezeCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 非退货订单已经冻结，则不能审批
      if (UFBoolean.TRUE.equals(vo.getHVO().getBfrozen())
          && UFBoolean.FALSE.equals(vo.getHVO().getBreturn())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0154")/*
                                                                     * @res
                                                                     * "单据已经冻结，不能审批！"
                                                                     */);
      }
    }
  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 状态是自由或提交或审批中才能进行审批
      if (!vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())
          && !vo.getHVO().getForderstatus()
              .equals(POEnumBillStatus.APPROVING.value())
          && !vo.getHVO().getForderstatus()
              .equals(POEnumBillStatus.COMMIT.value())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0155")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能审批！"
                                                                     */);
      }
    }
  }

}
