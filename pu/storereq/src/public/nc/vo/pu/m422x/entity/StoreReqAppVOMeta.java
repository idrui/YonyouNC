/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-18 下午07:49:44
 */
package nc.vo.pu.m422x.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单的元数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-18 下午07:49:44
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
