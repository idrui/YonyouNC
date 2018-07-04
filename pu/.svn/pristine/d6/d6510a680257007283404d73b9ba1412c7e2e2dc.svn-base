package nc.vo.pu.m25.rule.maintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 默认（自制或报税国为空时）设置报税国<br>
 * 取财务组织的所属国家
 * 
 * @since 6.0
 * @version 2012-2-14 下午10:07:11
 * @author zhaoyha
 */
public class DefaultTaxcountrySetter implements ICountrySetter {

  @Override
  public void setCountry(IKeyValue[] bills) {
    List<IKeyValue> filterBillLst = new ArrayList<IKeyValue>();
    List<String> orgLst = new ArrayList<String>();
    for (IKeyValue bill : bills) {
      String taxcountry =
          (String) bill.getHeadValue(InvoiceHeaderVO.CTAXCOUNTRYID);
      String pk_org = (String) bill.getHeadValue(InvoiceHeaderVO.PK_ORG);
      if (StringUtils.isNotBlank(taxcountry) || StringUtils.isBlank(pk_org)) {
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
      bill.setHeadValue(InvoiceHeaderVO.CTAXCOUNTRYID,
          orgCountryMap.get(bill.getHeadValue(InvoiceHeaderVO.PK_ORG)));
    }

  }

}
