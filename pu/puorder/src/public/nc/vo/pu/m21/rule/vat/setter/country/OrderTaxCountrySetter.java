package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 订单报税货国默认值处理类
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderTaxCountrySetter extends AbstractOrderCountrySetter implements
    ICountrySetter {

  public OrderTaxCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.taxCountry, bill, rows);
  }

  public OrderTaxCountrySetter(IKeyValue[] bills) {
    super(CountryType.taxCountry, bills);
  }

  @Override
  public void setCountry() {
    this.setCountryValue();
  }

  /**
   * 财务组织所属公司
   * 
   * @return 组织-公司 map结构
   */
  private Map<String, String> getFinanceOrgCorp() {
    String[] financeOrgValues = this.getQueryPKs(VatFieldType.financeOrg);
    Map<String, String> financeOrgCorpMap = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(financeOrgValues)) {
      financeOrgCorpMap = FinanceOrgPubService.queryPKCorp(financeOrgValues);
    }
    if (null == financeOrgCorpMap) {
      financeOrgCorpMap = new HashMap<String, String>();
    }
    return financeOrgCorpMap;
  }

  /**
   * 财务组织所属国家
   * 
   * @return 组织-国家 map结构
   */
  private Map<String, String> getFinanceOrgCountry() {
    String[] financeOrgValues = this.getQueryPKs(VatFieldType.financeOrg);
    Map<String, String> financeOrgCountryMap = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(financeOrgValues)) {
      financeOrgCountryMap =
          FinanceOrgPubService.queryCountryByFinanceOrg(financeOrgValues);
    }
    if (null == financeOrgCountryMap) {
      financeOrgCountryMap = new HashMap<String, String>();
    }
    return financeOrgCountryMap;
  }

  /**
   * 收货库存组织所属公司
   * 
   * @return 组织-公司 map结构
   */
  private Map<String, String> getRecievOrgCorp() {
    String[] OrgValues = this.getQueryPKs(VatFieldType.recieveStoreOrg);
    Map<String, String> mapValue = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(OrgValues)) {
      mapValue = StockOrgPubService.queryCorpIDByStockOrgIDs(OrgValues);
    }
    if (null == mapValue) {
      mapValue = new HashMap<String, String>();
    }
    return mapValue;
  }

  /**
   * 收货库存组织所属国家
   * 
   * @return 组织-国家 map结构
   */
  private Map<String, String> getRecievOrgCountry() {
    String[] OrgValues = this.getQueryPKs(VatFieldType.recieveStoreOrg);
    Map<String, String> mapValue = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(OrgValues)) {
      mapValue = StockOrgPubService.queryCountryByStockOrg(OrgValues);
    }
    if (null == mapValue) {
      mapValue = new HashMap<String, String>();
    }
    return mapValue;
  }

  /**
   * 赋值逻辑：
   * 1. 如果 采购结算组织与收货库存组织跨公司,取结算财务组织所在国家
   * 2. 否则 取收货库存组织所在国家
   */
  private void setCountryValue() {
    Map<String, String> financeOrgCountryMap = this.getFinanceOrgCountry();
    Map<String, String> financeOrgCorpMap = this.getFinanceOrgCorp();
    Map<String, String> recievOrgCountryMap = this.getRecievOrgCountry();
    Map<String, String> recievOrgCorpMap = this.getRecievOrgCorp();

    for (IKeyValue bill : this.bills) {
      int[] tempRows = this.rows;
      if (ArrayUtils.isEmpty(tempRows)) {
        tempRows = new int[bill.getItemCount()];
        for (int i = 0; i < tempRows.length; i++) {
          tempRows[i] = i;
        }
      }
      for (int i = 0; i < tempRows.length; i++) {
        String countryValue =
            (String) bill.getBodyValue(tempRows[i], this.countryType.getCode());
        // 国家非空，不处理
        if (StringUtils.isNotBlank(countryValue)) {
          continue;
        }
        String financeOrg =
            (String) bill
                .getBodyValue(tempRows[i], OrderItemVO.PK_PSFINANCEORG);
        String recievOrg =
            (String) bill.getBodyValue(tempRows[i], OrderItemVO.PK_ARRVSTOORG);
        // 财务组织不空并且财务组织不等收货库存组织
        if (StringUtils.isNotBlank(financeOrg) && !financeOrg.equals(recievOrg)) {
          // 不属于同一公司则取财务组织所属国家
          if (financeOrgCorpMap.get(financeOrg) != null
              && !financeOrgCorpMap.get(financeOrg).equals(
                  recievOrgCorpMap.get(recievOrg))) {
            bill.setBodyValue(tempRows[i], this.countryType.getCode(),
                financeOrgCountryMap.get(financeOrg));
            continue;
          }
        }
        // 否则取收货库存组织所属国家
        bill.setBodyValue(tempRows[i], this.countryType.getCode(),
            recievOrgCountryMap.get(recievOrg));
      }
    }
  }
}
