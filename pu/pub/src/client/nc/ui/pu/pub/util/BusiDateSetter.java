package nc.ui.pu.pub.util;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.StringUtils;

/**
 * �������ڣ�ȡҵ������
 * 
 * @since 6.0
 * @version 2011-4-7 ����08:24:20
 * @author wuxla
 */

public class BusiDateSetter {
  public static final String DBILLDATE = "dbilldate";

  public BusiDateSetter() {
    //
  }

  /**
   * Ϊ���ݱ�������ҵ������
   * 
   * @param bills
   */
  public void setBodyBusiDate(AbstractBill[] bills) {
    this.setBusiDate(bills, null, false);
  }

  /**
   * Ϊ���ݱ�������ҵ������
   * 
   * @param bills
   * @param dateKey
   */
  public void setBodyBusiDate(AbstractBill[] bills, String dateKey) {
    this.setBusiDate(bills, dateKey, false);
  }

  /**
   * Ϊ���ݱ�ͷ���õ�������
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
    // ����
    UFDate busidate = AppContext.getInstance().getBusiDate();
    keyValue.setHeadValue(BusiDateSetter.DBILLDATE, busidate);
  }

  /**
   * Ϊ����ͷ����ҵ������
   * 
   * @param bills
   */
  public void setHeaderBusiDate(AbstractBill[] bills) {
    this.setBusiDate(bills, null, true);
  }

  /**
   * Ϊ����ͷ����ҵ������
   * 
   * @param bills
   * @param dateKey
   */
  public void setHeaderBusiDate(AbstractBill[] bills, String dateKey) {
    this.setBusiDate(bills, dateKey, true);
  }

  /**
   * �����ƶ�����
   * 
   * @param bills
   * @param key
   * @param forHeader �Ƿ����ñ�ͷ��true-��ͷ��false - ����
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
