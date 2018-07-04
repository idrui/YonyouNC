package nc.vo.pu.m21.rule.vat.setter.country;

import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单发货国默认值处理类
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderSendCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  // public OrderSendCountrySetter(CountryType country) {
  // super(country);
  // }

  public OrderSendCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.sendCountry, bill, rows);
  }

  public OrderSendCountrySetter(IKeyValue[] bills) {
    super(CountryType.sendCountry, bills);
  }

  @Override
  public void setCountry() {
    if (ArrayUtils.isEmpty(this.rows)) {
      // 1. 如果 供应商发货地址非空,取发货地址国家
      new OrderSendAddressCountrySetter(this.countryType, this.bills)
          .setCountry();
      // 2. 否则,取供应商档案国家
      new OrderSupplierCountrySetter(this.countryType, this.bills).setCountry();
    }
    else {
      // 1. 如果 供应商发货地址非空,取发货地址国家
      new OrderSendAddressCountrySetter(this.countryType, this.bills[0],
          this.rows).setCountry();
      // 2. 否则,取供应商档案国家
      new OrderSupplierCountrySetter(this.countryType, this.bills[0], this.rows)
          .setCountry();
    }
  }

}
