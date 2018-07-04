package nc.vo.pu.est.rule;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.StringUtils;

/**
 * 费用暂估购销类型
 * 
 * @since 6.0
 * @version 2012-3-10 上午09:44:09
 * @author wuxla
 */
public class FeeBuysellflagSetter {
  /**
   * 设置费用行的购销类型
   * 
   * @param keyValue
   * @param pk_country
   * @param rows
   * @return
   */
  public List<Integer> setBodyBuysellFlag(IKeyValue keyValue,
      String pk_country, int[] rows) {
    List<Integer> changRows = new ArrayList<Integer>();
    for (int row : rows) {
      if (this.setBodyBuysellFlag(keyValue, pk_country, row)) {
        changRows.add(Integer.valueOf(row));
      }
    }
    return changRows;
  }

  /**
   * 批量设置费用项的购销类型
   * 因为没有联动计算，所以不用处理返回值
   * 
   * @param keyValues
   * @param pk_country
   */
  public void setBodyBuysellFlag(IKeyValue[] keyValues, String pk_country) {
    for (IKeyValue keyValue : keyValues) {
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        this.setBodyBuysellFlag(keyValue, pk_country, i);
      }
    }
  }

  private Integer getBuySellFlag(String ctaxcountryid, String csendcountryid) {
    if (StringUtils.equals(ctaxcountryid, csendcountryid)) {
      return BuySellFlagEnum.NATIONAL_BUY.value();
    }
    return BuySellFlagEnum.IMPORT.value();
  }

  private boolean setBodyBuysellFlag(IKeyValue keyValue, String pk_country,
      int row) {
    String ctaxcountryid =
        (String) keyValue
            .getBodyValue(row, PuAttrNameEnum.ctaxcountryid.name());
    if (StringUtils.isEmpty(ctaxcountryid)) {
      ctaxcountryid = pk_country;
    }
    String csendcountryid =
        (String) keyValue.getBodyValue(row,
            PuAttrNameEnum.csendcountryid.name());
    if (StringUtils.isEmpty(csendcountryid)) {
      csendcountryid = pk_country;
    }
    Integer newValue = this.getBuySellFlag(ctaxcountryid, csendcountryid);
    Integer oldValue =
        (Integer) keyValue
            .getBodyValue(row, PuAttrNameEnum.fbuysellflag.name());
    if (newValue.equals(oldValue)) {
      return false;
    }
    keyValue.setBodyValue(row, PuAttrNameEnum.fbuysellflag.name(), newValue);
    return true;
  }
}
