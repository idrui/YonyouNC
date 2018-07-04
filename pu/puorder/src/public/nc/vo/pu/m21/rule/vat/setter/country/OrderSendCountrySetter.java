package nc.vo.pu.m21.rule.vat.setter.country;

import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * ����������Ĭ��ֵ������
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:29:09
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
      // 1. ��� ��Ӧ�̷�����ַ�ǿ�,ȡ������ַ����
      new OrderSendAddressCountrySetter(this.countryType, this.bills)
          .setCountry();
      // 2. ����,ȡ��Ӧ�̵�������
      new OrderSupplierCountrySetter(this.countryType, this.bills).setCountry();
    }
    else {
      // 1. ��� ��Ӧ�̷�����ַ�ǿ�,ȡ������ַ����
      new OrderSendAddressCountrySetter(this.countryType, this.bills[0],
          this.rows).setCountry();
      // 2. ����,ȡ��Ӧ�̵�������
      new OrderSupplierCountrySetter(this.countryType, this.bills[0], this.rows)
          .setCountry();
    }
  }

}
