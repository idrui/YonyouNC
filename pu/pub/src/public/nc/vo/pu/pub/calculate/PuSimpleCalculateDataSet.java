package nc.vo.pu.pub.calculate;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;

/**
 * 简单联动计算的数据存储类
 * 
 * @since 6.1
 * @version 2012-8-7 上午11:08:19
 * @author tianft
 */
public class PuSimpleCalculateDataSet {

  private IKeyValue bill;

  /**
   * 联动计算字段映射
   */
  private PuSimpleRelationItems items;

  /**
   * 当前行
   */
  private int row = -1;

  public PuSimpleCalculateDataSet(IKeyValue bill, int row,
      PuSimpleRelationItems items) {
    this.bill = bill;
    this.row = row;
    this.items = items;
  }

  public UFDouble getAstNnum() {
    return (UFDouble) this.bill.getBodyValue(this.row,
        this.items.getAstnumKey());
  }

  public String getAstUnitid() {
    return (String) this.bill
        .getBodyValue(this.row, this.items.getAstunitKey());
  }

  public Object getAttribute(String itemKey) {
    return this.bill.getBodyValue(this.row, itemKey);
  }

  public IKeyValue getBill() {
    return this.bill;
  }

  public String getChangeRate() {
    return (String) this.bill.getBodyValue(this.row,
        this.items.getChangeRateKey());
  }

  /**
   * 币种，取表体或表头
   * 
   * @return
   */
  public String getCurrencyid() {
    String value =
        (String) this.bill.getBodyValue(this.row, this.items.getCurrencyKey());
    if (value == null) {
      value = (String) this.bill.getHeadValue(this.items.getCurrencyKey());
    }
    return value;
  }

  public PuSimpleRelationItems getItems() {
    return this.items;
  }

  public UFDouble getMny() {
    return (UFDouble) this.bill.getBodyValue(this.row, this.items.getMnyKey());
  }

  public UFDouble getNnum() {
    return (UFDouble) this.bill.getBodyValue(this.row, this.items.getNumKey());
  }

  public UFDouble getPrice() {
    return (UFDouble) this.bill
        .getBodyValue(this.row, this.items.getPriceKey());
  }

  public String getUnitid() {
    return (String) this.bill.getBodyValue(this.row, this.items.getUnitKey());
  }

  public void setAstNum(UFDouble num) {
    this.bill.setBodyValue(this.row, this.items.getAstnumKey(), num);
  }

  public void setAttribute(String itemKey, Object value) {
    this.bill.setBodyValue(this.row, itemKey, value);
  }

  public void setChangeRate(String value) {
    this.bill.setBodyValue(this.row, this.items.getChangeRateKey(), value);
  }

  public void setMny(UFDouble num) {
    this.bill.setBodyValue(this.row, this.items.getMnyKey(), num);
  }

  public void setNum(UFDouble num) {
    this.bill.setBodyValue(this.row, this.items.getNumKey(), num);
  }

  public void setPrice(UFDouble num) {
    this.bill.setBodyValue(this.row, this.items.getPriceKey(), num);
  }

}
