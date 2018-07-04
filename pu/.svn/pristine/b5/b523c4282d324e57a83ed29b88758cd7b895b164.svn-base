package nc.vo.pu.m21.entity;

import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaAdapter;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.scmpub.payterm.pay.AbstractAggPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 付款计划聚合VO
 * 
 * @since 6.0
 * @version 2010-12-31 上午11:12:31
 * @author wuxla
 */

public class AggPayPlanVO extends AbstractAggPayPlanVO {

  private static final long serialVersionUID = 6631669298189834237L;

  public static PayPlanViewVO[] getPayPlanViewVO(AggPayPlanVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    return new BillToViewConvertor<AggPayPlanVO, PayPlanViewVO>(
        PayPlanViewVO.class).convert(vos);
  }

  @Override
  public SuperVO[] getChildVOsByParentId(String tableCode, String parentid) {
    if (PayPlanVO.PK_ORDER_PAYPLAN.equals(tableCode)) {
      return (SuperVO[]) super.getChildren(PayPlanVO.class);
    }
    return null;
  }

  @Override
  public String getDefaultTableCode() {
    return PayPlanVO.PK_ORDER_PAYPLAN;
  }

  public OrderHeaderVO getHVO() {
    return (OrderHeaderVO) super.getParent();
  }

  @Override
  public IBillMeta getMetaData() {
    AggPayPlanVOMeta meta = new AggPayPlanVOMeta();

    BillMetaAdapter adapter = new BillMetaAdapter();
    adapter.loadDataFromModel(meta);

    return meta;
  }

  public PayPlanVO[] getPayPlanVO() {
    return (PayPlanVO[]) super.getChildrenVO();
  }

  @Override
  public String[] getTableCodes() {
    String[] tabs =
        ((AbstractBillMeta) this.getMetaData()).getAllSubAttrNames();
    // 默认第一个tabcode是pk_order_payplan，防止vo交换的时候出问题
    for (int i = 0; i < tabs.length; i++) {
      if (PayPlanVO.PK_ORDER_PAYPLAN.equals(tabs[i])) {
        tabs[i] = tabs[0];
        tabs[0] = PayPlanVO.PK_ORDER_PAYPLAN;
      }
    }
    return tabs;

  }

  @Override
  public CircularlyAccessibleValueObject[] getTableVO(String tableCode) {
    if (PayPlanVO.PK_ORDER_PAYPLAN.equals(tableCode)) {
      return super.getChildrenVO();
    }
    return null;
  }

  public void setHVO(OrderHeaderVO voParent) {
    super.setParent(voParent);
  }

  public void setPayPlanVO(PayPlanVO[] payplanVOs) {
    super.setChildrenVO(payplanVOs);
  }

  @Override
  public void setTableVO(String tableCode,
      CircularlyAccessibleValueObject[] values) {
    if (PUEntity.M21_PAYPLAN_TABLE.equals(tableCode)
        || PayPlanVO.PK_ORDER_PAYPLAN.equals(tableCode)) {
      super.setChildrenVO(values);
    }
    else {
      super.setTableVO(tableCode, values);
    }
  }

}
