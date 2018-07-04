/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 上午09:09:18
 */
package nc.bs.pu.est.m45.rule;

import nc.bs.pu.est.rule.SettledFeeChkRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;

/**
 * 
 * @description
 * 检查暂估费用项是否已经做过结算
 * 需要由结算提供服务，查分摊明细，确认是否进行过结算
 * 这里也起到并发控制的作用(费用暂估与费用结算之间的并发)
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:41:41
 * @author zhangshqb
 */
public class PurchsInSettledFeeChkRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    new SettledFeeChkRule().process(vos);
  }

}
