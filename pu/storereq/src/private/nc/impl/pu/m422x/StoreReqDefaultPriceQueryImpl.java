package nc.impl.pu.m422x;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pu.m21.price.IPriceStrategy;
import nc.itf.pu.m422x.IStoreReqDefaultPriceQuery;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.para.SysParaInitQuery;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.query.price.OrderPriceQueryDetail;
import nc.vo.pu.m21.query.price.OrderPriceQueryParam;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.0
 * @version 2015-8-17 上午10:42:40
 * @author sunwei
 */
public class StoreReqDefaultPriceQueryImpl implements
    IStoreReqDefaultPriceQuery {

  @Override
  public OrderPriceQueryParam[] queryOrderPrice4StoreReq(
      OrderPriceQueryParam[] params) throws nc.vo.pub.BusinessException {
  	for (OrderPriceQueryParam param : params) {
  		try {
  			if (param == null || param.getDetail() == null
  					|| param.getDetail().length == 0) {
  				continue;
  			}
  			
  			// 清除价格信息
  			this.clearPrices(param);
  			
  			String purchaseOrgId = param.getPurchaseOrg();
  			
  			// TODO 根据参数PO78获得需要执行的询价策略列表
  			String po78 = SysParaInitQuery.getParaString(purchaseOrgId, "PO78");
  			if (StringUtil.isEmptyWithTrim(po78)) {
  				continue;
  			}
  			
  			String[] sparams = po78.split(",");
  			PriceSource[] pss = new PriceSource[sparams.length];
  			for (int i = 0, len = sparams.length; i < len; i++) {
  				pss[i] = PriceSource.valueOf(sparams[i]);
  			}
  			
  			// 读取物料档案上的采购组织页签的属性
  			Map<String, MaterialPuVO> puvoMap = this.queryMaterialPuVO(param);
  			
  			// 物资需求申请单上只有本币含税单价，所以默认为含税优先
  			PricePriority pp = PricePriority.TAXPRICE_PRIOR_TO_PRICE;
  			
  			// 根据询价策略列表依次执行相应的询价策略
  			StoreReqPriceStrategyFactory fatory =
  					StoreReqPriceStrategyFactory.getInstance();
  			for (PriceSource ps : pss) {
  				// 如果遇到“无默认”价格，将会中止询价
  				if (ps == PriceSource.NoDefault) {
  					break;
  				}
  				// 如果所有的订单明细均已经询到价，将会中止询价
  				OrderPriceQueryParam newParam = this.getParamsToPrice(param);
  				if (newParam.getDetail().length == 0) {
  					break;
  				}
  				// 执行询价策略进行询价
  				IPriceStrategy strategy = fatory.getPriceStrategy(ps);
  				strategy.setQueryParameter(newParam);
  				strategy.setMaterialPuVO(puvoMap);
  				strategy.setPricePiority(pp);
  				strategy.queryPrice();
  			}
  		}
  		catch (Exception e) {
  			ExceptionUtils.marsh(e);
  		}
		}
    return params;
  }

  private void clearPrices(OrderPriceQueryParam param) {
    for (OrderPriceQueryDetail detail : param.getDetail()) {
      detail.setPrice(null);
      detail.setTaxPrice(null);
      detail.setOrigPrice(null);
      detail.setOrigTaxPrice(null);
    }
  }

  /**
   * 方法功能描述：获得尚未询到价格的参数列表。
   * <p>
   * <b>参数说明</b>
   * 
   * @param params
   *          前台传过来的询价参数的对象数组
   * @return 尚未询到价格的参数列表
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 下午09:52:14
   */
  private OrderPriceQueryParam getParamsToPrice(OrderPriceQueryParam param) {
    List<OrderPriceQueryDetail> priceParam =
        new ArrayList<OrderPriceQueryDetail>();
    for (OrderPriceQueryDetail detail : param.getDetail()) {
      if (detail.getPrice() == null && detail.getTaxPrice() == null
          && detail.getOrigPrice() == null && detail.getOrigTaxPrice() == null) {
        priceParam.add(detail);
      }
    }
    OrderPriceQueryParam pc = new OrderPriceQueryParam();
    pc.setPurchaseOrg(param.getPurchaseOrg());
    pc.setSupplier(param.getSupplier());
    pc.setCurrency(param.getCurrency());
    pc.setBillDate(param.getBillDate());
    pc.setDetail(priceParam.toArray(new OrderPriceQueryDetail[priceParam.size()]));

    return pc;
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
}
