package nc.vo.pu.m4t.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 发货国家/地区默认值
 * 根据上游单据生成时直接带入，且不可编辑；自制时，可编辑，默认取供应商档案国家。
 * 
 * @since 6.0
 * @version 2012-2-16 上午09:45:06
 * @author wuxla
 */
public class DefaultSendCountrySetter {

  public Set<Integer> setSendCountry(IKeyValue keyValue, int[] rows) {
    String pk_supplier =
        (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
    if (StringUtils.isEmpty(pk_supplier)) {
      return null;
    }
    Map<String, String> map =
        SupplierPubService.queryCountryBySupplier(new String[] {
          pk_supplier
        });
    if (null == map || map.size() == 0) {
      return null;
    }

    String csendcountryid = map.get(pk_supplier);
    Set<Integer> set = new HashSet<Integer>();
    for (int row : rows) {
      String oldValue =
          (String) keyValue.getBodyValue(row, InitialEstItemVO.CSENDCOUNTRYID);
      if (oldValue == null || !oldValue.equals(csendcountryid)) {
        keyValue.setBodyValue(row, InitialEstItemVO.CSENDCOUNTRYID,
            csendcountryid);
        set.add(Integer.valueOf(row));
      }
    }
    return set;
  }

  /**
   * 设置发货国
   * <p>
   * 使用场景：升级处理
   * <ul>
   * <li>
   * </ul>
   */
  public void setSendCountry(IKeyValue[] keyValues) {
    List<IKeyValue> keyList = new ArrayList<IKeyValue>();
    Set<String> supSet = new HashSet<String>();
    for (IKeyValue keyValue : keyValues) {
      String pk_supplier =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
      if (pk_supplier != null) {
        keyList.add(keyValue);
        supSet.add(pk_supplier);
      }
    }

    if (supSet.size() == 0) {
      return;
    }

    Map<String, String> map =
        SupplierPubService.queryCountryBySupplier(supSet
            .toArray(new String[supSet.size()]));
    if (null == map || map.size() == 0) {
      return;
    }
    for (IKeyValue keyValue : keyList) {
      String pk_supplier =
          (String) keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
      String csendcountryid = map.get(pk_supplier);
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        String oldValue =
            (String) keyValue.getBodyValue(i, InitialEstItemVO.CSENDCOUNTRYID);
        if (oldValue == null || !oldValue.equals(csendcountryid)) {
          keyValue.setBodyValue(i, InitialEstItemVO.CSENDCOUNTRYID,
              csendcountryid);
        }
      }
    }
  }

}
