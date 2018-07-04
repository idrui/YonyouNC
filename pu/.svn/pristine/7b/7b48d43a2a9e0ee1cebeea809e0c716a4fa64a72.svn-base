package nc.pubitf.pu.m21.et;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>出口明细单拣配采购订单的查询接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.0
 * @author zhangyhh
 * @time 2013-8-1 下午03:48:50
 */
public interface IOrderQueryForET {
  /**
   * 拣配查询
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryForet(IQueryScheme queryScheme) throws BusinessException;

  OrderVO[] queryForet(String org, String material, String warehouse,
      String batchcode, String bid) throws BusinessException;

}
