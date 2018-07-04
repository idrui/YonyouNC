/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-25 下午07:57:52
 */
package nc.vo.pu.m4t.entity;

import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单聚合VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-25 下午07:57:52
 */
@nc.vo.annotation.AggVoInfo(parentVO = "nc.vo.pu.m4t.entity.InitialEstHeaderVO")
public class InitialEstVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = 5829122842941708564L;

  public InitialEstHeaderVO getHeader() {
    return (InitialEstHeaderVO) super.getParentVO();
  }

  public InitialEstItemVO[] getItems() {
    return (InitialEstItemVO[]) super.getChildrenVO();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.bill.IBill#getBillMeta()
   */
  @Override
  public IBillMeta getMetaData() {
    return BillMetaFactory.getInstance().getBillMeta(InitialEstVOMeta.class);
  }

}
