package nc.vo.pu.m21.rule.vat.setter.country;

import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * ����Ŀ�Ĺ�Ĭ��ֵ�����࣬Ĭ��ȡ�ջ���ַ�ϵĹ���
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:29:09
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
      // �ջ���ַ
      new OrderReceiveAddressCountrySetter(this.countryType, this.bills)
          .setCountry();
    }
    else {
      // �ջ���ַ
      new OrderReceiveAddressCountrySetter(this.countryType, this.bills[0],
          this.rows).setCountry();
    }
  }

}
