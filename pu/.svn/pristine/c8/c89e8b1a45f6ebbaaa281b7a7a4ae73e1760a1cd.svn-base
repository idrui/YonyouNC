/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午04:40:42
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对于协同则VO对照中设置的收货存货组织和仓库的值不起作用（先清空）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午04:40:42
 */
public class CoopClearInfo implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        itemVO.setPk_arrvstoorg(null);
        itemVO.setPk_arrvstoorg_v(null);
        itemVO.setPk_recvstordoc(null);
        // 暂时
        itemVO.setPk_reqstoorg(null);
        itemVO.setPk_reqstoorg_v(null);
      }
    }
  }

}
