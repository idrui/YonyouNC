package nc.pubitf.pu.m4t.pu.settle;

import nc.vo.pu.m27.entity.InitialEstSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

public interface IInitialEstSettleQuery {
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
  public StockSettleVO[] queryByBID(String[] bid) throws BusinessException;

  /**
   * 方法功能描述：按指定的条件查询可结算的期初暂估单
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-9-10 上午10:55:02
   */
  public InitialEstSettleVO[] queryByCond(String cond) throws BusinessException;

  /**
   * 根据订单行ID查询待自动结算的VO[]
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
  public StockSettleVO[] queryByOrderBID(String[] bid) throws BusinessException;

  /**
   * 查询VO[]，是全VO。只有DR过滤。
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
  public InitialEstVO[] queryInitialEstAggVOs(String[] hids, String[] bids);

}
