package nc.itf.pu.m4202;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;

/**
 * 消耗汇总财务信息的查询服务
 * 
 * @since 6.0
 * @version 2010-11-13 下午03:44:37
 * @author duy
 */
public interface IVmiFinanceQuery {

  /**
   * 根据ID查询消耗汇总的财务信息，用于进行结算
   * 
   * @param ids
   * @return
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIByIds(String[] ids) throws BusinessException;

  /**
   * 根据查询方案查询消耗汇总的财务信息，用于进行结算
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIByScheme(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 根据SQL查询消耗汇总的财务信息，用于进行结算
   * 
   * @param sql 查询语句
   * @return 消耗汇总的结算VO
   * @throws BusinessException
   */
  public StockSettleVO[] queryVMIBySql(String sql) throws BusinessException;
}
