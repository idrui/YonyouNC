package nc.pubitf.pu.m21.to;

import java.util.List;

import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;

/**
 * 为内部交易提供的查询订单最新价服务
 * 
 * @since 6.0
 * @version 2011-4-20 上午11:25:32
 * @author zhaoyha
 */
public interface IOrderPriceQuery4TO {

  /**
   *查询集采订单的指定财务组织下的物料的订单最新价
   * 
   * @param queryParas 询价参数数组（请调用者自己实现接口）
   * @return OrderPriceData数组，可能为零长或null
   * @throws BusinessException
   */
  OrderPriceData[] queryLatestPrice(List<IOrderPriceQueryPara4TO> queryParas)
      throws BusinessException;

}
