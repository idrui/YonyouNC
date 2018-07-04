/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-22 上午09:52:52
 */
package nc.impl.pu.m21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pu.m21.price.IPriceStrategy;
import nc.impl.pu.m21.price.PriceStrategyFactory;
import nc.itf.pu.m21.IOrderDefPriceSideForm;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单侧边栏询价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-22 上午09:52:52
 */
public class OrderDefPriceSideFormImpl implements IOrderDefPriceSideForm {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderDefPriceSideForm#queryOrderPrice(nc.vo.pu.m21.query.price.OrderPriceQueryParam)
   */
  @Override
  public OrderPriceQueryParam queryOrderPrice(OrderPriceQueryParam param,
      PriceSource ps, PricePriority pp) throws BusinessException {

    try {
      if (param == null || param.getDetail() == null
          || param.getDetail().length == 0) {
        return param;
      }

      // 读取物料档案上的采购组织页签的属性
      Map<String, MaterialPuVO> puvoMap = this.queryMaterialPuVO(param);

      // 清除价格信息
      this.clearPrices(param);
      // 设置日期范围，2011.8.17根据性能组林XX和刘XX，以及需求wangyf的要求，最新价只询一个月内的订单
      // V61询价算法改进，不再限制一个月
      // this.setDateRange(param);
      // 根据询价策略列表依次执行相应的询价策略
      PriceStrategyFactory fatory = PriceStrategyFactory.getInstance();
      IPriceStrategy strategy = fatory.getPriceStrategy(ps);
      strategy.setQueryParameter(param);
      strategy.setMaterialPuVO(puvoMap);
      strategy.setPricePiority(pp);
      strategy.queryPrice();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return param;

  }

  /**
   * 方法功能描述：清楚价格
   * <p>
   * <b>参数说明</b>
   * 
   * @param param <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-22 下午01:05:58
   */
  private void clearPrices(OrderPriceQueryParam param) {
    for (OrderPriceQueryDetail detail : param.getDetail()) {
      detail.setPrice(null);
      detail.setTaxPrice(null);
      detail.setOrigPrice(null);
      detail.setOrigTaxPrice(null);
    }
  }

  private Map<String, MaterialPuVO> queryMaterialPuVO(OrderPriceQueryParam param) {
    Map<String, MaterialPuVO> map = null;
    String pk_purchaseorg = param.getPurchaseOrg();
    List<String> itemIds = new ArrayList<String>(param.getDetail().length);
    for (OrderPriceQueryDetail detail : param.getDetail()) {
      itemIds.add(detail.getMaterial());
    }
    map =
        MaterialPubService.queryMaterialPurchaseInfoMap(itemIds,
            pk_purchaseorg, new String[] {
              MaterialPuVO.ISINQUIREBYFACTORY,
              MaterialPuVO.ISINQUIREBYQLTYLEVEL,
              MaterialPuVO.ISINQUIREBYRECTAREA,
              MaterialPuVO.ISINQUIREBYSUPPLIER,
              MaterialPuVO.ISINQUIREBYTRANSTYPE, MaterialPuVO.PK_MATERIAL
            });
    return map;
  }

  // private void setDateRange(OrderPriceQueryParam param) {
  // UFDateTime dt = AppContext.getInstance().getServerTime();
  // param.setStartDate(dt.getDateTimeBefore(30).getBeginDate());
  // param.setEndDate(dt.getEndDate());
  // }
}
