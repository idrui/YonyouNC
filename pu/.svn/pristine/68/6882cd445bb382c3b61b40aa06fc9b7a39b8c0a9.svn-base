package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 根据收获库存组织设置国家处理类
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderReceiveStoreOrgCountrySetter extends
    AbstractOrderCountrySetter implements ICountrySetter {

  public OrderReceiveStoreOrgCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderReceiveStoreOrgCountrySetter(CountryType country,
      IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {
    // 取收货库存组织所在国家
    String[] pkValues = this.getQueryPKs(VatFieldType.recieveStoreOrg);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          StockOrgPubService.queryCountryByStockOrg(pkValues);
      this.setValue(valueMap, VatFieldType.recieveStoreOrg);
    }

  }

  private boolean needSetReceiveCountry(IKeyValue bill) {
    UFBoolean direct = UFBoolean.FALSE;
    Object value = bill.getHeadValue(OrderHeaderVO.BDIRECT);
    if (value != null) {
      if (value instanceof Boolean) {
        if (Boolean.TRUE.equals(value)) {
          direct = UFBoolean.TRUE;
        }
      }
      else if (value instanceof UFBoolean) {
        direct = (UFBoolean) value;
      }

    }
    // 非直运，重新找收货国家
    if (UFBoolean.FALSE.equals(direct)) {
      return true;
    }
    return false;
  }

  private boolean needSetTaxCountry(IKeyValue bill, int row) {
    String arriveStoreOrg =
        (String) bill.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
    String settleOrg =
        (String) bill.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);
    // 财务组织空的时候或者两组织相等
    if (StringUtils.isEmpty(settleOrg) || settleOrg.equals(arriveStoreOrg)) {
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
    // 同一公司时
    if (financeOrgCorpMap.get(settleOrg) != null
        && financeOrgCorpMap.get(settleOrg).equals(
            storeOrgCorpMap.get(arriveStoreOrg))) {
      return true;
    }
    return false;
  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    if (!this.isFromSourcebill()) {
      // 收货国时
      if (CountryType.receiveCountry.equals(this.countryType)) {
        return this.needSetReceiveCountry(bill);
      }
      // 报税国
      if (CountryType.taxCountry.equals(this.countryType)) {
        return this.needSetTaxCountry(bill, row);
      }
    }
    return super.needSetCountry(bill, row);
  }
}
