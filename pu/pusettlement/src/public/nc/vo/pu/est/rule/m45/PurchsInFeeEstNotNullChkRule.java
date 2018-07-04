package nc.vo.pu.est.rule.m45;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.rule.FeeEstNotNullChkRule;

/**
 * 
 * @description
 * 费用暂估非空项检查
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:39:17
 * @author zhangshqb
 */
public class PurchsInFeeEstNotNullChkRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    new FeeEstNotNullChkRule().process(vos);
  }

}
