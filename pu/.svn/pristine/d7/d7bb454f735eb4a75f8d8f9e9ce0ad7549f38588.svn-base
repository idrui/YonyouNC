package nc.impl.pu.m21transtype.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * 订单交易类型扩展属性的非空校验
 *
 * @since 6.0
 * @version 2011-5-24 上午11:32:13
 * @author zhaoyha
 */
public class NotNullChkRule implements IRule<PoTransTypeVO> {

  @Override
  public void process(PoTransTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] notNullNames =
        {
          PoTransTypeVO.PK_GROUP, PoTransTypeVO.CTRANTYPEID,
          PoTransTypeVO.VTRANTYPECODE
        };
    StringBuilder sb = new StringBuilder();
    for (PoTransTypeVO vo : vos) {
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