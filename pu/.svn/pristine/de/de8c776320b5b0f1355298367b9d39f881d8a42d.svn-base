package nc.vo.pu.m21.entity;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.CircularlyAccessibleValueObject;
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
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m21.entity.OrderHeaderVO")
public class OrderVO extends AbstractBill {
  private static final long serialVersionUID = -493197123529593215L;

  public OrderVO() {
    super();
  }

  /** 由订单VO得到关闭VO（视图VO） **/
  public static OrderCloseVO[] getCloseVO(OrderVO[] vos) {
    return new BillToViewConvertor<OrderVO, OrderCloseVO>(OrderCloseVO.class)
        .convert(vos);
  }

  public OrderItemVO[] getBVO() {
    return (OrderItemVO[]) this.getChildrenVO();
  }

  public OrderPaymentVO[] getPaymentVO(){
	  return (OrderPaymentVO[]) this.getChildren(OrderPaymentVO.class);
  }
  
  public OrderHeaderVO getHVO() {
    return (OrderHeaderVO) this.getParent();
  }

  @Override
  public IBillMeta getMetaData() {
    IBillMeta billMeta =
        BillMetaFactory.getInstance().getBillMeta(OrderVOMeta.class);
    return billMeta;
  }

  @Override
  public String[] getTableCodes() {
    String[] tabs = super.getTableCodes();
    // 默认第一个tabcode是pk_order_b，防止vo交换的时候出问题
    for (int i = 0; i < tabs.length; i++) {
      if (OrderItemVO.PK_ORDER_B.equals(tabs[i])) {
        tabs[i] = tabs[0];
        tabs[0] = OrderItemVO.PK_ORDER_B;
      }
    }
    return tabs;
  }

  public void setBVO(OrderItemVO[] voaChildren) {
    this.setChildrenVO(voaChildren);
  }

  public void setHVO(OrderHeaderVO voaParent) {
    this.setParent(voaParent);
  }

  @Override
  public void setTableVO(String tableCode,
      CircularlyAccessibleValueObject[] values) {
    if (PUEntity.M21_B_TABLE.equals(tableCode)
        || PUEntity.M21_PAYPLAN_TABLE.equals(tableCode)) {
      super.setChildrenVO(values);
    }
    else {
      super.setTableVO(tableCode, values);
    }
  }

}
