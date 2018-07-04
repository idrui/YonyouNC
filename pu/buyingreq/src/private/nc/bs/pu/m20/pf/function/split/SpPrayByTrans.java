package nc.bs.pu.m20.pf.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.scmf.pu.ordertranstype.pu.IOrderTranstypeSplit;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * �ֵ�����
 * 
 * @since 6.0
 * @version 2011-1-20 ����10:02:08
 * @author liuchx
 */
public class SpPrayByTrans {

  /**
   * ��������+�ɹ���֯ƥ�䶩���������û�ȡ��Ӧ�Ķ������ͣ�Ȼ���ն������ͷֵ���
   * 
   * @param vo VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByTrans(AggregatedValueObject vo)
      throws BusinessException {

    IOrderTranstypeSplit service =
        NCLocator.getInstance().lookup(IOrderTranstypeSplit.class);
    return service.splitBorgByOrderTrans(vo, new String[] {
      PraybillItemVO.PK_GROUP, PraybillItemVO.PK_ORG,
      PraybillItemVO.PK_SRCMATERIAL
    });
  }
}
