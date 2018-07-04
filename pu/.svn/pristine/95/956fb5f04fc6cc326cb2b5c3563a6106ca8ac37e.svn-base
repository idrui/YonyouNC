/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 上午09:34:15
 */
package nc.bs.pu.m21.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可到货 订单数量-累计到货
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-11 上午09:34:15
 */
public class CanArriveNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        // 可到货 订单数量-累计到货
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccumarrvnum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcanarrivenum(num);
      }
    }
  }

}
