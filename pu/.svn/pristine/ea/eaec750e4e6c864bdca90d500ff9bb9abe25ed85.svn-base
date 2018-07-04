/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 上午09:03:51
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.EstimatedFeeChkRule;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;

/**
 * 
 * @description
 * 检查是否存在已经暂估的过费用项
 * 只有纯费用暂估才使用此规则
 * 货物和费用一起暂估的不用加此规则校验
 * 因为：可以货物暂估，说明未结算完毕，也未暂估完毕，在此条件下不可能做过费用暂估
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:54:46
 * @author zhangshqb
 */
public class VMIEstimatedFeeChkRule extends EstimatedFeeChkRule implements
    ICompareRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos, VmiEstVO[] originVOs) {
    super.process(vos, originVOs);
  }
}
