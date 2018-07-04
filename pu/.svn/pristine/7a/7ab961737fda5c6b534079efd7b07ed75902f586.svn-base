package nc.vo.pu.est.rule;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.StringUtils;

/**
 * VMI货物暂估国家地区设置
 * 
 * @since 6.0
 * @version 2012-3-8 下午05:49:06
 * @author wuxla
 */
public class VmiGoodsCountrySetter {
  /**
   * 设置VMI货物暂估国家地区
   * 报税国家/地区:参照国家地区档案。不可编辑。按如下规则取值：
   * 如果 采购结算组织与库存组织跨公司
   * 取结算财务组织所在国家
   * 否则 取库存组织所在国家
   * 发货国家/地区、收货国家/地区取值和报税国相等。
   * <p>
   * 使用场景：
   * <ul>
   * <li>查询消耗汇总单时设置，国家地区不可以编辑
   * </ul>
   * 
   * @param vos
   */
  public void setCountry(VmiEstVO[] vos, String pk_financeorg) {
    // 只有消耗汇总用
    MapList<String, VmiEstVO> mapList = new MapList<String, VmiEstVO>();
    for (VmiEstVO vo : vos) {
      mapList.put(vo.getParentVO().getPk_storeorg(), vo);
    }

    String[] financeorgs = new String[] {
      pk_financeorg
    };
    Set<String> stockorgset = mapList.keySet();
    String[] stockorgs = stockorgset.toArray(new String[stockorgset.size()]);
    Map<String, String> financeorgCorpMap =
        FinanceOrgPubService.queryPKCorp(financeorgs);
    String financeorgCorp = financeorgCorpMap.get(pk_financeorg);
    Map<String, String> financeorgCountryMap =
        FinanceOrgPubService.queryCountryByFinanceOrg(financeorgs);
    String financeorgCountry = financeorgCountryMap.get(pk_financeorg);
    Map<String, String> stockorgCountryMap =
        StockOrgPubService.queryCountryByStockOrg(stockorgs);
    for (Entry<String, List<VmiEstVO>> entry : mapList.entrySet()) {
      String pk_stockorg = entry.getKey();
      String stockorgCorp = stockorgCountryMap.get(pk_stockorg);
      List<VmiEstVO> list = entry.getValue();
      if (StringUtils.equals(financeorgCorp, stockorgCorp)) {
        this.setCountry(list, stockorgCountryMap.get(pk_stockorg));
      }
      else {
        this.setCountry(list, financeorgCountry);
      }
    }
  }

  private void setCountry(List<VmiEstVO> list, String pk_country) {
    for (VmiEstVO vo : list) {
      VmiEstHeaderVO headvo = vo.getParentVO();
      headvo.setCsendcountryid(pk_country);
      headvo.setCrececountryid(pk_country);
      headvo.setCtaxcountryid(pk_country);
    }
  }
}
