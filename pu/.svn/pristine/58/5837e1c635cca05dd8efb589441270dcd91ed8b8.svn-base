/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 下午01:30:48
 */
package nc.bs.pu.m21.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单删除VO的检查
 * @scene
 *        采购订单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:37:58
 * @author luojw
 */
public class DelVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);
    // 检查是否存在到货计划
    this.checkArrivePlan(vos);

  }

  private void checkArrivePlan(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        if (UFBoolean.TRUE.equals(item.getBreceiveplan())) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0351")/*
                                                                       * @res
                                                                       * "存在到货计划，不能进行此操作！"
                                                                       */);
        }
      }
    }
  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 自由、审批不通过的直接删除
      if (!ApproveFlowUtil.isCanDel(vo)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0115")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能删除！"
                                                                     */);
      }
    }
  }
}
