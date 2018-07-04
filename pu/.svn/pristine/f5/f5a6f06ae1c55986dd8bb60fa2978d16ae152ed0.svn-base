package nc.pubimpl.pu.m23.ec.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 到货单发布校验规则。
 * 
 * @since 6.3
 * @version 2013-1-5 下午03:01:36
 * @author lixyp
 */
public class PublishValidateRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    for (ArriveVO vo : vos) {
      if (!POEnumBillStatus.FREE.toInteger().equals(
          vo.getHVO().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0204")/*
                                                                     * @res
                                                                     * "只有自由态的到货单可以进行发布"
                                                                     */);
      }
    }
  }

}
