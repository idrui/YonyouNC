package nc.vo.pu.pub.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

import org.apache.commons.lang.ArrayUtils;

/**
 * ���к�����Ϊ10...����ʽ��һ�����ں�̨�Ƶ���ȫ��Ϣ
 * 
 * @since 6.0
 * @version 2011-3-22 ����01:43:30
 * @author zhaoyha
 */
public class DefaultRowNoSetRule<E extends AggregatedValueObject> implements
    IRule<E> {

  @Override
  public void process(AggregatedValueObject[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (AggregatedValueObject vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getChildrenVO())) {
        continue;
      }
      CircularlyAccessibleValueObject[] items = vo.getChildrenVO();
      for (int i = 0; i < items.length; ++i) {
        items[i].setAttributeValue(OrderItemVO.CROWNO, String
            .valueOf((i + 1) * 10));
      }
    }
  }

}
