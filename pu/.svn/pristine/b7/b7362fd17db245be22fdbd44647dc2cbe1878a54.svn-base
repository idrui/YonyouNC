package nc.impl.pu.m25.pricequery;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IPriceQuery;
import nc.pubitf.pu.m24.IQueryPricestl;
import nc.vo.pu.m24.entity.PricParaVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 优质优价查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:16:07
 * @author 田锋涛
 */

public class HqHpPriceQuery implements IPriceQuery {

  public HqHpPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] paras) {
    String[] stockItemIds = this.getStockItemIds(paras);
    if (ArrayUtils.isEmpty(stockItemIds)) {
      return;
    }
    try {
      Map<String, PricParaVO> priceVOMap =
          this.queryPricStlPrices(stockItemIds);
      if (priceVOMap == null || priceVOMap.size() == 0) {
        return;
      }
      for (InvoicePriceQueryVO para : paras) {
        if (priceVOMap.containsKey(para.getPk_stockps_b())) {
          // 主本币含税单价
          para.setNtaxprice(priceVOMap.get(para.getPk_stockps_b())
              .getNtaxprice());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 入库单行id
   * 
   * @param paras
   * @return
   */
  private String[] getStockItemIds(InvoicePriceQueryVO[] paras) {
    Set<String> stockBIds = new HashSet<String>();
    for (InvoicePriceQueryVO para : paras) {
      if (para.getPk_stockps_b() != null) {
        stockBIds.add(para.getPk_stockps_b());
      }
    }
    if (stockBIds.size() == 0) {
      return null;
    }
    return stockBIds.toArray(new String[stockBIds.size()]);
  }

  /**
   * 优质优价查询
   * 
   * @param stockBids
   * @return
   * @throws BusinessException
   */
  private Map<String, PricParaVO> queryPricStlPrices(String[] stockBids)
      throws BusinessException {
    IQueryPricestl hqhpSrv =
        NCLocator.getInstance().lookup(IQueryPricestl.class);
    PricParaVO[] pricVos = hqhpSrv.queryPricStlPrices(stockBids);
    if (ArrayUtils.isEmpty(pricVos)) {
      return null;
    }
    Map<String, PricParaVO> retMap = new HashMap<String, PricParaVO>();
    for (PricParaVO pricParaVO : pricVos) {
      retMap.put(pricParaVO.getCgeneralbid(), pricParaVO);
    }
    return retMap;
  }

}
