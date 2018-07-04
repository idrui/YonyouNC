/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午01:30:45
 */
package nc.vo.pu.est.rule.m45;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.rule.GoodsEstNotNullChkRule;

/**
 * 
 * @description
 * 货物暂估时检查货物
 * 后台应该放到货物暂估更新的前规则中
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:42:52
 * @author zhangshqb
 */
public class PurchsInEstNotNullChkRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    new GoodsEstNotNullChkRule().process(vos);
  }

}
