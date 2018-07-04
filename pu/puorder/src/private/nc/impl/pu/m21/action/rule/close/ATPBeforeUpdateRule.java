package nc.impl.pu.m21.action.rule.close;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �ɹ������ر�ʱ������������ǰ�õ���
 * @scene
 *      �ɹ������ر�
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-1-12 ����03:27:17
 * @author zhaoyha
 */
public class ATPBeforeUpdateRule implements IRule<OrderCloseVO> {

  @Override
  public void process(OrderCloseVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderVO[] orderVOs = OrderCloseVO.getOrderVO(vos);
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), orderVOs);
  }

}
