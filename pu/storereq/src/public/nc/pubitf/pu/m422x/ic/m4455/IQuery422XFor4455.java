package nc.pubitf.pu.m422x.ic.m4455;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * ���������ѯ
 * 
 * @since 6.0
 * @version 2010-12-16 ����01:15:52
 * @author wuxla
 */

public interface IQuery422XFor4455 {

  /**
   * �������������ṩ���������뵥��ת����ѯ����
   * 
   * @param queryScheme ��ѯ����
   * @return ����������������
   * @throws BusinessException
   */
  StoreReqAppVO[] queryStoreReqAppsFor4455(IQueryScheme queryScheme)
      throws BusinessException;
}
