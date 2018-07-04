package nc.itf.pu.m4201;

import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存单据的结算查询
 * <li>
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-4-6 上午09:10:49
 */
public interface IStockFinanceQuery {
  /**
   * 查询其他入VO[]，是全VO。只有DR过滤。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   * @param bids
   * @return <p>
   * @author wangyf
   * @time 2010-4-6 上午09:44:33
   */
  public GeneralInVO[] queryGeneralinAggVOs(String[] hids, String[] bids);

  /**
   * 查询采购入VO[]，是全VO。只有DR过滤。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   * @param bids
   * @return <p>
   * @author wangyf
   * @time 2010-4-6 上午09:12:13
   */
  public PurchaseinFIVO[] queryPurchaseinAggVOs(String[] hids, String[] bids);

  /**
   * 根据库存单据行ID查询待自动结算的VO[]
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param saBId
   *          行ID[]
   * @return
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-7-1 上午09:25:14
   */
  public StockSettleVO[] queryPurchaseInByBID(String[] bid)
      throws BusinessException;

  /**
   * 根据采购订单ID查询待结算的采购入
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   * @return
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2010-3-24 下午02:38:21
   */
  public StockSettleVO[] queryPurchaseInByOrderBID(String[] bid)
      throws BusinessException;

  /**
   * 根据查询条件查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param whereSql
   * @return
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-13 下午12:56:10
   */
  public StockSettleVO[] queryPurchaseInByWhereSql(String fandw)
      throws BusinessException;

  /**
   * 查询委外入VO[]，是全VO。只有DR过滤。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   * @param bids
   * @return <p>
   * @author wangyf
   * @time 2010-4-6 上午09:43:48
   */
  public PurchaseinFIVO[] querySubcontractAggVOs(String[] hids, String[] bids);

  /**
   * 根据委外入BID查询待结算的委外入
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param saBId
   *          行ID[]
   * @return
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-7-1 上午09:25:14
   */
  public StockSettleVO[] querySubcontractByBID(String[] bid)
      throws BusinessException;

  /**
   * 根据委外订单ID查询待结算的委外入
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   * @return
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2010-3-24 下午02:37:53
   */
  public StockSettleVO[] querySubcontractByOrderBID(String[] bid)
      throws BusinessException;

  /**
   * 查询调拨入VO[]，是全VO。只有DR过滤。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   * @param bids
   * @return <p>
   * @author wangyf
   * @time 2010-4-6 上午09:44:25
   */
  public TransInVO[] queryTransinAggVOs(String[] hids, String[] bids);

  /**
   * 查询VOI VO[]，是全VO。只有DR过滤。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   * @param bids
   * @return <p>
   * @author wangyf
   * @time 2010-4-6 上午09:43:59
   */
  public PurchaseinFIVO[] queryVoiconsumeAggVOs(String[] hids, String[] bids);

  /**
   * * 根据消耗汇总BID查询待结算的消耗汇总
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   * @return
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2010-3-24 下午02:38:10
   */
  public StockSettleVO[] queryVoiconsumeByBID(String[] bid)
      throws BusinessException;
}
