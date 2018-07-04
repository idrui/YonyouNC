package nc.pubimpl.pu.m21.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m21.query.price.LatestPriceQueryBP;
import nc.bs.pu.m21.query.price.LowestPriceQueryBP;
import nc.bs.pu.m21.query.price.OrderPriceQueryBP;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.pubitf.pu.m21.pub.LastestPriceQueryVO;
import nc.vo.ct.uitl.ArrayUtil;
import nc.vo.pu.m21.query.price.OrderItemPriceVO;
import nc.vo.pu.m21.query.price.OrderPriceData;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为发票询价、暂估询价、请购单询价提供服务
 * <li>为内部交易查询最新采购价提供服务
 * <li>为生产制造查询最新采购价提供服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 下午02:59:36
 */
public class OrderPriceQueryImpl implements IOrderPriceQuery {

  @Override
  public OrderItemPriceVO[] queryItemPrice(String[] orderItemIds)
      throws BusinessException {
    try {
      OrderPriceQueryBP bp = new OrderPriceQueryBP();
      OrderItemPriceVO[] prices = bp.queryOrderItemByIds(orderItemIds);
      return prices;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new OrderItemPriceVO[0];
  }

  @Override
  public LastestPriceQueryVO[] queryLatestPrice(LastestPriceQueryVO[] queryVos)
      throws BusinessException {
    try {
      if (!ArrayUtil.isEmpty(queryVos)) {
        return this.exeQueryLastestPrice(queryVos);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new LastestPriceQueryVO[0];
  }

  @Override
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException {
    try {
      return this.exeQueryLatestPrice(purchaseOrg, supplier, currency, params,
          null, null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public IOrderPriceQueryParam[] queryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params,
      UFDate startDate, UFDate endDate) throws BusinessException {
    try {
      return this.exeQueryLatestPrice(purchaseOrg, supplier, currency, params,
          startDate, endDate);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderPriceData[] queryLatestPrice(String financeOrg, String[] materials)
      throws BusinessException {
    try {
      String[] financeOrgs = this.getArrayValue(financeOrg);

      LatestPriceQueryBP bp = new LatestPriceQueryBP();
      OrderPriceData[] prices =
          bp.queryLatestPrice(null, financeOrgs, null, null, materials, null,
              null);
      return prices;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new OrderPriceData[0];
  }

  @Override
  public IOrderPriceQueryParam[] queryLowestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params)
      throws BusinessException {
    if (StringUtils.isBlank(purchaseOrg) || ArrayUtils.isEmpty(params)) {
      return params;
    }
    try {
      String[] materials = this.getMaterials(params);
      String[] purchaseOrgs = this.getArrayValue(purchaseOrg);
      String[] suppliers = this.getArrayValue(supplier);
      String[] currencies = this.getArrayValue(currency);

      // 查询订单最低价
      LowestPriceQueryBP bp = new LowestPriceQueryBP();
      OrderPriceData[] prices =
          bp.queryLowestPrice(purchaseOrgs, suppliers, currencies, materials,
              null, null);

      // 把价格设置到参数中
      this.setPrice(params, prices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return params;
  }

  /**
   * 针对LastestPriceQueryVO询价接口，统一调用询价BP类进行处理。
   * 
   * @param queryVos 询价查询参数封装类。
   * @return 询价查询参数封装类。
   */
  private LastestPriceQueryVO[] exeQueryLastestPrice(
      LastestPriceQueryVO[] queryVos) {
    List<String> currencyList = new ArrayList<String>();
    List<String> materialList = new ArrayList<String>();
    List<String> supplierList = new ArrayList<String>();
    List<String> puchaseOrgList = new ArrayList<String>();

    for (int i = 0; i < queryVos.length; i++) {
      if (!StringUtils.isBlank(queryVos[i].getCurrency())) {
        currencyList.add(queryVos[i].getCurrency());
      }
      if (!StringUtils.isBlank(queryVos[i].getPk_srcmaterial())) {
        materialList.add(queryVos[i].getPk_srcmaterial());
      }
      if (!StringUtils.isBlank(queryVos[i].getPk_supplier())) {
        supplierList.add(queryVos[i].getPk_supplier());
      }
      if (!StringUtils.isBlank(queryVos[i].getPk_org())) {
        puchaseOrgList.add(queryVos[i].getPk_org());
      }
    }

    String[] currencies = currencyList.toArray(new String[currencyList.size()]);
    String[] materials = materialList.toArray(new String[materialList.size()]);
    String[] suppliers = supplierList.toArray(new String[supplierList.size()]);
    String[] puchaseOrgs =
        puchaseOrgList.toArray(new String[puchaseOrgList.size()]);

    // 查询订单最新价
    LatestPriceQueryBP bp = new LatestPriceQueryBP();
    OrderPriceData[] prices =
        bp.queryLatestPrice(puchaseOrgs, null, suppliers, currencies,
            materials, null, null);

    this.setPrice(queryVos, prices);
    return queryVos;
  }

  /**
   * 针对String... IOrderPriceQueryParam[]类型的询价接口，统一调用询价BP类进行处理。
   * 底层询价BP的采购组织、供应商、币种由单一值改为了批量，为适配底层BP且不影响之前的接口，
   * 此处创建字符串数组，将单值存放其中，传入BP。
   * 
   * @param purchaseOrg 采购组织
   * @param supplier 供应商
   * @param currency 币种
   * @param params 询价结果
   * @param startDate 开始日期
   * @param endDate 结束日期
   * @return 询价结果
   * @throws BusinessException
   */
  private IOrderPriceQueryParam[] exeQueryLatestPrice(String purchaseOrg,
      String supplier, String currency, IOrderPriceQueryParam[] params,
      UFDate startDate, UFDate endDate) throws BusinessException {
    if (StringUtils.isBlank(purchaseOrg) || ArrayUtils.isEmpty(params)) {
      return params;
    }
    try {
      String[] materials = this.getMaterials(params);
      String[] purchaseOrgs = this.getArrayValue(purchaseOrg);
      String[] suppliers = this.getArrayValue(supplier);
      String[] currencies = this.getArrayValue(currency);

      // 查询订单最新价
      LatestPriceQueryBP bp = new LatestPriceQueryBP();
      OrderPriceData[] prices =
          bp.queryLatestPrice(purchaseOrgs, null, suppliers, currencies,
              materials, startDate, endDate);

      // 把价格设置到参数中
      this.setPrice(params, prices);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return params;
  }

  /**
   * 将字符串转换为字符串数组。
   * 这个修改主要是为了适配接口的调整。询价接口之前采购组织、币种和供应商只能有一个，后来提供了一个批量的接口。
   * 所以之前的接口都需要将参数由String改为String[]，又由于底层是用ArrayUtils进行判空的，
   * 所以当String参数为空时，我们需要传入null，而不是[null]。转换的地方比较多，提出此方法。
   * 
   * @param value 字符串参数值。
   * @return 字符串数组参数。
   */
  private String[] getArrayValue(String value) {
    return StringUtils.isBlank(value) ? null : new String[] {
      value
    };
  }

  /**
   * 针对String... IOrderPriceQueryParam[]类型的询价接口，获取物料OID数组。
   * 此类询价接口有一个特点，即查询条件中的采购组织、币种、供应商只有一个（接口没有提供批量的参数）
   * 而物料的信息在IOrderPriceQueryParam中设置，由于是数组，可以有多个。
   * 此方法就是在调用询价BP时，将物料OID从IOrderPriceQueryParam中提取出来，作为参数传入BP类实例
   * 
   * @param params 询价查询参数封装类。
   * @return 物料OID数组
   */
  private String[] getMaterials(IOrderPriceQueryParam[] params) {
    String[] materials = new String[params.length];
    for (int i = 0; i < params.length; i++) {
      materials[i] = params[i].getMaterial();
    }
    return materials;
  }

  /**
   * 针对String... IOrderPriceQueryParam[]类型的询价接口，设置价格信息。
   * 此类询价接口有一个特点，即查询条件中的采购组织、币种、供应商只有一个（接口没有提供批量的参数）
   * 而物料的信息在IOrderPriceQueryParam中设置，由于是数组，可以有多个。
   * 所以在设置价格时，只需要物料一个维度就可以锁定结果。
   * 
   * @param params 询价查询参数封装类。
   * @param prices 询价结果数组
   */
  private void setPrice(IOrderPriceQueryParam[] params, OrderPriceData[] prices) {
    Map<String, OrderPriceData> priceMap =
        new HashMap<String, OrderPriceData>();
    for (OrderPriceData price : prices) {
      priceMap.put(price.getMaterial(), price);
    }

    for (IOrderPriceQueryParam param : params) {
      OrderPriceData price = priceMap.get(param.getMaterial());
      if (price != null) {
        param.setOrigPrice(price.getOrigPrice());
        param.setOrigTaxPrice(price.getOrigTaxPrice());
        param.setPrice(price.getPrice());
        param.setTaxPrice(price.getTaxPrice());
        param.setFinanceOrg(price.getPk_financeorg());
      }
    }
  }

  /**
   * 针对使用LastestPriceQueryVO类询价的接口，设置价格信息。
   * 由于此类接口采购组织、币种、供应商、物料都是批量的，所以相对于旧的setPrice方法，此方法不能只通过物料一个维度锁定结果。
   * 必须四个条件都符合（某条件为空则忽略之），才设置价格信息。
   * 
   * @param queryVos 询价查询数据封装类。
   * @param prices 询价结果数组。
   */
  private void setPrice(LastestPriceQueryVO[] queryVos, OrderPriceData[] prices) {
    for (OrderPriceData priceData : prices) {
      for (LastestPriceQueryVO queryVo : queryVos) {
        if ((StringUtils.isBlank(queryVo.getCurrency()) || queryVo
            .getCurrency().equals(priceData.getCurrencyId()))
            && (StringUtils.isBlank(queryVo.getPk_org()) || queryVo.getPk_org()
                .equals(priceData.getPk_purchaseOrg()))
            && (StringUtils.isBlank(queryVo.getPk_supplier()) || queryVo
                .getPk_supplier().equals(priceData.getPk_supplier()))
            && (StringUtils.isBlank(queryVo.getPk_srcmaterial()) || queryVo
                .getPk_srcmaterial().equals(priceData.getMaterial()))) {
          queryVo.setNnetprice(priceData.getPrice());
          queryVo.setNorignetprice(priceData.getOrigPrice());
          queryVo.setNorigtaxnetprice(priceData.getOrigTaxPrice());
          queryVo.setNtaxnetprice(priceData.getTaxPrice());
        }
      }
    }
  }
}
