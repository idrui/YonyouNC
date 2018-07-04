package nc.pubitf.pu.m21.dm.m4804;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;

/**
 * 运输单查询
 * 
 * @since 6.0
 * @version 2010-12-13 下午05:10:44
 * @author wuxla
 */

public interface IOrderQueryFor4804 {

  /**
   * @param condition查询方案
   * @return 视图VO
   * @throws BusinessException
   */
  OrderViewVO[] queryFor4804(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 根据到货计划主键查询到货计划号
   * 
   * @param bbids 到货计划主键
   * @return key：到货计划主键 value：对应的到货计划号
   * @throws BusinessException
   */
  Map<String, String> queryPlanCodeByBBidsFor4804(String[] bbids)
      throws BusinessException;

}
