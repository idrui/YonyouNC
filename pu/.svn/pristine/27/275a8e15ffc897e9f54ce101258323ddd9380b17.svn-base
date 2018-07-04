/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午08:44:05
 */
package nc.itf.pu.reference.ic;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.general.IICGenRewritePara;
import nc.pubitf.ic.m45.m21.IPurchaseInQueryFor21;
import nc.pubitf.ic.m45.m21.IRewrite45For21;
import nc.pubitf.ic.m45.m23.IPurchaseInMaintainserviceFor23;
import nc.pubitf.ic.m45.m25.IParameter45For25;
import nc.pubitf.ic.m45.m25.IRewrite45For25;
import nc.pubitf.ic.transtype.IInOutTransTypeQueryService;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单45提供给采购的服务适配
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 上午08:44:05
 */
public class M45PUServices {

  /**
   * 根据到货单表体查询是否有下游入库单
   * 
   * @param bids
   * @return
   */
  public static Map<String, UFBoolean> getMapBysrcBid(String[] bids) {
    IPurchaseInMaintainserviceFor23 purchaseIn =
        NCLocator.getInstance().lookup(IPurchaseInMaintainserviceFor23.class);
    try {
      return purchaseIn.queryPurchaseVOsFor23(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  public static Map<String, UFBoolean> getMapBysrcHid(String[] hids) {
    IPurchaseInQueryFor21 purchaseIn =
        NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
    try {
      return purchaseIn.getMapBysrcHid(hids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  /**
   * 方法功能描述：得到入库单交易类型是否影响成本标志。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trantypes 交易类型ID数组（V61调整）
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-5-22 下午10:18:16
   */
  public static Map<String, UFBoolean> getStockTrantypeAffectCostFlag(
      String[] trantypes) {
    IInOutTransTypeQueryService service =
        NCLocator.getInstance().lookup(IInOutTransTypeQueryService.class);
    Map<String, UFBoolean> retMap =
        new HashMap<String, UFBoolean>(trantypes.length);
    for (int i = 0; i < trantypes.length; i++) {
      String type = trantypes[i];
      try {
        retMap.put(type, service.isAffectCostById(type) ? UFBoolean.TRUE
            : UFBoolean.FALSE);
      }
      catch (BusinessException e) {
        // 日志异常
        ExceptionUtils.wrappException(e);
      }
    }
    return retMap;
  }

  /**
   * 根据订单号查询入库单表体id
   * 
   * @param codes
   * @return key:采购订单号 value：入库单表体id
   */
  public static MapList<String, String> queryBidByOrderCode(String[] codes) {
    try {
      IPurchaseInQueryFor21 service =
          NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
      return service.queryBidByOrderCode(codes);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }
  
  /**
   * 根据订单号查询入库单表体id
   * 
   * @param bids
   * @return key:采购订单号 value：入库单表体id
   */
  public static MapList<String, String> queryBidByOrderBid(String[] bids) {
    try {
      IPurchaseInQueryFor21 service =
          NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
      return service.queryPurchaseInBIDsByFirstBIDs(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * 查询到货单对应的入库单是否已经转固
   * 
   * @param bids到货单表体ID
   * @return key：到货单主键 value：已经转固为Y，否则为N
   */
  public static Map<String, UFBoolean> queryIsFixedAssetFor23(String[] bids) {
    try {
      IPurchaseInMaintainserviceFor23 service =
          NCLocator.getInstance().lookup(IPurchaseInMaintainserviceFor23.class);
      return service.queryIsFixedAssetFor23(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 根据入库单id找到订单号
   * 
   * @param bids
   * @return
   */
  public static Map<String, String> queryOrderCodeByPurchaseInBid(String[] bids) {
    IPurchaseInQueryFor21 service =
        NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
    try {
      return service.queryOrderCodeByPurchaseInBid(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 方法功能描述：采购订单回写退库单45
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          回写参数
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-4 上午11:32:18
   */
  public static void writeback45For21(IICGenRewritePara[] paras) {
    IRewrite45For21 service =
        NCLocator.getInstance().lookup(IRewrite45For21.class);
    try {
      service.rewrite45For21Rep(paras);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购发票回写采购入库单45。
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbVos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-3 上午08:48:23
   */
  public static void writeback45For25(IParameter45For25[] wbVos) {
    IRewrite45For25 service =
        NCLocator.getInstance().lookup(IRewrite45For25.class);
    try {
      service.rewrite45AccInNumFor25(wbVos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

}
