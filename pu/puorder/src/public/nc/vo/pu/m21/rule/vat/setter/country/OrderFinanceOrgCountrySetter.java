package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 根据结算财务设置国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderFinanceOrgCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderFinanceOrgCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderFinanceOrgCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {

    String[] pkValues = this.getQueryPKs(VatFieldType.financeOrg);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          FinanceOrgPubService.queryCountryByFinanceOrg(pkValues);
      this.setValue(valueMap, VatFieldType.financeOrg);
    }

  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    // boolean need = super.needSetCountry(bill, row);
    // 报税国：采购结算组织与收货库存组织跨公司， 取结算财务组织所在国家
    if (!this.isFromSourcebill()
        && CountryType.taxCountry.equals(this.countryType)) {
      String arriveStoreOrg =
          (String) bill.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      String settleOrg =
          (String) bill.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);

      // fanly3 2013-01-29 用户如果没有配置采购业务委托关系，财务结算组织可能为空
      if (StringUtils.isEmpty(settleOrg)) {
        return false;
      }
      // 收货组织空的时候覆盖
      if (StringUtils.isEmpty(arriveStoreOrg)
          || arriveStoreOrg.equals(settleOrg)) {
        return true;
      }

      Map<String, String> financeOrgCorpMap =
          FinanceOrgPubService.queryPKCorp(new String[] {
            settleOrg
          });
      Map<String, String> storeOrgCorpMap =
          StockOrgPubService.queryCorpIDByStockOrgIDs(new String[] {
            arriveStoreOrg
          });
      // 跨公司时
      if (financeOrgCorpMap.get(settleOrg) != null
          && !financeOrgCorpMap.get(settleOrg).equals(
              storeOrgCorpMap.get(arriveStoreOrg))) {
        return true;
      }
    }

    return super.needSetCountry(bill, row);
  }

}
