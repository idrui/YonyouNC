
package nc.bs.pu.m4t.maintain.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;

/**
 * 
 * @description
 * 表头整单合计重算：重新计算表头的整单合计信息
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:10:57
 * @author wuxla
 */

public class InitialEstTotalValueCalcRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      BillHelper<InitialEstVO> bill = new BillHelper<InitialEstVO>(vo);
      NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
      sum.setBlargessField(null);
      sum.sum();
    }
  }

}
