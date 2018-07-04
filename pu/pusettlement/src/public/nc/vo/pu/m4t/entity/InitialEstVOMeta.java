/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 下午07:59:47
 */
package nc.vo.pu.m4t.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单的元数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 下午07:59:47
 */
public class InitialEstVOMeta extends AbstractBillMeta {

  public InitialEstVOMeta() {
    this.setParent(InitialEstHeaderVO.class);
    this.addChildren(InitialEstItemVO.class);
  }

}
