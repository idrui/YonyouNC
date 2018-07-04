package nc.vo.pu.m4t.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 设置收货国家/地区
 * 根据上游单据生成时直接带入，且不可编辑；自制时，可编辑，默认按如下规则取默认值：
 * 取表头库存组织国家
 * 
 * @since 6.0
 * @version 2012-2-16 上午10:13:09
 * @author wuxla
 */
public class DefaultReceCountrySetter {

  public Set<Integer> setReceCountry(IKeyValue keyValue, int[] rows) {
    String pk_stockorg =
        (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    if (StringUtils.isEmpty(pk_stockorg)) {
      return null;
    }

    Map<String, String> map =
        StockOrgPubService.queryCountryByStockOrg(new String[] {
          pk_stockorg
        });
    if (null == map || map.size() == 0) {
      return null;
    }

    String crececountryid = map.get(pk_stockorg);
    Set<Integer> set = new HashSet<Integer>();
    for (int row : rows) {
      String oldValue =
          (String) keyValue.getBodyValue(row, InitialEstItemVO.CRECECOUNTRYID);
      if (oldValue == null || !oldValue.equals(crececountryid)) {
        keyValue.setBodyValue(row, InitialEstItemVO.CRECECOUNTRYID,
            crececountryid);
        set.add(Integer.valueOf(row));
      }
    }
    return set;
  }

  /**
   * 设置收货国家/地区
   * <p>
   * 使用场景：升级
   * <ul>
   * <li>
   * </ul>
   */
  public void setReceCountry(IKeyValue[] keyValues) {
    List<IKeyValue> keyList = new ArrayList<IKeyValue>();
    Set<String> stockorgSet = new HashSet<String>();
    for (IKeyValue keyValue : keyValues) {
      String pk_stockorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
      if (pk_stockorg != null) {
        keyList.add(keyValue);
        stockorgSet.add(pk_stockorg);
      }
    }

    if (stockorgSet.size() == 0) {
      return;
    }

    Map<String, String> map =
        StockOrgPubService.queryCountryByStockOrg(stockorgSet
            .toArray(new String[stockorgSet.size()]));
    if (null == map || map.size() == 0) {
      return;
    }

    for (IKeyValue keyValue : keyList) {
      String pk_stockorg =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
      String crececountryid = map.get(pk_stockorg);
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        String oldValue =
            (String) keyValue.getBodyValue(i, InitialEstItemVO.CRECECOUNTRYID);
        if (oldValue == null || !oldValue.equals(crececountryid)) {
          keyValue.setBodyValue(i, InitialEstItemVO.CRECECOUNTRYID,
              crececountryid);
        }
      }
    }

  }

}
