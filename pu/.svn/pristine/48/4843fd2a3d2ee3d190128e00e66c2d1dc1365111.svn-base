/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 上午10:19:01
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              采购订单对收回VO的状态等检查
 * @scene
 *        采购订单收回
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:17:38
 * @author luojw
 */
public class UnSendAppoveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);

  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 根据公共需求2011.6.25
      // 审批中状态、审批人为空的单据可以收回
      if (!vo.getHVO().getForderstatus()
          .equals(POEnumBillStatus.APPROVING.value())
          || StringUtils.isNotBlank(vo.getHVO().getApprover())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0276", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* 单据{0}的状态不正确，不能收回！ */);
      }
    }
  }

}
