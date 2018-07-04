/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午02:05:28
 */
package nc.bs.pu.m21.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单计算可开票数量
 * @scene
 *        采购发票转单查询
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午3:41:21
 * @author luojw
 */
public class CanInvoiceNumCalcRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccuminvoicenum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcaninvoicenum(num);
      }
    }
  }

}
