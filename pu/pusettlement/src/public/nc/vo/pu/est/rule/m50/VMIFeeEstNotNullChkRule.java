package nc.vo.pu.est.rule.m50;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;
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
 * @version 2014-10-23 上午9:54:20
 * @author zhangshqb
 */
public class VMIFeeEstNotNullChkRule implements IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    new FeeEstNotNullChkRule().process(vos);
  }

}
