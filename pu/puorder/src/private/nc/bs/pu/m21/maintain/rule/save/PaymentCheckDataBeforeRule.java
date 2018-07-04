package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单保存时校验付款协议页签，如果出账日有值，固定结账日也要有值
 * @scene
 *        采购订单保存校验
 * @param 无
 * @since 6.36
 * @version 2015-05-12 上午10:20:12
 * @author luojw
 */
public class PaymentCheckDataBeforeRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderPaymentVO[] payments = vo.getPaymentVO();
      if(payments == null){
        return;
      }
      for(OrderPaymentVO payment: payments){
        Integer accountday = payment.getAccountday();
        Integer checkdata = payment.getCheckdata();
        if(accountday !=null && checkdata == null){
          ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
              .getStrByID("4004030_0", "04004030-0381")/* 不允许出账日有值，固定结账日无值 */);
        }
      }
    }

  }
}
