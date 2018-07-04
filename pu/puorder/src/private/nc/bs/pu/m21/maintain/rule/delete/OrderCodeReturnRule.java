/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 ����09:51:33
 */
package nc.bs.pu.m21.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * @description
 *              �ɹ�����ɾ��ʱ���ɹ��������ݺ�(������)����
 * @scene
 *        �ɹ�����ɾ��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:45:31
 * @author luojw
 */
public class OrderCodeReturnRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (null != originVOs) {
      PUBillCodeUtils.getOrderCode().returnBillCode(originVOs);
    }
  }

}
