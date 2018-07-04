package nc.vo.pu.m21.rule.vat.setter.country;

import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单目的国默认值处理类，默认取收货地址上的国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderDestCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderDestCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.destCountry, bill, rows);
  }

  public OrderDestCountrySetter(IKeyValue[] bills) {
    super(CountryType.destCountry, bills);
  }

  @Override
  public void setCountry() {
    if (ArrayUtils.isEmpty(this.rows)) {
      // 收货地址
      new OrderReceiveAddressCountrySetter(this.countryType, this.bills)
          .setCountry();
    }
    else {
      // 收货地址
      new OrderReceiveAddressCountrySetter(this.countryType, this.bills[0],
          this.rows).setCountry();
    }
  }

}
