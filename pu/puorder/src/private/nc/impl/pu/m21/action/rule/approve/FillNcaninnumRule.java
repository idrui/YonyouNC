package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              ������ֱ���Ʋɹ���ⵥʱ��Ҫ���ÿ�����������˹���Ϊ��������ֱ������ⵥ�ṩ֧��
 * @scene
 *        �ɹ���������
 * @param ��
 * @since 6.3
 * @version 2014-10-20 ����4:55:35
 * @author luojw
 */

public class FillNcaninnumRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        itemVO.setNcaninnum(itemVO.getNnum());
      }
    }
  }

}
