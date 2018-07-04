package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * 根据供应商发货地址设置国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderSendAddressCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderSendAddressCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderSendAddressCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {
    // 先取收货地址
    String[] pkValues = this.getQueryPKs(VatFieldType.supplierSendAddr);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          AddrdocPubService.queryCountryByAddrdoc(pkValues);
      this.setValue(valueMap, VatFieldType.supplierSendAddr);
    }

  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    if (!this.isFromSourcebill()) {
      return true;
    }
    return super.needSetCountry(bill, row);
  }

}
