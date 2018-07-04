package nc.itf.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;

/**
 * 付款计划查询
 * 
 * @since 6.0
 * @version 2011-1-4 上午09:01:07
 * @author wuxla
 */

public interface IOrderPayPlanQuery {
  /**
   * 付款计划已生成有付款申请单或者付款单则该参数值不允许修改。
   * 查询付款计划是否已经生成付款申请或者付款单
   * 
   * @return
   * @throws BusinessException
   */
  boolean checkPO88Para(SysInitVO vo) throws BusinessException;

  // PayPlanViewVO[] queryPayPlanVOs(String cond) throws BusinessException;

  /**
   * 查询
   * 
   * @param queryScheme查询方案
   * @return
   * @throws BusinessException
   */
  PayPlanViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
      throws BusinessException;

  PayPlanViewVO[] queryPayPlanViews(String[] bids) throws BusinessException;

  /**
   * 根据订单表头id查询付款计划
   * 
   * @param hids
   * @return
   * @throws BusinessException
   */
  AggPayPlanVO[] queryPayPlanVOs(String[] hids) throws BusinessException;
}
