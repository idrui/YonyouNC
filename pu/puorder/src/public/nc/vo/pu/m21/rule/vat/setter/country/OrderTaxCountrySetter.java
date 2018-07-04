package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ������˰����Ĭ��ֵ������
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:29:09
 * @author tianft
 */
public class OrderTaxCountrySetter extends AbstractOrderCountrySetter implements
    ICountrySetter {

  public OrderTaxCountrySetter(IKeyValue bill, int[] rows) {
    super(CountryType.taxCountry, bill, rows);
  }

  public OrderTaxCountrySetter(IKeyValue[] bills) {
    super(CountryType.taxCountry, bills);
  }

  @Override
  public void setCountry() {
    this.setCountryValue();
  }

  /**
   * ������֯������˾
   * 
   * @return ��֯-��˾ map�ṹ
   */
  private Map<String, String> getFinanceOrgCorp() {
    String[] financeOrgValues = this.getQueryPKs(VatFieldType.financeOrg);
    Map<String, String> financeOrgCorpMap = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(financeOrgValues)) {
      financeOrgCorpMap = FinanceOrgPubService.queryPKCorp(financeOrgValues);
    }
    if (null == financeOrgCorpMap) {
      financeOrgCorpMap = new HashMap<String, String>();
    }
    return financeOrgCorpMap;
  }

  /**
   * ������֯��������
   * 
   * @return ��֯-���� map�ṹ
   */
  private Map<String, String> getFinanceOrgCountry() {
    String[] financeOrgValues = this.getQueryPKs(VatFieldType.financeOrg);
    Map<String, String> financeOrgCountryMap = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(financeOrgValues)) {
      financeOrgCountryMap =
          FinanceOrgPubService.queryCountryByFinanceOrg(financeOrgValues);
    }
    if (null == financeOrgCountryMap) {
      financeOrgCountryMap = new HashMap<String, String>();
    }
    return financeOrgCountryMap;
  }

  /**
   * �ջ������֯������˾
   * 
   * @return ��֯-��˾ map�ṹ
   */
  private Map<String, String> getRecievOrgCorp() {
    String[] OrgValues = this.getQueryPKs(VatFieldType.recieveStoreOrg);
    Map<String, String> mapValue = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(OrgValues)) {
      mapValue = StockOrgPubService.queryCorpIDByStockOrgIDs(OrgValues);
    }
    if (null == mapValue) {
      mapValue = new HashMap<String, String>();
    }
    return mapValue;
  }

  /**
   * �ջ������֯��������
   * 
   * @return ��֯-���� map�ṹ
   */
  private Map<String, String> getRecievOrgCountry() {
    String[] OrgValues = this.getQueryPKs(VatFieldType.recieveStoreOrg);
    Map<String, String> mapValue = new HashMap<String, String>();
    if (!ArrayUtils.isEmpty(OrgValues)) {
      mapValue = StockOrgPubService.queryCountryByStockOrg(OrgValues);
    }
    if (null == mapValue) {
      mapValue = new HashMap<String, String>();
    }
    return mapValue;
  }

  /**
   * ��ֵ�߼���
   * 1. ��� �ɹ�������֯���ջ������֯�繫˾,ȡ���������֯���ڹ���
   * 2. ���� ȡ�ջ������֯���ڹ���
   */
  private void setCountryValue() {
    Map<String, String> financeOrgCountryMap = this.getFinanceOrgCountry();
    Map<String, String> financeOrgCorpMap = this.getFinanceOrgCorp();
    Map<String, String> recievOrgCountryMap = this.getRecievOrgCountry();
    Map<String, String> recievOrgCorpMap = this.getRecievOrgCorp();

    for (IKeyValue bill : this.bills) {
      int[] tempRows = this.rows;
      if (ArrayUtils.isEmpty(tempRows)) {
        tempRows = new int[bill.getItemCount()];
        for (int i = 0; i < tempRows.length; i++) {
          tempRows[i] = i;
        }
      }
      for (int i = 0; i < tempRows.length; i++) {
        String countryValue =
            (String) bill.getBodyValue(tempRows[i], this.countryType.getCode());
        // ���ҷǿգ�������
        if (StringUtils.isNotBlank(countryValue)) {
          continue;
        }
        String financeOrg =
            (String) bill
                .getBodyValue(tempRows[i], OrderItemVO.PK_PSFINANCEORG);
        String recievOrg =
            (String) bill.getBodyValue(tempRows[i], OrderItemVO.PK_ARRVSTOORG);
        // ������֯���ղ��Ҳ�����֯�����ջ������֯
        if (StringUtils.isNotBlank(financeOrg) && !financeOrg.equals(recievOrg)) {
          // ������ͬһ��˾��ȡ������֯��������
          if (financeOrgCorpMap.get(financeOrg) != null
              && !financeOrgCorpMap.get(financeOrg).equals(
                  recievOrgCorpMap.get(recievOrg))) {
            bill.setBodyValue(tempRows[i], this.countryType.getCode(),
                financeOrgCountryMap.get(financeOrg));
            continue;
          }
        }
        // ����ȡ�ջ������֯��������
        bill.setBodyValue(tempRows[i], this.countryType.getCode(),
            recievOrgCountryMap.get(recievOrg));
      }
    }
  }
}
