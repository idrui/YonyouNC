package nc.pubitf.pu.it;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.31
 * @version 2013-10-8 下午04:41:52
 * @author mengjian
 */
public interface IPurchaseInEstQueryForIT {

  /**
   * 方法功能描述：费用暂估的查询。
   * 
   * @param queryScheme
   * @param includefeeEstimated
   * @return
   * @throws BusinessException
   */
  PurchaseInEstVO[] feeEstQuery4IT(IQueryScheme queryScheme,
      UFBoolean includefeeEstimated) throws BusinessException;

  /**
   * 方法功能描述：货物暂估(可同时进行费用暂估)的查询
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  PurchaseInEstVO[] goodsEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：取消暂估的查询。
   */
  PurchaseInEstVO[] unEstQuery4IT(IQueryScheme queryScheme)
      throws BusinessException;
}
