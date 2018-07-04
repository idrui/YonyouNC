package nc.pubitf.pu.m21.srm.m4s1a;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * 为投诉建议单提供的转单查询服务。
 * 
 * @since 6.31
 * @version 2014-2-28 下午03:10:00
 * @author zhangyhh
 */
public interface IQuery21For4s1a {

  /**
   * 为投诉建议单提供的转单查询服务
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryPuOrderApps(IQueryScheme queryScheme) throws BusinessException;
}
