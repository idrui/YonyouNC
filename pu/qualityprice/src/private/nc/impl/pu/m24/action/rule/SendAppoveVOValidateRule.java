package nc.impl.pu.m24.action.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              价格结算单对送审VO的状态等检查
 * @scene
 *        价格结算单送审
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:30:25
 * @author luojw
 */
public class SendAppoveVOValidateRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    // 单据状态合法性检查
    this.orderStatusCheck(vos);
  }

  private void orderStatusCheck(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      // 状态是自由的才能提交
      if (!(vo.getHVO().getFbillstatus().intValue() == POEnumBillStatus.FREE
          .toInt())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004070_0", "04004070-0013", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* 单据{0}的状态不正确，不能提交！ */);
      }
    }
  }

}
