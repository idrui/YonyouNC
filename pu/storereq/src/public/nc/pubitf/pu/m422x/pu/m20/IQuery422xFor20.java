package nc.pubitf.pu.m422x.pu.m20;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * Ϊ�빺���ṩ��ת����ѯ����
 * 
 * @since 6.0
 * @version 2012-5-28 ����03:10:00
 * @author lixyp
 */
public interface IQuery422xFor20 {

  /**
   * Ϊ�빺���ṩ��ת����ѯ����
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  StoreReqAppVO[] queryStoreReqApps(IQueryScheme queryScheme)
      throws BusinessException;
}
