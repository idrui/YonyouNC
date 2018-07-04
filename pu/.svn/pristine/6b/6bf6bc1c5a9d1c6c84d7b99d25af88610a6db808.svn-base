package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.payment.PaymentService;
import nc.vo.bd.payment.PaymentChVO;
import nc.vo.bd.payment.PaymentVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pub.SuperVO;

/**
 * 根据付款协议档案主键补全付款协议页签内容
 * 
 * @author zhangshqb
 */
public class PaymentInfo {
  /**
   * 获取付款协议页签VO
   * 
   * @param pk_payterm
   *          付款协议档案主键
   * @return
   */
  public static OrderPaymentVO[] getOrderPaymentVOs(String pk_payterm) {
    SuperVO[] vos = PaymentService.queryPaymentByIds(new String[] {
      pk_payterm
    }, PaymentVO.class);
    if (vos == null || vos.length != 1) {
      return new OrderPaymentVO[0];
    }
    PaymentChVO[] chvos = ((PaymentVO) vos[0]).getPaymentch();
    OrderPaymentVO[] paymentVOs = new OrderPaymentVO[chvos.length];
    for (int i = 0; i < chvos.length; i++) {
      PaymentChVO vo = chvos[i];
      paymentVOs[i] = new OrderPaymentVO();
      paymentVOs[i].setAttributeValue(OrderPaymentVO.SHOWORDER,
          vo.getAttributeValue(OrderPaymentVO.SHOWORDER));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.ACCRATE,
          vo.getAttributeValue(OrderPaymentVO.ACCRATE));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.PREPAYMENT,
          vo.getAttributeValue(OrderPaymentVO.PREPAYMENT));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTADDMONTH,
          vo.getAttributeValue(OrderPaymentVO.EFFECTADDMONTH));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTMONTH,
          vo.getAttributeValue(OrderPaymentVO.EFFECTMONTH));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE,
          vo.getAttributeValue(OrderPaymentVO.EFFECTDATEADDDATE));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_PAYPERIOD,
          vo.getAttributeValue(OrderPaymentVO.PK_PAYPERIOD));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_BALATYPE,
          vo.getAttributeValue(OrderPaymentVO.PK_BALATYPE));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.PK_RATE,
          vo.getAttributeValue(OrderPaymentVO.PK_RATE));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.PAYMENTDAY,
          vo.getAttributeValue(OrderPaymentVO.PAYMENTDAY));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.CHECKDATA,
          vo.getAttributeValue(OrderPaymentVO.CHECKDATA));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.ISDEPOSIT,
          vo.getAttributeValue(OrderPaymentVO.ISDEPOSIT));
      paymentVOs[i].setAttributeValue(OrderPaymentVO.ACCOUNTDAY,
          vo.getAttributeValue(OrderPaymentVO.ACCOUNTDAY));
    }
    return paymentVOs;
  }
}
