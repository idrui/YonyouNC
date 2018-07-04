package nc.vo.pu.m4t.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 设置报税国/地区
 * 根据上游单据生成时直接带入，且不可编辑；自制时，可编辑，默认按如下规则取默认值：
 * 如果 财务组织与库存组织跨公司
 * 取财务组织所在国家
 * 否则 取库存组织所在国家
 * 
 * @since 6.0
 * @version 2012-2-16 上午10:19:06
 * @author wuxla
 */
public class DefaultTaxCountrySetter {
  private Map<String, String> allFinanceorgMap;

  private Map<String, String> allStockorgMap;

  private List<IKeyValue> filterList = new ArrayList<IKeyValue>();

  public Set<Integer> setTaxCountry(IKeyValue keyValue, int[] rows) {
    String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    String pk_stockorg =
        (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    if (StringUtils.isEmpty(pk_org) && StringUtils.isEmpty(pk_stockorg)) {
      return null;
    }
    Map<String, String> financeorgMap =
        FinanceOrgPubService.queryPKCorp(new String[] {
          pk_org
        });
    Map<String, String> stockorgMap =
        StockOrgPubService.queryCorpIDByStockOrgIDs(new String[] {
          pk_stockorg
        });
    String orgCorp = financeorgMap.get(pk_org);
    String stockorgCorp = stockorgMap.get(pk_stockorg);
    if (StringUtils.equals(orgCorp, stockorgCorp)) {
      return this.setTaxCountryByStockOrg(keyValue, rows, pk_stockorg);
    }

    return this.setTaxCountryByOrg(keyValue, rows, pk_org);
  }

  /**
   * 设置报税国/地区
   * <p>
   * 使用场景： 升级
   * <ul>
   * <li>
   * </ul>
   */
  public void setTaxCountry(IKeyValue[] keyValues) {
    this.init(keyValues);
    if (this.filterList.size() == 0) {
      return;
    }
    List<IKeyValue> sameList = new ArrayList<IKeyValue>();
    Set<String> orgSet = new HashSet<String>();
    List<IKeyValue> notsameList = new ArrayList<IKeyValue>();
    Set<String> stockSet = new HashSet<String>();
    for (IKeyValue keyValue : this.filterList) {
      String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
      String pk_stockorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
      String orgCorp = this.allFinanceorgMap.get(pk_org);
      String stockorgCorp = this.allStockorgMap.get(pk_stockorg);
      if (StringUtils.equals(orgCorp, stockorgCorp)) {
        sameList.add(keyValue);
        stockSet.add(pk_stockorg);
      }
      else {
        notsameList.add(keyValue);
        orgSet.add(pk_org);
      }
    }

    if (sameList.size() > 0) {
      this.setTaxCountryWhenCorpSame(sameList,
          stockSet.toArray(new String[stockSet.size()]));
    }
    if (notsameList.size() > 0) {
      this.setTaxCountryWhenCorpNotSame(notsameList,
          orgSet.toArray(new String[orgSet.size()]));
    }
  }

  private void init(IKeyValue[] keyValues) {
    Set<String> orgSet = new HashSet<String>();
    Set<String> stockorgSet = new HashSet<String>();
    for (IKeyValue keyValue : keyValues) {
      String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
      String pk_stockorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
      if (StringUtils.isNotBlank(pk_org) && StringUtils.isNotBlank(pk_stockorg)) {
        this.filterList.add(keyValue);
        orgSet.add(pk_org);
        stockorgSet.add(pk_stockorg);
      }
    }
    if (orgSet.size() == 0) {
      return;
    }

    this.allFinanceorgMap =
        FinanceOrgPubService.queryPKCorp(orgSet.toArray(new String[orgSet
            .size()]));
    this.allStockorgMap =
        StockOrgPubService.queryCorpIDByStockOrgIDs(stockorgSet
            .toArray(new String[stockorgSet.size()]));
  }

  private Set<Integer> setTaxCountry(IKeyValue keyValue, int[] rows,
      String ctaxcountryid) {
    Set<Integer> set = new HashSet<Integer>();
    for (int row : rows) {
      String oldValue =
          (String) keyValue.getBodyValue(row, InitialEstItemVO.CTAXCOUNTRYID);
      if (StringUtils.isEmpty(oldValue) || !oldValue.equals(ctaxcountryid)) {
        keyValue.setBodyValue(row, InitialEstItemVO.CTAXCOUNTRYID,
            ctaxcountryid);
        set.add(Integer.valueOf(row));
      }
    }
    return set;
  }

  private Set<Integer> setTaxCountryByOrg(IKeyValue keyValue, int[] rows,
      String pk_org) {
    Map<String, String> orgCountryMap =
        FinanceOrgPubService.queryCountryByFinanceOrg(new String[] {
          pk_org
        });
    if (null == orgCountryMap || orgCountryMap.size() == 0) {
      return null;
    }
    String ctaxcountryid = orgCountryMap.get(pk_org);
    return this.setTaxCountry(keyValue, rows, ctaxcountryid);
  }

  private Set<Integer> setTaxCountryByStockOrg(IKeyValue keyValue, int[] rows,
      String pk_stockorg) {
    Map<String, String> stockCountryMap =
        StockOrgPubService.queryCountryByStockOrg(new String[] {
          pk_stockorg
        });
    if (null == stockCountryMap || stockCountryMap.size() == 0) {
      return null;
    }
    String ctaxcountryid = stockCountryMap.get(pk_stockorg);
    return this.setTaxCountry(keyValue, rows, ctaxcountryid);
  }

  private void setTaxCountryWhenCorpNotSame(List<IKeyValue> notsameList,
      String[] pk_orgs) {
    Map<String, String> orgCountryMap =
        FinanceOrgPubService.queryCountryByFinanceOrg(pk_orgs);
    if (null == orgCountryMap || orgCountryMap.size() == 0) {
      return;
    }
    for (IKeyValue keyValue : notsameList) {
      String pk_org = (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
      String ctaxcountryid = orgCountryMap.get(pk_org);
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        keyValue.setBodyValue(i, InitialEstItemVO.CTAXCOUNTRYID, ctaxcountryid);
      }
    }
  }

  private void setTaxCountryWhenCorpSame(List<IKeyValue> sameList,
      String[] pk_stocks) {
    Map<String, String> stockCountryMap =
        StockOrgPubService.queryCountryByStockOrg(pk_stocks);
    if (null == stockCountryMap || stockCountryMap.size() == 0) {
      return;
    }
    for (IKeyValue keyValue : sameList) {
      String pk_stockorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
      String ctaxcountryid = stockCountryMap.get(pk_stockorg);
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        keyValue.setBodyValue(i, InitialEstItemVO.CTAXCOUNTRYID, ctaxcountryid);
      }
    }
  }
}
