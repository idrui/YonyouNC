package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.pu.m21.pub.IOrderPriceQuery;
import nc.pubitf.pu.m21.pub.IOrderPriceQueryParam;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.price.OrderPrice;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置主本币含税单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-1 上午11:16:16
 */
public class SetPrice {

  /**
   * 方法功能描述：设置主本币含税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:29:10
   */
  public void setPrice(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setPrice(keyValue, rows);
  }

  /**
   * 方法功能描述：根据参数PO29请购单价格处理规则设置主本币含税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param keyValue
   * @param rows <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-1 上午11:17:06
   */
  public void setPrice(IKeyValue keyValue, int[] rows) {

    if (null == keyValue.getHeadValue(PraybillHeaderVO.CCURRENCYID)) {
      return;
    }
    String pk_org = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);

    String currency =
        (String) keyValue.getHeadValue(PraybillHeaderVO.CCURRENCYID);

    String pk_financeOrg = null;
    if (null == pk_org || null == currency) {

      return;
    }
    Map<String, String> finance =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
          pk_org
        });
    if (null != finance) {
      pk_financeOrg = finance.get(pk_org);
    }

    PriceSource type = this.getPO29(pk_org);

    Map<String, MaterialFiVO> info = null;
    Map<String, IOrderPriceQueryParam> retParams = null;
    if (PriceSource.PlanPrice.equals(type)) {
      info = this.getPlanPrice(pk_financeOrg, keyValue, rows);
    } else {
      retParams = this.getOrderPrice(keyValue, rows, type);
    }
    for (int i = 0, len = rows.length; i < len; i++) {

      String pk_material =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
      String opk_org =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG);
      String supplier =
          (String) keyValue.getBodyValue(rows[i],
              PraybillItemVO.PK_SUGGESTSUPPLIER);
      if (null == pk_material || null == opk_org) {
        continue;
      }

      // 无默认
      if (PriceSource.NoDefault.equals(type)) {
        continue;
      }
      UFDouble dPrice = null;
      // 计划价
      if (PriceSource.PlanPrice.equals(type) && null != info) {
        MaterialFiVO fi = info.get(pk_material);
        if (null != fi) {
          dPrice = fi.getPlanprice();
        }
      }
      // 订单最低价或订单最新价
      if(retParams != null && (PriceSource.OrderMinPrice.equals(type) 
          || PriceSource.OrderNewestPrice.equals(type))){
        String key = opk_org + supplier + pk_material;
        IOrderPriceQueryParam param = retParams.get(key);
        if(param != null){
          dPrice = this.changeRateFromPoOrderToBuyingReq(keyValue, param);
        }
      }
      if (dPrice != null) {
        keyValue.setBodyValue(rows[i], PraybillItemVO.NTAXPRICE, dPrice);
      }

      // if (null == dPrice) {
      // keyValue.setBodyValue(rows[i], PraybillItemVO.NTAXMNY, null);
      // }
      // else {
      // UFDouble mny =
      // dPrice.multiply((UFDouble) keyValue.getBodyValue(rows[i],
      // PraybillItemVO.NNUM));
      // keyValue.setBodyValue(rows[i], PraybillItemVO.NTAXMNY, mny);
      // }
      }
  }

  private Map<String, IOrderPriceQueryParam> getOrderPrice(IKeyValue keyValue,
      int[] rows, PriceSource type) {
    // 组织+供应商作为key， 物料的集合作为value。调用接口查询
    Map<String, Set<String>> materialMap = new HashMap<>();
    // 需要返回的参数。组织+供应商+物料作为key，价格参数作为value
    Map<String, IOrderPriceQueryParam> paramMap = new HashMap<>();
    for(int row : rows){
      String pk_material =
          (String) keyValue.getBodyValue(row, PraybillItemVO.PK_SRCMATERIAL);
      String pk_org =
          (String) keyValue.getBodyValue(row, PraybillItemVO.PK_PURCHASEORG);
      String supplier =
          (String) keyValue.getBodyValue(row, PraybillItemVO.PK_SUGGESTSUPPLIER);
      String key = pk_org + "," + supplier;
      Set<String> materialList = materialMap.get(key);
      if(materialList == null){
        materialList = new HashSet<>();
        materialMap.put(key, materialList);
      }
      materialList.add(pk_material);
    }
    
    IOrderPriceQuery query =
        NCLocator.getInstance().lookup(IOrderPriceQuery.class);
    for(Entry<String, Set<String>> entry : materialMap.entrySet()){
      String pk_org = entry.getKey().split(",")[0];
      String supplier = entry.getKey().split(",")[1];
      Set<String> materialList = entry.getValue();
      List<IOrderPriceQueryParam> paramList = new ArrayList<>();
      for(String material : materialList){
        IOrderPriceQueryParam param = new OrderPrice(material);
        paramList.add(param);
      }
      try {
        IOrderPriceQueryParam[] retParams = null;
        if(PriceSource.OrderMinPrice.equals(type)){
          retParams =
              query.queryLowestPrice(pk_org, supplier, null,
                  paramList.toArray(new IOrderPriceQueryParam[0]));
        }
        if(PriceSource.OrderNewestPrice.equals(type)){
          retParams =
              query.queryLatestPrice(pk_org, supplier, null,
                  paramList.toArray(new IOrderPriceQueryParam[0]));
        }
        if(retParams == null){
          continue;
        }
        for(IOrderPriceQueryParam param : retParams){
          String material = param.getMaterial();
          String key = pk_org + supplier + material;
          paramMap.put(key, param);
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return paramMap;
  }

  /*
   * 61需求：
   * 如果本币币种不同时，则需要根据源币种（订单本位币币种）+目的币种（请购单本位币币种）+请购单单据日期匹配库存组织本位币汇率方案自动获取汇率值，进行转换。
   */
  private UFDouble changeRateFromPoOrderToBuyingReq(IKeyValue keyValue,
      IOrderPriceQueryParam retParam) {
    String currency =
        (String) keyValue.getHeadValue(PraybillHeaderVO.CCURRENCYID);
    if (retParam.getFinanceOrg() == null) {
      return null;
    }
    String PoOrderOrgCurr =
        OrgUnitPubService.queryOrgCurrByPk(retParam.getFinanceOrg());
    if (currency.equals(PoOrderOrgCurr)) {
      return retParam.getTaxPrice();
    }
    UFDouble[] priceArray = new UFDouble[] {
      retParam.getTaxPrice()
    };
    priceArray =
        CurrencyRate.getAmountsByOpp(PoOrderOrgCurr, currency, priceArray,
            null, (UFDate) keyValue.getHeadValue(PraybillHeaderVO.DBILLDATE));

    return priceArray[0];
  }

  private Map<String, MaterialFiVO> getPlanPrice(String pk_financeOrg,
      IKeyValue keyValue, int[] rows) {
    if (null == pk_financeOrg || null == rows || rows.length == 0) {
      return null;
    }

    HashSet<String> pk_materials = new HashSet<String>();
    for (int i = 0, len = rows.length; i < len; i++) {
      pk_materials.add((String) keyValue.getBodyValue(rows[i],
          PraybillItemVO.PK_MATERIAL));
    }

    Map<String, MaterialFiVO> info =
        MaterialPubService.getFIInfo(
            pk_materials.toArray(new String[pk_materials.size()]),
            pk_financeOrg, new String[] {
              MaterialFiVO.PLANPRICE
            });
    return info;
  }

  /**
   * 方法功能描述：取得参数PO29请购单价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_stockorg 库存组织
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-1 下午05:10:04
   */
  private PriceSource getPO29(String pk_stockorg) {
    return PUSysParamUtil.getPO29(pk_stockorg);
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }
}
