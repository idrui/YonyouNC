package nc.vo.pu.m23.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单VO的元数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午03:49:38
 */
public class ArriveVOMeta extends AbstractBillMeta {
  public ArriveVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(ArriveHeaderVO.class);
    this.addChildren(ArriveItemVO.class);
  }
}
