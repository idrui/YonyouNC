package nc.vo.pu.m25.rule.maintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * Ĭ�ϣ����ƻ��ջ���Ϊ��ʱ�������ջ���<br>
 * ����ʱ���ɱ༭�������¹���ȡĬ��ֵ��<br>
 * �����ͷ�����֯��ֵ
 * ȡ��ͷ�����֯����<br>
 * ����
 * ȡ����֯���������֯���ҡ�<br>
 * ���Ļ���ת��Ҳ���ô˹���
 * 
 * @since 6.0
 * @version 2012-2-14 ����10:07:11
 * @author zhaoyha
 */
public class DefaultRececountrySetter implements ICountrySetter {

  @Override
  public void setCountry(IKeyValue[] bills) {
    List<IKeyValue> filterStockorgBillLst = new ArrayList<IKeyValue>();
    List<IKeyValue> filterOrgBillLst = new ArrayList<IKeyValue>();
    List<String> stockorgLst = new ArrayList<String>();
    List<String> orgLst = new ArrayList<String>();
    for (IKeyValue bill : bills) {
      String rececountry =
          (String) bill.getHeadValue(InvoiceHeaderVO.CRECECOUNTRYID);
      if (StringUtils.isNotBlank(rececountry)) {
        continue;
      }
      String pk_stockorg =
          (String) bill.getHeadValue(InvoiceHeaderVO.PK_STOCKORG);
      if (StringUtils.isNotBlank(pk_stockorg)) {
        stockorgLst.add(pk_stockorg);
        filterStockorgBillLst.add(bill);
        continue;
      }
      String pk_org = (String) bill.getHeadValue(InvoiceHeaderVO.PK_ORG);
      if (StringUtils.isNotBlank(pk_org)) {
        orgLst.add(pk_org);
        filterOrgBillLst.add(bill);
      }
    }
    this.setByStockOrg(filterStockorgBillLst, stockorgLst);
    this.setByOrg(filterOrgBillLst, orgLst);
  }

  private void setByOrg(List<IKeyValue> filterOrgBillLst, List<String> orgLst) {
    if (filterOrgBillLst.size() > 0) {
      Map<String, String> orgCountryMap =
          FinanceOrgPubService.queryCountryByFinanceOrg(orgLst
              .toArray(new String[orgLst.size()]));
      for (IKeyValue bill : filterOrgBillLst) {
        bill.setHeadValue(InvoiceHeaderVO.CRECECOUNTRYID,
            orgCountryMap.get(bill.getHeadValue(InvoiceHeaderVO.PK_ORG)));
      }
    }
  }

  private void setByStockOrg(List<IKeyValue> filterStockorgBillLst,
      List<String> stockorgLst) {
    if (filterStockorgBillLst.size() > 0) {
      Map<String, String> stockCountryMap =
          StockOrgPubService.queryCountryByStockOrg(stockorgLst
              .toArray(new String[stockorgLst.size()]));
      for (IKeyValue bill : filterStockorgBillLst) {
        bill.setHeadValue(InvoiceHeaderVO.CRECECOUNTRYID,
            stockCountryMap.get(bill.getHeadValue(InvoiceHeaderVO.PK_STOCKORG)));
      }
    }
  }

}
