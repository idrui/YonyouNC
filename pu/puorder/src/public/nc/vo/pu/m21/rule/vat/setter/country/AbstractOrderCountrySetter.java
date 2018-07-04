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
 * ����������������
 * 
 * @since 6.0
 * @version 2012-2-23 ����02:21:35
 * @author tianft
 */
public class AbstractOrderCountrySetter {
  /**
   * ����(����)����
   * 
   * @since 6.0
   * @version 2012-2-23 ����02:21:08
   * @author tianft
   */
  public enum CountryType {
    /** Ŀ�ĵ��� */
    destArea(OrderItemVO.CDESTIAREAID),
    /** Ŀ�Ĺ��� */
    destCountry(OrderItemVO.CDESTICOUNTRYID),
    /** ԭ������ */
    origArea(OrderItemVO.CORIGAREAID),
    /** ԭ������ */
    origCountry(OrderItemVO.CORIGCOUNTRYID),
    /** �ջ����� */
    receiveCountry(OrderItemVO.CRECECOUNTRYID),
    /** �������� */
    sendCountry(OrderItemVO.CSENDCOUNTRYID),
    /** ��˰���� */
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
   * �ֶ�����
   * 
   * @since 6.0
   * @version 2012-2-23 ����02:21:22
   * @author tianft
   */

  public enum VatFieldType {
    /** ���������֯ */
    financeOrg(OrderItemVO.PK_PSFINANCEORG),
    /** �ջ��ͻ� */
    receiveCustomer(OrderHeaderVO.PK_RECVCUSTOMER),
    /** �ջ���ַ */
    recieveAddr(OrderItemVO.PK_RECEIVEADDRESS),
    /** �ջ������֯ */
    recieveStoreOrg(OrderItemVO.PK_ARRVSTOORG),
    /** ��Ӧ�� */
    supplier(OrderHeaderVO.PK_SUPPLIER),
    /** ��Ӧ�̷�����ַ */
    supplierSendAddr(OrderItemVO.VVENDDEVADDR);

    private String code;

    private VatFieldType(String code) {
      this.code = code;
    }

    public String getCode() {
      return this.code;
    }

  }

  /** �Ƿ���Դ���� */
  private boolean fromSourcebill = true;

  /** �Ƿ�������ֵ */
  private boolean reset = false;

  protected IKeyValue[] bills;

  protected CountryType countryType;

  protected int[] rows = null;

  /**
   * ���캯��
   * 
   * @param country ��������
   * @param bill ����
   * @param rows ��Ҫ�������
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
   * ���캯��
   * 
   * @param country ��������
   * @param bills ����
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
   * ���ݹ��Ҳ�ѯ��Ҫ������ֶ�ֵ
   * 
   * @param bill ����
   * @param paraRows ��Ҫ�������
   * @param fieldType �ֶ�
   * @return �ֶ�ֵ
   */
  private String[] getQueryPKs(IKeyValue bill, int[] paraRows,
      VatFieldType fieldType) {
    Set<String> valueSet = new HashSet<String>();
    for (int i = 0; i < paraRows.length; i++) {
      // String country =
      // (String) bill.getBodyValue(paraRows[i], this.countryType.getCode());
      // �����Ѿ���ֵ��������
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
   * ���ݹ��Ҳ�ѯ��Ҫ������ֶ�ֵ
   * 
   * @param fieldType �ֶ�����
   * @return �ֶ�ֵ
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
   * �Ƿ���Ҫ�������
   */
  protected boolean needSetCountry(IKeyValue bill, int row) {
    // Ĭ�Ϲ���Ϊ����Ҫ��������ɸ�д
    String country =
        (String) bill.getBodyValue(row, this.countryType.getCode());
    return StringUtils.isEmpty(country);
  }

  /**
   * ���ݲ�ѯ������ù���
   * 
   * @param valueMap
   *          ��ѯ���map
   * @param fieldType
   *          �����ֶ�����
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
        // �����Ѿ���ֵ��������
        if (this.needSetCountry(bill, tempRows[i]) || this.isReset()) {
          String keyValue;
          if (VatFieldType.supplier.equals(fieldType)
              || VatFieldType.receiveCustomer.equals(fieldType)) {// ��ͷ�ֶ�
            keyValue = (String) bill.getHeadValue(fieldType.getCode());
          }
          else {// �����ֶ�
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
