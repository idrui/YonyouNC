package nc.vo.pu.m24.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单元数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 下午05:11:57
 */
public class PricestlVOMeta extends AbstractBillMeta {

  public PricestlVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(PricestlHeaderVO.class);
    this.addChildren(PricestlItemVO.class);
    this.addChildren(PricestlItemBVO.class);
  }
}
