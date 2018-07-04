package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 根据供应商设置国家
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderSupplierCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderSupplierCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderSupplierCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {
    String[] pkValues = this.getQueryPKs(VatFieldType.supplier);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          SupplierPubService.queryCountryBySupplier(pkValues);
      this.setValue(valueMap, VatFieldType.supplier);
    }

  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    boolean need = super.needSetCountry(bill, row);
    // vo交换情况不按此处理
    if (!this.isFromSourcebill()) {
      String pk_sendAddress =
          (String) bill.getHeadValue(OrderHeaderVO.PK_DELIVERADD);
      // 收货地址空的时候需要重新设置
      return need || StringUtils.isEmpty(pk_sendAddress);

    }
    return need;
  }

}
