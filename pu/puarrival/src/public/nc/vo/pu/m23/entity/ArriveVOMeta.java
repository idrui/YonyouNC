package nc.vo.pu.m23.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������VO��Ԫ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:49:38
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
