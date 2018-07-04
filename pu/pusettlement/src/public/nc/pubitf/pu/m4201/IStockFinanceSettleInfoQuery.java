package nc.pubitf.pu.m4201;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 查询采购入库财务副本结算信息
 * 
 * @since 6.0
 * @version 2011-3-25 下午03:18:45
 * @author yinfy
 */

public interface IStockFinanceSettleInfoQuery {
  /**
   * 查询采购入库表体行结算平均单价
   * 
   * @param bids 采购入库副本表体pks
   * @return
   * @throws BusinessException
   */
  public Map<String, UFDouble> getPriceArrayByRowIdsNoTax(String[] bids)
      throws BusinessException;

  /**
   * 查询已结算数量
   * 
   * @param bids 副本bid
   * @return
   * @throws BusinessException
   */
  public Map<String, UFDouble> getTotalSettleNum(String[] bids)
      throws BusinessException;
}
