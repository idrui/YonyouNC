package nc.pubitf.pu.m21.srm.m4s21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * 为评估申请单提供的转单查询服务。
 * 
 * @since 6.31
 * @version 2014-3-26 下午03:10:00
 * @author zhangyhh
 */
public interface IQuery21For4s21 {

  /**
   * 为评估申请单提供的转单查询服务
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryPuOrderApps(IQueryScheme queryScheme) throws BusinessException;
}
