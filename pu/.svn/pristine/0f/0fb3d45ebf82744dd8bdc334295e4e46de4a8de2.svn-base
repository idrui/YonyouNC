/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-14 下午12:10:56
 */
package nc.bs.pu.m21.query.pu.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可暂估数量：订单数量>0，暂估数量=订单数量-累计入库数量 2、订单数量<0，暂估数量=订单数量+累计入库数量
 * <li>使用可入库数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-14 下午12:10:56
 */
public class InitialEstNumCalcRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
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
