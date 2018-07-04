package nc.vo.pu.m21.rule.vat.setter.country;

import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ���ݽ���������ù���
 * 
 * @since 6.0
 * @version 2012-2-21 ����03:29:09
 * @author tianft
 */
public class OrderFinanceOrgCountrySetter extends AbstractOrderCountrySetter
    implements ICountrySetter {

  public OrderFinanceOrgCountrySetter(CountryType country, IKeyValue bill,
      int[] rows) {
    super(country, bill, rows);
  }

  public OrderFinanceOrgCountrySetter(CountryType country, IKeyValue[] bills) {
    super(country, bills);
  }

  @Override
  public void setCountry() {

    String[] pkValues = this.getQueryPKs(VatFieldType.financeOrg);
    if (!ArrayUtils.isEmpty(pkValues)) {
      Map<String, String> valueMap =
          FinanceOrgPubService.queryCountryByFinanceOrg(pkValues);
      this.setValue(valueMap, VatFieldType.financeOrg);
    }

  }

  @Override
  protected boolean needSetCountry(IKeyValue bill, int row) {
    // boolean need = super.needSetCountry(bill, row);
    // ��˰�����ɹ�������֯���ջ������֯�繫˾�� ȡ���������֯���ڹ���
    if (!this.isFromSourcebill()
        && CountryType.taxCountry.equals(this.countryType)) {
      String arriveStoreOrg =
          (String) bill.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);
      String settleOrg =
          (String) bill.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);

      // fanly3 2013-01-29 �û����û�����òɹ�ҵ��ί�й�ϵ�����������֯����Ϊ��
      if (StringUtils.isEmpty(settleOrg)) {
        return false;
      }
      // �ջ���֯�յ�ʱ�򸲸�
      if (StringUtils.isEmpty(arriveStoreOrg)
          || arriveStoreOrg.equals(settleOrg)) {
        return true;
      }

      Map<String, String> financeOrgCorpMap =
          FinanceOrgPubService.queryPKCorp(new String[] {
            settleOrg
          });
      Map<String, String> storeOrgCorpMap =
          StockOrgPubService.queryCorpIDByStockOrgIDs(new String[] {
            arriveStoreOrg
          });
      // �繫˾ʱ
      if (financeOrgCorpMap.get(settleOrg) != null
          && !financeOrgCorpMap.get(settleOrg).equals(
              storeOrgCorpMap.get(arriveStoreOrg))) {
        return true;
      }
    }

    return super.needSetCountry(bill, row);
  }

}
