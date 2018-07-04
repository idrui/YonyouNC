package nc.impl.pu.m20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillPriceInfo;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.pp.m29.pu.m20.IAskBillQueryFor20;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.pu.m20.entity.PrayPriceInfoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 请购单价格论证表服务实现
 * 
 * @since 6.0
 * @version 2011-3-22 上午09:12:29
 * @author 田锋涛
 */

public class PraybillPriceInfoImpl implements IPraybillPriceInfo {

  /**
   * 订单最新价询价参数
   * 
   * @since 6.0
   * @version 2011-3-22 上午10:11:35
   * @author 田锋涛
   */
  public static class OrderPriceQueryPara implements IOrderPriceQueryParam {

    private String financeOrg;

    private String material;

    private UFDouble origPrice;

    private UFDouble origTaxPrice;

    private UFDouble price;

    private UFDouble taxPrice;

    public OrderPriceQueryPara(String pk_srcmaterial) {
      this.material = pk_srcmaterial;
    }

    @Override
    public String getFinanceOrg() {
      return this.financeOrg;
    }

    /**
     * @return material
     */
    @Override
    public String getMaterial() {
      return this.material;
    }

    /**
     * @return origPrice
     */
    @Override
    public UFDouble getOrigPrice() {
      return this.origPrice;
    }

    /**
     * @return origTaxPrice
     */
    @Override
    public UFDouble getOrigTaxPrice() {
      return this.origTaxPrice;
    }

    /**
     * @return price
     */
    @Override
    public UFDouble getPrice() {
      return this.price;
    }

    /**
     * @return taxPrice
     */
    @Override
    public UFDouble getTaxPrice() {
      return this.taxPrice;
    }

    @Override
    public void setFinanceOrg(String financeOrg) {
      this.financeOrg = financeOrg;
    }

    /**
     * @param material 要设置的 material
     */
    public void setMaterial(String material) {
      this.material = material;
    }

    /**
     * @param origPrice 要设置的 origPrice
     */
    @Override
    public void setOrigPrice(UFDouble origPrice) {
      this.origPrice = origPrice;
    }

    /**
     * @param origTaxPrice 要设置的 origTaxPrice
     */
    @Override
    public void setOrigTaxPrice(UFDouble origTaxPrice) {
      this.origTaxPrice = origTaxPrice;
    }

    /**
     * @param price 要设置的 price
     */
    @Override
    public void setPrice(UFDouble price) {
      this.price = price;
    }

    /**
     * @param taxPrice 要设置的 taxPrice
     */
    @Override
    public void setTaxPrice(UFDouble taxPrice) {
      this.taxPrice = taxPrice;
    }

  }

  @Override
  public PrayPriceInfoVO[] queryPriceInfo(PrayPriceInfoVO[] priceInfoVOs,
      String dateWhere) throws BusinessException {
    try {
      // 1.查询询报价单最低价（自动转换为本位币价格）
      PrayPriceInfoVO[] queryInfoVOs =
          this.queryAskbillPrice(priceInfoVOs, dateWhere);
      // 2.查询采购最新价（不涉及价格转换）
      queryInfoVOs = this.queryOrderLatestPrice(priceInfoVOs);
      for (PrayPriceInfoVO queryInfoVO : queryInfoVOs) {
        if (PubAppTool.isNull(queryInfoVO.getCcurrencyid())) {
          String ccurrency =
              OrgUnitPubService.queryOrgCurrByPk(queryInfoVO.getPk_org());
          queryInfoVO.setCcurrencyid(ccurrency);
        }
      }
      return queryInfoVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 查询询报价单最低价
   * 
   * @param priceInfoVOs
   * @param dateWhere
   * @return
   * @throws BusinessException
   */
  private PrayPriceInfoVO[] queryAskbillPrice(PrayPriceInfoVO[] priceInfoVOs,
      String dateWhere) throws BusinessException {
    try {
      if (!SysInitGroupQuery.isPPEnabled()) {
        return priceInfoVOs;
      }
      // 查询询报价单最低价
      IAskBillQueryFor20 askbillSrv =
          NCLocator.getInstance().lookup(IAskBillQueryFor20.class);
      return askbillSrv.queryPriceInfo(priceInfoVOs, dateWhere);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 查询采购组织对应的订单最新价
   * 
   * @param priceInfoVOs
   * @return
   * @throws BusinessException
   */
  private PrayPriceInfoVO[] queryOrderLatestPrice(PrayPriceInfoVO[] priceInfoVOs)
      throws BusinessException {
    try {
      IOrderPriceQuery orderSrv =
          NCLocator.getInstance().lookup(IOrderPriceQuery.class);
      // TODO tianft 暂时循环调用，以后考虑订单提供批查的服务
      // 改为批量查询
      Map<String, Set<OrderPriceQueryPara>> map =
          new HashMap<String, Set<OrderPriceQueryPara>>();
      for (PrayPriceInfoVO vo : priceInfoVOs) {
        Set<OrderPriceQueryPara> set = map.get(vo.getPk_org());
        if (set == null) {
          set = new HashSet<OrderPriceQueryPara>();
          map.put(vo.getPk_org(), set);
        }
        set.add(new OrderPriceQueryPara(vo.getPk_srcmaterial()));
      }
      HashMap<String, UFDouble> priceMap = new HashMap<String, UFDouble>();
      for (Entry<String, Set<OrderPriceQueryPara>> entry : map.entrySet()) {
        IOrderPriceQueryParam[] queryLatestPrice =
            orderSrv.queryLatestPrice(entry.getKey(), null, null, entry
                .getValue().toArray(new OrderPriceQueryPara[0]));
        for (IOrderPriceQueryParam param : queryLatestPrice) {
          priceMap.put(entry.getKey() + "-" + param.getMaterial(),
              param.getPrice());
        }
      }
      for (PrayPriceInfoVO vo : priceInfoVOs) {
        String key = vo.getPk_org() + "-" + vo.getPk_material();
        // 主本币价
        vo.setNlatestprice(priceMap.get(key));
      }
      return priceInfoVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
