package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * ��������������
 * 
 * @since 6.0
 * @version 2011-3-14 ����01:59:18
 * @author wuxla
 */

public class OrderApproveUtil {

  /**
   * �õ������ɹ��Ĳɹ�����
   * 
   * @param vos �ɹ�����
   * @return �����ɹ��Ĳɹ�����
   */
  public static OrderVO[] getApproveVOs(OrderVO[] vos) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (POEnumBillStatus.APPROVE.value()
          .equals(vo.getHVO().getForderstatus())) {
        list.add(vo);
      }
    }

    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }

    return null;
  }
}
