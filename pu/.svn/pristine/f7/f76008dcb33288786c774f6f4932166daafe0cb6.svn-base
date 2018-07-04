package nc.impl.pu.m25trantype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * 发票交易类型扩展属性的非空校验
 *
 * @since 6.0
 * @version 2011-5-24 上午11:32:13
 * @author zhaoyha
 */
public class NotNullChkRule implements IRule<InvcTranTypeVO> {

  @Override
  public void process(InvcTranTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] notNullNames =
        {
          InvcTranTypeVO.PK_GROUP, InvcTranTypeVO.CTRANTYPEID,
          InvcTranTypeVO.VTRANTYPECODE, InvcTranTypeVO.ITOARAPMODE,
          InvcTranTypeVO.BCHECKQUALITY, InvcTranTypeVO.BCONSUMEPUR,
          InvcTranTypeVO.BSENDPAY
        };
    StringBuilder sb = new StringBuilder();
    for (InvcTranTypeVO vo : vos) {
      for (String name : notNullNames) {
        Object value = vo.getAttributeValue(name);
        if (null != value && StringUtils.isNotBlank(value.toString())) {
          continue;
        }
        sb.append("[");
        sb.append(vo.getMetaData().getAttribute(name).toString());
        sb.append("]");
      }

    }
    if (sb.length() == 0) {
      return;
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0054")/*@res "以下属性不可为空："*/
        + SystemUtils.LINE_SEPARATOR + sb.toString());
  }

}