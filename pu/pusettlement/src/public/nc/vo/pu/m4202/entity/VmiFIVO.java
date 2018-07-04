/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 上午10:44:05
 */
package nc.vo.pu.m4202.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.BillMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总财务聚合VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 上午10:44:05
 */
public class VmiFIVO extends AbstractBill {

  /**
   * 
   */
  private static final long serialVersionUID = 150425633168813939L;

  @Override
  public VmiFIFeeVO[] getChildrenVO() {
    return (VmiFIFeeVO[]) super.getChildrenVO();
  }

  @Override
  public IBillMeta getMetaData() {
    return BillMetaFactory.getInstance().getBillMeta(PUEntity.VMIFI_H);
  }

  @Override
  public VmiFIHeaderVO getParentVO() {
    return (VmiFIHeaderVO) super.getParentVO();
  }

}
