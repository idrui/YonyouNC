package nc.pubitf.pu.it;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;

/**
 * @since 6.31
 * @version 2013-9-16 上午11:16:34
 * @author mengjian
 */
public interface ISettleBillQueryForIT {

  /**
   * 根据查询对话框形成的查询方案查询结算单
   * 
   * @param queryscheme 查询对话框形成的查询方案
   * @return 结算单VO数组
   * @throws BusinessException
   */
  SettleBillVO[] query4IT(IQueryScheme queryscheme) throws BusinessException;

  /**
   * 提供给进口的入库查询（只包含采购入库）
   * 
   * @param queryscheme
   * @return 查询到的采购入库单
   * @throws BusinessException
   */
  StockSettleVO[] queryStockByQueryScheme4IT(IQueryScheme queryscheme)
      throws BusinessException;

}
