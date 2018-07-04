package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * �����ջ��ַ���ù���
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:29:09
 * @author tianft
 */
public class OrderReceiveAddressCountrySetter extends
    AbstractOrderCountrySetter implements ICountrySetter {

  public OrderReceiveAddressCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderReceiveAddressCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {
    // ��ȡ�ջ���ַ
    String[] pkValues = this.getQueryPKs(VatFieldType.recieveAddr);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          AddrdocPubService.queryCountryByAddrdoc(pkValues);
      this.setValue(valueMap, VatFieldType.recieveAddr);
    }

  }

}
