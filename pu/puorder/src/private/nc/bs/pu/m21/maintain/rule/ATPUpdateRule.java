/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 ����10:37:07
 */
package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ������������¿�����
 * @scene
 *        �ɹ�����ɾ�������رա��޸ı���
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:42:54
 * @author luojw
 */
public class ATPUpdateRule implements ICompareRule<OrderVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    // ɾ��
    if (ArrayUtils.isEmpty(vos)) {
      ATPServices.modifyATPAfter(POBillType.Order.getCode(), originVOs);
    }
    else {
      ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
    }
  }

}
