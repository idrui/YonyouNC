package nc.pubitf.pu.m21.pub;

import nc.vo.pu.m21.query.price.OrderItemPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为发票询价、暂估询价、请购单询价提供服务
 * <li>为内部交易查询最新采购价提供服务
 * <li>为生产制造查询最新采购价提供服务
 * <li>为采购入库单查询订单价格提供服务（入库单生成资产卡片使用）
 * </ul>
 * 
 * @version 2010-3-21 下午02:56:30
 * @since 6.0
 * @author duy
 */
public interface IOrderPriceQuery {
  /**
   * 方法功能描述：查询某特定采购订单行的单价
   * 
   * @param orderItemIds 采购订单行ID数组
   * @return 符合条件的订单价格数据数组
   * @throws BusinessException
   * @since 6.0
   */
  public OrderItemPriceVO[] queryItemPrice(String[] orderItemIds)
      throws BusinessException;

  /**
   * 查询订单的最新价格。
   * 为采购价格提供的最新价查询接口。
   * 接口输入为查询VO数组，其中包括供应商、币种、采购组织、物料OID、价格等信息。
   * 在实现此接口时，根据参数vo的条件进行查询，将结果赋值到此VO中直接返回。
   * 由于输入为数组，相比之前提供的接口，此接口能够支持多供应商、多币种、多采购组织、多物料
   * 
   * @param conds 最新价查询VO数组
   * @return 同上。
   * @throws BusinessException
   */
  public LastestPriceQueryVO[] queryLatestPrice(LastestPriceQueryVO[] queryVos)
      throws BusinessException;

  /**
   * 方法功能描述：查询订单的最新价格。
   * 
   * @param purchaseOrg 采购组织
   * @param supplier 供应商（可用为空）
   * @param currency 币种
   * @param params 询价参数对象数组
   * @return 方法执行完毕会将把携带了四个价格信息的参数返回
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException;

  /**
   * 方法功能描述：查询订单的最新价格（可以查指定日期范围内的最新价）
   * 
   * @param purchaseOrg 采购组织
   * @param supplier 供应商（可用为空）
   * @param currency 币种
   * @param params 询价参数对象数组
   * @param startDate 开始日期
   * @param endDate 结束日期
   * @return 方法执行完毕会将把携带了四个价格信息的参数返回
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params,
      UFDate startDate, UFDate endDate) throws BusinessException;

  /**
   * 方法功能描述：根据结算财务组织和物料查询最新采购订单价格
   * 
   * @param financeOrg 结算财务组织的OID
   * @param materials 物料OID数组
   * @return 符合条件的订单价格数据数组
   * @throws BusinessException
   * @since 6.0
   */
  public OrderPriceData[] queryLatestPrice(String financeOrg, String[] materials)
      throws BusinessException;

  /**
   * 方法功能描述：查询订单的最低价格。
   * 
   * @param purchaseOrg 采购组织
   * @param supplier 供应商
   * @param currency 币种
   * @param params 询价参数对象数组
   * @return 方法执行完毕会将把携带了四个价格信息的参数返回
   * @throws BusinessException
   * @since 6.0
   */
  public IOrderPriceQueryParam[] queryLowestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException;
}
