package nc.vo.pu.m21.rule.vat.setter.area;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.vo.bd.cust.addressdoc.AddressDocVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.IAreaSetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 订单原产地区默认值处理类，默认值取发货地址国家行政地区
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderOrigAreaSetter extends AbstractOrderCountrySetter implements
    IAreaSetter {

  public OrderOrigAreaSetter(IKeyValue bill, int[] rows) {
    // 以地区代替国家
    super(CountryType.origArea, bill, rows);
  }

  public OrderOrigAreaSetter(IKeyValue[] bills) {
    // 以地区代替国家
    super(CountryType.origArea, bills);
  }

  @Override
  public void setArea() {
    // 默认值取发货地址国家行政地区
    String[] pkValues = this.getQueryPKs(VatFieldType.supplierSendAddr);
    Map<String, String> valueMap = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(pkValues)) {
      for (String pkValue : pkValues) {
        AddressDocVO docvo = AddrdocPubService.queryAddrDocVOByID(pkValue);
        if (docvo != null && !StringUtils.isEmpty(docvo.getProvince())) {
          valueMap.put(pkValue, docvo.getProvince());
        }
      }
      this.setValue(valueMap, VatFieldType.supplierSendAddr);
    }
  }

}
