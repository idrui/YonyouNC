/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 下午01:58:47
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.GoodsEstPriceQueryRule;
import nc.bs.pu.est.rule.pricequery.AbstractEstPriceQueryStrategy;
import nc.bs.pu.est.rule.pricequery.EstPriceQryStrategyFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.pub.enumeration.PriceSource;

/**
 * 
 * @description
 * 消耗汇总货物暂估询价
 * @scene
 * 前台货物(费用同时)暂估查询暂估
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:59:25
 * @author zhangshqb
 */
public class VMIEstPriceQryRule extends GoodsEstPriceQueryRule implements
    IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

  @Override
  protected AbstractEstPriceQueryStrategy getPriceQryStrategy(PriceSource ps) {
    return EstPriceQryStrategyFactory.getVMIStrategy(ps);
  }

}
