package nc.bs.pu.est.rule;

import nc.bs.pu.m27.rule.SettleTOIAChkRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstVOUtil;

/**
 * 
 * @description
 * 费用暂估时，检查入库单的货物结算是否已经传存货
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:42:22
 * @author zhangshqb
 */
public class FeeEstSettleTOIAChkRule<E extends EstVO> implements IRule<E> {

  @Override
  public void process(E[] vos) {
    GoodsEstVO[] gevos = EstVOUtil.getGoodsEstVos(vos);
    new SettleTOIAChkRule().process(gevos);
  }

}
