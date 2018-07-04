package nc.ui.pu.pub.util;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.StringUtils;

/**
 * 设置日期，取业务日期
 * 
 * @since 6.0
 * @version 2011-4-7 上午08:24:20
 * @author wuxla
 */

public class BusiDateSetter {
  public static final String DBILLDATE = "dbilldate";

  public BusiDateSetter() {
    //
  }

  /**
   * 为单据表体设置业务日期
   * 
   * @param bills
   */
  public void setBodyBusiDate(AbstractBill[] bills) {
    this.setBusiDate(bills, null, false);
  }

  /**
   * 为单据表体设置业务日期
   * 
   * @param bills
   * @param dateKey
   */
  public void setBodyBusiDate(AbstractBill[] bills, String dateKey) {
    this.setBusiDate(bills, dateKey, false);
  }

  /**
   * 为单据表头设置单据日期
   * 
   * @param bills
   */
  public void setBusiDate(AbstractBill[] bills) {
    UFDate busidate = AppContext.getInstance().getBusiDate();
    for (AbstractBill bill : bills) {
      bill.getParent().setAttributeValue(BusiDateSetter.DBILLDATE, busidate);
    }
  }

  /**
   * @param keyValue
   */
  public void setBusiDate(IKeyValue keyValue) {
    // 日期
    UFDate busidate = AppContext.getInstance().getBusiDate();
    keyValue.setHeadValue(BusiDateSetter.DBILLDATE, busidate);
  }

  /**
   * 为单据头设置业务日期
   * 
   * @param bills
   */
  public void setHeaderBusiDate(AbstractBill[] bills) {
    this.setBusiDate(bills, null, true);
  }

  /**
   * 为单据头设置业务日期
   * 
   * @param bills
   * @param dateKey
   */
  public void setHeaderBusiDate(AbstractBill[] bills, String dateKey) {
    this.setBusiDate(bills, dateKey, true);
  }

  /**
   * 根据制定设置
   * 
   * @param bills
   * @param key
   * @param forHeader 是否设置表头，true-表头；false - 表体
   */
  private void setBusiDate(AbstractBill[] bills, String dateKey,
      boolean forHeader) {
    UFDate busidate = AppContext.getInstance().getBusiDate();
    String key =
        StringUtils.isBlank(dateKey) ? BusiDateSetter.DBILLDATE : dateKey;

    for (AbstractBill bill : bills) {
      if (forHeader) {
        bill.getParent().setAttributeValue(key, busidate);
      }
      else {
        for (CircularlyAccessibleValueObject item : bill.getChildrenVO()) {
          item.setAttributeValue(key, busidate);
        }
      }
    }
  }

}
