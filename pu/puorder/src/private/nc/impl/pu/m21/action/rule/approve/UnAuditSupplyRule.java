/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 ����09:56:17
 */
package nc.impl.pu.m21.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ReserveServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              Ԥ��Ϊ�ɹ��ṩ�ķ�������
 * @scene
 *        �ɹ�����ȡ�����
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����11:11:57
 * @author luojw
 */
public class UnAuditSupplyRule implements IRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    OrderVO[] reserveVOs = OrderVOUtil.getInsance().getSupplyVO(vos);
    if (ArrayUtils.isEmpty(reserveVOs)) {
      return;
    }
    String[] billids = AggVOUtil.getPrimaryKeys(reserveVOs);
    ReserveServices.unAuditSupply(billids);
  }

}
