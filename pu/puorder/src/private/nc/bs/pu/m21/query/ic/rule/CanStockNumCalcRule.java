/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午02:18:23
 */
package nc.bs.pu.m21.query.ic.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算可入库数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-11 下午02:18:23
 */
public class CanStockNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (null == vos) {
      return;
    }
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccumstorenum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcaninnum(num);
      }
    }

  }

}
