/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 ����07:49:44
 */
package nc.vo.pu.m422x.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥��Ԫ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 ����07:49:44
 */
public class StoreReqAppVOMeta extends AbstractBillMeta {

  public StoreReqAppVOMeta() {
    this.init();
  }

  public void init() {
    this.setParent(StoreReqAppHeaderVO.class);
    this.addChildren(StoreReqAppItemVO.class);
  }
}
