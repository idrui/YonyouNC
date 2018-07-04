package nc.vo.pu.pub.rule.vat;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.StringUtils;

/**
 * 设置购销类型
 * 自制时如果报税国家/地区==发货国家/地区，则购销类型为“国内采购”；否则为“进口采购”
 * 区分表头和表体
 * 
 * @since 6.0
 * @version 2012-2-14 上午11:24:12
 * @author wuxla
 */
public class BuysellflagSetter {

  /**
   * 设置表体购销类型
   * <p>
   * 使用场景：只适合单个KeyValue情形
   * <ul>
   * <li>
   * </ul>
   * 
   * @param rows
   */
  public List<Integer> setBodyBuysellFlag(IKeyValue keyValue, int[] rows) {
    List<Integer> changRows = new ArrayList<Integer>();
    for (int row : rows) {
      if (this.setBodyBuysellFlag(keyValue, row)) {
        changRows.add(Integer.valueOf(row));
      }
    }
    return changRows;
  }

  /**
   * <p>
   * 使用场景：设置表体购销类型
   * <ul>
   * <li>
   * </ul>
   */
  public MapList<Integer, Integer> setBodyBuysellFlag(IKeyValue[] keyValues) {
    MapList<Integer, Integer> changeBills = new MapList<Integer, Integer>();
    for (int billCount = 0; billCount < keyValues.length; billCount++) {
      IKeyValue keyValue = keyValues[billCount];
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        if (this.setBodyBuysellFlag(keyValue, i)) {
          changeBills.put(Integer.valueOf(billCount), Integer.valueOf(i));
        }
      }
    }
    return changeBills;
  }

  /**
   * <p>
   * 使用场景：设置表头购销类型
   * <ul>
   * <li>
   * </ul>
   */
  public List<Integer> setHeadBuysellFlag(IKeyValue[] keyValues) {
    List<Integer> changeBills = new ArrayList<Integer>();
    for (int i = 0; i < keyValues.length; i++) {
      IKeyValue keyValue = keyValues[i];
      String ctaxcountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.ctaxcountryid.name());
      String csendcountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.csendcountryid.name());
      Integer oldValue =
          (Integer) keyValue.getHeadValue(PuAttrNameEnum.fbuysellflag.name());
      Integer newValue = this.getBuySellFlag(ctaxcountryid, csendcountryid);
      if (newValue.equals(oldValue)) {
        continue;
      }
      keyValue.setHeadValue(PuAttrNameEnum.fbuysellflag.name(), newValue);
      changeBills.add(Integer.valueOf(i));
    }
    return changeBills;
  }

  /**
   * <p>
   * 使用场景：如果报税国家/地区==发货国家/地区，则购销类型为“国内采购”；否则为“进口采购”
   * <ul>
   * <li>
   * </ul>
   * 
   * @param ctaxcountryid 报税国家/地区
   * @param csendcountryid 发货国家/地区
   * @return 购销类型
   */
  private Integer getBuySellFlag(String ctaxcountryid, String csendcountryid) {
    // 国家有一方为空，默认处理为国内采购，不影响后续三角贸易的标志
    if (StringUtils.isEmpty(csendcountryid)
        || StringUtils.isEmpty(ctaxcountryid)) {
      return BuySellFlagEnum.NATIONAL_BUY.value();
    }
    else if (StringUtils.equals(ctaxcountryid, csendcountryid)) {
      return BuySellFlagEnum.NATIONAL_BUY.value();
    }
    return BuySellFlagEnum.IMPORT.value();
  }

  /**
   * 设置购销类型，并返回新值与旧值是否一致
   * 
   * @param keyValue
   * @param row
   * @return 值是否变化，true-值有变化；false-值未变
   */
  private boolean setBodyBuysellFlag(IKeyValue keyValue, int row) {
    String ctaxcountryid =
        (String) keyValue
            .getBodyValue(row, PuAttrNameEnum.ctaxcountryid.name());
    String csendcountryid =
        (String) keyValue.getBodyValue(row,
            PuAttrNameEnum.csendcountryid.name());
    Integer oldValue =
        (Integer) keyValue
            .getBodyValue(row, PuAttrNameEnum.fbuysellflag.name());
    Integer newValue = this.getBuySellFlag(ctaxcountryid, csendcountryid);
    // 前后值一致
    if (newValue.equals(oldValue)) {
      return false;
    }
    keyValue.setBodyValue(row, PuAttrNameEnum.fbuysellflag.name(), newValue);
    return true;

  }
}
