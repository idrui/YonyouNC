package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单收货国默认值处理类
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:29:09
 * @author tianft
 */
public class OrderReceiveCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderReceiveCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.receiveCountry, bill, rows);
  }

  public OrderReceiveCountrySetter(IKeyValue[] bills) {
    super(CountryType.receiveCountry, bills);
  }

  @Override
  public void setCountry() {

    if (ArrayUtils.isEmpty(this.rows)) {
      // 第1步：如果 是直运业务, 收货地址非空时，取收货地址国家;否则，取客户档案国家。
      this.setValueByDirect(this.bills);
      // 第2步： 否则 取收货库存组织国家
      new OrderReceiveStoreOrgCountrySetter(CountryType.receiveCountry,
          this.bills).setCountry();
      // 第3步：如果为空，取结算财务组织在国家
      new OrderFinanceOrgCountrySetter(CountryType.receiveCountry, this.bills)
          .setCountry();

    }
    else {
      // 第1步：如果 是直运业务, 收货地址非空时，取收货地址国家;否则，取客户档案国家。
      this.setValueByDirect(this.bills[0], this.rows);
      // 第2步： 否则 取收货库存组织国家
      new OrderReceiveStoreOrgCountrySetter(CountryType.receiveCountry,
          this.bills[0], this.rows).setCountry();
      // 第3步：如果为空，取结算财务组织在国家
      new OrderFinanceOrgCountrySetter(CountryType.receiveCountry,
          this.bills[0], this.rows).setCountry();
    }

  }

  /**
   * 根据是否直运过滤单据
   * 
   * @param bills 订单
   * @param direct 是否直运
   * @return 过滤后的订单
   */
  private IKeyValue[] filterBills(IKeyValue[] orderBills, UFBoolean direct) {
    List<IKeyValue> billList = new ArrayList<IKeyValue>();
    for (IKeyValue bill : orderBills) {
      UFBoolean bDirect = (UFBoolean) bill.getHeadValue(OrderHeaderVO.BDIRECT);
      if (bDirect == null) {
        bDirect = UFBoolean.FALSE;
      }
      if (direct.equals(bDirect)) {
        billList.add(bill);
      }
    }
    if (billList.size() > 0) {
      return billList.toArray(new IKeyValue[billList.size()]);
    }
    return null;
  }

  private void setValueByDirect(IKeyValue bill, int[] rows) {
    if (!UFBoolean.TRUE.equals(bill.getHeadValue(OrderHeaderVO.BDIRECT))) {
      return;
    }
    // 收货地址
    new OrderReceiveAddressCountrySetter(this.countryType, bill, rows)
        .setCountry();
    // 客户档案
    new OrderCustomerCountrySetter(CountryType.receiveCountry, bill, rows)
        .setCountry();
  }

  /**
   * 直运业务处理
   * 
   * @param bills
   */
  private void setValueByDirect(IKeyValue[] bills) {
    IKeyValue[] directBills = this.filterBills(bills, UFBoolean.TRUE);
    if (ArrayUtils.isEmpty(directBills)) {
      return;
    }
    // 收货地址
    new OrderReceiveAddressCountrySetter(this.countryType, bills).setCountry();
    // 客户档案
    new OrderCustomerCountrySetter(this.countryType, bills).setCountry();
  }
}
