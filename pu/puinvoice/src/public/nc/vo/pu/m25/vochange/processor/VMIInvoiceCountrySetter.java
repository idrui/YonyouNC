package nc.vo.pu.m25.vochange.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * ���Ļ��ܷ�Ʊ�����������ջ�������˰�����ù���<br>
 * ��ȡ������֯����������
 * 
 * @since 6.0
 * @version 2012-2-15 ����10:12:58
 * @author zhaoyha
 */
public class VMIInvoiceCountrySetter implements ICountrySetter {

  @Override
  public void setCountry(IKeyValue[] bills) {
    // ������
    this.setCountry(bills, InvoiceHeaderVO.CSENDCOUNTRYID);
    // �ջ���
    this.setCountry(bills, InvoiceHeaderVO.CRECECOUNTRYID);
    // ��˰��
    this.setCountry(bills, InvoiceHeaderVO.CTAXCOUNTRYID);

  }

  private void setCountry(IKeyValue[] bills, String countryFdName) {
    List<IKeyValue> filterBillLst = new ArrayList<IKeyValue>();
    List<String> orgLst = new ArrayList<String>();
    for (IKeyValue bill : bills) {
      String country = (String) bill.getHeadValue(countryFdName);
      String pk_org = (String) bill.getHeadValue(InvoiceHeaderVO.PK_ORG);
      if (StringUtils.isNotBlank(country) || StringUtils.isBlank(pk_org)) {
        continue;
      }
      filterBillLst.add(bill);
      orgLst.add(pk_org);
    }
    if (filterBillLst.size() == 0) {
      return;
    }
    Map<String, String> orgCountryMap =
        FinanceOrgPubService.queryCountryByFinanceOrg(orgLst
            .toArray(new String[orgLst.size()]));
    for (IKeyValue bill : filterBillLst) {
      bill.setHeadValue(countryFdName,
          orgCountryMap.get(bill.getHeadValue(InvoiceHeaderVO.PK_ORG)));
    }
  }

}
