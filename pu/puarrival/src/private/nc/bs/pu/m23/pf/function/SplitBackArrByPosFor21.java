package nc.bs.pu.m23.pf.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * �ɹ����������˻�������������+�ɹ���֯ƥ��ɹ������û�ȡ��Ӧ�Ĳɹ��ڣ�Ȼ���ղɹ��ڷֵ���
 * 
 * @since 6.0
 * @version 2010-11-5 ����01:52:04
 * @author wuxla
 */

public class SplitBackArrByPosFor21 {

  /**
   * ��������+�ɹ���֯ƥ��ɹ������û�ȡ��Ӧ�Ĳɹ��ڣ�Ȼ���ղɹ��ڷֵ���
   * 
   * @param vo ������VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitHorgByPosition(vo, new String[] {
      ArriveHeaderVO.PK_PURCHASEORG, ArriveItemVO.PK_MATERIAL, null
    }, PositionHeaderVO.purchasePosition);
  }
}
