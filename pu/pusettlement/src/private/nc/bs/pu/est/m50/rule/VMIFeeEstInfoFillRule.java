/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 上午09:15:18
 */
package nc.bs.pu.est.m50.rule;

import nc.bs.pu.est.rule.fee.FeeEstInfoFillRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;

/**
 * 
 * @description
 * 费用暂估信息填充
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:55:35
 * @author zhangshqb
 */
public class VMIFeeEstInfoFillRule extends FeeEstInfoFillRule implements
    IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    super.process(vos);
  }

}
