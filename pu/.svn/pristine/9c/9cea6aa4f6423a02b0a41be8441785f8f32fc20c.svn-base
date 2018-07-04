package nc.vo.pu.m21.rule.vat.setter.country;

import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单原产国默认值处理类，默认值取发货地址国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderOrigCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderOrigCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.origCountry, bill, rows);
  }

  public OrderOrigCountrySetter(IKeyValue[] bills) {
    super(CountryType.origCountry, bills);
  }

  @Override
  public void setCountry() {
    // 默认值取发货地址国家
    if (ArrayUtils.isEmpty(this.rows)) {
      new OrderSendAddressCountrySetter(this.countryType, this.bills)
          .setCountry();
    }
    else {
      new OrderSendAddressCountrySetter(this.countryType, this.bills[0],
          this.rows).setCountry();
    }
  }

}
