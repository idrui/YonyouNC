/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 ����04:21:03
 */
package nc.bs.pu.m21.query.ic.rule;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * @description
 *              �ɹ�����������;״̬����
 * @scene
 *        �ɹ�������ⵥת����ѯ
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����8:52:30
 * @author luojw
 */
public class FilterByOnWayStatusRule implements IFilterRule<OrderVO> {

  @Override
  public OrderVO[] process(OrderVO[] vos) {
    // zhaoyha �������
    return vos;
  }

}
