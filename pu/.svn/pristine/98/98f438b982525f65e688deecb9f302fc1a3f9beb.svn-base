package nc.vo.pu.est.rule;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 费用三角贸易
 * 
 * @since 6.0
 * @version 2012-3-10 上午09:41:25
 * @author wuxla
 */
public class FeeTriatradeflagSetter {
  /**
   * 三角贸易
   * <p>
   * 使用场景：
   * <ul>
   * <li>编辑事件
   * </ul>
   * 
   * @param keyValue
   * @param pk_country
   * @param rows
   */
  public void setBodyTriatradeflag(IKeyValue keyValue, String pk_country,
      int[] rows) {
    for (int row : rows) {
      this.setBodyTriatradeflag(keyValue, pk_country, row);
    }
  }

  public void setBodyTriatradeflag(IKeyValue[] keyValues, String pk_country) {
    for (IKeyValue keyValue : keyValues) {
      for (int i = 0; i < keyValue.getItemCount(); ++i) {
        this.setBodyTriatradeflag(keyValue, pk_country, i);
      }
    }
  }

  private UFBoolean getTriatradeflag(Integer fbuysellflag,
      String ctaxcountryid, String crececountryid) {
    if (BuySellFlagEnum.IMPORT.value().equals(fbuysellflag)
        && !StringUtils.equals(ctaxcountryid, crececountryid)) {
      return UFBoolean.TRUE;
    }
    return UFBoolean.FALSE;
  }

  private void setBodyTriatradeflag(IKeyValue keyValue, String pk_country,
      int row) {
    Integer fbuysellflag =
        (Integer) keyValue
            .getBodyValue(row, PuAttrNameEnum.fbuysellflag.name());
    String ctaxcountryid =
        (String) keyValue
            .getBodyValue(row, PuAttrNameEnum.ctaxcountryid.name());
    if (StringUtils.isEmpty(ctaxcountryid)) {
      ctaxcountryid = pk_country;
    }
    String crececountryid =
        (String) keyValue.getBodyValue(row,
            PuAttrNameEnum.crececountryid.name());
    if (StringUtils.isEmpty(crececountryid)) {
      crececountryid = pk_country;
    }
    keyValue.setBodyValue(row, PuAttrNameEnum.btriatradeflag.name(),
        this.getTriatradeflag(fbuysellflag, ctaxcountryid, crececountryid));

  }
}
