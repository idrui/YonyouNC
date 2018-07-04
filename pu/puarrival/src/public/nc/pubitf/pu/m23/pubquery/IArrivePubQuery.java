package nc.pubitf.pu.m23.pubquery;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给外模块的公共查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-7-29 上午10:48:12
 */
public interface IArrivePubQuery {

  /**
   * 方法功能描述：通过到货单的表头ID查询对应的到货单聚合VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param hid
   *          到货单的表头ID
   * @return 到货单聚合VO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:53:29
   */
  public ArriveVO queryAggVOByHid(String hid) throws BusinessException;

  /**
   * 方法功能描述：通过到货单的表头ID查询对应的到货单聚合VO。支持批量查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   *          到货单的表头ID
   * @return 到货单聚合VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:53:29
   */
  public ArriveVO[] queryAggVOByHids(String[] hids) throws BusinessException;

  /**
   * 方法功能描述：通过到货单的表头ID查询对应的到货单表头VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param hid
   *          到货单的表头ID
   * @return 到货单表头VO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:53:29
   */
  public ArriveHeaderVO queryHeadVOByHid(String hid) throws BusinessException;

  /**
   * 方法功能描述：通过到货单的表头ID查询对应的到货单表头VO。支持批量查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids
   *          到货单的表头ID
   * @return 到货单表头VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:53:29
   */
  public ArriveHeaderVO[] queryHeadVOByHids(String[] hids)
      throws BusinessException;

  /**
   * 方法功能描述：通过到货单的表体ID查询对应的到货单表体VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   *          表体ID
   * @return 货单表体VO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:56:00
   */
  public ArriveItemVO queryItemVOByBid(String bid) throws BusinessException;

  /**
   * 方法功能描述：通过到货单的表体ID查询对应的到货单表体VO。支持批量查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bid
   *          表体ID
   * @return 货单表体VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-29 上午10:56:00
   */
  public ArriveItemVO[] queryItemVOByBids(String[] bids)
      throws BusinessException;

  /**
   * 根据表体ID查询到货单聚合VO，不含孙表信息
   * 
   * @param bids 表体主键
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryAggVOByBids(String[] bids) throws BusinessException;

  /**
   * 根据表体ID，孙表ID查询到货单聚合VO，含孙表信息
   * 
   * @param bids 表体主键
   * @param bbids 孙表主键，如果没有孙表，可以传入null或者空数组
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryAggVOByBidsAndBBids(String[] bids, String[] bbids)
      throws BusinessException;

  /**
   * 根据采购订单表体id查询到货单
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryAggVOByOrderBids(String[] bids) throws BusinessException;

  /**
   * 根据表体ID查询到货单聚合VO，不含孙表信息
   * 
   * @param bids 表体主键
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryAggVOIncludeBBVOByBids(String[] bids)
      throws BusinessException;
}
