package nc.itf.pu.m4203;

import nc.vo.pu.m27.entity.SubcontInSettleVO;
import nc.vo.pub.BusinessException;

/**
 * ί�мӹ���ⵥ���񸱱���ѯ����
 * 
 * @since 6.0
 * @version 2011-1-21 ����03:17:52
 * @author zhaoyha
 */
public interface ISubcontinFIQuery {
  /**
   * �ɹ������ѯ�ɽ����ί�мӹ���ⵥ
   * 
   * @param where ��ѯ����
   * @return
   * @throws BusinessException
   */
  SubcontInSettleVO[] settleQuery(String where) throws BusinessException;

}
