package nc.vo.pu.m21.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * 订单聚合VO
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-31 下午01:46:12
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m21.entity.OrderOnwayHeaderVO")
public class OrderOnwayVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = -4728789920743103125L;

  public OrderOnwayVO() {
    super();

  }

  public OrderOnwayItemVO[] getBVO() {
    return (OrderOnwayItemVO[]) this.getChildrenVO();
  }

  public OrderOnwayHeaderVO getHVO() {
    return (OrderOnwayHeaderVO) this.getParent();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(OrderOnwayVOMeta.class);
    return billMeta;
  }

  public void setBVO(OrderOnwayItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(OrderOnwayHeaderVO voaParent) {
    this.setParent(voaParent);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("******** PO OrderVO [");
    if (null != this.getParent()) {
      builder.append("1");
    }
    else {
      builder.append("0");
    }
    builder.append("] Header [");
    Object[] items = this.getChildrenVO();
    if (null != items) {
      builder.append(items.length);
    }
    else {
      builder.append("0");
    }
    builder.append("] items ********");
    builder.append(System.getProperty("line.separator"));
    if (null != this.getParent()) {
      builder.append("Header:");
      builder.append(System.getProperty("line.separator"));
    }
    builder.append(this.getParentVO());
    builder.append(System.getProperty("line.separator"));
    if (null == items) {
      builder.append("****************************************************");
      return builder.toString();
    }

    for (int i = 0; i < items.length; i++) {
      builder.append("item").append(i).append(":");
      builder.append(System.getProperty("line.separator"));
      builder.append(items[i]);
      builder.append(System.getProperty("line.separator"));
    }
    builder.append("****************************************************");
    return builder.toString();
  }

}
