package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 采购订单保存时，检查付款协议的质保金是否多余一个
 * @scene
 * 采购订单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-4-12 上午10:48:27
 * @author zhangshqb
 */
public class IsdepositCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] arg0) {
    for (OrderVO orderVO : arg0) {
      ISuperVO[] vos = orderVO.getChildren(OrderPaymentVO.class);
      // 补货订单没有付款协议页签
      if (vos == null || vos.length == 0) {
        continue;
      }
      int i = 0;

      for (ISuperVO vo : vos) {
        if (vo.getStatus() == VOStatus.DELETED) {
          continue;
        }
        OrderPaymentVO pvo = (OrderPaymentVO) vo;
        UFBoolean isdeposit = pvo.getIsdeposit();
        if (isdeposit.booleanValue()) {
          i++;
        }
      }
      if (i > 1) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0376")/* 只能有一个账期是质保金 */);
      }
    }
  }

}
