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

/**
 * @description
 *              采购订单对送审VO的状态等检查
 * @scene
 *        采购订单送审
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:55:29
 * @author luojw
 */
public class SendAppoveVOValidateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);

  }

  private void orderStatusCheck(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      // 状态是自由的才能提交
    	/*
    	 * add by wandl 
    	 * 单据是自由态或者审批中的才可以提交
    	 */
      if (!vo.getHVO().getForderstatus().equals(POEnumBillStatus.FREE.value())
      		&& !vo.getHVO().getForderstatus().equals(POEnumBillStatus.APPROVING.value())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0274", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* 单据{0}的状态不正确，不能提交！ */);
      }
    }
  }

}
