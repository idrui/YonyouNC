package nc.vo.pu.m21.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * 表体和到货计划Mata
 * 
 * @since 6.0
 * @version 2011-4-26 上午10:38:58
 * @author wuxla
 */

public class AggItemReceivePlanVOMeta extends AbstractBillMeta {

  public AggItemReceivePlanVOMeta() {
    this.setParent(OrderItemVO.class);
    this.addChildren(OrderReceivePlanVO.class);
  }
}
