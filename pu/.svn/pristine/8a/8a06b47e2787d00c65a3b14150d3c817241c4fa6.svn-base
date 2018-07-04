package nc.pubitf.pu.m21.pub;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pf.SourceBillInfo;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单对外提供的公共查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-10 上午10:48:34
 */
public interface IOrderPubQuery {

  /**
   * 方法功能描述：检索交易类型是否被引用
   * <p>
   * <b>参数说明</b>
   * 
   * @param transType 交易类型
   * @return 被引用的交易类型
   * @throws BusinessException <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-11-3 上午09:00:02
   */
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException;

  /**
   * 方法功能描述：通过订单表体ID数组，查询表体VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param bidArray
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-10 上午10:51:08
   */
  public OrderItemVO[] queryOrderItem(String[] bidArray)
      throws BusinessException;

  /**
   * 方法功能描述：通过订单表体ID数组，查询视图VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param bidArray
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-10 上午10:51:52
   */
  public OrderViewVO[] queryOrderView(String[] bidArray)
      throws BusinessException;

  /**
   * 根据采购订单BID数组查询关联（参照）的合同号
   * 
   * @param pk_order_bs
   * @return 可能为null
   * @throws BusinessException
   */
  Map<String, String> getCtCodeByBID(String[] pk_order_bs)
      throws BusinessException;

  /**
   * 方法功能描述：取得上游采购订单单据信息，发送上游消息时使用
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderIds
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-17 上午09:31:32
   */
  SourceBillInfo[] getOrderBillInfo(String[] orderIds) throws BusinessException;

  /**
   * @param hids
   * @param attrs
   * @return
   * @throws BusinessException
   */
  OrderHeaderVO[] queryOrderHeaderVOByHids(String[] hids, String[] attrs)
      throws BusinessException;

  /**
   * 根据表体id查询订单VO
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryOrderVOByBids(String[] bids) throws BusinessException;

  /**
   * 根据采购订单BID数组查询关联（参照）的结算方式主键
   * 
   * @param pk_order_bs
   * @return 可能为null
   * @throws BusinessException
   */
  Map<String, String> splitByStypeByBID(String[] pk_order_bs)
		  throws BusinessException;
}
