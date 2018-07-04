package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 根据客户设置国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderCustomerCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderCustomerCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderCustomerCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {
    String[] pkValues;
    pkValues = this.getQueryPKs(VatFieldType.receiveCustomer);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          CustomerPubService.queryCountryByCustomer(pkValues);
      this.setValue(valueMap, VatFieldType.receiveCustomer);
    }

  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    boolean direct = false;
    Object directValue = bill.getHeadValue(OrderHeaderVO.BDIRECT);
    if (directValue != null) {
      if (directValue instanceof UFBoolean) {
        direct = ((UFBoolean) directValue).booleanValue();
      }
      else if (directValue instanceof Boolean) {
        direct = ((Boolean) directValue).booleanValue();
      }
    }
    String addr =
        (String) bill.getBodyValue(row, OrderItemVO.PK_RECEIVEADDRESS);
    return direct && StringUtils.isEmpty(addr)
        || super.needSetCountry(bill, row);
  }

}
