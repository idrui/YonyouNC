/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 上午07:11:32
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 上午07:11:32
 */
public class SendApproveStatusChangeRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    for (StoreReqAppVO vo : vos) {
      vo.getHVO().setFbillstatus((Integer) POEnumBillStatus.APPROVING.value());
      vo.getHVO().setStatus(VOStatus.UPDATED);
    }
  }

}
