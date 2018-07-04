package nc.pubitf.pu.it;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.31
 * @version 2013-10-8 ����04:41:52
 * @author mengjian
 */
public interface IPurchaseInEstQueryForIT {

  /**
   * �������������������ݹ��Ĳ�ѯ��
   * 
   * @param queryScheme
   * @param includefeeEstimated
   * @return
   * @throws BusinessException
   */
  PurchaseInEstVO[] feeEstQuery4IT(IQueryScheme queryScheme,
      UFBoolean includefeeEstimated) throws BusinessException;

  /**
   * �������������������ݹ�(��ͬʱ���з����ݹ�)�Ĳ�ѯ
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  PurchaseInEstVO[] goodsEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ��������������ȡ���ݹ��Ĳ�ѯ��
   */
  PurchaseInEstVO[] unEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException;
}
