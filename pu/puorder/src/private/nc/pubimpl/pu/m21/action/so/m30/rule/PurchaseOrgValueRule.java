/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午04:03:31
 */
package nc.pubimpl.pu.m21.action.so.m30.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PurchaseOrgValue;
import nc.vo.pu.pub.util.BillHelper;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补充组织相关的信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午04:03:31
 */
public class PurchaseOrgValueRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
      new PurchaseOrgValue(helper).setPurchaseOrgValue();
    }
  }

}
