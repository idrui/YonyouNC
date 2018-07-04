/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-4 ����08:25:03
 */
package nc.bs.pu.m20.pf.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ɲɹ�����ʱ���ݲɹ��ڷֵ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-11-4 ����08:25:03
 */
public class SplitPrayByPosFor21 {

  /**
   * ��������+�ɹ���֯ƥ��ɹ������û�ȡ��Ӧ�Ĳɹ��ڣ�Ȼ���ղɹ��ڷֵ���
   * 
   * @param vo �빺��VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitBorgByPosition(vo, new String[] {
      PraybillItemVO.PK_PURCHASEORG, PraybillItemVO.PK_MATERIAL, null
    }, PositionHeaderVO.purchasePosition);
  }
}
