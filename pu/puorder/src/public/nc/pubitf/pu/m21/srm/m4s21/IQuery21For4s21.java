package nc.pubitf.pu.m21.srm.m4s21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * Ϊ�������뵥�ṩ��ת����ѯ����
 * 
 * @since 6.31
 * @version 2014-3-26 ����03:10:00
 * @author zhangyhh
 */
public interface IQuery21For4s21 {

  /**
   * Ϊ�������뵥�ṩ��ת����ѯ����
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryPuOrderApps(IQueryScheme queryScheme) throws BusinessException;
}
