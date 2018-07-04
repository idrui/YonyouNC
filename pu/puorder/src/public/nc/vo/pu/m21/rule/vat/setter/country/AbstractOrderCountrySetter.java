package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 国家设置器抽象类
 * 
 * @since 6.0
 * @version 2012-2-23 下午02:21:35
 * @author tianft
 */
public class AbstractOrderCountrySetter {
  /**
   * 国家(地区)类型
   * 
   * @since 6.0
   * @version 2012-2-23 下午02:21:08
   * @author tianft
   */
  public enum CountryType {
    /** 目的地区 */
    destArea(OrderItemVO.CDESTIAREAID),
    /** 目的国家 */
    destCountry(OrderItemVO.CDESTICOUNTRYID),
    /** 原产地区 */
    origArea(OrderItemVO.CORIGAREAID),
    /** 原产国家 */
    origCountry(OrderItemVO.CORIGCOUNTRYID),
    /** 收货国家 */
    receiveCountry(OrderItemVO.CRECECOUNTRYID),
    /** 发货国家 */
    sendCountry(OrderItemVO.CSENDCOUNTRYID),
    /** 报税国家 */
    taxCountry(OrderItemVO.CTAXCOUNTRYID);

    private String code;

    private CountryType(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }

  }

  /**
   * 字段类型
   * 
   * @since 6.0
   * @version 2012-2-23 下午02:21:22
   * @author tianft
   */

  public enum VatFieldType {
    /** 结算财务组织 */
    financeOrg(OrderItemVO.PK_PSFINANCEORG),
    /** 收货客户 */
    receiveCustomer(OrderHeaderVO.PK_RECVCUSTOMER),
    /** 收货地址 */
    recieveAddr(OrderItemVO.PK_RECEIVEADDRESS),
    /** 收货库存组织 */
    recieveStoreOrg(OrderItemVO.PK_ARRVSTOORG),
    /** 供应商 */
    supplier(OrderHeaderVO.PK_SUPPLIER),
    /** 供应商发货地址 */
    supplierSendAddr(OrderItemVO.VVENDDEVADDR);

    private String code;

    private VatFieldType(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }

  }

  /** 是否来源上游 */
  private boolean fromSourcebill = true;

  /** 是否重新设值 */
  private boolean reset = false;

  protected IKeyValue[] bills;

  protected CountryType countryType;

  protected int[] rows = null;

  /**
   * 构造函数
   * 
   * @param country 国家类型
   * @param bill 单据
   * @param rows 需要处理的行
   */
  public AbstractOrderCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    this.countryType = country;
    this.rows = rows;
    this.bills = new IKeyValue[] {
      bill
    };
  }

  /**
   * 构造函数
   * 
   * @param country 国家类型
   * @param bills 单据
   */
  public AbstractOrderCountrySetter(CountryType country, IKeyValue[] bills) {
    this.countryType = country;
    this.bills = bills;
  }

  public boolean isFromSourcebill() {
    return this.fromSourcebill;
  }

  public boolean isReset() {
    return this.reset;
  }

  // protected boolean needQueryFieldPk(IKeyValue bill, int row) {
  // return false;
  // }

  public void setFromSourcebill(boolean fromSourcebill) {
    this.fromSourcebill = fromSourcebill;
  }

  public void setReset(boolean reset) {
    this.reset = reset;
  }

  /**
   * 根据国家查询需要处理的字段值
   * 
   * @param bill 单据
   * @param paraRows 需要处理的行
   * @param fieldType 字段
   * @return 字段值
   */
  private String[] getQueryPKs(IKeyValue bill, int[] paraRows,
      VatFieldType fieldType) {
    Set<String> valueSet = new HashSet<String>();
    for (int i = 0; i < paraRows.length; i++) {
      // String country =
      // (String) bill.getBodyValue(paraRows[i], this.countryType.getCode());
      // 国家已经有值，不处理
      if (!this.needSetCountry(bill, paraRows[i]) && !this.isReset()) {
        continue;
      }
      String value = null;
      if (VatFieldType.supplier.equals(fieldType)
          || VatFieldType.receiveCustomer.equals(fieldType)) {
        value = (String) bill.getHeadValue(fieldType.getCode());
      }
      else {
        value = (String) bill.getBodyValue(paraRows[i], fieldType.getCode());
      }
      if (!StringUtils.isEmpty(value)) {
        valueSet.add(value);
      }

    }
    if (valueSet.size() > 0) {
      return valueSet.toArray(new String[valueSet.size()]);
    }
    return null;
  }

  /**
   * 根据国家查询需要处理的字段值
   * 
   * @param fieldType 字段类型
   * @return 字段值
   */
  protected String[] getQueryPKs(VatFieldType fieldType) {
    String[] values = null;
    for (IKeyValue bill : this.bills) {
      int[] tempRows = this.rows;
      if (ArrayUtils.isEmpty(tempRows)) {
        tempRows = new int[bill.getItemCount()];
        for (int i = 0; i < bill.getItemCount(); i++) {
          tempRows[i] = i;
        }
      }
      String[] queryValues = this.getQueryPKs(bill, tempRows, fieldType);
      if (!ArrayUtils.isEmpty(queryValues)) {
        values = (String[]) ArrayUtils.addAll(values, queryValues);
      }
    }
    return values;

  }

  /**
   * 是否需要处理国家
   */
  protected boolean needSetCountry(IKeyValue bill, int row) {
    // 默认国家为空需要处理，子类可覆写
    String country =
        (String) bill.getBodyValue(row, this.countryType.getCode());
    return StringUtils.isEmpty(country);
  }

  /**
   * 根据查询结果设置国家
   * 
   * @param valueMap
   *          查询结果map
   * @param fieldType
   *          依据字段类型
   */
  protected void setValue(Map<String, String> valueMap, VatFieldType fieldType) {
    if (MapUtils.isEmpty(valueMap)) {
      return;
    }
    for (IKeyValue bill : this.bills) {
      int[] tempRows = this.rows;
      if (ArrayUtils.isEmpty(tempRows)) {
        tempRows = new int[bill.getItemCount()];
        for (int i = 0; i < bill.getItemCount(); i++) {
          tempRows[i] = i;
        }
      }
      for (int i = 0; i < tempRows.length; i++) {
        // String country =
        // (String) bill.getBodyValue(tempRows[i], this.countryType.getCode());
        // 国家已经有值，不处理
        if (this.needSetCountry(bill, tempRows[i]) || this.isReset()) {
          String keyValue;
          if (VatFieldType.supplier.equals(fieldType)
              || VatFieldType.receiveCustomer.equals(fieldType)) {// 表头字段
            keyValue = (String) bill.getHeadValue(fieldType.getCode());
          }
          else {// 表体字段
            keyValue =
                (String) bill.getBodyValue(tempRows[i], fieldType.getCode());
          }
          bill.setBodyValue(tempRows[i], this.countryType.getCode(),
              valueMap.get(keyValue));
        }
      }
    }
  }

}
