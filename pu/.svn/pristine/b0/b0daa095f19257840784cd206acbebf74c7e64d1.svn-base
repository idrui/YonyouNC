package nc.vo.pu.pub.rule.vat;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 设置三角贸易，必须在BuysellflagSetter之后
 * 如果 购销类型=进口采购　而且　报税国≠收货国 三角贸易＝是 否则　为否
 * 
 * @since 6.0
 * @version 2012-2-14 上午11:57:58
 * @author wuxla
 */
public class TriatradeflagSetter {

  /**
   * 设置表体三角贸易
   * <p>
   * 使用场景：只适合单个KeyValue情形
   * <ul>
   * <li>
   * </ul>
   * 
   * @param rows
   */
  public void setBodyTriatradeflag(IKeyValue keyValue, int[] rows) {

    for (int row : rows) {
      this.setBodyTriatradeflag(keyValue, row);
    }

  }

  /**
   * <p>
   * 使用场景：设置表体三角贸易
   * <ul>
   * <li>
   * </ul>
   */
  public void setBodyTriatradeflag(IKeyValue[] keyValues) {
    for (IKeyValue keyValue : keyValues) {
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        this.setBodyTriatradeflag(keyValue, i);
      }
    }
  }

  /**
   * <p>
   * 使用场景：设置表头三角贸易
   * <ul>
   * <li>
   * </ul>
   */
  public void setHeadTriatradeflag(IKeyValue[] keyValues) {
    for (IKeyValue keyValue : keyValues) {
      Integer fbuysellflag =
          (Integer) keyValue.getHeadValue(PuAttrNameEnum.fbuysellflag.name());
      String ctaxcountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.ctaxcountryid.name());
      String crececountryid =
          (String) keyValue.getHeadValue(PuAttrNameEnum.crececountryid.name());
      keyValue.setHeadValue(PuAttrNameEnum.btriatradeflag.name(),
          this.getTriatradeflag(fbuysellflag, ctaxcountryid, crececountryid));
    }
  }

  /**
   * <p>
   * 使用场景：如果 购销类型=进口采购　而且　报税国≠收货国 三角贸易＝是 否则　为否
   * <ul>
   * <li>
   * </ul>
   * 
   * @param fbuysellflag 购销类型
   * @param ctaxcountryid 报税国
   * @param crececountryid 收货国
   * @return 是否三角贸易
   */
  private UFBoolean getTriatradeflag(Integer fbuysellflag,
      String ctaxcountryid, String crececountryid) {
    if (BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)) {
      // 国家有空的情况不处理
      if (StringUtils.isEmpty(crececountryid)
          || StringUtils.isEmpty(ctaxcountryid)) {
        return UFBoolean.FALSE;

      }
      return UFBoolean.valueOf(!StringUtils.equals(ctaxcountryid,
          crececountryid));
    }
    return UFBoolean.FALSE;
  }

  private void setBodyTriatradeflag(IKeyValue keyValue, int row) {
    Integer fbuysellflag =
        (Integer) keyValue
            .getBodyValue(row, PuAttrNameEnum.fbuysellflag.name());
    String ctaxcountryid =
        (String) keyValue
            .getBodyValue(row, PuAttrNameEnum.ctaxcountryid.name());
    String crececountryid =
        (String) keyValue.getBodyValue(row,
            PuAttrNameEnum.crececountryid.name());
    keyValue.setBodyValue(row, PuAttrNameEnum.btriatradeflag.name(),
        this.getTriatradeflag(fbuysellflag, ctaxcountryid, crececountryid));

  }
}
